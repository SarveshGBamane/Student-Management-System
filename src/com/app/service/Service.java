package com.app.service;

import java.sql.SQLException;

public interface Service {
	
	public void addStudent() throws SQLException;
	
	public void viewAllStudents() throws SQLException;
	
	public void findStudent() throws SQLException;
	
	public void updateStudent() throws SQLException;
	
	public void deleteStudent() throws SQLException;
	
}
