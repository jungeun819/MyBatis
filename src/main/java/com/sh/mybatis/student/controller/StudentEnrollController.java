package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentEnrollController extends AbstractController {

	private StudentService studentService;

	public StudentEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "student/studentEnroll";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		Student student = new Student();
		student.setName(name);
		student.setTel(tel);
		System.out.println(student);
		
		// 2.업무로직
		int result = studentService.insertStudent(student);
		
		// 3.사용자 피드백전달
		HttpSession session = request.getSession();
		session.setAttribute("msg", "학생을 성공적으로 등록했습니다.");
		
		return "redirect:/student/studentEnroll.do";
	}
	
}
