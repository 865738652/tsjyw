package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GradeForm;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.ViewGrade;

@Service
@Transactional
public interface GradeService {

	public Page getAllGrade(int pageNow, int pageSize);

	public Page getGradeBySchool(int userId, int pageNumber, int pageSize, int schoolId);

	public ViewGrade getGrade(int gradeId);

	public String createGrade(GradeForm grade) throws Exception;

	public boolean deleteGrade(int gradeId);

	public boolean modifyGrade(GradeForm grade) throws Exception;

	List<GradeMaster> getGradeMaster();

}
