package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import dao.IUser;
import dao.MysqlUserImpl;
import db.DataAccess;
import entity.Account;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String checkCode = request.getParameter("checkCode");
		System.out.println(checkCode);
		HttpSession session = request.getSession();
		String ranStr = (String) session.getAttribute("randCheckCode");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(!checkCode.equals(ranStr)){
			out.println("error");
		}else{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
			Account user = new Account(name,password,sex,tel,0.0,address);
			IUser iUser = new DataAccess().CreateUser();
			boolean isregister = iUser.insertAccount(user);
			if(isregister){
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				out.println("error");
			}
		}
		
	}

}
