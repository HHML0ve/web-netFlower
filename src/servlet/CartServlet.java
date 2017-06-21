package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsuper.CashContext;
import dao.AccountDao;
import dao.IUser;
import dao.ItemsDao;
import dao.MinxiDao;
import entity.Account;
import entity.Cart;
import entity.Items;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;

	private ItemsDao idao = new ItemsDao();

	private static ArrayList<Integer> cartItemID = new ArrayList<Integer>(1024 * 10);
	private static ArrayList<Integer> cartItemNumber = new ArrayList<Integer>(1024 * 10);
	private static Map<Integer, Integer> cartItem = new HashMap<Integer, Integer>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// // TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("action") != null) {
			this.action = request.getParameter("action");
			if (action.equals("add")) {
				if (addToCart(request, response)) {
					request.getRequestDispatcher("/add_success.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			if (action.equals("show")) {
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			if (action.equals("delete")) {
				deleteFromCart(request, response);
				request.getRequestDispatcher("/cart.jsp").forward(request, response);

			}
			if (action.equals("buy")) {
				buyDoodsServlet(request, response);
				request.getRequestDispatcher("/success.jsp").forward(request, response);
			}
		}
	}

	private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
		String tempid = request.getParameter("id");
		int id = Integer.parseInt(tempid);
		String number = request.getParameter("num");
		int num = Integer.parseInt(number);
		Items item = idao.getItemsById(id);
		cartItem.put(id, num);
		if (request.getSession().getAttribute("cart") == null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart.addGoodsInCart(item, Integer.parseInt(number))) {
			return true;
		} else {
			return false;
		}

	}

	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
		String tempid = request.getParameter("id");
		int id = Integer.parseInt(tempid);
		Items item = idao.getItemsById(id);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cartItem.remove(id);
		if (cart.removeGoodsFromCart(item)) {
			return true;
		} else {
			return false;
		}
	}

	private void buyDoodsServlet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			String type = request.getParameter("vip");
			if (type.equals("1012896")) {
				type = "rebate";
			} else if (type.equals("2012896")) {
				type = "return";
			} else {
				type = "normal";
			}
			CashContext context = new CashContext(type);
			float tmpMoney = (float) cart.getTotalPrice();
			float totalmoney = context.GetResult(tmpMoney);
			HttpSession buysession = request.getSession();
			AccountDao aDao = new AccountDao();
			Account account = (Account) buysession.getAttribute("user");
			int accountid = account.getId();
			String payWay = request.getParameter("payWay");
			PrintWriter out = response.getWriter();
			if (payWay.equals("balance")) {
				if (cart.getTotalPrice() > account.getMoney()) {
					out.println("余额不足，请换种方式支付");
				} else {
					MinxiDao mDao = new MinxiDao();
					mDao.buyGoods(accountid, cartItem, totalmoney);
					/***/
					System.out.println("payWay： " + payWay);
					System.out.println("totalMoney： " + totalmoney);
					System.out.println("ID： " + accountid);
					Iterator<Entry<Integer, Integer>> iter = cartItem.entrySet().iterator();
					while (iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry entry = (Map.Entry) iter.next();
						System.out.print("key" + entry.getKey() + "  ");
						System.out.print("value" + entry.getValue() + "  ");
						System.out.println((Integer) entry.getKey() + (Integer) entry.getValue());
					}
					/***/
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
