package com.tlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tlb.beans.OrderDao;
import com.tlb.entity.Order;

/**
 * Servlet implementation class GetOrdersServlet
 */
@WebServlet("/GetOrdersServlet")
public class GetOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOrdersServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("application/json;charset=utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		OrderDao orderDao = new OrderDao();
		ArrayList<Order> orders = new ArrayList<Order>(0);
		orders = orderDao.getOrders(username);
		Gson gson = new Gson();
		String json_orders = gson.toJson(orders);
		out.write(json_orders);
		out.close();
	}

}
