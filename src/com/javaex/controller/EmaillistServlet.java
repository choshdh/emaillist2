package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDAO;
import com.javaex.vo.EmailVO;


@WebServlet("/el")
public class EmaillistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get 방식 servlet 사용");
		
		String actionName = request.getParameter("a");
		if(actionName.equals("form")) {
			System.out.println("form 진입");
			RequestDispatcher rd = request.getRequestDispatcher("form.jsp"); //현재 받아온 request 요청값을 form.jsp 에 넘기겠다
			rd.forward(request, response);
		}else if(actionName.equals("insert")) {
			System.out.println("insert 진입");
			String lastName = request.getParameter("ln");
			String firstName = request.getParameter("fn");
			String email = request.getParameter("email");
			
			EmailVO vo = new EmailVO();
			vo.setLastName(lastName);
			vo.setFirstName(firstName);
			vo.setEmail(email);
			
			System.out.println(vo.toString());
			
			EmaillistDAO dao = new EmaillistDAO();
			dao.insert(vo);
			
			response.sendRedirect("el?a=list");
			
		}else if(actionName.equals("list")) {
			System.out.println("list 진입");
			
			EmaillistDAO dao = new EmaillistDAO();
			List<EmailVO> l = dao.getList();
			
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			request.setAttribute("l", l);
			rd.forward(request, response);
		}else {
			System.out.println("잘못된 입력입니다.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post 방식 servlet 사용");
	}

}
