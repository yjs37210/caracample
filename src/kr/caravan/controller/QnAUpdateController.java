package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.consultDAO;

@WebServlet("/qnAUpdate.do")
public class QnAUpdateController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int num = Integer.parseInt(request.getParameter("num").split("¹ø")[0]);
		String time = request.getParameter("time");
		
		consultDAO dao = new consultDAO();
		dao.consultUpdate(num, time);
		int cnt = dao.consultSelect(num);
		
		PrintWriter out = response.getWriter();
		out.print(cnt);
	}

}
