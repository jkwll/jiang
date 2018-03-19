package util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ������
 * 1. ��ʼ��C3P0���ӳ�
 * 2. ����DbUtils���Ĺ��������
 * @author Jie.Yuan
 *
 */
public class JdbcUtils {

	/**
	 *  1. ��ʼ��C3P0���ӳ�
	 */
	private static  DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
	/*
	public User FindByNamePwd(String name,String pwd) throws SQLException{
		try {
			String sql = "select * from admin where userName=? and pwd=?";
			return JdbcUtils.getQueryRuner()//
					.query(sql, 
							new BeanHandler<User>(User.class),
							name,
							pwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * 2. ����DbUtils���Ĺ��������
	 */
	public static QueryRunner getQueryRuner(){
		// ����QueryRunner���󣬴������ӳض���
		// �ڴ���QueryRunner�����ʱ���������������Դ����
		// ��ô��ʹ��QueryRunner���󷽷���ʱ�򣬾Ͳ���Ҫ�������Ӷ���
		// ���Զ�������Դ�л�ȡ����(���ùر�����)
		return new QueryRunner(dataSource);
	}
}