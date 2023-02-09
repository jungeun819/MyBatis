package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentMapEnrollController extends AbstractController {
	
	private StudentService studentService;

	public StudentMapEnrollController(StudentService service) {
		super();
		this.studentService = service;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Map<String, Object> studentMap = new HashMap<>();
		studentMap.put("name", name);
		studentMap.put("tel", tel);
		System.out.println(studentMap);
		
		// 2. 업무로직
		int result = studentService.insertStudent(studentMap);
		
		// 3. 사용자피드백
		request.getSession().setAttribute("msg", "학생을 성공적으로 등록했습니다.");
		
		return "redirect:/student/studentEnroll.do";
	}
}
