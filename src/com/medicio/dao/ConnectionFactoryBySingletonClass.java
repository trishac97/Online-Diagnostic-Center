package com.medicio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// SINGLETON CLASS
//we can create one object of this class and can create many 
//Connection objects (one factory, many objects).

public class ConnectionFactoryBySingletonClass 
{
	//static reference to itself
    private static ConnectionFactoryBySingletonClass connectionfactroyobject = new ConnectionFactoryBySingletonClass();
    
    public static final String MySQLDRIVER_CLASS = "com.mysql.jdbc.Driver"; 
    public static final String MySQLURL = "jdbc:mysql://localhost/medical";
    public static final String MySQLUSER = "root";
    public static final String MySQLPASSWORD = "trisha97";
    
    
  //private constructor
    private ConnectionFactoryBySingletonClass() 
    {
        try 
        {
            Class.forName(MySQLDRIVER_CLASS);
        } catch (ClassNotFoundException e) { e.printStackTrace();}
    }
    
    private Connection createConnection() 
    {
        Connection connectionobject = null;
        try 
        {
        	connectionobject = DriverManager.getConnection(MySQLURL, MySQLUSER, MySQLPASSWORD);
        } catch (SQLException e) 
          {
            System.out.println("ERROR: Unable to Connect to Database.");
          }
        return connectionobject;
    }   
     
    public static Connection getConnection() 
    {
        return connectionfactroyobject.createConnection();
    }
}