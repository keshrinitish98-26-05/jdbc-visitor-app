package com.ty.visitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.ty.visitor.dto.Visitor;
import com.ty.visitor.util.AES;
import com.ty.visitor.util.ConnectionObject;
import static com.ty.visitor.util.AppConstants.*;

public class VisitorDao {
	
	
	public int saveVisitorData(Visitor visitor)
	{
		
	    String sql="INSERT INTO visitor VALUES(?,?,?,?,?,?,?,?)";
	    
	    Connection connection=ConnectionObject.getConnection();
	    
	    String enc1=AES.encrypt(visitor.getName(),SECRETE_KEY );
	    String enc2=AES.encrypt(visitor.getEmail(),SECRETE_KEY);
	    String enc3=AES.encrypt(visitor.getPhone(), SECRETE_KEY);
	    
	    try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,visitor.getId());
			preparedStatement.setString(2, enc1);
			preparedStatement.setString(3, enc2);
			preparedStatement.setString(4, enc3);
			preparedStatement.setInt(5, visitor.getAge());
			preparedStatement.setString(6, visitor.getGender());
			preparedStatement.setString(7, visitor.getDob());
			preparedStatement.setString(8,visitor.getVisitdatetime());
			
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    finally
	    {
	    	if(connection!=null)
	    	{
	    		try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	    }
		return 0;
	}
	
	public int deleteById(int id)
	{
		
		String sql="DELETE  FROM visitor WHERE id=?";
		Connection connection=ConnectionObject.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(connection!=null)
				{
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	public Visitor getVisitorById(int id)
	{
		String sql="SELECT * FROM visitor WHERE id=?";
		Connection connection=ConnectionObject.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Visitor visitor=new Visitor();
				String enc1=AES.decrypt(resultSet.getString(2),SECRETE_KEY);
				String enc2=AES.decrypt(resultSet.getString(3), SECRETE_KEY);
				String enc3=AES.decrypt(resultSet.getString(4), SECRETE_KEY);
				visitor.setId(resultSet.getInt(1));
				visitor.setName(enc1);
				visitor.setEmail(enc2);
				visitor.setPhone(enc3);
				visitor.setAge(resultSet.getInt(5));
				visitor.setGender(resultSet.getString(6));
				visitor.setDob(resultSet.getString(7));
				visitor.setVisitdatetime(resultSet.getString(8));
				return visitor;
			}
			else
				System.out.println("No such id present");
				
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	public Visitor getVisitorByDate(String visitdatetime)
	{
		String sql="SELECT * FROM visitor WHERE visitdatetime=?";
		Connection connection=ConnectionObject.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, visitdatetime);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Visitor visitor=new Visitor();
				String enc1=AES.decrypt(resultSet.getString(2),SECRETE_KEY);
				String enc2=AES.decrypt(resultSet.getString(3), SECRETE_KEY);
				String enc3=AES.decrypt(resultSet.getString(4), SECRETE_KEY);
				visitor.setId(resultSet.getInt(1));
				visitor.setName(enc1);
				visitor.setEmail(enc2);
				visitor.setPhone(enc3);
				visitor.setAge(resultSet.getInt(5));
				visitor.setGender(resultSet.getString(6));
				visitor.setDob(resultSet.getString(7));
				visitor.setVisitdatetime(resultSet.getString(8));
				return visitor;
			}
			else
				System.out.println("No such id present");
				
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	public List<Visitor> getAllVisitorsByAgeRange(int age1,int age2)
	{
		String sql="SELECT * FROM visitor WHERE age BETWEEN ? AND ?";
		Connection connection=ConnectionObject.getConnection();
		PreparedStatement preparedStatement=null;
		List<Visitor> list=new ArrayList();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,age1);
			preparedStatement.setInt(2, age2);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Visitor visitor=new Visitor();
				String enc1=AES.decrypt(resultSet.getString(2),SECRETE_KEY);
				String enc2=AES.decrypt(resultSet.getString(3), SECRETE_KEY);
				String enc3=AES.decrypt(resultSet.getString(4), SECRETE_KEY);
				visitor.setId(resultSet.getInt(1));
				visitor.setName(enc1);
				visitor.setEmail(enc2);
				visitor.setPhone(enc3);
				visitor.setAge(resultSet.getInt(5));
				visitor.setGender(resultSet.getString(6));
				visitor.setDob(resultSet.getString(7));
				visitor.setVisitdatetime(resultSet.getString(8));
				list.add(visitor);	
			}
			else
				System.out.println("No such id present");
				
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return list;
	}

}
