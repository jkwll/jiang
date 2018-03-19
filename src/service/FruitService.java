package service;

import java.sql.SQLException;
import java.util.List;

import antity.Fruit;
import antity.User;
import dao.FruitDao;

public class FruitService {
	 static FruitDao  f = new FruitDao();
	public static void service(Fruit fruit) throws SQLException{
		 f.save(fruit);
	}
	public static List<Fruit> show() throws SQLException {
		 List<Fruit> list = null;
		 list=f.FindAll();
		 return list; 
	}
	public static List<Fruit> ShowById(User user) throws SQLException {
		 List<Fruit> list = null;
		 list=f.FindById(user);
		 return list; 
	}

}
