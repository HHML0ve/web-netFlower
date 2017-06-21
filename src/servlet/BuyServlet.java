package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import dao.MinxiDao;
import entity.Account;
import entity.Cart;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("user");
		int accountid = account.getId();
		int itemsid = Integer.parseInt(request.getParameter("id"));
		Float price = Float.parseFloat(request.getParameter("price"));
		int number = Integer.parseInt(request.getParameter("number"));
		String payWay = request.getParameter("payWay");
		PrintWriter out = response.getWriter();
		float totalMoney = price * number;
		if (payWay.equals("balance")) {
			if (totalMoney > account.getMoney()) {
				out.println("余额不足，请换种支付方式");
			} else {
				MinxiDao mDao = new MinxiDao();
				try {
					mDao.buyGood(accountid, itemsid, number, price);
					mDao.insertMinXi(accountid, itemsid, number, price);
					request.getRequestDispatcher("success.jsp").forward(request, response);
				} catch (SQLException e) {
					throw new RuntimeException("购买出错");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
