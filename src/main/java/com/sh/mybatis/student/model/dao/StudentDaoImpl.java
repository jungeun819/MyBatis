package com.sh.mybatis.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession session, Student student) {
		// session.insert(statement:String, parameter:Object)
		// statement - mapper파일의 namespace.id(쿼리태그의 아이디값)
		// parameter - 0 / 1개
		return session.insert("student.insertStudent", student);
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudent", studentMap);
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("student.getTotalCount");
	}

	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent", no);
	}

	@Override
	public int updateStudent(SqlSession session, Student student) {
		return session.update("student.updateStudent", student);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudentMap", no);
	}

	@Override
	public int deleteStudent(SqlSession session, int no) {
		return session.update("student.deleteStudent", no);
	}

}
