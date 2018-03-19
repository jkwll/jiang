package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.mysql.jdbc.PreparedStatement;

import antity.Feedback;
import antity.Fruit;
import util.JdbcUtil;

	public class FeedbackDao {
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		private QueryRunner qr = new QueryRunner();
		
		public void save(Feedback feedback) throws SQLException {
		String sql = "INSERT INTO feedback VALUES(?,?,?,?)";
		conn = JdbcUtil.getConnection();
		qr.update(conn, sql, feedback.getUserName(),feedback.getContent(),feedback.getContact(),null);
		JdbcUtil.close(conn, pstmt, null);
	}
}
