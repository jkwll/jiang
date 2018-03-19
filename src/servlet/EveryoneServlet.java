package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antity.Fruit;
import antity.User;

import service.AdminService;
import service.FruitService;
import util.Objutil;

public class EveryoneServlet extends HttpServlet {

	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				List<User> list=null;
				User user = new User();
				//��ȡsession�е�User����
				HttpSession session = request.getSession(false);
				if(session != null&&request.getParameter("userName")==null){
					user = (User)session.getAttribute("user");
					System.out.println("�û�����"+user.getUserName()+"   ��1�룺"+user.getPassWord());
				}else{	//����request����û��USER
						user =(User) Objutil.copyToBean(request, User.class);	
				}	
				try {
					
					list = AdminService.service(user);
				} catch (SQLException e) {
					System.out.println("����������102");
					e.printStackTrace();
				}				
				if(list!=null){
					//1.����session
					System.out.println("������list");
					request.getSession().setAttribute("user", user);
					//1request.setAttribute(arg0, arg1);
					//2.�ѽ�����浽�������
					request.setAttribute("ListUser", list);
					//3.��ת��jspҳ��
					request.getRequestDispatcher("/system/everyone.jsp").forward(request, response);

				} 
		
		}
		

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
