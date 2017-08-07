package edu.iasd.security;

public class NotLoginException extends Exception {
	public NotLoginException() {
		super("尚未登录,请登录后再访问此页面");
	}
	
	public NotLoginException(String message) {
		super(message);
	}
}
