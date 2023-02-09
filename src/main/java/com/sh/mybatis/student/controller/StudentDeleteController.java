package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentDeleteController extends AbstractController {
	
	private StudentService studentService;

	public StudentDeleteController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		int result = studentService.deleteStudent(no);
		System.out.println("학생 정보삭제 " + (result > 0 ? "성공!" : "실패ㅠ"));
		
		// 3. 응답 json형식으로 출력
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(result, response.getWriter());
		
		return null;
	}
}
