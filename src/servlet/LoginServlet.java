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
import db.DataAccess;
import entity.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String checkCode = request.getParameter("checkCode");
		HttpSession session = request.getSession();
		String ranStr = (String) session.getAttribute("randCheckCode");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (!checkCode.equals(ranStr)) {
			out.println("error");
		} else {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			IUser iUser = new DataAccess().CreateUser();
			boolean islogin = iUser.islogin(name, password);
			if (islogin) {
				session.setAttribute("userName", name);
				Account account = iUser.getAccountByNameandPwd(name, password);
				session.setAttribute("user", account);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				out.println("login_error");
			}
		}
	}
}
