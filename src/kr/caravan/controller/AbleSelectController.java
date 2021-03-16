package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.customerDAO;

@WebServlet("/ableSelect.do")
public class AbleSelectController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		customerDAO dao = new customerDAO();
		String json = dao.ableselect();

		PrintWriter out = response.getWriter();
		out.print(json);

	}

}
