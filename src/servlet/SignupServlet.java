package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.UserExistsException;

import antity.User;

import service.LoginService;
import service.SignupService;
import util.Objutil;

public class SignupServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				//��ȡ�û���Ϣ			
				if(request.getParameter("userName")!=null){
					User user = new User();
					user = (User) Objutil.copyToBean(request, User.class);//User [id=null, userName=null, passWord=null, qq=null]
					 System.out.println(user.toString()+"������");
					try {
					user = SignupService.service(user);					
					request.getSession().setAttribute("user", user);
					System.out.println("yunxing" + user.toString());										
					//����ע���user ��Session��
					//1.����session����
					HttpSession session = request.getSession();
					/**
					 * �õ�session���
					 */
					System.out.println("id="+session.getId());
					/**
					 * �޸�session����Чʱ��
					 */
					session.setMaxInactiveInterval(60*60*24*365);
					/**
					 * �ֶ�����һ��Ӳ�̱����cookie�����������ֹsession����������رն���ʧ
					 */
					Cookie c = new Cookie("JSESSIONID",session.getId());
					c.setMaxAge(60*60*24*365);
					response.addCookie(c);
					//2.����Ự������session��
					session.setAttribute("user", user);		
					
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UserExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("�쳣1" );	
						request.setAttribute("UserError", "�û�����"+user.getUserName()+" �Ѿ���ע����Ŷ!");
						request.getRequestDispatcher("/signup.jsp").forward(request, response);
					}
				} 
}

	/**
	 * 2.init����
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("2.init����������");
	}
	

	/**
	 * 4.destroy����
	 */
	@Override
	public void destroy() {
		System.out.println("4.servlet����������");
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
