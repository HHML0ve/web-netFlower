package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.IUser;
import db.DataAccess;
import entity.Account;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeServlet() {
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
		String username = request.getParameter("username");
		String tel = request.getParameter("tel");
		String newpwd = request.getParameter("newpwd");
		IUser iUser = new DataAccess().CreateUser();
		/*
		 * 判断用户手机验证码不正确
		 */
		PrintWriter out = response.getWriter();
		Account account = new Account(username, tel);
		account.setPassword(newpwd);
		boolean ischange = iUser.changePwd(account);
		if (ischange) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			;
		} else {
			out.println("请确认这个账号真的属于你？");
		}
	}

}
