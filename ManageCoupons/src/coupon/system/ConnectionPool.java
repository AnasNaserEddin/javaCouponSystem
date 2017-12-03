package coupon.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {

	private static ConnectionPool INSTANCE;
	
	private static int MAX_CONNECTIONS = 5; 
	private Set<Connection> myConnection;
	private Object key = new Object();
	
	public synchronized static ConnectionPool getInstance() throws ClassNotFoundException, SQLException
	{
		if (INSTANCE == null)
		{
			INSTANCE = new ConnectionPool();
		}
		return INSTANCE;
	}
	
	private ConnectionPool() throws ClassNotFoundException, SQLException
	{
		myConnection = new HashSet<>();
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coupondb", "root","root");
			Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=CouponDB;integratedSecurity=true;");
			System.out.println("connect to data base coupon db");
			myConnection.add(connection);
			//jdbc:sqlserver://localhost;user=MyUserName;password=*****;
		}
	}
		
	
	public Connection getConnection() throws InterruptedException 
	{
		synchronized(key)
		{
			while (myConnection.size() == 0)
				key.wait();
			
			Connection result = myConnection.iterator().next();
			myConnection.remove(result);
			return result;
		}
	}
	
	public void returnConnection(Connection conn)
	{
		synchronized(key)
		{
			myConnection.add(conn);
			key.notifyAll();
		}
	}
}