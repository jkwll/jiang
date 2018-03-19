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
	//使用了BeanUtil封装
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//验证用户信息
		/*String name = request.getParameter("UserName");
		String pwd = request.getParameter("PassWord");
		user.setUserName(name);
		user.setPassWord(pwd);*/
		User user = new User();
		user = (User) Objutil.copyToBean(request, User.class);
		 System.out.println(user.getUserName());
		try {
			user = LoginService.service(user);
			
			if(user!=null){//验证成功,这里应该有高并发的问题要用 synchronized
					//保存登录的user 到Session中
					//1.创建session对象
					HttpSession session = request.getSession();
					/**
					 * 得到session编号
					 */
					System.out.println("id="+session.getId());
					/**
					 * 修改session的有效时间
					 */
					session.setMaxInactiveInterval(60*60*24*365);
					/**
					 * 手动发送一个硬盘保存的cookie给浏览器，防止session随着浏览器关闭而丢失
					 */
					Cookie c = new Cookie("JSESSIONID",session.getId());
					c.setMaxAge(60*60*24*365);
					response.addCookie(c);
	
					//2.保存会话数据在session中
					session.setAttribute("user", user);

					System.out.print("dd"+user);
					//转发
					//this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					//重定向
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else {
				request.setAttribute("error", "用户名或者密码输入错误,请重新输入");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	response.sendRedirect("/Login/密码错误.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
