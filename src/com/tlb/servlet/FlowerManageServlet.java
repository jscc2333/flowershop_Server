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
import com.mysql.fabric.xmlrpc.base.Data;
import com.tlb.beans.FlowerDao;
import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.jsoninstance.StatusInstance;

/**
 * Servlet implementation class FlowerManageServlet
 */
@WebServlet("/FlowerManageServlet")
public class FlowerManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlowerManageServlet() {
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
		FlowerDao flowerDao = new FlowerDao();
		ArrayList<Flower> flowers = flowerDao.getFlowers();
		Gson gson = new Gson();
		out.write(gson.toJson(flowers));
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
		int flowerID = Integer.parseInt(request.getParameter("flowerID"));
		int categoryID = Integer.parseInt(request.getParameter("categoryID"));
		String flowerName = request.getParameter("flowerName");
		String flowerDesc = request.getParameter("flowerDesc");
		String flowerImg = request.getParameter("flowerImg");
		int flowerTotal = Integer.parseInt(request.getParameter("flowerTotal"));
		float flowerPrice = Float.parseFloat(request.getParameter("flowerPrice"));
		Flower flower = new Flower(flowerID, flowerName, flowerDesc, flowerImg, flowerTotal, flowerPrice,
				new Category(categoryID));
		FlowerDao flowerDao = new FlowerDao();
		StatusInstance si = null;
		if (status_no == 0) {
			si = new StatusInstance(flowerDao.insertFlowers(flower));
		} else if (status_no == 1) {
			si = new StatusInstance(flowerDao.updateFlower(flower));
		} else {
			si = new StatusInstance(flowerDao.deleteFlower(flower));
		}
		Gson gson = new Gson();
		out.write(gson.toJson(si));
	}
}
