package com.sh.mybatis.student.model.service;

import java.util.Map;

import com.sh.mybatis.student.model.dto.Student;

public interface StudentService {
	
	int insertStudent(Student student);

	int insertStudent(Map<String, Object> studentMap);

	int getTotalCount();

	Student selectOneStudent(int no);

	int updateStudent(Student student);

	Map<String, Object> selectOneStudentMap(int no);

	int deleteStudent(int no);
	
}
