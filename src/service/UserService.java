package service;

import java.sql.SQLException;
import java.util.List;

import antity.Feedback;
import antity.Fruit;
import antity.User;
import dao.FeedbackDao;
import dao.FruitDao;
import dao.UserDao;

public class UserService {
	 static FruitDao  f = new FruitDao();
	 static UserDao u = new UserDao();
	public static User  Findinfo(User user) throws ClassNotFoundException, SQLException{
		UserDao u = new UserDao();
		//通过USERNAME和密码获取个人信息
		User r =  u.FindByNamePwd(user.getUserName(),user.getPassWord());
		if(r!=null){
			System.out.println("1."+r.toString()+r.getClass());			
			return 	r;//对象的实例
		}
		return null;
	}
	public static List<Fruit> FindFruitByUser(User user) throws SQLException{
		List<Fruit> list = f.FindByUser(user);
		return list;
	}
	public static boolean FindFruitByUser1(User user) throws SQLException{
		return f.FindByUser1(user);

	}
	public static User FindUserByUserName(User user) throws SQLException {
		return  u.FindByUserName(user.getUserName());

	}

	public static void SaveFeedback(Feedback feedback) throws SQLException{
		FeedbackDao fb = new FeedbackDao();
		fb.save(feedback);
	}
	public static void Saveflag(User user) throws SQLException{
		UserDao u = new UserDao();
		u.saveflag(user);
	}
	

}
