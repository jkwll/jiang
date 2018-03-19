package service;

import java.sql.SQLException;
import java.util.List;

import antity.User;
import dao.UserDao;

public class AdminService {
	public static List<User>  service(User user) throws SQLException {
		String pwd = user.getPassWord();
		String uname = user.getUserName();
		System.out.println(pwd);
		System.out.println(uname);
		List<User> list = null;
		if(uname.equals("wu")&&pwd.equals("16229")){
			UserDao u = new UserDao();
				list = u.FindAll();
		}
		return list; 
	}
}
