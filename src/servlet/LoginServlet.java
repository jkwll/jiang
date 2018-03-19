package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antity.User;

import service.LoginService;
import util.Objutil;


public class LoginServlet extends HttpServlet {
	//ʹ����BeanUtil��װ
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��֤�û���Ϣ
		/*String name = request.getParameter("UserName");
		String pwd = request.getParameter("PassWord");
		user.setUserName(name);
		user.setPassWord(pwd);*/
		User user = new User();
		user = (User) Objutil.copyToBean(request, User.class);
		 System.out.println(user.getUserName());
		try {
			user = LoginService.service(user);
			
			if(user!=null){//��֤�ɹ�,����Ӧ���и߲���������Ҫ�� synchronized
					//�����¼��user ��Session��
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

					System.out.print("dd"+user);
					//ת��
					//this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					//�ض���
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else {
				request.setAttribute("error", "�û������������������,����������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	response.sendRedirect("/Login/�������.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
