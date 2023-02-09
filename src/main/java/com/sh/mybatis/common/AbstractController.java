package com.sh.mybatis.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {

	/**
	 * 자식클래스에서 오버라이드 하지 않고 호출시 예외발생!
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new RuntimeException("GET 요청은 허락되지 않습니다.");
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new RuntimeException("POST 요청은 허락되지 않습니다.");
	}
}
