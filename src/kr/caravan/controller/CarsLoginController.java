package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.carsDAO;

@WebServlet("/carsLogin.do")
public class CarsLoginController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String car_name = request.getParameter("car_name");
		String pw = request.getParameter("pw");
		int admin = Integer.parseInt(request.getParameter("admin"));
		PrintWriter out = response.getWriter();

		if ((admin == 0 && car_name.equals("CaravanAdmin")) || (admin == 1 && !car_name.equals("CaravanAdmin"))) {
				out.print("inaccesible");
		}
		
		carsDAO dao = new carsDAO();
		boolean check = dao.login(car_name, pw);
		out.print(check);

	}

}
