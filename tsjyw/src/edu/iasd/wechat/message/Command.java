package edu.iasd.wechat.message;

public class Command {
	
	
	//���������� ��ʱ���� ɨ�赽���ݿ�ı�
	public static final int ACTION_SEND = 1;
	public static final int ACTION_SENDNOTICE = 2;
	//����Ķ���
	private int action;
	
	//���������
	private Object conetnt;

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Object getConetnt() {
		return conetnt;
	}

	public void setConetnt(Object conetnt) {
		this.conetnt = conetnt;
	}

	
	
	
	
}
