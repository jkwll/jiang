package service;

import java.sql.SQLException;
import java.util.List;

import antity.Fruit;
import dao.FruitDao;

public class GameService {
	public static void service(Fruit fruit) throws SQLException{
		 FruitDao  f = new FruitDao();
		 f.save(fruit);
	}
	public static int max2048(){
		
		return 6;
	}
	public static List<Fruit> show() throws SQLException {
		 FruitDao  f = new FruitDao();
		 List<Fruit> list = null;
		 list=f.FindAll();
		 return list; 
	}
}
