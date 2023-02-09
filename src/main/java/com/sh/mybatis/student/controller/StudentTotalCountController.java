package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentTotalCountController extends AbstractController {
	
	private StudentService studentService;

	public StudentTotalCountController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직 - 총학생수 조회
		int totalCount = studentService.getTotalCount();
		System.out.println("totalCount = " + totalCount);
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		
		// 2. 응답 json형식으로 출력
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
		
		return null;
	}
	
}
