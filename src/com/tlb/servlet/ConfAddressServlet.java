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
import com.tlb.entity.Address;
import com.tlb.entity.Order;
import com.tlb.jsoninstance.AddressInstance;

/**
 * Servlet implementation class ConfAddressServlet
 */
@WebServlet("/ConfAddressServlet")
public class ConfAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfAddressServlet() {
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
		String test = request.getParameter("test");
		Gson gsonTemp = new Gson();
		Address address = gsonTemp.fromJson(test, Address.class);
		System.out.println(address.getAddressname() + address.getConsignee());
		int operationType = Integer.parseInt(request.getParameter("operationType"));
		switch (operationType) {
		case -1:
			OrderDao orderDao = new OrderDao();
			ArrayList<Order> orderList = orderDao.getOrders(username);
			Gson gson = new Gson();
			String json_ol = gson.toJson(orderList);
			out.write(json_ol);
			break;
		default:
			out.close();
		}
	}
}
