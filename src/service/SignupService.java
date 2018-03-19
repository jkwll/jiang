package service;

import java.sql.SQLException;

import antity.User;
import dao.UserDao;
import exception.UserExistsException;

public class SignupService {
	private static UserDao userDao = new UserDao();
	public static User service(User user) throws ClassNotFoundException, SQLException, UserExistsException{
			UserDao ud = new UserDao();
			//查看用户是否存在
			boolean flag=ud.userExists(user.getUserName());
			//如果存在，抛出异常 
			if(flag){
				// 不允许注册, 给调用者提示
				throw new UserExistsException("用户名已经被注册！");
			}else{
				userDao.save(user);

				return user;				
			}
	}
}
