package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antity.Feedback;
import antity.Fruit;
import antity.User;
import net.sf.json.JSONObject;
import service.UserService;
import util.Objutil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<Fruit> Fruit = null;
       
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		//��ȡsession��user
		HttpSession session = request.getSession(false);		
		if(session != null&&request.getParameter("userName")==null){
			user = (User)session.getAttribute("user");
		}else{	
		return ;
		}
		String flag = request.getParameter("flag");
		//System.out.println(user.toString());
		if(flag.equals("user")){
			try {
				user = UserService.Findinfo(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(user.toString());
			if(user!=null){
				//2.�ѽ�����浽�������
				request.setAttribute("User", user);
				System.out.println(user.toString());
				//3.��ת��jspҳ��
				request.getRequestDispatcher("pageinfo.jsp").forward(request, response);
			}
		}else if(flag.equals("fruit")){
			List<Fruit> list =new ArrayList<Fruit>();
			System.out.println("a3");

			try {
				System.out.println("a2");

			 list= UserService.FindFruitByUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(list!=null){
				System.out.println("a1");
				//2.�ѽ�����浽�������
				request.setAttribute("ListFruit", list);
				//3.��ת��jspҳ��
				request.getRequestDispatcher("pagefruit.jsp").forward(request, response);
			}else{
				System.out.println("list="+list);
			}
		}else if(flag.equals("feedback")){
			Feedback feedback = new Feedback();
			
			feedback =(Feedback) Objutil.copyToBean(request,feedback.getClass());
			feedback.setUserName(user.getUserName());
		
			 try {
				 System.out.println("�û����ǣ�"+feedback.getUserName());
				UserService.SaveFeedback(feedback);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 request.setAttribute("v", "1");
			 request.getRequestDispatcher("pagefeedback.jsp").forward(request, response); 
		}else if(flag.equals("trueORfalse")){
			//b��ʾ�����Ƿ��Ѿ��齱��
			boolean b = false;
			try {
				b = UserService.FindFruitByUser1(user);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("b=="+b);
			//a��ʾqq�����Ƿ����5
			boolean a = false;
			try {
				user = UserService.FindUserByUserName(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user.getQq().length()>5){//�����������û���һ�γ齱��һ������
				a= true;
			}
			//System.out.println("a=="+a);
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("flag2", a).accumulate("isExist", b);
			response.setContentType("application/json");
		    response.getWriter().write(jsonObject.toString());
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
