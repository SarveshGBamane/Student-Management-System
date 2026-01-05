package com.app.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.app.serviceImp.ServiceImp;

public class UI {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		
		boolean isRun = true;
		
		
		int ch ;
		
		ServiceImp si = new ServiceImp();
		
		while(isRun) {
			
			System.out.println("------ Student Management System --------");
			
			System.out.println("1. Add Student Record :");
			System.out.println("2. View Students Records:");
			System.out.println("3. find Student Record:");
			System.out.println("4. Update Student Record :");
			System.out.println("5. Delete Student Record :");
			
			ch = sc.nextInt();
		
			switch(ch) {
				
			case 1:{
				si.addStudent();
			}
			break;
			
			case 2:{
				si.viewAllStudents();		
			}
			break;
						
			case 3:{
				si.findStudent();
			}
			break;
			
			case 4:{
				si.updateStudent();
			}
			break;
			
			case 5:{
				si.deleteStudent();
			}
			break;
			
			case 6:{
				isRun = false;
				System.out.println("Thank you....");
			}
			break;
			
			default:
					System.out.println("Enter valid choice");
			}
		}
	}
}
