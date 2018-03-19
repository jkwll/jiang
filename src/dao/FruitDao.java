package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import antity.Fruit;
import antity.User;
import util.JdbcUtil;

public class FruitDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private QueryRunner qr = new QueryRunner();
	
	public List<Fruit> FindAll() throws SQLException{
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM fruits ORDER BY time DESC;";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		//5.���Ͳ�����ִ��sql
		rs =  stmt.executeQuery();

		List<Fruit> list =  new ArrayList<Fruit>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		while(rs.next()){
			Fruit a = new Fruit();			
			a.setQq(rs.getString("qq"));
			a.setAward(rs.getString("award"));
			a.setNum(rs.getInt("num"));
			a.setTime(rs.getTimestamp("time"));
			System.out.println(a.toString());
			a.setUserName(rs.getString("username"));
			list.add(a);
		}
		JdbcUtil.close(conn,stmt,rs);  
		return list;
	}
	/*
	@Test
	public void FindAl1l() throws SQLException{
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM fruits";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		//5.���Ͳ�����ִ��sql
		rs =  stmt.executeQuery();
		List<Fruit> list =  new ArrayList<Fruit>();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

	}*/
	
	
	public List<Fruit> FindByUser(User user) throws SQLException{	
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM fruits WHERE username=?";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//4.���ò���
		pstmt.setString(1,user.getUserName());
		//5.���Ͳ�����ִ��sql
		rs =  pstmt.executeQuery();
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
		JdbcUtil.close(conn,pstmt,rs);  
		return list;
	}
	
	public List<Fruit> FindById(User user) throws SQLException{
				//ͨ��id��ѯusername
				String username = "";
				//2.��������
				conn=JdbcUtil.getConnection();
				//2׼��Ԥ�����sql
				String sql = "SELECT username FROM users WHERE id=?";
				//3.ִ��Ԥ����sql���(����﷨)
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				//4.���ò���
				System.out.println("id=="+user.getId());
				pstmt.setString(1, user.getId());	
				//5.���Ͳ�����ִ��sql
				rs =  pstmt.executeQuery();
				if(rs.next()){
					username = rs.getString("username");
					System.out.println("f;lag="+username);
				}
				//ͨ��username��ѯ ���е�filter
				
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		//String sql1 = "SELECT * FROM fruits WHERE username='"+username+"'";
		System.out.println("zheli1513 ");
		//ģ����ѯ����test�����ݲ����
		String sql1 = "SELECT * FROM fruits WHERE username LIKE '"+username+"%'";
		//3.ִ��Ԥ����sql���(����﷨)
		Statement pstmt = (Statement) conn.prepareStatement(sql1);	
		//pstmt.setString(1, "%"+username+"%");	
		//5.���Ͳ�����ִ��sql
		rs =  pstmt.executeQuery(sql1);
		List<Fruit> list =  new ArrayList<Fruit>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next()){
			Fruit a = new Fruit();
			a.setUserName(rs.getString("username"));
			a.setAward(rs.getString("award"));
			a.setQq(rs.getString("qq"));
			a.setNum(rs.getInt("num"));
			a.setTime(rs.getTimestamp("time"));
			list.add(a);
			System.out.println("aaa"+a.toString());
		}
		JdbcUtil.close(conn,pstmt,rs);  
		return list;
	}

	public String findNum( Fruit fruit ){
		//2.��������
		conn=JdbcUtil.getConnection();
		//�Ѿ��齱�Ĵ���
		String sql = "SELECT MAX(num) FROM fruits WHERE username = ?";
		try {
			//3.ִ��Ԥ����sql���(����﷨)
			 pstmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.���ò���
			pstmt.setString(1, fruit.getUserName());
			//5.���Ͳ�����ִ��sql
			rs =  pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("max(num)")+1+"";
				}
		} catch (SQLException e) {
			System.out.println("�쳣123");
			return "1";
		}
		return "1";

	}
	public String findQq( Fruit fruit ){ //������ѯ����ͬ�������ݣ�����������д��
		//2.��������
		conn=JdbcUtil.getConnection();
		//�Ѿ��齱�Ĵ���
		String sql = "SELECT qq FROM users WHERE username = ?";
		try {
			//3.ִ��Ԥ����sql���(����﷨)
			 pstmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.���ò���
			 String username =fruit.getUserName();
			 //���￼�ǵ��� --username�鲻��qq����ʱ�����ַ���
			 int length = username.length();
			 if(length>6){
				 if( username.substring(length-6, length).equals("--test") ){
					 username = username.substring(0, length-6);
					 System.out.println("����������");
					 System.out.println(username);
				 }
			 }
			 
			pstmt.setString(1, username);
			//5.���Ͳ�����ִ��sql
			rs =  pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("qq");
			}
		} catch (SQLException e) {
			System.out.println("�쳣123");
			return "1";
		}
		return "1";

	}

	

	public void save(Fruit fruit) throws SQLException {
		String sql = "INSERT INTO fruits VALUES(?,?,?,?,?);";
		conn = JdbcUtil.getConnection();
		Date d = new Date();//�������ֱ��дnull
		String qq= findQq(fruit);
		System.out.println("qq="+qq);
		qr.update(conn, sql ,fruit.getUserName(),qq,fruit.getAward(),d,findNum(fruit));
		
		JdbcUtil.close(conn, pstmt, null);
	}
	/*���ֻ�Ƿ��ص����*/
	public boolean FindByUser1(User user) throws SQLException{
		//2.��������
				conn=JdbcUtil.getConnection();
				//2׼��Ԥ�����sql
				 Date d = new Date();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			     String date= sdf.format(d);
				String sql = "SELECT * FROM fruits WHERE (TIME LIKE '"+date+"%')AND username=?";
				//3.ִ��Ԥ����sql���(����﷨)
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				//4.���ò���
				pstmt.setString(1,user.getUserName());
				//5.���Ͳ�����ִ��sql
				rs =  pstmt.executeQuery();
				boolean b =rs.next() ;
				JdbcUtil.close(conn,pstmt,rs);  
				return b;
	}
	
	@Test
	public void Findr() throws SQLException{
		//2.��������
				conn=JdbcUtil.getConnection();
				//2׼��Ԥ�����sql
				 Date d = new Date();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			     String date= sdf.format(d);
				String sql = "SELECT * FROM fruits WHERE (TIME LIKE'"+date+"%')AND username='ggk'";
				//3.ִ��Ԥ����sql���(����﷨)
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				//4.���ò���
			//	pstmt.setString(1,"ww");
				//5.���Ͳ�����ִ��sql
				rs =  pstmt.executeQuery();
				System.out.println(rs.next());
				JdbcUtil.close(conn,pstmt,rs);
				

				
	}


	public void del(String id) throws SQLException{
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "DELETE FROM users WHERE id=?";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, id);
		//5.���Ͳ�����ִ��sql
		int num =  stmt.executeUpdate();
		System.out.println("Ӱ����"+num+"hang");
	}

	
}
