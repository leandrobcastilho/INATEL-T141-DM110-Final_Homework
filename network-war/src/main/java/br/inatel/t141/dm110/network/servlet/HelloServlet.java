package br.inatel.t141.dm110.network.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 4659676789363940322L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h1>INATEL-T141-DM110-Final_Homework</h1>");
	}

}
