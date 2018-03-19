package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antity.Fruit;
import antity.User;
import mail.Mail;
import service.FruitService;

/**
 * Servlet implementation class FruitsServlet
 */
@WebServlet("/FruitServlet")
public class FruitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FruitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户名   时间属性在dao中直接生成		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		Fruit fruit = new Fruit();
		System.out.println("123");
		//这里解决了空指针异常
		String flag = "";
		if(request.getParameter("flag")!=null){
			flag=request.getParameter("flag");
		}
		System.out.println("flag="+flag);
		if(flag.equals("testchou")&&user.getUserName()!=null){
			//获取ajax 传来的抽奖结果
			String award = request.getParameter("award");
			System.out.println(flag + "   " + award);
			fruit.setAward(award);
			//设置中奖者的用户名
			fruit.setUserName(user.getUserName()+"--test");
			try {
				//保存抽奖结果到数据库
				FruitService.service(fruit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		else if(flag.equals("chou")&&user.getUserName()!=null){
			//获取ajax 传来的抽奖结果,已经设置了一定中间
			String award = request.getParameter("award");
			System.out.println(flag + "   " + award);
			fruit.setAward(award);
			//设置中奖者的用户名
			fruit.setUserName(user.getUserName());
			
			try {
				//有人抽奖后，自动发送邮件到1622913887@qq.com
				Mail.SendEmails(user.getQq());
				//保存抽奖结果到数据库
				FruitService.service(fruit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//2.把结果保存到域对象中
			request.setAttribute("PointOut", "密码错误!");
			request.getRequestDispatcher("/root.jsp").forward(request, response);
		}		

		//System.out.println("username="+user.getUserName());
		//System.out.println( "   fruit = " + fruit);	
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
