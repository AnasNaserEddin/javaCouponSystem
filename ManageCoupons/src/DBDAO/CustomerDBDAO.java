package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CustomerDAO;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import coupon.system.ConnectionPool;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.CustomerSQLQueries;

public class CustomerDBDAO implements CustomerDAO {
	
private ConnectionPool connectionPool;
	private long Customerid;
	public long getId() {
		return Customerid;
	}
	public CustomerDBDAO()          /**constructor for */
	{
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Collection<Customer> getAllCustomer() {

		Connection conn = null;
		ArrayList<Customer> result = new ArrayList<Customer>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs;

			
			rs = stmt.executeQuery(CustomerSQLQueries.SELECT_ALL_CUSTOMERS);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Cust_Name");
				String pass=rs.getString("Password");
				// add other fields too..
				
				Customer c = new Customer();
				c.setId(id);
				c.setCustName(name);
				c.setPassword(pass);
				// add other fields too..
				
				result.add(c);
			}			
					
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return result;
		
	}
	
	@Override
	public void createCustomer(Customer c) {

		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CustomerSQLQueries.CREATE_CUSTOMER);
			preparedStatement.setString(1, c.getCustName());
			preparedStatement.setString(2, c.getPassword());
			
			int rowsAffected = preparedStatement.executeUpdate();
		
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		
	}
	
	
	
	//*************************************************************



	/** 
	 * remove a specified customer from customer table and his coupons  from customer_coupons
	 */
	@Override
	
	public void removeCustomer(Customer c) {
		Connection conn=null;
		try
		{
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(CustomerSQLQueries.REMOVE_CUSTOMER);
			preparedStatement.setString(1, c.getCustName());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
			
			
		}
		catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}
	
	
	

	/**
	 * to get a specified customer by his id

	 */
	@Override
	public Customer getCustomer(long Id) {
		Connection conn=null;
		ResultSet resultSet = null;
		Customer c=null;
		
		try
		{
			conn =connectionPool.getConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(CustomerSQLQueries.GET_CUSTOMER);
			preparedStatement.setLong(1, Id);
			resultSet= preparedStatement.executeQuery();
			while(resultSet.next())
			{
				c=new Customer();
				c.setId(resultSet.getLong("Id"));
				c.setCustName(resultSet.getString("Cust_Name"));
				c.setPassword(resultSet.getString("Password"));
			
			}
			
		}
		catch (SQLException | InterruptedException e) {
			System.out.println("customer is not existed");
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return c;
		
	}

	

	@Override
	 /** to get all coupons of the customer*/
	public Collection<Coupon> getCoupons() { 
		Connection conn=null;
		ResultSet resutlSet=null;
		ArrayList<Coupon> Coupons=new ArrayList<Coupon>();
		
		try
		{
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(CustomerSQLQueries.GET_COUPONS);
			
		}
		catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return Coupons;
		
	}

	@Override
	/** to check the log in */
	public boolean login(String custName, String password) {      
		Connection conn=null;
		ResultSet resultSet = null;
		boolean exist=false;
		try
		{
			conn=connectionPool.getConnection();
		PreparedStatement preparedStatement=conn.prepareStatement(CustomerSQLQueries.chk_CUSTOMER);
		preparedStatement.setString(1, custName);
		preparedStatement.setString(2, password);
		resultSet= preparedStatement.executeQuery();
	
		if(resultSet.next())
		{
			
			System.out.println(resultSet.getLong(1));
			Customerid=resultSet.getLong(1);
			exist= true;
		}
		else
		{
			exist= false;
			
		}
		}
		catch(SQLException | InterruptedException | NullPointerException e)
		{
			System.out.println("check the login information");;
			
		}
		finally
		{
			if(conn!=null)
			{
				connectionPool.returnConnection(conn);
			}
		}
	return exist;	
	}

	@Override
	 /**to update a specified customer*/
	public void updateCustomer(Customer c) {    
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CustomerSQLQueries.UPDATE_CUSTOMER);

			preparedStatement.setString(1, c.getCustName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setLong(3, c.getId());
			int rowsAffected = preparedStatement.executeUpdate();
		
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			System.out.println("the company name is existed");
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		
	}

	

	@Override
	 /**
	  * 
	  * to create a customer with id (for test only)
	  * */
	public void createCustomerWithId(Customer c) {
Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CustomerSQLQueries.CREATE_CUSTOMER);
			preparedStatement.setLong(1, c.getId());
			preparedStatement.setString(2, c.getCustName());
			preparedStatement.setString(3, c.getPassword());
			
			int rowsAffected = preparedStatement.executeUpdate();
		
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			System.out.println("this customer with this id is existed or the name is existed");
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}		
	}
	/**
	 * the customer purchase a coupon 
	 */
	@Override
	
	public void purchaseCoupon(long c, Coupon coupon) {
		Connection con=null;
		try {
			
			if(coupon.getAmount()>0)
			{
			con=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			con.prepareStatement(CustomerSQLQueries.insert_COUPON);
			preparedStatement.setLong(1,c);
			preparedStatement.setLong(2, coupon.getId());
			int rows=preparedStatement.executeUpdate();
			System.out.println(rows);
			int amount=coupon.getAmount()-1;
			PreparedStatement pre=con.prepareStatement(CouponSQLQueries.AmountLessOne);
			pre.setInt(1, amount);
			pre.setLong(2, coupon.getId());
			int updaterows=pre.executeUpdate();
			}
			else
			{
				System.out.println("the amount of this coupon is finished");
			}
			System.out.println("purchase coupon succeed");
		} catch (InterruptedException e) {
			System.out.println("this coupon is existed for this customer");
		}
		 catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		System.out.println("the coupon that you are trying to purchase is already purchased by you  ");    
	}
		
	}

}
