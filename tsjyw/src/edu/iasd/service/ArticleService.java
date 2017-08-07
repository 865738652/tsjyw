package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.ArticleState;
import edu.iasd.pojo.CommentType;
import edu.iasd.pojo.ViewComment;
import edu.iasd.form.ArticleForm;
import edu.iasd.form.AttachVO;
import edu.iasd.form.CommentForm;
import edu.iasd.pojo.ViewArticle;

@Service
@Transactional
public interface ArticleService {
	public Page getAllArticle(int pageNow,int pageSize, Boolean recommend, String queryString);
	
	public Page getAllArticleBySchool(int pageNow, int pageSize, int schoolId, Boolean recommend, String queryString);
	
	public Page getAllSchoolArticle(int pageNow, int pageSize,Boolean recommend);
	
	public Page getArticleByModule(int pageNow,int pageSize, int moduleId, Boolean recommend);
	
	public Page getArticleByType(int pageNow,int pageSize, int moduleTypeId, Boolean recommend);

	public Page getBlogArticle(int pageNow, int pageSize, int userId, Boolean recommend, String queryString) throws Exception;
	
	public ViewArticle getArticle(int articleId)  throws Exception;
	
	public ViewArticle getPrevArticle(int articleId);
	
	public ViewArticle getNextvArticle(int articleId);
	
	public String createArticle(ArticleForm articleForm);
	
	public Boolean deleteArticle(int articleId);
	
	public Boolean modifyArticle(ArticleForm articleForm);
	
	public List<ArticleState> getArticleState();
	
	public List<AttachVO> getImageByArticle(int articleId);
	
	public List<AttachVO> getVideoByArticle(int articleId);

	

	public ViewComment getComment(int commentId);

	public Boolean deleteComment(int commentId);

	public String createComment(CommentForm commentForm);

	public List<CommentType> getCommentType();

	public Page getAllCommentByArticle(int pageNumber, int pageSize, int articleId, int commentTypeId);

	
}
