package service;

import java.sql.SQLException;

import antity.User;
import dao.UserDao;
import exception.UserExistsException;

public class SignupService {
	private static UserDao userDao = new UserDao();
	public static User service(User user) throws ClassNotFoundException, SQLException, UserExistsException{
			UserDao ud = new UserDao();
			//�鿴�û��Ƿ����
			boolean flag=ud.userExists(user.getUserName());
			//������ڣ��׳��쳣 
			if(flag){
				// ������ע��, ����������ʾ
				throw new UserExistsException("�û����Ѿ���ע�ᣡ");
			}else{
				userDao.save(user);

				return user;				
			}
	}
}
