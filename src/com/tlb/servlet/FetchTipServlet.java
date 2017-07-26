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
import com.tlb.beans.TipDao;
import com.tlb.entity.Tip;
import com.tlb.global.Global;
import com.tlb.jsoninstance.StatusInstance;
import com.tlb.jsoninstance.TipInstance;

/**
 * Servlet implementation class FetchTipServlet
 */
@WebServlet("/FetchTipServlet")
public class FetchTipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchTipServlet() {
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
		System.out.println(json_request);
		TipInstance ti = gson.fromJson(json_request, TipInstance.class);
		if (ti.getStatus_no() == Global.STATUS_OK) {
			TipDao tipDao = new TipDao();
			ArrayList<Tip> tipList = tipDao.getTips();
			TipInstance tiTemp = null;
			if (tipList.size() > 0) {
				tiTemp = new TipInstance(Global.STATUS_OK, tipList);
			} else {
				tiTemp = new TipInstance(Global.STATUS_ERR, null);
			}
			String json_response = gson.toJson(tiTemp);
			out.write(json_response);
			out.close();
		}
	}
}
