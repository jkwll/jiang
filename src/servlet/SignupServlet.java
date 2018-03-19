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
				//获取用户信息			
				if(request.getParameter("userName")!=null){
					User user = new User();
					user = (User) Objutil.copyToBean(request, User.class);//User [id=null, userName=null, passWord=null, qq=null]
					 System.out.println(user.toString()+"啊啊啊");
					try {
					user = SignupService.service(user);					
					request.getSession().setAttribute("user", user);
					System.out.println("yunxing" + user.toString());										
					//保存注册的user 到Session中
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
						System.out.println("异常1" );	
						request.setAttribute("UserError", "用户名："+user.getUserName()+" 已经被注册了哦!");
						request.getRequestDispatcher("/signup.jsp").forward(request, response);
					}
				} 
}

	/**
	 * 2.init方法
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("2.init方法被调用");
	}
	

	/**
	 * 4.destroy方法
	 */
	@Override
	public void destroy() {
		System.out.println("4.servlet对象销毁了");
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
