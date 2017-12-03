package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CompanyDAO;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import coupon.system.ConnectionPool;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.CustomerSQLQueries;

public class CompanyDBDAO implements CompanyDAO 
{

	private ConnectionPool connectionPool;
	private long Companyid;
	public long getId() {
		return  Companyid;
	}
//	public CompanyDBDAO() throws ClassNotFoundException, SQLException
//	{
//		this.connectionPool = ConnectionPool.getInstance();
//	}
	public CompanyDBDAO()
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
	 /**return an array of all companies*/
	public Collection<Company> getAllCompanies() throws SQLException {  

		Connection conn = null;
		ArrayList<Company> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs;

			
			rs = stmt.executeQuery(CompanySQLQueries.SELECT_ALL_COMPANIES);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("COMP_NAME");
				// add other fields too..
				
				Company c = new Company();
				c.setId(id);
				c.setCompName(name);
				// add other fields too..
				
				result.add(c);
			}			
					
		} catch (SQLException | InterruptedException e) {
			System.out.println("check the logged in information");
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return result;
		
	}
	
	@Override
	/**return a coupon by it's title*/
	public long getCompanyId(String Title) throws SQLException{  								
		Connection conn=null;
		ResultSet rs=null;
		long id=0;
		
		try
		{
			
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.GET_COMPANY_BY_TITLE);
			preparedStatement.setString(1, Title);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				id=rs.getLong("Id");	
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			System.out.println("this title is not existed");
		}
		
		finally
		{
			if(conn!=null)
			{
				connectionPool.returnConnection(conn);
			}
			
		}
		return id;
		
		
}
	
	@Override
	/** to create a new company*/
	public void createCompany(Company c) throws SQLException
	{ 

		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.CREATE_COMPANY);
			
			preparedStatement.setString(1, c.getCompName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setString(3, c.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		catch(InterruptedException inter)
		{
			
			
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		
	}
	
	/**check the login information   */
	public boolean login(String name, String password) throws SQLException 
	{
		// check in DB if user name + password matches!
		Connection conn=null;
		ResultSet resultSet = null;
		boolean exist=false;
		try
		{
			conn=connectionPool.getConnection();
		PreparedStatement preparedStatement=conn.prepareStatement(CompanySQLQueries.chk_COMPANY);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		resultSet= preparedStatement.executeQuery();
	
		if(resultSet.next())
		{
			Companyid=resultSet.getLong(1);
			exist= true;
		}
		else
		{
			exist= false;
			
		}
		}
		catch(SQLException | InterruptedException e)
		{
			System.out.println("check the login information");
			
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
	 /**to remove a company*/
	public void removeCompany(Company c) throws SQLException{  
		Connection conn=null;
		try
		{
			conn=connectionPool.getConnection();
		
			
			//to remove all the coupons from the company_cusomer table 
			
			long id=this.getCompanyId(c.getCompName());
				PreparedStatement RemoveCouForCustomer =
						conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_FOR_CUSTOMER_COMPANY);
				RemoveCouForCustomer.setLong(1, id);
				RemoveCouForCustomer.executeUpdate();
			System.out.println("******************remove all of the coupons for the customers done");
				//to remove all the coupons for the company_coupon table
				PreparedStatement RemoveCouForCompany =
						conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_FOR_COMPANY_COUPON_COMPANY_ID);
				RemoveCouForCompany.setLong(1, id);
				RemoveCouForCompany.executeUpdate();
				System.out.println("*********************remove all of the coupons for the company done");

			//to remove the company
				PreparedStatement preparedStatement=conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY);
				preparedStatement.setString(1,c.getCompName());
				int rowsAffected=preparedStatement.executeUpdate();
				System.out.println(rowsAffected+" rows affected");
				System.out.println("*********************remove the company done");

		}
		catch(SQLException | InterruptedException e)
		{
			System.out.println("company is not existed");
			
		}
		finally
		{
			if(conn!=null)
			{
				connectionPool.returnConnection(conn);
			}
		}
		
	}

	@Override
	/**to get a company by id*/
	public Company getCompany(long Id) throws SQLException {      
		// TODO Auto-generated method stub
		Connection conn=null;
		ResultSet resultSet = null;
		Company c=null;
		try
		{
			conn=connectionPool.getConnection();
		PreparedStatement preparedStatement=conn.prepareStatement(CompanySQLQueries.GET_COMPANY);
		preparedStatement.setLong(1, Id);
		resultSet= preparedStatement.executeQuery();
		while(resultSet.next())
		{
			c=new Company();
			c.setId(resultSet.getLong("Id"));
			c.setCompName(resultSet.getString("Comp_Name"));
			c.setPassword(resultSet.getString("Password"));
			c.setEmail(resultSet.getString("Email"));
		
		}
		
		}
		catch(SQLException | InterruptedException e)
		{
			System.out.println("id is not existed");
			
		}
		finally
		{
			if(conn!=null)
			{
				connectionPool.returnConnection(conn);
			}
		}
		return c;
	}

	/**
	 * to get all the coupons belongs to company
	 */
	@Override
	
	public Collection<Coupon> getCoupons(long compId) throws SQLException{  
		// TODO Auto-generated method stub
		Connection conn=null;
		ResultSet rs=null;
		Coupon c=null;
		Collection<Coupon> Coupons=null;
		try
		{
			Coupons=new ArrayList<Coupon>();
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(CompanySQLQueries.SELECT_ALL_COUPON);
			preparedStatement.setLong(1, compId);
			rs= preparedStatement.executeQuery();		
			while(rs.next())
			{
				c=new Coupon();
				c.setId(rs.getLong("Id"));
				c.setAmount(rs.getInt("Amount"));
				c.setStartDate(rs.getDate("Start_Date"));
				c.setEndDate(rs.getDate("End_Date"));
				c.setTitle(rs.getString("Title"));
				c.setPrice(rs.getDouble("Price"));
				
				
				Coupons.add(c);
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			System.out.println("check the company id");
		}
		
		finally
		{
			if(conn!=null)
			{
				connectionPool.returnConnection(conn);
			}
			
		}
		return Coupons;
	}

	@Override
	/**to update a company */
	public void updateCompany(Company c) throws SQLException{  
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.UPDATE_COMPANY);

			preparedStatement.setString(1, c.getCompName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setString(3, c.getEmail());
			preparedStatement.setLong(4, c.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		
	}

	
	
	

}