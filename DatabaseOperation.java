package com.edu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseOperation {
	
	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	
	private static String name ,email;
	private static int id ;
	private static float fees;
	private static String dob;
	private static int updvar;
	
	public static void displayStudentDetails () throws SQLException {
	conn=DatabaseConnections.getConnection();
	
	//System.out.println("Connection"+conn+"\n");
	

	sql="select * from student_data";
    pst=conn.prepareStatement(sql);	
	 rs=pst.executeQuery();
	 
	 
	 System.out.println("Id\tName\tfees\tdob\t\temail");
		System.out.println("____________________________________________________");
		while(rs.next()) {
			id = rs.getInt("studentid");
			name = rs.getString("studentname");
			email = rs.getString("studentemail");
			fees = rs.getFloat("studentfees");
			Date dob = rs.getDate("studentdob");
			
			System.out.println(id+"\t"+name+"\t"+fees+"\t"+dob+"\t"+email);
		}
	

}
	
	public static void registersStudent() throws SQLException {
		conn= DatabaseConnections.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter Name");
		name=sc.nextLine();
		System.out.println("Enter Email");
		email=sc.nextLine();
		System.out.println("Enter Fees");
		fees=sc.nextFloat();
		System.out.println("Enter Date of Birth (yyyy-mm-dd)");
		dob=sc.next();
		stmt=conn.createStatement();
		String s="select max(studentid)+1 as sid from student_data";
		rs=stmt.executeQuery(s);
		System.out.println(rs);
		int stid = 0;
		if ( rs.next()) {
			stid=rs.getInt("sid");
		}
		sql="insert into student_data (studentid,studentname,studentemail,studentfees,studentdob)values (?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,stid);
		pst.setString(2, name);
		pst.setString(3, email);
		pst.setFloat(4, fees);
		pst.setDate(5,Date.valueOf(dob));
		
		int i=pst.executeUpdate();
		if( i>0){
			System.out.println("Student registered Successfully...!");
		}
		else {
			System.out.println("Not Registered Successfully");
		}
		}
	
	public static void deleteStudent() throws SQLException {
		
		conn= DatabaseConnections.getConnection();
		Scanner sc = new Scanner (System.in);
        System.out.println("Enter the student id to delete ");
        id=sc.nextInt();
        
        // cheak id exists
        
        sql="select * from student_data where studentid=?";
        pst=conn.prepareStatement(sql);
        pst.setInt(1, id);
        rs=pst.executeQuery();
        if ( rs.next()){
        	String del= "delete from student_data where studentid =?";
        	  pst=conn.prepareStatement(del);
        	  pst.setInt(1, id);
        	  int i=pst.executeUpdate();
        	  
        	if (i>0 ) {
        		System.out.println("Record is deleted ");
        	}
        }
        else {
    		System.out.println("student id not Exists");
    	}
       }
	
public static void updateStudent() throws SQLException {
		
		conn= DatabaseConnections.getConnection();
		
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter the id");
		id=sc.nextInt();
		sql="select * from student_data where studentid=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1, id);
		
		rs = pst.executeQuery();
		
		if ( rs.next()) {// cheak exist
			int ch;
			System.out.println("chose one of them for  update ");
			System.out.println("1.To update Student Name\n2.To update Student Email\n3.To updateStudent fees\n4.To updateStudent DOB");
			int choise=sc.nextInt();
			switch(choise) {
			
			case 1:// update Student Name
				
				System.out.println("Enter the Name of Student");
				sc.nextLine();
				name=sc.nextLine();
				sql="update student_data set studentname=? where studentid=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,name);
				pst.setInt(2,id);
				updvar=pst.executeUpdate();
				if ( updvar>0) {
					System.out.println("Your Name is "+name);
				}
				else {
				System.out.println(id+" Not Exist");
			}
			break;

        case 2:// update Student Email
        	
        	System.out.println("Enter the Email of Student");
        	sc.nextLine();
        	email=sc.nextLine();
        	sql="update student_data set studentemail=? where studentid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,email);
			pst.setInt(2,id);
			updvar=pst.executeUpdate();
			if ( updvar>0) {
				System.out.println("Your email is: "+email);
			}
			else {
			System.out.println(id+" Not Exist");
		}
		break;
		
        case 3:// update Student Fees
		
			System.out.println("Enter the Fees of Student");
			sc.nextLine();
			fees=sc.nextFloat();
			sql="update student_data set studentfees=? where studentid=?";
			pst=conn.prepareStatement(sql);
			pst.setFloat(1,fees);
			pst.setInt(2,id);
			updvar=pst.executeUpdate();
			if ( updvar>0) {
				System.out.println("Your fees is: "+fees);
			}
			else {
			System.out.println(id+" Not Exist");
		}
			break;
			
        case 4:// update Student D O B
        	
			System.out.println("Enter the DOB of Student");
			sc.nextLine();
			dob=sc.nextLine();
			sql="update student_data set studentdob=? where studentid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,dob);
			pst.setInt(2,id);
			updvar=pst.executeUpdate();
			if ( updvar>0) {
				System.out.println("Your date of Brith  is: "+dob);
			}
			else {
			System.out.println(id+" Not Exist");
		}
			break;
			
			}
		}
}
}



