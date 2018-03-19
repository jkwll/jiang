package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antity.Fruit;
import antity.User;
import service.AdminService;
import service.FruitService;
import service.UserService;
import util.Objutil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fruit fruit = new Fruit();
		User user = new User();
		//获取session中的User对象
		System.out.println("zheli 000");
		HttpSession session = request.getSession(false);
		if(session != null&&request.getParameter("userName")==null){
			user = (User)session.getAttribute("user");
			System.out.println("zheli 789");
		}else {	//看看request中有没有USER
			System.out.println("zheli 456");
			user =(User) Objutil.copyToBean(request, User.class);
		}
		List<User> list2 = null;
		try {
			list2 = AdminService.service(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//判断是否验证成功
		if(list2==null){
			request.setAttribute("PointOut", "密码错le!");
			request.getRequestDispatcher("/system/root.jsp").forward(request, response);
			return;
		}
			System.out.println("id="+session.getId());
			session.setMaxInactiveInterval(60*60*24*365);
			Cookie c = new Cookie("JSESSIONID",session.getId());
			c.setMaxAge(60*60*24*365);
			response.addCookie(c);
			session.setAttribute("user", user);
			//这里解决了空指针异常
			String flag = "";
			if(request.getParameter("flag")!=null){
				flag=request.getParameter("flag");
			}
			
			if(flag.equals("users")){
				List<User> list =  new ArrayList<User>();
				try {
				list = AdminService.service(user);
				} catch (SQLException e) {
					System.out.println("运行了这里102");
					e.printStackTrace();
				}				
				if(list!=null){
					//1.保存session
					request.getSession().setAttribute("user", user);
					//2.把结果保存到域对象中
					request.setAttribute("ListUser", list);
				}	
				//3.跳转到jsp页面
				request.getRequestDispatcher("/system/everyone.jsp").forward(request, response);
	
				
			}else if(flag.equals("showfilter")){
					List<Fruit> list1=null;
					try {
						//获取所有中奖信息
						list1 = FruitService.show();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(list1!=null){
						//2.把结果保存到域对象中
						request.setAttribute("ListFruit", list1);
						//3.跳转到jsp页面
						request.getRequestDispatcher("system/fruit.jsp").forward(request, response);
					} 
			}else if(flag.equals("userflag")){
				String id = request.getParameter("id");
				user.setId(id);
				
				try {
					UserService.Saveflag(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("AdminServlet?flag=users").forward(request, response);
			}else if(flag.equals("show")){
				String id = request.getParameter("id");
				user.setId(id);
				try {
					List<Fruit> list=null;
					list = FruitService.ShowById(user);
					//2.把结果保存到域对象中
					request.setAttribute("ListFruit", list);
					request.getRequestDispatcher("system/fruit.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
