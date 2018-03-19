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
import service.FruitService;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
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
				//这里解决了空指针异常
				String flag = "";
				if(request.getParameter("flag")!=null){
					flag=request.getParameter("flag");
				}
				if(flag.equals("2048")){
					
					//3.跳转到jsp页面
					request.getRequestDispatcher("/system/fruit.jsp").forward(request, response);
				}
				
				//获取ajax 传来的抽奖结果
				String record = request.getParameter("record");
//				System.out.println(flag + "  = " + record);
				System.out.println(flag + "  = " + record);
		/*		//设置中奖者的用户名
				fruit.setUserName(user.getUserName());
				try {
					//保存抽奖结果到数据库
					FruitService.service(fruit);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
