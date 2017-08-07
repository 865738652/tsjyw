package edu.iasd.utils;

import java.util.HashMap;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.User;

public class CreditHelper {
	public final static Integer CREDIT_TYPE_REGISTER = 1;
	public final static Integer CREDIT_TYPE_COMPLETE = 2; 
	public final static Integer CREDIT_TYPE_LOGIN = 3;
	public final static Integer CREDIT_TYPE_NOTICE_CLASS = 4;
	public final static Integer CREDIT_TYPE_NOTICE_GRADE = 5;
	public final static Integer CREDIT_TYPE_NOTICE_SCHOOL = 6;
	public final static Integer CREDIT_TYPE_BLOG = 7; 
	public final static Integer CREDIT_TYPE_BLOG_DELETE = 8; 
	public final static Integer CREDIT_TYPE_BLOG_GOOD = 9;
	public final static Integer CREDIT_TYPE_ANSWER = 10;
	
	static class Credit {
		private Integer creditType;
		private int point;
		private CreditValidator validator;

		public Credit(Integer t, int p) {
			creditType = t;
			point = p;
		}

		public Credit(Integer t, int p, CreditValidator v) {
			creditType = t;
			point = p;
			validator = v;
		}

		public Integer getCreditType() {
			return creditType;
		}

		public int getPoint() {
			return point; 
		}

		public Boolean validate(IBaseDao<edu.iasd.pojo.User> userDao, int userId) {
			if (validator == null)
				return true;
			return validator.validate(userDao, userId);
		}
	}

	interface CreditValidator {
		public Boolean validate(IBaseDao<edu.iasd.pojo.User> userDao, int userId);
	}
	
	private static HashMap<Integer, Credit> credits;

	static {
		credits = new HashMap<Integer, Credit>();
		credits.put(CREDIT_TYPE_REGISTER, new Credit(CREDIT_TYPE_REGISTER, 10));
		credits.put(CREDIT_TYPE_COMPLETE, new Credit(CREDIT_TYPE_COMPLETE, 10));
		credits.put(CREDIT_TYPE_LOGIN, new Credit(CREDIT_TYPE_LOGIN, 2, new CreditValidator() {
			public Boolean validate(IBaseDao<edu.iasd.pojo.User> userDao, int userId) {
				User user = userDao.get(userId);
				if (user == null) 
					return false;
				
				if (user.getLastLoginTime() == null)
					return true;

				Date date=new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(date);
				return !dateString.equals(formatter.format(user.getLastLoginTime()));
			}
		}));
		credits.put(CREDIT_TYPE_NOTICE_CLASS, new Credit(CREDIT_TYPE_NOTICE_CLASS, 10));
		credits.put(CREDIT_TYPE_NOTICE_GRADE, new Credit(CREDIT_TYPE_NOTICE_GRADE, 20));
		credits.put(CREDIT_TYPE_NOTICE_SCHOOL, new Credit(CREDIT_TYPE_NOTICE_SCHOOL, 30));
		credits.put(CREDIT_TYPE_BLOG, new Credit(CREDIT_TYPE_BLOG, 5));
		credits.put(CREDIT_TYPE_BLOG_DELETE, new Credit(CREDIT_TYPE_BLOG_DELETE, -5));
		credits.put(CREDIT_TYPE_BLOG_GOOD, new Credit(CREDIT_TYPE_BLOG_GOOD, 5));
		credits.put(CREDIT_TYPE_ANSWER, new Credit(CREDIT_TYPE_ANSWER, 10));
	}

	public static void trigger(IBaseDao<edu.iasd.pojo.User> userDao, int userId, int creditType) {
		try {
			User user = userDao.get(userId);
			if (user == null) 
				return;
	
			Credit c = credits.get(creditType);
			if (c == null || !c.validate(userDao, userId))
				return;
	
			if (user.getUserIntegral() == null)
				user.setUserIntegral(c.getPoint());
			else if (user.getUserIntegral() + c.getPoint() < 0)
				return;
			else
				user.setUserIntegral(user.getUserIntegral() + c.getPoint());
			user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			userDao.save(user);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
