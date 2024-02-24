
// MainStudentApp.java
package com.edu;

import java.sql.SQLException;
import java.util.Scanner;

public class MainStudentApp {

	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		for(;;) {
		int ch ;
		Scanner sc = new Scanner (System.in);
		System.out.println();
		System.out.println("******** MENU*********");
		System.out.println("Enter your Choise ");
		System.out.println("1.Show Student details");
		System.out.println("2.Register Student");
        System.out.println("3.Delete Student details");
        System.out.println("4.Update Student Details");
        System.out.println("5.Exit");
        
        ch=sc.nextInt();
        
        switch(ch) {
        case 1:  // display student details 
        DatabaseOperation.displayStudentDetails();
              break;
              
        case 2: //  registersStudent
        	DatabaseOperation.registersStudent();
        	break;
        	
        case 3:// delete the record
        	DatabaseOperation.deleteStudent();
        	break;
        	
        case 4 :
        	// update the record
        	DatabaseOperation.updateStudent();
            break;
            
        case 5:
        	System.out.println("thank you");
        	System.exit(0);
        	break;
        	
        	default :
        		System.out.println("Invalid Choise ....! Enter valid choise ");
        }
        
	}
	}

}
