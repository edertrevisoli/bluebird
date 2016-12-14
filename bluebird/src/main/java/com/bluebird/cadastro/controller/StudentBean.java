package com.bluebird.cadastro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.cadastro.model.Student;
import com.bluebird.cadastro.service.StudentService;

@Named("studentBean")
@Scope("view")
public class StudentBean implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Student> students;

	@Inject
	private StudentService studentService;

	@PostConstruct
	public void init() {
		students = studentService.getStudents();

	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}