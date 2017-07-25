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
import com.tlb.entity.Address;
import com.tlb.entity.Order;
import com.tlb.global.Global;
import com.tlb.jsoninstance.AddressInstance;
import com.tlb.jsoninstance.StatusInstance;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String json_address = request.getParameter("address");
		int operationType = Integer.parseInt(request.getParameter("operationType"));
		Gson gson = new Gson();
		if (operationType == -1) {
			// 获取个人收货信息
			AddressDao addressDao = new AddressDao();
			ArrayList<Address> addressList = addressDao.getAddress(username);
			String json_ol = gson.toJson(addressList);
			out.write(json_ol);
		} else {
			// 配置个人收货信息
			AddressDao addressDao = new AddressDao();
			Address address = gson.fromJson(json_address, Address.class);
			int status_no = addressDao.configAddress(username, address, operationType);
			StatusInstance si = new StatusInstance(status_no);
			String json_si = gson.toJson(si);
			out.write(json_si);
		}
		out.close();
	}
}
