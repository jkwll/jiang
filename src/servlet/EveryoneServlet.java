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
				//获取session中的User对象
				HttpSession session = request.getSession(false);
				if(session != null&&request.getParameter("userName")==null){
					user = (User)session.getAttribute("user");
					System.out.println("用户名："+user.getUserName()+"   密1码："+user.getPassWord());
				}else{	//看看request中有没有USER
						user =(User) Objutil.copyToBean(request, User.class);	
				}	
				try {
					
					list = AdminService.service(user);
				} catch (SQLException e) {
					System.out.println("运行了这里102");
					e.printStackTrace();
				}				
				if(list!=null){
					//1.保存session
					System.out.println("设置了list");
					request.getSession().setAttribute("user", user);
					//1request.setAttribute(arg0, arg1);
					//2.把结果保存到域对象中
					request.setAttribute("ListUser", list);
					//3.跳转到jsp页面
					request.getRequestDispatcher("/system/everyone.jsp").forward(request, response);

				} 
		
		}
		

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
