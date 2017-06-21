package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCode
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String checkCode = request.getParameter("checkCode");
		System.out.println(checkCode);
		HttpSession session = request.getSession();
		String ranStr = (String) session.getAttribute("randCheckCode");
		System.out.println(ranStr);
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		if (!checkCode.equals(ranStr)) {
			out.println("error");
		} else {
			out.println("seccess");
		}
		/*
		 * String name = request.getParameter("name"); String password =
		 * request.getParameter("password"); String sex =
		 * request.getParameter("sex"); String tel =
		 * request.getParameter("tel"); String address =
		 * request.getParameter("address");
		 */

	}

	public static char getRandomChar() {
		String str = "";
		int hightPos;
		int lowPos;

		Random random = new Random();

		hightPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = (161 + Math.abs(random.nextInt(93)));

		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(hightPos)).byteValue();
		b[1] = (Integer.valueOf(lowPos)).byteValue();

		try {
			str = new String(b, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str.charAt(0);
	}

	public void service(HttpServletRequest req, HttpServletResponse resp) {
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "No-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		int width = 166, height = 45;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.getGraphics();
		Random random = new Random();
		Font mFont = new Font("Microsoft Yahei", Font.ITALIC, 26);
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(mFont);
		String sRand = "";
		String ctmp = "";
		int itemp = 0;
		/*
		 * for(int i = 0;i<4;i++){ String[] rBase =
		 * {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
		 * int r1 = random.nextInt(3)+11; String str_r1 = rBase[r1]; int r2;
		 * if(r1 == 13){ r2 = random.nextInt(7); }else{ r2 = random.nextInt(16);
		 * } String str_r2 = rBase[r2]; int r3 = random.nextInt(6)+10; String
		 * str_r3 = rBase[r3]; int r4; if(r3 == 10){ r4 = random.nextInt(15);
		 * }else if(r3 == 15){ r4 = random.nextInt(15); }else{ r4 =
		 * random.nextInt(16); } String str_r4 = rBase[r4]; byte[] bytes = new
		 * byte[2]; String str_r12 = str_r1+str_r2; int tempLow =
		 * Integer.parseInt(str_r12,16); bytes[0] = (byte) tempLow; String
		 * str_r34 = str_r3+str_r4; int tempHigh = Integer.parseInt(str_r34,16);
		 * bytes[1] = (byte) tempHigh; ctmp = new String(bytes); sRand += ctmp;
		 * Color color = new Color(20+random.nextInt(110));
		 * graphics.setColor(color); graphics.drawString(ctmp, width/6*i+23,
		 * height/2); }
		 */
		char[] words = new char[4];
		for (int i = 0; i < 4; i++) {
			sRand += getRandomChar();
		}
		Color color = new Color(20 + random.nextInt(110));
		graphics.setColor(color);
		graphics.drawString(sRand, width / 6, height / 2);
		HttpSession session = req.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		graphics.dispose();
		try {
			ImageIO.write(image, "JPEG", resp.getOutputStream());
		} catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException("验证码出错");
		}
	}

}
