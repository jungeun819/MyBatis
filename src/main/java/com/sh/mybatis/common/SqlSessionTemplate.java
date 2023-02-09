package com.sh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	/**
	 * 1.FactoryBuilder
	 * 2.Factory (설정파일)
	 * 3.SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		try {
			// 설정파일을 읽어 공장을 읽기..?
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false); // auto-commit : false 자동커밋제어
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}

}
