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
import com.tlb.beans.AddressDao;
import com.tlb.beans.OrderDao;
import com.tlb.entity.Order;
import com.tlb.global.Global;
import com.tlb.jsoninstance.OrderInstance;

/**
 * Servlet implementation class FetchOrderServlet
 */
@WebServlet("/FetchOrderServlet")
public class FetchOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchOrderServlet() {
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
		String json_request = request.getParameter("json_request");
		OrderInstance oi = gson.fromJson(json_request, OrderInstance.class);
		if (oi.getStatus_no() == Global.STATUS_OK) {
			OrderInstance oiTemp = null;
			OrderDao orderDao = new OrderDao();
			ArrayList<Order> orderList = orderDao.getOrders(oi.getUsername());
			if (orderList != null) {
				oiTemp = new OrderInstance(Global.STATUS_OK, oi.getUsername(), orderList);
			} else {
				oiTemp = new OrderInstance(Global.STATUS_ERR, oi.getUsername(), null);
			}
			String json_response = gson.toJson(oiTemp);
			out.write(json_response);
			out.close();
		}
	}
}
