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
		Gson gson = new Gson();
		String json_request = request.getParameter("json_request");
		AddressInstance ai = (AddressInstance) gson.fromJson(json_request, AddressInstance.class);
		String username = ai.getUsername();
		Address address = ai.getAddress();
		if (ai.getStatus_no() == -1) {
			// 获取个人收货信息
			AddressDao addressDao = new AddressDao();
			ArrayList<Address> addressList = addressDao.getAddress(ai.getUsername());
			AddressInstance aiTemp = new AddressInstance(Global.STATUS_OK, null, null, addressList);
			String json_response = gson.toJson(aiTemp);
			out.write(json_response);
		} else {
			// 配置个人收货信息
			System.out.println(json_request);
			AddressDao addressDao = new AddressDao();
			AddressInstance aiTemp = addressDao.configAddress(username, address, ai.getStatus_no());
			String json_response = gson.toJson(aiTemp);
			out.write(json_response);
		}
		out.close();
	}
}
