package com.sh.mybatis.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.student.controller.StudentSelectListController;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dao.StudentDaoImpl;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 요청 url과 이를 처리할 controller 객체를 매핑(연결함)
	 * 
	 * - /student/selectList.do -> StudentSelectListController
	 * - /student/selectOne.do -> StudentSelectOneController
	 * - ...
	 * 
	 * 모든 컨트롤러는 AbstractController의 자식클래스로, AbstractController타입으로 제어될 것.(다형성)
	 * commanMap이란 컴퓨터 명령어때문에 생김. 정보를 연결해줌..?
	 */
	private Map<String, AbstractController> commandMap = new HashMap<>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        // properties를 읽어옴
    	// 1. command-map.properties -> prop
    	Properties prop = new Properties();
    	String filepath = DispatcherServlet.class.getResource("/command-map.properties").getPath();
    	try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// 2. prop -> commandMap (reflection api)
    	StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    	Set<String> proNames = prop.stringPropertyNames();
    	try {
    		
    		for(String url : proNames) {
        		String className = prop.getProperty(url);
        		// ?는 object와 유사. 어떤 타입이든 들어올 수 있음
        		Class<?> clz = Class.forName(className);
        		Constructor<?> constructor = clz.getDeclaredConstructor(StudentService.class); // 생성자객체
        		AbstractController controller = (AbstractController)constructor.newInstance(studentService); // new XXXController()
//        		AbstractController controller = new StudentSelectListController();
        		commandMap.put(url, controller);
        	}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("commandMap : " + commandMap);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청주소 가져오기
		String url = request.getRequestURI(); // /mybatis/student/selectList.do
		url = url.replace(request.getContextPath(), ""); // /student/selectList.do
		
		// 2. commandMap에서 해당 controller 가져오기
		AbstractController controller = commandMap.get(url);
		if(controller == null) {
			// 404 에러처리
			response.sendError(HttpServletResponse.SC_NOT_FOUND, url);
			return;
		}
		
		// 3. 전송방식에 따라 doGet 또는 doPost 호출
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default : throw new RuntimeException("허용되지 않은 전송방식 - " + method);
		}
		
		// 4. 응답처리 forwarding | redirect | bypass(controller에서 응답직접 출력)
		if(viewName != null) {
			// redirect : redirect:/
			if(viewName.startsWith("redirect:")){
				response.sendRedirect(request.getContextPath() + viewName.replace("redirect:", "")); // /mybatis/
			}
			// forward : student/selectList
			else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				viewName = prefix + viewName +suffix;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
