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
		//��ȡ��ǰ�û���   ʱ��������dao��ֱ������		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		Fruit fruit = new Fruit();
		System.out.println("123");
		//�������˿�ָ���쳣
		String flag = "";
		if(request.getParameter("flag")!=null){
			flag=request.getParameter("flag");
		}
		System.out.println("flag="+flag);
		if(flag.equals("testchou")&&user.getUserName()!=null){
			//��ȡajax �����ĳ齱���
			String award = request.getParameter("award");
			System.out.println(flag + "   " + award);
			fruit.setAward(award);
			//�����н��ߵ��û���
			fruit.setUserName(user.getUserName()+"--test");
			try {
				//����齱��������ݿ�
				FruitService.service(fruit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		else if(flag.equals("chou")&&user.getUserName()!=null){
			//��ȡajax �����ĳ齱���,�Ѿ�������һ���м�
			String award = request.getParameter("award");
			System.out.println(flag + "   " + award);
			fruit.setAward(award);
			//�����н��ߵ��û���
			fruit.setUserName(user.getUserName());
			
			try {
				//���˳齱���Զ������ʼ���1622913887@qq.com
				Mail.SendEmails(user.getQq());
				//����齱��������ݿ�
				FruitService.service(fruit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//2.�ѽ�����浽�������
			request.setAttribute("PointOut", "�������!");
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
