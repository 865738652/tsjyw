package edu.iasd.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.NoticeAcceptVO;
import edu.iasd.form.NoticeForm;
import edu.iasd.form.NoticeReceiver;
import edu.iasd.form.ReplyForm;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.ClassMaster;
import edu.iasd.pojo.County;
import edu.iasd.pojo.CountyManager;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.Message;
import edu.iasd.pojo.Notice;
import edu.iasd.pojo.NoticeAccept;
import edu.iasd.pojo.NoticeAcceptType;
import edu.iasd.pojo.Reply;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.Student;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNotice;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.utils.JsonHelper;
import edu.iasd.pojo.SchoolManager;
import edu.iasd.utils.CreditHelper;

@Service
@Transactional
public class SendNoticeServiceImpl implements SendNoticeService{

	@Resource
	private IBaseDao<edu.iasd.pojo.Attachment> attachmentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AttachmentType> attachmentTypeDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.County> countyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.CountyManager> countyManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolManager> schoolManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ClassMaster> classMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Student> studentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Reply> replyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GradeMaster> gradeMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeState> noticeStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Notice> noticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAccept> noticeAcceptDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAcceptType> noticeAcceptTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNoticeAccept> viewNoticeAcceptDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNotice> viewNoticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Message> messageDao;
	
	
	@Override
	public List<NoticeReceiver> getNoticeReceiver(int userId) {
		List<NoticeReceiver> recv = new ArrayList<NoticeReceiver>();		
		User user = userDao.findUniqueBy("userId", userId);
		
		/*
		 * get all schools, grades and classes according role
		 */
		List<School> schools = new ArrayList<School>();
		List<Grade> grades = new ArrayList<Grade>();
		List<SchoolClass> classes = new ArrayList<SchoolClass>();
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SCHOOLMASTER) {
				Teacher t = teacherDao.findUniqueBy("userId", userId);
				schools.add(t.getSchool());
			}
			else if (role.getRoleId() == Role.ROLE_SCHOOLADMIN) {
				SchoolManager m = schoolManagerDao.findUniqueBy("userId", userId);
				schools.add(m.getSchool());
			}
			else if (role.getRoleId() == Role.ROLE_GRADEMASTER) {
				List<GradeMaster> g = (List<GradeMaster>)gradeMasterDao.find("from GradeMaster g where g.teacher.userId=?", userId);
				for (GradeMaster m : g) {
					grades.add(m.getGrade());
				}
			}
			else if (role.getRoleId() == Role.ROLE_CLASSMASTER) {
				List<ClassMaster> g = (List<ClassMaster>)classMasterDao.find("from ClassMaster g where g.teacher.userId=?", userId);
				for (ClassMaster m : g) {
					classes.add(m.getSchoolClass());
				}
			}
		}
		
		/*
		 * create tree, avoiding reduplicating receiver
		 */
		for (School school : schools) {
			NoticeReceiver sr = new NoticeReceiver();
			sr.setId(school.getSchoolId().toString());
			sr.setText(school.getSchoolName());
			sr.setNodes(new ArrayList<NoticeReceiver>());
			for (Object og : school.getGrades()) {
				Grade g = (Grade)og;
				NoticeReceiver gr = new NoticeReceiver();
				gr.setId(school.getSchoolId().toString() + "-" + g.getGradeId().toString());
				gr.setText(g.getGradeName());
				gr.setNodes(new ArrayList<NoticeReceiver>());
				for (Object oc: g.getSchoolClasses()) {
					SchoolClass c = (SchoolClass)oc;
					NoticeReceiver cr = new NoticeReceiver();
					cr.setId(school.getSchoolId().toString() + "-" + g.getGradeId().toString() + "-" + c.getSchoolClassId());
					cr.setText(c.getSchoolClassName());
					gr.getNodes().add(cr);
				}
				sr.getNodes().add(gr);
			}
			
			recv.add(sr);
		}
		
		for (Grade grade: grades) {
			School school = grade.getSchool();
			NoticeReceiver sr = null;
			for (NoticeReceiver s : recv) {
				if (s.getId().equals(school.getSchoolId().toString())) {
					sr = s;
					break;
				}
			}
			
			if (sr == null) {
				sr = new NoticeReceiver();
				sr.setId(school.getSchoolId().toString());
				sr.setText(school.getSchoolName());
				sr.setCheckable(false);
				sr.setNodes(new ArrayList<NoticeReceiver>());
				recv.add(sr);
			}
			
			Boolean found = false;
			for (NoticeReceiver g : sr.getNodes()) {
				if (g.getId().equals(school.getSchoolId() + "-" + grade.getGradeId())) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				NoticeReceiver gr = new NoticeReceiver();
				gr.setId(grade.getSchool().getSchoolId().toString() + "-" + grade.getGradeId().toString());
				gr.setText(grade.getGradeName());
				gr.setNodes(new ArrayList<NoticeReceiver>());
				for (Object oc: grade.getSchoolClasses()) {
					SchoolClass c = (SchoolClass)oc;
					NoticeReceiver cr = new NoticeReceiver();
					cr.setId(school.getSchoolId().toString() + "-" + grade.getGradeId().toString() + "-" + c.getSchoolClassId());
					cr.setText(c.getSchoolClassName());
					gr.getNodes().add(cr);
				}
				sr.getNodes().add(gr);
			}
		}
		
		for (SchoolClass schoolClass : classes) {
			Grade grade = schoolClass.getGrade();
			School school = grade.getSchool();
			
			NoticeReceiver sr = null;
			for (NoticeReceiver s : recv) {
				if (s.getId().equals(school.getSchoolId().toString())) {
					sr = s;
					break;
				}
			}
			
			if (sr == null) {
				sr = new NoticeReceiver();
				sr.setId(school.getSchoolId().toString());
				sr.setText(school.getSchoolName());
				sr.setCheckable(false);
				sr.setNodes(new ArrayList<NoticeReceiver>());
				recv.add(sr);
			}
			
			NoticeReceiver gr = null;
			for (NoticeReceiver g : sr.getNodes()) {
				if (g.getId().equals(school.getSchoolId().toString() + "-" + grade.getGradeId())) {
					gr = g;
					break;
				}
			}
			
			if (gr == null) {
				gr = new NoticeReceiver();
				gr.setId(school.getSchoolId().toString() + "-" + grade.getGradeId());
				gr.setText(grade.getGradeName());
				gr.setCheckable(false);
				gr.setNodes(new ArrayList<NoticeReceiver>());
				sr.getNodes().add(gr);
			}
			
			Boolean found = false;
			for (NoticeReceiver c : gr.getNodes()) {
				if (c.getId().equals(school.getSchoolId() + "-" + grade.getGradeId() + "-" + schoolClass.getSchoolClassId())) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				NoticeReceiver cr = new NoticeReceiver();
				cr.setId(school.getSchoolId().toString() + "-" + grade.getGradeId().toString() + "-" + schoolClass.getSchoolClassId());
				cr.setText(schoolClass.getSchoolClassName());
				cr.setNodes(new ArrayList<NoticeReceiver>());
				gr.getNodes().add(cr);
			}
		}
		return recv;
	}
	
	@Override
	public String getTheSelect2School(Integer userId) {
		// TODO Auto-generated method stub
		System.out.println("SendNoticeServiceImpl getTheSelect2School "+userId);
		User user = userDao.findUniqueBy("userId",userId);
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN)
			{
				List<County> countys = countyDao.getAll();		
				String[] a = new String[]{"countyName","schools","schoolId","schoolName"};
				String[] b = new String[]{"countyName","schools","schoolId","schoolName"};
				return JsonHelper.JsonToSelect2(countys, a, b);
			}
			else if (role.getRoleId() == Role.ROLE_COUNTYADMIN) 
			{
				CountyManager countyManager = countyManagerDao.get(userId);
				List<County> countys = new ArrayList();
				countys.add(countyManager.getCounty());
				String[] a = new String[]{"countyName","schools","schoolId","schoolName"};
				String[] b = new String[]{"countyName","schools","schoolId","schoolName"};
				System.out.println(JsonHelper.JsonToSelect2(countys, a, b));
				return JsonHelper.JsonToSelect2(countys, a, b);
			}
			else if (role.getRoleId() == Role.ROLE_SCHOOLADMIN)
			{
				SchoolManager schoolManager = schoolManagerDao.get(userId);
				School school = schoolManager.getSchool();
				Set<School> schools = new HashSet(0);
				schools.add(school);
				List<County> countys = new ArrayList();
				County county = new County();
				county.setCountyName(school.getCounty().getCountyName());
				county.setSchools(schools);
				countys.add(county);
				String[] a = new String[]{"countyName","schools","schoolId","schoolName"};
				String[] b = new String[]{"countyName","schools","schoolId","schoolName"};
				return JsonHelper.JsonToSelect2(countys, a, b);
			}				
			else if (role.getRoleId() == Role.ROLE_SCHOOLMASTER) {
				return null;
 			}
			else if (role.getRoleId() == Role.ROLE_GRADEMASTER){
				GradeMaster gradeMaster = gradeMasterDao.get(userId);
				Grade grade = gradeMaster.getGrade();
				School school = grade.getSchool();
				Set<School> schools = new HashSet(0);
				schools.add(school);
				List<County> countys = new ArrayList();
				County county = new County();
				county.setCountyName(school.getCounty().getCountyName());
				county.setSchools(schools);
				countys.add(county);
				String[] a = new String[]{"countyName","schools","schoolId","schoolName"};
				String[] b = new String[]{"countyName","schools","schoolId","schoolName"};
				return JsonHelper.JsonToSelect2(countys, a, b);
			}
			else if (role.getRoleId() == Role.ROLE_CLASSMASTER){
				ClassMaster classMaster = classMasterDao.get(userId);
				School school = classMaster.getSchoolClass().getGrade().getSchool();
				Set<School> schools = new HashSet(0);
				schools.add(school);
				List<County> countys = new ArrayList();
				County county = new County();
				county.setCountyName(school.getCounty().getCountyName());
				county.setSchools(schools);
				countys.add(county);
				String[] a = new String[]{"countyName","schools","schoolId","schoolName"};
				String[] b = new String[]{"countyName","schools","schoolId","schoolName"};
				return JsonHelper.JsonToSelect2(countys, a, b);
			}
		}
		return null;
	}


	@Override
	public String getTheSelect2Grade(Integer userId,Integer schoolId) {
		Set<Grade> grades = new HashSet(0);
		User user = userDao.findUniqueBy("userId",userId);
		for(Object o:user.getUserRoles())
		{
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN || role.getRoleId() == Role.ROLE_COUNTYADMIN || role.getRoleId() == Role.ROLE_SCHOOLADMIN || role.getRoleId() == Role.ROLE_SCHOOLMASTER)
			{
				School school = schoolDao.get(schoolId);
				grades = school.getGrades();
			}
			else if(role.getRoleId() == Role.ROLE_GRADEMASTER)
			{
				GradeMaster gradeMaster = gradeMasterDao.get(userId);
				grades = new HashSet(0);
				Grade grade = gradeMaster.getGrade();
				grades.add(grade);
			}
			else if(role.getRoleId() == Role.ROLE_CLASSMASTER)
			{
				ClassMaster classMaster = classMasterDao.get(userId);
				Grade grade = classMaster.getSchoolClass().getGrade();
				grades = new HashSet(0);
				grades.add(grade);
			}
		}
		String[] a = new String[]{"gradeId","gradeName"};
		String returnJsonString = JsonHelper.JsonToSelect2One(grades, a);
		// TODO Auto-generated method stub
		return returnJsonString;
	}

	@Override
	public String getTheSelect2Classes(Integer userId,Object object) {
		System.out.println("service sendNoticeServiceImpl getTheSelect2Classes");
		// TODO Auto-generated method stub
		
		User user = userDao.findUniqueBy("userId",userId);
		Set<Grade> grades = new HashSet(0);
		for(Object o:user.getUserRoles())
		{
			Role role = (Role)o;
			if(role.getRoleId() == Role.ROLE_CLASSMASTER)
			{
				ClassMaster classMaster = classMasterDao.get(userId);
				Set<SchoolClass> schoolClasses = new HashSet(0);
				SchoolClass schoolClass = classMaster.getSchoolClass();
				schoolClasses.add(schoolClass);
				Grade grade = new Grade();
				grade.setGradeName(schoolClass.getGrade().getGradeName());
				grade.setSchoolClasses(schoolClasses);
				grades.add(grade);
			}
			else
			{
				List<Integer> gradeIds = (List<Integer>) object;
				for(int gradeId:gradeIds)
				{
					grades.add(gradeDao.get(gradeId));
				}
			}
		}
		String[] a = new String[]{"gradeName","schoolClasses","schoolClassId","schoolClassName"};
		System.out.println(JsonHelper.JsonToSelect2(grades, a, a));
		return JsonHelper.JsonToSelect2(grades, a, a);
	}


	@Override
	public String sendNotice(Integer userId,NoticeForm form) throws Exception{
		/*
		 * base info of notice
		 */
		Notice notice = new Notice();
		notice.setUser(userDao.get(userId));
		notice.setNoticeTime(new Timestamp(System.currentTimeMillis()));
		
		if(form.getNoticeOverTime() != null && !form.getNoticeOverTime().equals(""))
		{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			String str_test = form.getNoticeOverTime();
			if(format.parse(str_test).getTime() < new Date().getTime())
				throw new Exception("过期时间选择有误！");
			notice.setNoticeOverTime(new Timestamp(format.parse(str_test).getTime()));
		}
		else	
			notice.setNoticeOverTime(new Timestamp(System.currentTimeMillis()+7*Notice.DAY_TIMESTAMP));		
		notice.setNoticeTitle(form.getNoticeTitle());
		notice.setNoticeContent(form.getNoticeContent());
		notice.setNoticeIsOrNotReply(0);
		notice.setNoticeState(noticeStateDao.get(1));
		/*
		 * notice type
		 */
		if (form.getAccepts() == null)
			throw new Exception("未选择收件人");
		int noticeAcceptTypeId = NoticeAcceptType.NOTICEACCEPTTYPE_PERSON;
		
		//通过标志位判断,判断是否有班级 反馈表
		int noticeTypeFlag = NoticeAcceptType.NOTICEACCEPTTYPE_PERSON;
		
		for (NoticeAcceptVO a : form.getAccepts()) {
			if (a.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL) {
				noticeAcceptTypeId = NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL;
				noticeTypeFlag = NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL;
				break;
			}
			else if(a.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_GRADE) {
				noticeAcceptTypeId = NoticeAcceptType.NOTICEACCEPTTYPE_GRADE;
				noticeTypeFlag = NoticeAcceptType.NOTICEACCEPTTYPE_GRADE;
				break;
			}
			else if(a.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS) {
				noticeAcceptTypeId = NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS;
				noticeTypeFlag = NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS;
				break;
			}
		}		
		notice.setNoticeAcceptType(noticeAcceptTypeDao.get(noticeAcceptTypeId));
		
		/*
		 * attachments
		 */		
		if (form.getAttachments() != null) {
			Set<Attachment> attachments = new HashSet<Attachment>(0);
			for (AttachVO v : form.getAttachments()) {
				Attachment m = new Attachment();
				m.setAttachmentName(v.getAttachName());
				m.setAttachmentSize(v.getAttachSize());
				m.setAttachmentUrl(v.getAttachUrl());
				m.setAttachmentType(attachmentTypeDao.findUniqueBy("attachmentTypeId", 1));
				attachmentDao.save(m);
				attachments.add(m);
			}
			notice.setNoticeAttachments(attachments);
		}
		
		noticeDao.save(notice);
		
		/*
		 * receivers of notice
		 */
		List<Integer> schoolClassIds = new ArrayList<Integer>();
		List<Integer> personIds = new ArrayList<Integer>();
		
		for (NoticeAcceptVO a : form.getAccepts()) {
			NoticeAccept noticeAccept = new NoticeAccept();
			noticeAccept.setNotice(notice);
			noticeAccept.setNoticeAcceptType(noticeAcceptTypeDao.get(a.getNoticeAcceptTypeId()));
			noticeAccept.setNoticeAcceptGroupId(a.getNoticeAcceptGroupId());
			if(a.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
				schoolClassIds.add(a.getNoticeAcceptGroupId());
			if(a.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_PERSON)
				personIds.add(a.getNoticeAcceptGroupId());
			noticeAcceptDao.save(noticeAccept);
		}		
		
		/*
		 * reply
		 */
		if(noticeTypeFlag == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
		{
			for(Integer schoolClassId:schoolClassIds)
			{
				SchoolClass schoolClass = schoolClassDao.get(schoolClassId);
				Set<Student> students = schoolClass.getStudents();
				for(Student a:students)
				{
					Reply reply = new Reply();
					reply.setNotice(notice);
					reply.setUser(a.getUser());
					reply.setIsOrNotLook(Reply.LOOK_NOT);
					replyDao.save(reply);
				}
			}
		}
		if(noticeTypeFlag == NoticeAcceptType.NOTICEACCEPTTYPE_PERSON)
		{
			for(Integer personId:personIds)
			{
				User user = userDao.get(personId);
				Reply reply = new Reply();
				reply.setNotice(notice);
				reply.setUser(user);
				reply.setIsOrNotLook(Reply.LOOK_NOT);
				replyDao.save(reply);
			}
		}		
		
		/*
		 * WeChat message 
		 */
		Message message = new Message();
		message.setWechatMessageId(notice.getNoticeId());
		message.setMessageTypeId(Message.MESSAGE_NOTICE);
		message.setMessageStateId(Message.MESSAGE_STATE_WAIT);
		messageDao.save(message);
		
		Integer creditType = -1;
		if (notice.getNoticeAcceptType().getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL)
			creditType = CreditHelper.CREDIT_TYPE_NOTICE_SCHOOL;
		else if (notice.getNoticeAcceptType().getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_GRADE)
			creditType = CreditHelper.CREDIT_TYPE_NOTICE_GRADE;
		else if (notice.getNoticeAcceptType().getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
			creditType = CreditHelper.CREDIT_TYPE_NOTICE_CLASS;
		if (creditType >= 0)
			CreditHelper.trigger(userDao, userId, creditType);
		return null;
	}


	@Override
	public Page getAllMyRecvNotice(Integer userId, int pageNow, int pageSize) {
		User user = userDao.get(userId);
		
		List<ViewNoticeAccept> result = new ArrayList<ViewNoticeAccept>();
		for (Object o : user.getUserRoles()) {
			Role r = (Role)o;
			if(r.getRoleId() == Role.ROLE_STUDENT)
			{
				List<ViewNoticeAccept> l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS,studentDao.get(userId).getSchoolClass().getSchoolClassId(),new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
				l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOTICEACCEPTTYPE_GRADE,studentDao.get(userId).getSchoolClass().getGrade().getGradeId(),new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
				l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL,studentDao.get(userId).getSchoolClass().getGrade().getSchool().getSchoolId(),new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
			}
			else if(r.getRoleId() == Role.ROLE_SCHOOLTEACHER)
			{
				List<ViewNoticeAccept> l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL,teacherDao.get(userId).getSchool().getSchoolId(),new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
			}
			else if(r.getRoleId() == Role.ROLE_USER)
			{
				List<ViewNoticeAccept> l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId=? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOTICEACCEPTTYPE_PERSON,userId,new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
			}
		}
		ViewNoticeAccept [] array = new ViewNoticeAccept[result.size()];
		result.toArray(array);
		Arrays.sort(array);
		
		result.clear();
		int start = (pageNow - 1) * pageSize;
		for (int i = 0; i < pageSize && start + i < array.length; i++) {
			result.add(array[start + i]);
		}
		
		Page page = new Page(start, array.length, pageSize, result);
		return page;
	}


	@Override
	public ViewNotice getNotice(Integer noticeId) {
		return viewNoticeDao.get(noticeId);
	}


	@Override
	public List<ViewNoticeAccept> getRecvWeChatNotice(String userOpendId)
	{
		// TODO Auto-generated method stub
		List<User> users = userDao.findBy("userOpenId", userOpendId);
		if(users.size() != 0)
		{	
			List<ViewNoticeAccept> viewNoticeAccepts = new ArrayList<ViewNoticeAccept>();
			for(User user:users)
			{
				Integer userId = user.getUserId();
				for(Object o:user.getUserRoles())
				{
					Role role = (Role)o;
					if(role.getRoleId() == Role.ROLE_STUDENT)
					{
						List<ViewNoticeAccept> s = (List<ViewNoticeAccept>)viewNoticeAcceptDao
								.find("from ViewNoticeAccept v where (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) and v.noticeOverTime>?",
										NoticeAcceptType.NOTICEACCEPTTYPE_PERSON,userId,
										NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS,studentDao.get(userId).getSchoolClass().getSchoolClassId(),
										NoticeAcceptType.NOTICEACCEPTTYPE_GRADE,studentDao.get(userId).getSchoolClass().getGrade().getGradeId(),
										NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL,studentDao.get(userId).getSchoolClass().getGrade().getSchool().getSchoolId(),new Timestamp(System.currentTimeMillis()));
						viewNoticeAccepts.addAll(s);
					}
				}
			}
			//viewNoticeAccepts.size()--chinese--yaoyao
			System.out.println("viewNoticeAccepts.size()");
			return viewNoticeAccepts;
		}
		else
		{
			return null;
		}
	}


	@Override
	public List<ViewNoticeAccept> getRecvWeChatNoticeByUserId(Integer userId,Integer roleId) {
		// TODO Auto-generated method stub		
		User user = userDao.get(userId);
		if(roleId == Role.ROLE_STUDENT)
		{
			Page page = viewNoticeAcceptDao.pagedQuery("from ViewNoticeAccept v where (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) and v.noticeOverTime>? order by v.noticeTime desc", 1, 100,NoticeAcceptType.NOTICEACCEPTTYPE_PERSON,userId,NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS,studentDao.get(userId).getSchoolClass().getSchoolClassId(),NoticeAcceptType.NOTICEACCEPTTYPE_GRADE,studentDao.get(userId).getSchoolClass().getGrade().getGradeId(),NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL,studentDao.get(userId).getSchoolClass().getGrade().getSchool().getSchoolId(),new Timestamp(System.currentTimeMillis()));
			return (List<ViewNoticeAccept>)page.getResult();
			
		}
		if(roleId == Role.ROLE_SCHOOLTEACHER || roleId == Role.ROLE_CLASSMASTER || roleId == Role.ROLE_GRADEMASTER)
		{
			
			List<ViewNoticeAccept> viewNoticeAccepts= viewNoticeAcceptDao.find("from ViewNoticeAccept v where (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?)"
					+ " or (v.noticeAcceptTypeId = ? and v.noticeAcceptGroupId=?) and v.noticeOverTime>? order by v.noticeTime desc",
					NoticeAcceptType.NOTICEACCEPTTYPE_PERSON,userId,
					NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL,teacherDao.get(userId).getSchool().getSchoolId(),new Timestamp(System.currentTimeMillis()));
			return viewNoticeAccepts;
		}
		if(roleId == Role.ROLE_USER)
		{
			Page page = viewNoticeAcceptDao.pagedQuery("from ViewNoticeAccept v where v.noticeAcceptTypeId=? and v.noticeAcceptGroupId=? and v.noticeOverTime>? order by v.noticeTime desc", 1, 100,NoticeAcceptType.NOTICEACCEPTTYPE_PERSON,userId,new Timestamp(System.currentTimeMillis()));
			return (List<ViewNoticeAccept>)page.getResult();
		}
		return null;
	}


	@Override
	public Set<Attachment> getAttachemnts(Integer noticeId) {
		// TODO Auto-generated method stub
		Notice notice = noticeDao.get(noticeId);
		return notice.getNoticeAttachments();
	}


	@Override
	public Attachment getAttachment(Integer attachmentId) {
		
		return attachmentDao.get(attachmentId);
	}


	@Override
	public boolean weChatSendNotice(Integer userId,JSONObject jsonObject) {
		// TODO Auto-generated method stub
		User user = userDao.get(userId);
		Notice notice = new Notice();
		System.out.println(jsonObject.toString());
		notice.setUser(user);
		notice.setNoticeIsOrNotReply(jsonObject.getInt("noticeRequireCheck"));
		notice.setNoticeState(noticeStateDao.get(1));
		notice.setNoticeTime(new Timestamp(System.currentTimeMillis()));
		notice.setNoticeOverTime(new Timestamp(System.currentTimeMillis()+7*Notice.DAY_TIMESTAMP));
		System.out.println("title:"+jsonObject.getString("noticeTitle"));
		notice.setNoticeTitle(jsonObject.getString("noticeTitle"));
		notice.setNoticeContent(jsonObject.getString("noticeContent"));
		notice.setNoticeAcceptType(noticeAcceptTypeDao.get(jsonObject.getInt("noticeAcceptTypeId")));
		
		
		
		Set<Attachment> attachments = new HashSet<Attachment>();
		JSONArray attachJsonArray = jsonObject.getJSONArray("noticeAttachs");
		
		if(attachJsonArray != null && attachJsonArray.size()>0){
			for(int i=0;i<attachJsonArray.size();i++)
			{
				Attachment a = new Attachment();
				JSONObject attachJsonObject = attachJsonArray.getJSONObject(i);
				a.setAttachmentName(attachJsonObject.getString("attachName"));
				a.setAttachmentSize((float) attachJsonObject.getInt("attachSize"));
				a.setAttachmentUrl(attachJsonObject.getString("attachUrl"));
				a.setAttachmentType(attachmentTypeDao.get(1));
				attachmentDao.save(a);
				attachments.add(a);
			}
		}
		if(attachments.size() == 0 || attachments == null)
			notice.setNoticeAttachments(null);
		else
			notice.setNoticeAttachments(attachments);

		noticeDao.save(notice);
		Message message = new Message();
		message.setWechatMessageId(notice.getNoticeId());
		message.setMessageTypeId(Message.MESSAGE_NOTICE);
		message.setMessageStateId(Message.MESSAGE_STATE_WAIT);
		messageDao.save(message);
		
		/**
		 * 反馈
		 */
		if(jsonObject.getInt("noticeAcceptTypeId") == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
		{
			Integer schoolClassId = jsonObject.getInt("noticeAcceptId");
			SchoolClass schoolClass = schoolClassDao.get(schoolClassId);
			Set<Student> students = schoolClass.getStudents();
			for(Student student:students)
			{
				Reply reply = new Reply();
				reply.setNotice(notice);
				reply.setUser(student.getUser());
				reply.setIsOrNotLook(Reply.LOOK_NOT);
				replyDao.save(reply);
				
			}
		}		
		
		NoticeAccept noticeAccept = new NoticeAccept();
		noticeAccept.setNotice(notice);
		noticeAccept.setNoticeAcceptType(noticeAcceptTypeDao.get(jsonObject.getInt("noticeAcceptTypeId")));
		noticeAccept.setNoticeAcceptGroupId(jsonObject.getInt("noticeAcceptId"));
		noticeAcceptDao.save(noticeAccept);
		return true;
	}

	@Override
	public Page getAllSendedNotice(Integer userId, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		Page page = viewNoticeDao.pagedQuery("from ViewNotice v where v.userId = ? order by v.noticeTime desc", pageNow, pageSize, userId);
		return page;
	}

	@Override
	public boolean deleteMyNotice(Integer noticeId, Integer userId)
			throws Exception {
		Notice notice = noticeDao.get(noticeId);
		if(notice.getUser().getUserId() != userId)
			throw new Exception("你没有发过此通知");
		List<NoticeAccept> noticeAccepts = noticeAcceptDao.findBy("notice",notice);
		Set<Attachment> attachments = notice.getNoticeAttachments();
		if(noticeAccepts != null || noticeAccepts.size()>0)
		{
			for(NoticeAccept noticeAccept:noticeAccepts)
				noticeAcceptDao.remove(noticeAccept);
		}
		if(attachments != null || attachments.size()>0)
		{
			for(Attachment attachment:attachments)
				attachmentDao.remove(attachment);
		}
		noticeDao.remove(notice);
		return true;
	}

	@Override
	public List<AttachVO> getNoticeAttachment(Integer noticeId, int pageSize,
			int pageNumber) {
		// TODO Auto-generated method stub
		
		Notice notice = noticeDao.get(noticeId);
		
		System.out.println(notice.getNoticeContent());
		Set<Attachment> as = notice.getNoticeAttachments();
		
		List<AttachVO> attachVOs = new ArrayList<AttachVO>();
		for(Attachment attachment:as)
		{
			AttachVO attachVO = new AttachVO();
			attachVO.setAttachId(attachment.getAttachmentId());
			attachVO.setAttachName(attachment.getAttachmentName());
			attachVO.setAttachSize(attachment.getAttachmentSize());
			attachVO.setAttachUrl(attachment.getAttachmentUrl());
			attachVOs.add(attachVO);
		}
		
		return attachVOs;
	}

	@Override
	public Reply checkNoticeReply(Integer userId, Integer noticeId) {
		// TODO Auto-generated method stub
		Notice notice = noticeDao.get(noticeId);
		
		if(notice.getUser().getUserId() == userId)
			return null;
		List<Reply> replys = replyDao.find("from Reply v where v.notice=? and v.user=?", notice,userDao.get(userId));
		if(replys == null || replys.size() == 0)
			return null;
		else if(replys.size() == 1)
		{
			Reply reply = replys.get(0);
			if(reply.getIsOrNotLook().equals(Reply.LOOK_IS))
				return reply;
			reply.setLookTime(new Timestamp(System.currentTimeMillis()));
			reply.setIsOrNotLook(Reply.LOOK_IS);
			replyDao.save(reply);
			return reply;
		}
		else
			return null;
	}

	@Override
	public boolean checkNoticeIsMine(Integer noticeId, Integer userId) {
		// TODO Auto-generated method stub
		
		if(noticeDao.get(noticeId).getUser().getUserId().intValue() == userId.intValue())
			return true;
		else
			return false;
	}

	@Override
	public List<ReplyForm> getReplyforNotice(Integer noticeId) {
		// TODO Auto-generated method stub
		Notice notice = noticeDao.get(noticeId);
		Set<Reply> replys = notice.getReplies();
		System.out.println(replys.size());
		
		List<ReplyForm> islook = new ArrayList<ReplyForm>();
		List<ReplyForm> notlook = new ArrayList<ReplyForm>();
		
		if(replys == null || replys.size() == 0)
			return new ArrayList<ReplyForm>();
		for(Reply reply:replys)
		{
			ReplyForm a = new ReplyForm();
			a.setUserName(reply.getUser().getUserName());
			a.setCheckTime(reply.getLookTime());
			a.setCheckState(reply.getIsOrNotLook());
			a.setReplyId(reply.getReplyId());
			a.setReplyContent(reply.getReplyContent());
			System.out.println(reply.getIsOrNotLook());
			
			if(reply.getIsOrNotLook().intValue() == 0)
			{
				islook.add(a);
			}	
			else if(reply.getIsOrNotLook().intValue() == 1)
			{
				notlook.add(a);
			}
		}
		List<ReplyForm> s = new ArrayList<ReplyForm>();
		s.addAll(islook);
		s.addAll(notlook);
		
		return s;
	}

	@Override
	public boolean addNoticeReplyContent(Integer replyId,
			String replyContent) {
		// TODO Auto-generated method stub
		Reply reply = replyDao.get(replyId);
		reply.setReplyTime(new Timestamp(System.currentTimeMillis()));
		reply.setReplyContent(replyContent);
		replyDao.save(reply);
		return true;
	}

	@Override
	public Reply getReplyById(Integer replyId) {
		// TODO Auto-generated method stub
		return replyDao.get(replyId);
	}
}
