package service;

import java.sql.SQLException;
import java.sql.ResultSet;

import antity.User;
import dao.UserDao;


public class LoginService {
	public static User  service(User user) throws ClassNotFoundException, SQLException{
		UserDao u = new UserDao();
		
		User r =  u.FindByNameORQQPwd(user.getUserName(),user.getPassWord());
		if(r!=null){
			System.out.println("1."+r.toString()+r.getClass());			
			return 	r;
		}
		return null;
	}

}
