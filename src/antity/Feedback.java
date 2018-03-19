package antity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Feedback {
	String userName;
	@Override
	public String toString() {
		return "Feedback [userName=" + userName + ", content=" + content + ", contact=" + contact + ", time=" + time
				+ "]";
	}
	String content;//建议内容
	String contact;//联系方式
	Date time;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(this.time);
	}
	public void setTime(Date time) {
		this.time=time;	
	}
}
