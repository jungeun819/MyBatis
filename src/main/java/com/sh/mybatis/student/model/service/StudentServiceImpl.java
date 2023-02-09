package com.sh.mybatis.student.model.service;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.common.SqlSessionTemplate;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	/**
	 * 학생 정보입력(dto)
	 */
	@Override
	public int insertStudent(Student student) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close(); // DBCP에 반환(메모리 해제 아님)
		}
		return result;
	}

	/**
	 * 학생 정보입력(map)
	 */
	@Override
	public int insertStudent(Map<String, Object> studentMap) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, studentMap);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close(); 
		}
		return result;
	}

	/**
	 * 전체 학생 수 조회
	 */
	@Override
	public int getTotalCount() {
		SqlSession session = getSqlSession();
		int totalCount = studentDao.getTotalCount(session);
		session.close();
		return totalCount;
	}

	/**
	 * 특정 학생 정보조회(dto)
	 */
	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(session, no);
		session.close();
		return student;
	}

	/**
	 * 특정 학생 정보조회(map)
	 */
	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String, Object> studentMap = studentDao.selectOneStudentMap(session, no);
		session.close();
		return studentMap;
	}

	/**
	 * 학생 정보수정
	 */
	@Override
	public int updateStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.updateStudent(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close(); 
		}
		return result;
	}

	/**
	 * 학생 정보삭제
	 */
	@Override
	public int deleteStudent(int no) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.deleteStudent(session, no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close(); 
		}
		return result;
	}

}
