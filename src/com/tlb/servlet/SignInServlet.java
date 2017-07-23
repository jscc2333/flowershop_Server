package com.tlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.entity.Order;
import com.tlb.entity.Tip;
import com.tlb.entity.User;
import com.tlb.jsoninstance.HomeInstance;
import com.google.gson.Gson;
import com.tlb.beans.CategoryDao;
import com.tlb.beans.DBUtils;
import com.tlb.beans.FlowerDao;
import com.tlb.beans.OrderDao;
import com.tlb.beans.TipDao;
import com.tlb.beans.UserDao;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		// doPost(request,response);
		// System.out.print("doget");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setContentType("application/json;charset=utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("userpass");
		User user = new User(username, password);
		UserDao userDao = new UserDao();
		OrderDao orderDao = new OrderDao();
		TipDao tipDao = new TipDao();
		Gson gson = new Gson();
		int status_no = userDao.signIn(user);
		HomeInstance hi;
		if (status_no == 1) {
			CategoryDao categoryDao = new CategoryDao();
			FlowerDao flowerDao = new FlowerDao();
			ArrayList<Category> categories = categoryDao.getCategory();
			ArrayList<Flower> flowers = flowerDao.getFlowers();
			ArrayList<Order> orders = orderDao.getOrders(username);
			ArrayList<Tip> tips = tipDao.getTips();
			hi = new HomeInstance(status_no, categories, flowers, orders, tips);
		} else {
			hi = new HomeInstance(status_no, null, null, null, null);
		}
		String json_hi = gson.toJson(hi);
		out.write(json_hi);
		out.close();
	}

}
