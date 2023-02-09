package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentOneMapController extends AbstractController {

	private StudentService studentService;

	public StudentOneMapController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no : " + no);
		
		// 2. 업무로직
		Map<String, Object> studentMap = studentService.selectOneStudentMap(no);
		System.out.println("studentMap : " + studentMap);
		
		// 3.json으로 응답
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(studentMap, response.getWriter());
		
		return null;
	}
}
