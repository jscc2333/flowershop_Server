package com.tlb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.entity.User;
import com.tlb.jsoninstance.HomeInstance;
import com.google.gson.Gson;
import com.tlb.beans.CategoryDao;
import com.tlb.beans.DBUtils;
import com.tlb.beans.FlowerDao;
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
		int status_no = userDao.signIn(user);
		CategoryDao categoryDao = new CategoryDao();
		FlowerDao flowerDao = new FlowerDao();
		ArrayList<Category> categories = categoryDao.getCategory();
		ArrayList<Flower> flowers = flowerDao.getFlowers();
		HomeInstance hi = new HomeInstance(status_no,categories,flowers);
		Gson gson = new Gson();
		String json_hi = gson.toJson(hi);
		out.write(json_hi);
		out.close();
	}

}
