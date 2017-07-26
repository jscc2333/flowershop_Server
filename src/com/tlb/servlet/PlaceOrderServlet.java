package com.tlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tlb.beans.OrderDao;
import com.tlb.entity.Order;
import com.tlb.jsoninstance.StatusInstance;

/**
 * Servlet implementation class PlaceOrderServler
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int status_no = 0;
		String json_request = request.getParameter("json_request");
		Order order = gson.fromJson(json_request, Order.class);
		OrderDao orderDao = new OrderDao();
		status_no = orderDao.placeOrder(order);
		StatusInstance si = new StatusInstance(status_no);
		String json_response = gson.toJson(si);
		out.write(json_response);
		out.close();
	}

}
