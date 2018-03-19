package antity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fruit {
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(this.time);
	}

	
	@Override
	public String toString() {
		return "Fruit [userName=" + userName + ", award=" + award + ", time=" + time + ", qq=" + qq + ", num=" + num + "]";
	}

	String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	String userName;
	String award;
	Date time;
	int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setDate(Date time ){
		this.time = time;
	}
	public void setTime(Date time) {
		 //将字符串类型的日期转换成Date类型的指定格式的日期  
      /*  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            try {
				this.time = f.parse(string);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  	*/
		this.time=time;
		
	}
}
