package edu.iasd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ArticleForm;
import edu.iasd.form.AttachVO;
import edu.iasd.form.CommentForm;
import edu.iasd.pojo.Article;
import edu.iasd.pojo.ArticleState;
import edu.iasd.pojo.Comment;
import edu.iasd.pojo.CommentType;
import edu.iasd.pojo.Image;
import edu.iasd.pojo.Module;
import edu.iasd.pojo.NetCourse;
import edu.iasd.pojo.User;
import edu.iasd.pojo.Video;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewComment;
import edu.iasd.pojo.ViewNetCourse;
import edu.iasd.utils.Constants;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private IBaseDao<edu.iasd.pojo.Article> articleDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewArticle> viewArticleDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ArticleState> articleStateDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.Module> moduleDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.Image> imageDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.Video> videoDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Comment> commentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewComment> viewCommentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.CommentType> commentTypeDao;
	@Override
	public Page getAllArticle(int pageNow, int pageSize, Boolean recommend, String queryString) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		String hql = "from ViewArticle v where v.schoolId is NULL";
		if (recommend!= null && recommend)
			hql += " and v.articleRecommend=true ";
		hql += where;
		hql += " order by v.articleTime desc";
		
		return viewArticleDao.pagedQuery(hql, pageNow, pageSize);
	}
	
	@Override
	public Page getAllArticleBySchool(int pageNow, int pageSize, int schoolId, Boolean recommend, String queryString) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		String hql = "from ViewArticle v where v.schoolId=?";
		if (recommend!= null && recommend)
			hql += " and v.articleRecommend=true ";
		hql += where;
		hql += " order by v.articleTime desc";
		
		return viewArticleDao.pagedQuery(hql, pageNow, pageSize, schoolId);
	}
	
	@Override
	public Page getAllSchoolArticle(int pageNow, int pageSize,Boolean recommend) {
		String hql = "from ViewArticle v where v.schoolId is not NULL";
		if (recommend!= null && recommend)
			hql += " and v.articleRecommend=true";
		hql += " order by v.articleTime desc";
		
		return viewArticleDao.pagedQuery(hql, pageNow, pageSize);
	}

	@Override
	public Page getArticleByModule(int pageNow, int pageSize, int moduleId,	Boolean recommend) {
		String hql = "from ViewArticle v where 1=1 ";
		if (recommend!= null && recommend)
			hql += " and v.articleRecommend=true ";
		
		Module m = moduleDao.findUniqueBy("moduleId", moduleId);
		Set child = m.getModules();
		Page page;
		if (child == null || child.size() == 0) { //没有子栏目，只获取本栏目下的文章
			hql += " and v.moduleId=? ";
			hql += " order by v.articleTime desc";
			page = viewArticleDao.pagedQuery(hql, pageNow, pageSize, moduleId);
		}
		else { //有子栏目，依靠栏目和文章编码的关系，获取本栏目和所有子栏目下的文章
			hql += " and v.articleNumber like ? ";
			hql += " order by v.articleTime desc";
			page = viewArticleDao.pagedQuery(hql, pageNow, pageSize, m.getModuleNumber() + "%");
		}
		return page;
	}
	
	@Override
	public Page getArticleByType(int pageNow,int pageSize, int moduleTypeId, Boolean recommend) {
		String hql = "from ViewArticle v where v.moduleTypeId=? ";
		if (recommend!= null && recommend)
			hql += " and v.articleRecommend=true ";
		hql += " order by v.articleTime desc";
		
		return viewArticleDao.pagedQuery(hql, pageNow, pageSize, moduleTypeId);
	}

	@Override
	public Page getBlogArticle(int pageNow, int pageSize, int userId, Boolean recommend, String queryString) throws Exception {
		Module m = moduleDao.findUniqueBy("moduleId", Constants.MODULE_ID_BLOG);
		if (m == null)
			throw new Exception("No blog module exists");

		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		String hql = "from ViewArticle v where v.userId=? and v.articleNumber like ? ";
		if (recommend != null && recommend)
			hql += " and v.articleRecommend=true ";
		hql += where;
		hql += " order by v.articleTime desc";
		
		return viewArticleDao.pagedQuery(hql, pageNow, pageSize, userId, m.getModuleNumber() + "%");
	}

	@Override
	public ViewArticle getArticle(int articleId) throws Exception {
		/*
		 * 先更新文章的阅读次数
		 */
		Article article = articleDao.findUniqueBy("articleId", articleId);
		if (article == null)
			throw new Exception("文章已不存在");
		article.setArticleReadCount(article.getArticleReadCount() + 1);
		articleDao.save(article);
		return viewArticleDao.findUniqueBy("articleId", articleId);
	}
	
	@Override
	public ViewArticle getPrevArticle(int articleId) {
		Article article = articleDao.findUniqueBy("articleId", articleId);
		if (article == null)
			return null;
		Page p = viewArticleDao.pagedQuery("from ViewArticle v where v.moduleId=? and v.articleTime<? order by articleTime desc", 1, 1, article.getModule().getModuleId(), article.getArticleTime());
		if (p.getResult() != null) {
			List<ViewArticle> list = (List<ViewArticle>)p.getResult();
			return list.size() > 0 ? list.get(0) : null;
		}
		else
			return null;
	}
	
	@Override
	public ViewArticle getNextvArticle(int articleId) {
		Article article = articleDao.findUniqueBy("articleId", articleId);
		if (article == null)
			return null;
		
		Page p = viewArticleDao.pagedQuery("from ViewArticle v where v.moduleId=? and v.articleTime>? order by articleTime asc", 1, 1, article.getModule().getModuleId(), article.getArticleTime());
		if (p.getResult() != null) {
			List<ViewArticle> list = (List<ViewArticle>)p.getResult();
			return list.size() > 0 ? list.get(0) : null;
		}
		else
			return null;
	}

	@Override
	public String createArticle(ArticleForm articleForm) {
		Article article = new Article();
		fillArticle(article, articleForm);		
		article.setArticleReadCount(0);
		article.setArticleNumber("0000");
		articleDao.save(article);
		
		Module module = article.getModule();
		article.setArticleNumber(module.getModuleNumber() + String.format("%04d", article.getArticleId()));
		articleDao.save(article);
		
		saveAttachOfArticle(articleForm, article);
		return article.getArticleId().toString();
	}

	@Override
	public Boolean deleteArticle(int articleId) {
		Article a = articleDao.findUniqueBy("articleId", articleId);
		deleteAttachByArticle(a);
		articleDao.removeById(articleId);
		return true;
	}

	@Override
	public Boolean modifyArticle(ArticleForm articleForm) {
		Article article = articleDao.findUniqueBy("articleId", articleForm.getArticleId());
		deleteAttachByArticle(article);
		
		saveAttachOfArticle(articleForm, article);
		
		fillArticle(article, articleForm);
		articleDao.save(article);
		return true;
	}

	@Override
	public List<ArticleState> getArticleState() {
		return articleStateDao.getAll();
	}
	
	private void fillArticle(Article article, ArticleForm form) {
		article.setArticleContent(form.getArticleContent());
		article.setArticleAbstract(form.getArticleAbstract());
		//article.setArticleNumber(form.getArticleNumber());
		article.setArticleRecommend(form.getArticleRecommend());
		article.setArticleTime(form.getArticleTime());
		article.setArticleTitle(form.getArticleTitle());
		article.setArticleKeyword(form.getArticleKeyword());
		
		ArticleState s = articleStateDao.findUniqueBy("articleStateId", form.getArticleStateId());
		article.setArticleState(s);
		
		Module m = moduleDao.findUniqueBy("moduleId", form.getModuleId());
		article.setModule(m);
		
		User u = userDao.findUniqueBy("userId", form.getUserId());
		article.setUser(u);
	}
	
	private void deleteAttachByArticle(Article article) {
		Set images = article.getImages();
		if (images != null) {
			for (Object o : images)
				imageDao.remove(o);
			article.setImages(new HashSet(0));
		}
		
		Set videos = article.getVideos();
		if (videos != null) {
			for (Object o : videos)
				videoDao.remove(o);
			article.setVideos(new HashSet(0));
		}
	}
	
	private void saveAttachOfArticle(ArticleForm articleForm, Article article) {
		if (articleForm.getPictures() != null) {
			for (AttachVO pic : articleForm.getPictures()) {
				Image image = new Image();
				image.setArticleId(article.getArticleId());
				image.setImageIntro(pic.getAttachIntro());
				image.setImageName(pic.getAttachName());
				image.setImagePath(pic.getAttachUrl());
				image.setImageSerial(pic.getAttachSerial());
				image.setImageSize(pic.getAttachSize());
				image.setIsThumbnail(pic.getIsThumbnail());
				image.setIsShow(pic.getIsShow());
				imageDao.save(image);
			}
		}
		
		if (articleForm.getVideos() != null) {
			for (AttachVO vdo : articleForm.getVideos()) {
				Video video = new Video();
				video.setArticleId(article.getArticleId());
				video.setVideoIntro(vdo.getAttachIntro());
				video.setVideoName(vdo.getAttachName());
				video.setVideoPath(vdo.getAttachUrl());
				video.setVideoSerial(vdo.getAttachSerial());
				video.setVideoSize(vdo.getAttachSize());
				videoDao.save(video);
			}
		}
	}
	
	public List<AttachVO> getImageByArticle(int articleId) {
		Article a = articleDao.findUniqueBy("articleId", articleId);
		List<AttachVO> list = new ArrayList<AttachVO>();
		for (Object o : a.getImages()) {
			Image v = (Image)o;
			AttachVO vo = new AttachVO();
			vo.setAttachId(v.getImageId());
			vo.setAttachIntro(v.getImageIntro());
			vo.setAttachName(v.getImageName());
			vo.setAttachSerial(v.getImageSerial());
			vo.setAttachSize(v.getImageSize());
			vo.setAttachUrl(v.getImagePath());
			vo.setOwnerId(v.getArticleId());
			vo.setIsThumbnail(v.getIsThumbnail());
			vo.setIsShow(v.getIsShow());
			list.add(vo);
		}
		return list;
	}
	
	public List<AttachVO> getVideoByArticle(int articleId) {
		Article a = articleDao.findUniqueBy("articleId", articleId);
		System.out.println(articleId);
		List<AttachVO> list = new ArrayList<AttachVO>();
		for (Object o : a.getVideos()) {
			Video v = (Video)o;
			AttachVO vo = new AttachVO();
			vo.setAttachId(v.getVideoId());
			vo.setAttachIntro(v.getVideoIntro());
			vo.setAttachName(v.getVideoName());
			vo.setAttachSerial(v.getVideoSerial());
			vo.setAttachSize(v.getVideoSize());
			vo.setAttachUrl(v.getVideoPath());
			vo.setOwnerId(v.getArticleId());
			list.add(vo);
		}
		return list;
	}

	@Override
	public ViewComment getComment(int commentId) {
		// TODO Auto-generated method stub
		ViewComment vc = viewCommentDao.findUniqueBy("commentId", commentId);
		return vc;
	}

	@Override
	public Boolean deleteComment(int commentId) {
		// TODO Auto-generated method stub
		Comment a = commentDao.findUniqueBy("commentId", commentId);
		
		commentDao.removeById(commentId);
		return true;
		
	}

	@Override
	public String createComment(CommentForm commentForm) {
		// TODO Auto-generated method stub
		Comment n=new Comment();
		fillComment(n,commentForm); 
		
		commentDao.save(n);
		return n.getCommentId().toString();
		
	}
	private void fillComment(Comment n, CommentForm commentForm) {
		// TODO Auto-generated method stub
		n.setCommentContent(commentForm.getCommentContent());
		n.setCommentTime(commentForm.getCommentTime());
		
		CommentType c=commentTypeDao.findUniqueBy("commentTypeId",commentForm.getCommentTypeId());
		n.setCommentType(c);
		Article a=articleDao.findUniqueBy("articleId", commentForm.getArticleId());
		n.setArticle(a);
		if (commentForm.getUserId() != null) {
			User u=userDao.findUniqueBy("userId",commentForm.getUserId());
			n.setUser(u);
		}
	}

	@Override
	public List<CommentType> getCommentType() {
		// TODO Auto-generated method stub
		return commentTypeDao.getAll();
		
	}

	@Override
	public Page getAllCommentByArticle(int pageNumber, int pageSize, int articleId, int commentTypeId) {
		// TODO Auto-generated method stub
		return viewCommentDao.pagedQuery("from ViewComment where articleId=? and commentTypeId=? order by commentTime desc", pageNumber, pageSize, articleId, commentTypeId);
	}

}
