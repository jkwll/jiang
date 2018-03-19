package service;

import java.sql.SQLException;

import dao.UserDao;

public class DelService {
	static public  void  service(String id) throws SQLException{
		UserDao u = new UserDao();
		u.del(id);
	}

}
