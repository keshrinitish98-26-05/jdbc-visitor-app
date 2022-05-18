package com.ty.visitor.controller;

import java.time.LocalDate;

import com.ty.visitor.dao.VisitorDao;
import com.ty.visitor.dto.Visitor;

public class TestVisitor {
	
	public static void main(String[] args) {
		
		Visitor visitor=new Visitor();
		visitor.setId(4);
		visitor.setName("cadhu");
		visitor.setEmail("cadhu@gmail.com");
		visitor.setPhone("66876552346");
		visitor.setAge(30);
		visitor.setGender("mail");
		visitor.setDob("");  // LocalDate.now()
		visitor.setVisitdatetime("2022-03-13"); //LocalDateTime.now()
		
//		VisitorDao visitorDao=new VisitorDao();
//	    int res=visitorDao.saveVisitorData(visitor);
//		int res=visitorDao.deleteById(1);
//		if(res>0)
//     	{
//			System.out.println("Data inserted");
//		}
//		else
//			System.out.println("Data not inserted");
//		
//		Visitor visitor2=visitorDao.getVisitorByDate("2022-05-15");
//		System.out.println(visitor2.getId());
//		System.out.println(visitor2.getName());
	}

}
