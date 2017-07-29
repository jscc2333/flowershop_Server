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
import com.tlb.entity.Category;
import com.tlb.entity.Tip;
import com.tlb.jsoninstance.StatusInstance;

/**
 * Servlet implementation class TipManageServlet
 */
@WebServlet("/TipManageServlet")
public class TipManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TipManageServlet() {
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
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		TipDao tipDao = new TipDao();
		ArrayList<Tip> tips = tipDao.getTips();
		Gson gson = new Gson();
		out.write(gson.toJson(tips));
		out.close();
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
		PrintWriter out = response.getWriter();
		int status_no = Integer.parseInt(request.getParameter("statusNo"));
		int tipID = Integer.parseInt(request.getParameter("tipID"));
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String imgsrc = request.getParameter("imgsrc");
		StatusInstance si = null;
		Tip tip = new Tip(tipID, title, text, imgsrc);
		TipDao tipDao = new TipDao();
		if (status_no == 0) {
			si = new StatusInstance(tipDao.insertTip(tip));
		} else {
			si = new StatusInstance(tipDao.deleteTip(tip));
		}
		Gson gson = new Gson();
		out.write(gson.toJson(si));
	}

}
