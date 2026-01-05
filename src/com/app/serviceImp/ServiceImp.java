package com.app.serviceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.app.DBconnection.DBConnection;
import com.app.model.Student;
import com.app.service.Service;

public class ServiceImp implements Service{
	
	Scanner sc  = new Scanner(System.in);
	
	
	
	Connection con;
	
	@Override
	public void addStudent() throws SQLException {

	    Student s = new Student();

	    System.out.println("Enter Student ID :");
	    s.setId(sc.nextInt());
	    sc.nextLine();

	    System.out.println("Enter Student Name :");
	    s.setName(sc.nextLine());

	    System.out.println("Enter Student Age :");
	    s.setAge(sc.nextInt());
	    sc.nextLine();

	    System.out.println("Enter Student Address :");
	    s.setAdderss(sc.nextLine());

	    System.out.println("Enter Student Class :");
	    s.setS_class(sc.nextLine());

	    System.out.println("Enter Student Mobile :");
	    s.setMobile(sc.nextLong());

	    String insertData = "INSERT INTO studentData VALUES (?,?,?,?,?,?)";

	    
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(insertData)) {

	        ps.setInt(1, s.getId());
	        ps.setString(2, s.getName());
	        ps.setInt(3, s.getAge());
	        ps.setString(4, s.getAdderss());
	        ps.setString(5, s.getS_class());
	        ps.setLong(6, s.getMobile());

	        ps.executeUpdate();  

	        System.out.println("Successfully added student record");
	    }
	    catch (SQLException e) {
	        System.out.println("Database error occurred");
	        e.printStackTrace();
	    }
	}


	@Override
	public void viewAllStudents() {

	    String sql = "SELECT * FROM studentData";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        boolean found = false;

	        System.out.println("ID | Name | Age | Address | Class | Mobile");
	        System.out.println("---------------------------------------------");

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                    rs.getInt(1) + " | " +
	                    rs.getString(2) + " | " +
	                    rs.getInt(3) + " | " +
	                    rs.getString(4) + " | " +
	                    rs.getString(5) + " | " +
	                    rs.getLong(6));
	        }

	        if (!found) {
	            System.out.println("No student records found.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error while fetching student records");
	        e.printStackTrace();
	    }
	}


	@Override
	public void findStudent() {

		Student s = new Student();
		 
	    System.out.print("Enter Student ID: ");
	    int studentId = sc.nextInt();

	    String sql = "SELECT * FROM studentData WHERE id = ?";
	    
	    
	    System.out.println("ID | Name | Age | Address | Class | Mobile");
        System.out.println("---------------------------------------------");
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, studentId);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                System.out.println(
	                        rs.getInt(1) + " | " +
	                        rs.getString(2) + " | " +
	                        rs.getInt(3) + " | " +
	                        rs.getString(4) + " | " +
	                        rs.getString(5)+ " | " +
	                        rs.getLong(6));
	            } else {
	                System.out.println("Student not found!");
	            }
	        } 
	    } catch (SQLException e) {
	        System.out.println("Error while fetching student record");
	        e.printStackTrace();
	    }
	    
	}


	@Override
	public void updateStudent() {

	    System.out.print("Enter Student ID: ");
	    int studentId = sc.nextInt();

	    System.out.println("What field do you want to update?");
	    System.out.println("1.Name\n2.Age\n3.Address\n4.Class\n5.Mobile");
	    int choice = sc.nextInt();

	    String sql = null;

	    switch (choice) {
	        case 1: sql = "UPDATE studentData SET name = ? WHERE id = ?"; break;
	        case 2: sql = "UPDATE studentData SET age = ? WHERE id = ?"; break;
	        case 3: sql = "UPDATE studentData SET address = ? WHERE id = ?"; break;
	        case 4: sql = "UPDATE studentData SET student_class = ? WHERE id = ?"; break;
	        case 5: sql = "UPDATE studentData SET mobile = ? WHERE id = ?"; break;
	        default:
	            System.out.println("Invalid choice");
	            return;
	    }

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        // Set value
	        switch (choice) {
	            case 1:
	                sc.nextLine();
	                System.out.print("Enter updated name: ");
	                ps.setString(1, sc.nextLine());
	                break;
	            case 2:
	                System.out.print("Enter updated age: ");
	                ps.setInt(1, sc.nextInt());
	                break;
	            case 3:
	                sc.nextLine();
	                System.out.print("Enter updated address: ");
	                ps.setString(1, sc.nextLine());
	                break;
	            case 4:
	                System.out.print("Enter updated class: ");
	                ps.setInt(1, sc.nextInt());
	                break;
	            case 5:
	                System.out.print("Enter updated mobile: ");
	                ps.setLong(1, sc.nextLong());
	                break;
	        }

	        ps.setInt(2, studentId);

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Student record updated successfully");
	        } else {
	            System.out.println("Student ID not found");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error while updating student record");
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteStudent() throws SQLException {
		
			con = DBConnection.getConnection();
			
			String deleteData = "delete from studentData where id = ?";
			
			PreparedStatement ps = con.prepareStatement(deleteData);
			
			System.out.println("Enter Student ID:");
			ps.setInt(1, sc.nextInt());
			
			ps.execute();
			
			ps.close();
			con.close();
			System.out.println("Succesfully deleted student record");

	}
		
}
