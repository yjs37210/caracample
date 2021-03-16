package kr.caravan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.caravan.model.carsDAO;
import kr.caravan.model.customerDAO;
import kr.caravan.model.customerVO;

@WebServlet("/customerInsert.do")
public class CustomerInsertController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String car_name = request.getParameter("car_name").substring(0,8);
		int male = Integer.parseInt(request.getParameter("male"));
		int female = Integer.parseInt(request.getParameter("female"));
	
		customerVO vo = new customerVO(name, tel, car_name, male, female);
		customerDAO dao = new customerDAO();
		boolean check = dao.car_name_ableselect(car_name);
		String pw = "";
		if(!check) {
			dao.customerInsert(vo);
			ArrayList<String> arr = new ArrayList<String>();
			for(int i = 2; i <= 9; i++) {
				arr.add(""+i);
			}
			for(int i = (int)'A'; i <= (int)'Z'; i++) {
				arr.add(Character.toString((char)(i+32)));
			}
			for(int i = 0; i < 8; i++) {
				Random rd = new Random();
				int rd_num = rd.nextInt(arr.size());
				pw += arr.get(rd_num);
			}
			carsDAO cars_dao = new carsDAO();
			cars_dao.pwChange(car_name,pw);
		}
		
		PrintWriter out = response.getWriter();
		out.print(pw);
	}

}
