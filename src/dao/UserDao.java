package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import util.JdbcUtil;


import antity.User;

import com.mysql.jdbc.PreparedStatement;
public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private QueryRunner qr = new QueryRunner();
	@Test
	public void FindAll1() throws SQLException{
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM users";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		//5.���Ͳ�����ִ��sql
		rs =  stmt.executeQuery();
		List<User> list =  new ArrayList<User>();
		
		while(rs.next()){
			User a = new User();
			a.setUserName(rs.getString("username"));
			a.setPassWord(rs.getString("password"));
			a.setId(rs.getString("id"));
			System.out.println(a.toString());
			list.add(a);
		}
		JdbcUtil.close(conn,stmt,rs);  
		}
	
	@SuppressWarnings("null")
	public List<User> FindAll() throws SQLException{
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql,id�������
		String sql = "SELECT * FROM users ORDER BY id DESC;";
		//3.ִ��Ԥ����sql���(����﷨)
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		//5.���Ͳ�����ִ��sql
		rs =  stmt.executeQuery();
		List<User> list =  new ArrayList<User>();
		
		while(rs.next()){
			User a = new User();
			a.setUserName(rs.getString("username"));
			a.setPassWord(rs.getString("password"));
			a.setQq(rs.getString("qq"));
			a.setId(rs.getString("id"));
			a.setFlag(rs.getString("flag"));
		//	System.out.println(rs.getString("password"));
			list.add(a);
		}
		JdbcUtil.close(conn,stmt,rs);  
		return list;
		}
	public User FindByNameORQQPwd(String name,String pwd) throws SQLException{
			//name�п�����QQ���п���username
		PreparedStatement pstmt = null;
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM users WHERE (username=? OR qq=?) AND PASSWORD=?";

		//3.ִ��Ԥ����sql���(����﷨)
		pstmt = (PreparedStatement)conn.prepareStatement(sql);
		//4.���ò���
		pstmt.setString(1, name);
		pstmt.setString(2, name);
		pstmt.setString(3, pwd);	
		//5.���Ͳ�����ִ��sql
		rs =  pstmt.executeQuery();
		if(rs.next()){
			User a = new User();
			a.setUserName(rs.getString("username"));
			a.setPassWord(rs.getString("password"));
			JdbcUtil.close(conn,pstmt,rs);  
			return a;
		}
		return null;
	}
		
		public User FindByNamePwd(String name,String pwd) throws SQLException{
			PreparedStatement stmt = null;
			//2.��������
			conn=JdbcUtil.getConnection();
			//2׼��Ԥ�����sql
			String sql = "SELECT * FROM users WHERE username=? AND password=?";
			//3.ִ��Ԥ����sql���(����﷨)
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.���ò���
			stmt.setString(1, name);
			stmt.setString(2, pwd);	
			//5.���Ͳ�����ִ��sql
			rs =  stmt.executeQuery();
			if(rs.next()){
				User a = new User();
				a.setUserName(rs.getString("username"));
				a.setPassWord(rs.getString("password"));
				a.setQq(rs.getString("qq"));
				a.setId(rs.getString("id"));
				JdbcUtil.close(conn,stmt,rs);  
				return a;
			}
			JdbcUtil.close(conn,stmt,rs);  
			return null;
		}
		
		public User FindByUserName(String name) throws SQLException{
			PreparedStatement stmt = null;
			//2.��������
			conn=JdbcUtil.getConnection();
			//2׼��Ԥ�����sql
			String sql = "SELECT * FROM users WHERE username=?";
			//3.ִ��Ԥ����sql���(����﷨)
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			//4.���ò���
			stmt.setString(1, name);
			//5.���Ͳ�����ִ��sql
			rs =  stmt.executeQuery();
			if(rs.next()){
				User a = new User();
				a.setUserName(rs.getString("username"));
				a.setPassWord(rs.getString("password"));
				a.setQq(rs.getString("qq"));
				a.setId(rs.getString("id"));
				JdbcUtil.close(conn,stmt,rs);  
				return a;
			}
			JdbcUtil.close(conn,stmt,rs);  
			return null;
		}

	public User FindByQQPwd(String qq,String pwd) throws SQLException{
		PreparedStatement stmt = null;
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT * FROM users WHERE qqs=? AND password=?";
		//3.ִ��Ԥ����sql���(����﷨)
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		//4.���ò���
		stmt.setString(1, qq);
		stmt.setString(2, pwd);	
		//5.���Ͳ�����ִ��sql
		rs =  stmt.executeQuery();
		if(rs.next()){
			User a = new User();
			a.setUserName(rs.getString("username"));
			a.setPassWord(rs.getString("password"));
			JdbcUtil.close(conn,stmt,rs);  
			return a;
		}
		JdbcUtil.close(conn,stmt,rs);  
		return null;
	}
	
	public void save(User user) {
		String sql = "INSERT INTO users(username,password,qq,flag) VALUES(?,?,?,?)  ";
		try {
			conn = JdbcUtil.getConnection();
			/*pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassWord());
			// ִ�и���
			pstmt.executeUpdate();	*/
			// ʹ��DbUtils����ķ�������
			qr.update(conn, sql, user.getUserName(),user.getPassWord(),user.getQq(),"0");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
	public void saveflag(User user) throws SQLException {
		//�鿴��ǰ״̬
		String flag = "";
		//2.��������
		conn=JdbcUtil.getConnection();
		//2׼��Ԥ�����sql
		String sql = "SELECT flag FROM users WHERE id=?";
		//3.ִ��Ԥ����sql���(����﷨)
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//4.���ò���
		System.out.println("id=="+user.getId());
		pstmt.setString(1, user.getId());	
		//5.���Ͳ�����ִ��sql
		rs =  pstmt.executeQuery();
		System.out.println("f;lag="+flag);
		if(rs.next()){
			flag = rs.getString("flag");
			System.out.println("zheli  " + flag);
		}
		//�ı�״̬
		String sql1 ="";
		if(flag.equals("1")){
			sql1 = "UPDATE users SET flag='0' WHERE id=?;";
		}else {
			sql1 = "UPDATE users SET flag='1' WHERE id=?;";
			
		}	
		try {
			conn = JdbcUtil.getConnection();
			qr.update(conn, sql1,user.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
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
	}
	
	public boolean userExists(String name) {
		String sql = "select id from users where username=?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, name);
			// ִ�в�ѯ
			rs = pstmt.executeQuery();
			// �ж�
			if (rs.next()) {
				int id = rs.getInt("id");
				if (id > 0) {
					// �û����Ѿ�����
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
