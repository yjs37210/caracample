package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.consultDAO;
import kr.caravan.model.customerDAO;

@WebServlet("/consultInsert.do")
public class ConsultInsertController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String car_name = request.getParameter("car_name").substring(0, 8);
		String cmt = request.getParameter("cmt");

		customerDAO cus_dao = new customerDAO();
		int num = cus_dao.numSelect(car_name);

		consultDAO dao = new consultDAO();
		int cnt = dao.consultInsert(num, cmt);

		PrintWriter out = response.getWriter();
		out.print(cnt);

	}

}
