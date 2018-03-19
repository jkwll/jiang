package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import antity.Fruit;
import util.JdbcUtil;

public class GameDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private QueryRunner qr = new QueryRunner();
	public List<Fruit> FindAll() throws SQLException{
		//2.建立连接
		conn=JdbcUtil.getConnection();
		//2准备预编译的sql
		String sql = "SELECT * FROM fruits";
		//3.执行预编译sql语句(检查语法)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		//5.发送参数，执行sql
		rs =  stmt.executeQuery();
		List<Fruit> list =  new ArrayList<Fruit>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next()){
			Fruit a = new Fruit();
			a.setUserName(rs.getString("username"));
			a.setAward(rs.getString("award"));
			a.setNum(rs.getInt("num"));
			a.setTime(rs.getTimestamp("time"));
			System.out.println(format.format(rs.getTimestamp("time")));				
			System.out.println(a.toString());
			list.add(a);
		}
		JdbcUtil.close(conn,stmt,rs);  
		return list;
	}
	public Fruit FindByNamePwd(String name) throws SQLException{
		PreparedStatement stmt = null;
		//2.建立连接
		conn=JdbcUtil.getConnection();
		//2准备预编译的sql
		String sql = "SELECT * FROM users WHERE username=?";
		//3.执行预编译sql语句(检查语法)
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		//4.设置参数
		stmt.setString(1, name);
		//5.发送参数，执行sql
		rs =  stmt.executeQuery();
		if(rs.next()){
			Fruit a = new Fruit();
			a.setUserName(rs.getString("username"));
			JdbcUtil.close(conn,stmt,rs);  
			return a;
		}
		JdbcUtil.close(conn,stmt,rs);  
		return null;
	}
	@Test
	public void  findNum1( ) throws SQLException{
	/*	//2.建立连接
		conn=JdbcUtil.getConnection();
		//已经抽奖的次数
		String sql = "SELECT MAX(num) FROM fruits WHERE username=?";
			//3.执行预编译sql语句(检查语法)
			 pstmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.设置参数
			pstmt.setString(1, "ww");
			//5.发送参数，执行sql
			rs =  pstmt.executeQuery();
			System.out.println("异常123");
			if(rs.next()){
			rs.getString("MAX(num)");
			}*/
		//2.建立连接
				conn=JdbcUtil.getConnection();
				//已经抽奖的次数
				String sql = "SELECT MAX(num) FROM fruits WHERE username = ?";
					//3.执行预编译sql语句(检查语法)
					 pstmt = (PreparedStatement) conn.prepareStatement(sql);
					//4.设置参数
					pstmt.setString(1, "ww");
					//5.发送参数，执行sql
					rs =  pstmt.executeQuery();
					System.out.println("异常123");
					if(rs.next()){
						System.out.println(rs.getString("MAX(num)"));
						}

	}
	public String findNum( Fruit fruit ){
		//2.建立连接
		conn=JdbcUtil.getConnection();
		//已经抽奖的次数
		String sql = "SELECT MAX(num) FROM fruits WHERE username = ?";
		try {
			//3.执行预编译sql语句(检查语法)
			 pstmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.设置参数
			pstmt.setString(1, fruit.getUserName());
			//5.发送参数，执行sql
			rs =  pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("max(num)")+1+"";
				}
		} catch (SQLException e) {
			System.out.println("异常123");
			return "1";
		}
		return "1";

	}

	public void save(Fruit fruit) throws SQLException {
		String sql = "INSERT INTO gamefruits VALUES(?,?,?);";
		conn = JdbcUtil.getConnection();
		Date d = new Date();//下面可以直接写null
		
		qr.update(conn, sql, fruit.getUserName() , fruit.getAward() , d , findNum(fruit) );
		
		JdbcUtil.close(conn, pstmt, null);

	}
	/*	@Test
	public void save1() throws SQLException {
		String sql = "INSERT INTO fruits VALUES(?,?,?);";
			conn = JdbcUtil.getConnection();
			Date d = new Date();
			qr.update(conn, sql,"wu","0",d);		
	}*/
	public void del(String id) throws SQLException{
		//2.建立连接
		conn=JdbcUtil.getConnection();
		//2准备预编译的sql
		String sql = "DELETE FROM users WHERE id=?";
		//3.执行预编译sql语句(检查语法)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, id);
		//5.发送参数，执行sql
		int num =  stmt.executeUpdate();
		System.out.println("影响了"+num+"hang");
	}
	
	public boolean userExists(String name) {
		String sql = "select id from users where username=?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, name);
			// 执行查询
			rs = pstmt.executeQuery();
			// 判断
			if (rs.next()) {
				int id = rs.getInt("id");
				if (id > 0) {
					// 用户名已经存在
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
