package edu.iasd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.Article;
import edu.iasd.pojo.ClassMaster;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.Message;
import edu.iasd.pojo.MyFans;
import edu.iasd.pojo.Notice;
import edu.iasd.pojo.NoticeAccept;
import edu.iasd.pojo.NoticeAcceptType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.SchoolManager;
import edu.iasd.pojo.Student;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.pojo.WechatNoticeUser;
import edu.iasd.utils.RecvNoticeObject;
import edu.iasd.wechat.message.News;



@Service
@Transactional
public class WechatServiceImpl implements WechatService{

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Message> messageDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Article> articleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Notice> noticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNoticeAccept> viewNoticeAcceptDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAccept> noticeAcceptDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewArticle> viewArticleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Student> studentDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ClassMaster> classMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GradeMaster> gradeMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolManager> schoolManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.MyFans> myfansDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Override
	public boolean BindUser(String userAccount, String userCode,String userOpenId) {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userAccount",userAccount);
		System.out.println(user.getUserName());
		if(user != null){
			System.out.println("you code :"+user.getUserCode()+" mycode:"+userCode);
			if(user.getUserCode().equals(userCode)){
				user.setUserOpenId(userOpenId);
				userDao.save(user);
				return true;
			}
			return false;
		}
		return false;
	}




	@Override
	public Integer sendArticleToMessage(int articleId) {
		// TODO Auto-generated method stub
		
		List<Message> messages = messageDao.findBy("messageTypeId", Message.MESSAGE_ARTICLE);
		int num = messages.size();
		if(num >= Message.MESSAGE_SENDNUM)
			return 5;
		Message message = new Message();
		message.setWechatMessageId(articleId);
		message.setMessageTypeId(Message.MESSAGE_ARTICLE);
		message.setMessageStateId(Message.MESSAGE_STATE_WAIT);
		messageDao.save(message);
		return Message.MESSAGE_SENDNUM-num-1;
	}




	public boolean startSendToWechat() {
		// TODO Auto-generated method stub
		List<Message> messages = messageDao.findBy("messageTypeId", Message.MESSAGE_ARTICLE);
		
		for(Message e:messages)
		{
			e.setMessageStateId(Message.MESSAGE_STATE_START);
			messageDao.save(e);
		}
		
		return true;
	}




	@Override
	public List<News> timingCheck() {
		// TODO Auto-generated method stub
		
		List<Message> messages = messageDao.findBy("messageTypeId",Message.MESSAGE_ARTICLE);
		List<News> news = new ArrayList<News>();
		for(Message e:messages)
		{
			if(e.getMessageStateId() == Message.MESSAGE_STATE_WAIT)
			{
				return news;
			}
				
		}
		for(Message e:messages)
		{
			News a = new News();
			int articleid = e.getWechatMessageId();
			Article b = articleDao.get(articleid);
			a.setTitle(b.getArticleTitle());
			a.setDescription(b.getArticleContent());
			a.setPicUrl("http://myapp.tunnel.qydev.com/tsjyw/images/1.jpg");
			a.setUrl("www.baidu.com");
			news.add(a);
		}
		return news;
	}




	@Override
	public List<WechatNoticeUser> getSendUserList() {
		// TODO Auto-generated method stub
		
		List<WechatNoticeUser> wechatNoticeUsers = new ArrayList<WechatNoticeUser>();
		List<Message> messages = messageDao.findBy("messageTypeId", Message.MESSAGE_NOTICE);
		if(messages.size() > 0)
		{
			Message message = messages.get(0);
			List<ViewNoticeAccept> noticeAccepts = viewNoticeAcceptDao.findBy("noticeId",message.getWechatMessageId());
			for(ViewNoticeAccept noticeAccept:noticeAccepts)
			{
				if(noticeAccept.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_PERSON)
				{
					User user = userDao.get(noticeAccept.getNoticeAcceptGroupId());
					if(user.getUserOpenId() != null)
					{
						WechatNoticeUser a = new WechatNoticeUser();
						a.setNoticeId(noticeAccept.getNoticeId());
						a.setNoticeTitle(noticeAccept.getNoticeTitle());
						a.setNoticeContent(noticeAccept.getNoticeContent());
						a.setUserOpenId(user.getUserOpenId());
						a.setNoticeTime(noticeAccept.getNoticeTime().toString());
						a.setSchoolClassName("");
						a.setSendUserName(user.getUserName());
						a.setRecvUserName(userDao.get(noticeAccept.getUserId()).getUserName());
						wechatNoticeUsers.add(a);
					}
				}
				else if(noticeAccept.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
				{
					SchoolClass schoolClass = schoolClassDao.get(noticeAccept.getNoticeAcceptGroupId());
					Set<Student> students =  schoolClass.getStudents();
					for(Student s:students){
						User user = s.getUser();
						if(user.getUserOpenId() != null)
						{
							WechatNoticeUser a = new WechatNoticeUser();
							a.setNoticeId(noticeAccept.getNoticeId());
							a.setNoticeTitle(noticeAccept.getNoticeTitle());
							a.setNoticeContent(noticeAccept.getNoticeContent());
							a.setUserOpenId(user.getUserOpenId());
							a.setNoticeTime(noticeAccept.getNoticeTime().toString());
							a.setSchoolClassName(s.getSchoolClass().getSchoolClassName());
							a.setSendUserName(user.getUserName());
							a.setRecvUserName(userDao.get(noticeAccept.getUserId()).getUserName());
							wechatNoticeUsers.add(a);
						}
					}
					
				}
				else if(noticeAccept.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_GRADE)
				{
					Grade grade = gradeDao.get(noticeAccept.getNoticeAcceptGroupId());
					Set<SchoolClass> schoolClasses = grade.getSchoolClasses();
					Set<User> users = new HashSet<User>();
					for(SchoolClass s:schoolClasses)
					{
						Set<Student> students = s.getStudents();
						for(Student ss:students)
						{
							User user = ss.getUser();
							users.add(user);
						}	
						Set<Teacher> teachers = s.getCourseTeachers();
						for(Teacher teacher:teachers)
						{
							users.add(teacher.getUser());
						}
					}
					for(User user:users)
					{
						if(user.getUserOpenId() != null)
						{
							WechatNoticeUser a = new WechatNoticeUser();
							a.setNoticeId(noticeAccept.getNoticeId());
							a.setNoticeTitle(noticeAccept.getNoticeTitle());
							a.setNoticeContent(noticeAccept.getNoticeContent());
							a.setUserOpenId(user.getUserOpenId());
							a.setNoticeTime(noticeAccept.getNoticeTime().toString());
							a.setSchoolClassName(grade.getGradeName());
							a.setSendUserName(user.getUserName());
							a.setRecvUserName(userDao.get(noticeAccept.getUserId()).getUserName());
							wechatNoticeUsers.add(a);
						}
					}
				}
				else if(noticeAccept.getNoticeAcceptTypeId() == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL)
				{
					School school = schoolDao.get(noticeAccept.getNoticeAcceptGroupId());
					Set<User> users = new HashSet<User>();
					Set<Grade> grades = school.getGrades();
					for(Grade s:grades)
					{
						Set<GradeMaster> gradeMasters = s.getGradeMasters();
						for(GradeMaster a:gradeMasters)
						{
							users.add(a.getTeacher().getUser());
						}
						Set<SchoolClass> schoolClasses = s.getSchoolClasses();
						for(SchoolClass ss:schoolClasses)
						{
							Set<Student> students = ss.getStudents();
							for(Student sss:students)
							{
								User user = sss.getUser();
								users.add(user);
							}	
							Set<Teacher> teachers = ss.getCourseTeachers();
							for(Teacher teacher:teachers)
							{
								users.add(teacher.getUser());
							}
							List<ClassMaster> classMasters = classMasterDao.findBy("schoolClass", ss);
							for(ClassMaster c:classMasters)
							{
								users.add(c.getTeacher().getUser());
							}
						}
					}
					for(User user:users)
					{
						if(user.getUserOpenId() != null)
						{
							WechatNoticeUser a = new WechatNoticeUser();
							a.setNoticeId(noticeAccept.getNoticeId());
							a.setNoticeTitle(noticeAccept.getNoticeTitle());
							a.setNoticeContent(noticeAccept.getNoticeContent());
							a.setUserOpenId(user.getUserOpenId());
							a.setNoticeTime(noticeAccept.getNoticeTime().toString());
							a.setSchoolClassName(school.getSchoolName());
							a.setSendUserName(user.getUserName());
							a.setRecvUserName(userDao.get(noticeAccept.getUserId()).getUserName());
							wechatNoticeUsers.add(a);
						}
					}
				}
			}
			messageDao.removeById(message.getMessageId());
			return wechatNoticeUsers;
		}
		else{
			return wechatNoticeUsers;
		}
	}

	@Override
	public boolean saveMyfans(MyFans myfans) {
		// TODO Auto-generated method stub
		myfansDao.save(myfans);
		
		return true;
	}

	@Override
	public boolean removeMyfans(MyFans myfans) {
		// TODO Auto-generated method stub
		myfansDao.removeById(myfansDao.findBy("myfansOpenId",myfans.getMyfansOpenId()).get(0).getMyfansId());
		return true;
	}

	@Override
	public List<ViewArticle> getAllArticle() {
		List<ViewArticle> va=viewArticleDao.find("from ViewArticle v");
		List<ViewArticle> lv=new ArrayList<ViewArticle>();
		for(int i=va.size()-1;i>=0;i--)
		{
			lv.add(va.get(i));
		}
		return lv;
	}




	@Override
	public ViewArticle getArticle(Integer articleId) {
		// TODO Auto-generated method stub
		return viewArticleDao.get(articleId);
	}

	
	
	
	@Override
	public User getUserDetail(String userOpenId) {
		User user = userDao.findUniqueBy("userOpenId",userOpenId);
		return user;
	}




	@Override
	public User getUserDetailByuserId(int userId) {
		User user = userDao.findUniqueBy("userId",userId);
		return user;
	}


	@Override
	public User loginCheck(String userAccount, String passWord) {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userAccount",userAccount);
		if(user == null)
		{
			user = userDao.findUniqueBy("userCode", userAccount);
			if (user != null && user.getUserAccount() != null){
				user = null;
			}	
		}
		else if(!user.getUserPassword().equals(passWord))
		{
			user = null;
		}
		
		return user;
	}


	@Override
	public boolean bind(Integer userId, String myOpenId) {
		// TODO Auto-generated method stub
		User user = userDao.get(userId);
		if(user == null)
		{
			return false;
		}
		user.setUserOpenId(myOpenId);
		userDao.save(user);
		return true;
	}




	@Override
	public boolean registerCheck(User user) {
		// TODO Auto-generated method stub
		
		String account = user.getUserAccount();
		
		List<User> users = userDao.findBy("userAccount",account);
		if(users == null)
		{
			Set<Role> userRoles = new HashSet<Role>();
			Role role = roleDao.get(Role.ROLE_USER);
			userRoles.add(role);
			user.setUserRoles(userRoles);
			userDao.save(user);
			return true;
		}
		if(users.size() == 0)
		{
			Set<Role> userRoles = new HashSet<Role>();
			Role role = roleDao.get(Role.ROLE_USER);
			userRoles.add(role);
			user.setUserRoles(userRoles);
			userDao.save(user);
			return true;
		}
		else
		{
			return false;
		}
	}




	@Override
	public boolean IsOrNotHaveYouOpenId(String openid) {
		System.out.println(openid);
		List<User> users = userDao.findBy("userOpenId", openid);
		if(users == null){
			return false;
		}
		if(users.size() == 0){
			return false;
		}
		return true;
	}




	@Override
	public RecvNoticeObject getMySendNoticeObject(Integer userId) {
		// TODO Auto-generated method stub
		
		List<JSONObject> jsonse = new ArrayList<JSONObject>();
		User user = userDao.get(userId);
		Set<Role> userRoles = user.getUserRoles();
		RecvNoticeObject recvNoticeObject = new RecvNoticeObject();
		for(Role role:userRoles){
			if(role.getRoleId() == Role.ROLE_CLASSMASTER){
				List<ClassMaster> classMasters = classMasterDao.findBy("teacher", teacherDao.get(userId));
				if(classMasters == null || classMasters.size() == 0)
					continue;
				List<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
				for(ClassMaster a:classMasters){
					if(a.getSchoolClass() == null)
						continue;
					schoolClasses.add(a.getSchoolClass());
				}
				recvNoticeObject.setSchoolClass(schoolClasses);
			}
			else if(role.getRoleId() == Role.ROLE_GRADEMASTER){
				List<GradeMaster> gradeMasters = gradeMasterDao.findBy("teacher", teacherDao.get(userId));
				if(gradeMasters == null || gradeMasters.size() == 0)
					continue;
				List<Grade> grades = new ArrayList<Grade>();
				for(GradeMaster a:gradeMasters){
					if(a.getGrade() == null)
						continue;
					grades.add(a.getGrade());
				}
				recvNoticeObject.setGrade(grades);
			}
			if(role.getRoleId() == Role.ROLE_SCHOOLMASTER)
			{
				Teacher teacher = teacherDao.get(userId);
				School school = teacher.getSchool();
				if(school == null)
					continue;
				List<School> schools = new ArrayList<School>();
				schools.add(school);
				if(recvNoticeObject.getSchool() == null || recvNoticeObject.getSchool().size() == 0){
					recvNoticeObject.setSchool(schools);
				}
				else{
					recvNoticeObject.getSchool().addAll(schools);
				}
			}
			if(role.getRoleId() == Role.ROLE_SCHOOLADMIN)
			{
				SchoolManager schoolManager = schoolManagerDao.get(userId);
				if(schoolManager == null)
					continue;
				List<School> schools = new ArrayList<School>();
				schools.add(schoolManager.getSchool());
				if(recvNoticeObject.getSchool() == null || recvNoticeObject.getSchool().size() == 0){
					recvNoticeObject.setSchool(schools);
				}
				else{
					recvNoticeObject.getSchool().addAll(schools);
				}
			}
		}
		return recvNoticeObject;
	}


}
