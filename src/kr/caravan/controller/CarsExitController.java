package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.customerDAO;
import kr.caravan.model.reviewDAO;

@WebServlet("/carsExit.do")
public class CarsExitController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String car_name = request.getParameter("car_name").substring(0, 8);
		int score = Integer.parseInt(request.getParameter("score"));
		String cmt_review = request.getParameter("cmt_review");

		customerDAO cus_dao = new customerDAO();
		int num = cus_dao.numSelect(car_name);

		reviewDAO review_dao = new reviewDAO();
		review_dao.reviewInsert(num, score, cmt_review);

		int cnt = cus_dao.ableUpdate(num);

		PrintWriter out = response.getWriter();
		out.print(cnt);
	}

}
