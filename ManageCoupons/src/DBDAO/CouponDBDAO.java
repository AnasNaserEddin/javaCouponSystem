package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.fabric.xmlrpc.base.Array;

import DAO.CouponDAO;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import coupon.system.ConnectionPool;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.CustomerSQLQueries;

public class CouponDBDAO implements CouponDAO { /** this class deal with and save the data on database*/
	private ConnectionPool connectionPool;
	
	public CouponDBDAO() throws ClassNotFoundException, SQLException
	{
		this.connectionPool = ConnectionPool.getInstance();
	}
	@Override
	 /**create a coupon with id auto increased */
	public void createCoupon(Coupon c) {         
			Connection conn = null;
			long id;
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.CREATE_COUPON);
				preparedStatement.setString(1, c.getTitle());
				preparedStatement.setDate(2, (Date) c.getStartDate());
				preparedStatement.setDate(3, (Date) c.getEndDate());
				preparedStatement.setInt(4, c.getAmount());
				preparedStatement.setString(5,String.valueOf(c.getType()));
				preparedStatement.setString(6, c.getMessage());
				preparedStatement.setDouble(7, c.getPrice());
				preparedStatement.setString(8, c.getImage());
			
			//preparedStatement.setString(8, c.get);
		
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
			
		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getSQLState()+" "+e.getErrorCode());
		if(e.getSQLState()=="23000")
		{
			System.out.println("**************the name of this coupon already existed ...choose another name******************************************");
		}
			
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

	@Override
	 /**
	  * remove a coupon from database
	  * and remove all the coupons for the customers in customer_coupon
	  * and remove the coupon for the company in company_coupon
	  *  */
	public void removeCoupon(Coupon c) {                
		Connection conn = null;
		long id=0;
		try
		{
		conn = connectionPool.getConnection();
		id=this.getCouponId(c.getTitle());
		//Remove a coupon for the company in company_coupon table
		PreparedStatement preparedStatement1 =
				conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_FOR_COMPANY_COUPON_COUPON_ID);
		preparedStatement1.setLong(1,this.getCouponId(c.getTitle()));
	    preparedStatement1.executeUpdate();
		//remove a coupon that the company want to remove from the customer_coupon table
		 PreparedStatement RemoveCouForCust =
					conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_FOR_CUSTOMER);
			RemoveCouForCust.setLong(1, id);
			RemoveCouForCust.executeUpdate();
		
		 
		 
		 
		//remove a coupon from coupon table
		PreparedStatement preparedStatement =
				conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON);
		preparedStatement.setLong(1,id );
		int rowsAffected = preparedStatement.executeUpdate();
		System.out.println(rowsAffected + " rows affected");
		
			
		
		
		
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getSQLState());
		}
		catch (InterruptedException interrupt) {
		System.out.println(interrupt.getMessage());
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}

	@Override
	  /**return a coupon by its id*/
	public Coupon getCoupon(long Id) {   				
		Connection conn=null;
		ResultSet rs=null;
		Coupon c=null;
		
		try
		{
			
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.GET_COUPON);
			preparedStatement.setLong(1, Id);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				c=new Coupon();
				c.setId(rs.getLong("Id"));
				c.setAmount(rs.getInt("Amount"));
				c.setMessage(rs.getString("Message"));
				c.setStartDate(rs.getDate("Start_Date"));
				c.setEndDate(rs.getDate("End_Date"));
				c.setTitle(rs.getString("Title"));
				c.setPrice(rs.getDouble("Price"));
				c.setType(CouponType.valueOf(rs.getString("Type")));
				
				
				
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			e.printStackTrace();
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

	@Override
    /**return all the coupons*/
	public Collection<Coupon> getAllCoupons() {         
		// TODO Auto-generated method stub
		Connection conn=null;
		ResultSet rs=null;
		Coupon c=null;
		Collection<Coupon> Coupons=null;
		try
		{
			Coupons=new ArrayList<Coupon>();
			conn=connectionPool.getConnection();

			Statement stmt = conn.createStatement();
			

			
			rs = stmt.executeQuery(CouponSQLQueries.SELECT_ALL_COUPON);
			
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
			e.printStackTrace();
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
	/**return a coupon by type */
	public Collection<Coupon> getCouponByType(CouponType type) {  							
		Connection conn=null;
		ResultSet rs=null;
		Collection<Coupon> c=null;
		Coupon cou;
		try
		{
			
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_TYPE);
			preparedStatement.setString(1, String.valueOf(type));
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				cou=new Coupon();
				cou.setId(rs.getLong("Id"));	
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			e.printStackTrace();
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

	@Override
	/**update the coupon data */
	public void updateCoupon(long couponId,Coupon c) {        								 
				// TODO Auto-generated method stub
Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.UPDATE_COUPON);

			preparedStatement.setString(1, c.getTitle());
			preparedStatement.setString(2, c.getMessage());
			preparedStatement.setString(3, c.getImage());
			preparedStatement.setDate(4, (Date) c.getStartDate());
			preparedStatement.setDate(5, (Date) c.getEndDate());
			preparedStatement.setInt(6, c.getAmount());
			preparedStatement.setDouble(7, c.getPrice());
			preparedStatement.setString(8, c.getType().toString());
			preparedStatement.setLong(9, couponId);
		
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

	
	@Override
    /**create coupon with id for testing */
	public void createCouponWithId(Coupon c) {       							
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.CREATE_COUPON);
			preparedStatement.setLong(1, c.getId());
			preparedStatement.setString(2, c.getTitle());
			preparedStatement.setDate(3, (Date) c.getStartDate());
			preparedStatement.setDate(4, (Date) c.getEndDate());
			preparedStatement.setInt(5, c.getAmount());
			preparedStatement.setString(6,String.valueOf(c.getType()));
			preparedStatement.setString(7, c.getMessage());
			preparedStatement.setDouble(8, c.getPrice());
			preparedStatement.setString(9, c.getImage());
			
			
			
			//preparedStatement.setString(8, c.get);
		
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
	@Override
	/**return a coupon by it's title*/
	public Coupon getCoupon(String Title) {  										
		Connection conn=null;
		ResultSet rs=null;
		Coupon c=null;
		
		try
		{
			
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_TITLE);
			preparedStatement.setString(1, Title);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				c=new Coupon();
				c.setId(rs.getLong("Id"));	
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			e.printStackTrace();
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
	
	@Override
	/**return a coupon by it's title*/
	public long getCouponId(String Title) {  								
		Connection conn=null;
		ResultSet rs=null;
		long id=0;
		
		try
		{
			
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_TITLE);
			preparedStatement.setString(1, Title);
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				id=rs.getLong("Id");	
			}
			
		}
		catch(SQLException |InterruptedException e)
		{
			e.printStackTrace();
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
	/**get all coupons were bought by the customer*/
	public Collection<Coupon> getAllPurchsasedCoupons(Long Custid) {  						
		
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		Coupon cou;
		try {
			// could be in wait state if no connection available!
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			conn.prepareStatement(CustomerSQLQueries.get_All_Coupons);
			ResultSet rs;

			
			preparedStatement.setLong(1, Custid);
			rs= preparedStatement.executeQuery();
			while (rs.next())
			{
				cou=new Coupon();
				cou.setId(rs.getLong("Coupon_Id"));
				cou.setTitle(rs.getString("Title"));
				cou.setAmount(rs.getInt("Amount"));
				cou.setType(CouponType.valueOf(rs.getString("Type")));
				result.add(cou);
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
	/**get all purchased coupon by a customer*/
	public Collection<Coupon> getAllPurchsasedCouponsByType(Long Custid, CouponType type) {          
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		Coupon cou;
		try {
			// could be in wait state if no connection available!
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			conn.prepareStatement(CustomerSQLQueries.get_All_Coupons_By_Type);
			
			ResultSet rs;

			
			preparedStatement.setLong(1, Custid);
			preparedStatement.setString(2, String.valueOf(type));
			rs= preparedStatement.executeQuery();
			while (rs.next())
			{
				cou=new Coupon();
				cou.setId(rs.getLong("Coupon_Id"));
				cou.setTitle(rs.getString("Title"));
				cou.setAmount(rs.getInt("Amount"));
				cou.setType(CouponType.valueOf(rs.getString("Type")));
				result.add(cou);
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
	/**get all purchased coupon by a customer filtered by price*/
	public Collection<Coupon> getAllPurchsasedCouponsByPrice(long Custid, float Price) { 				 
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		Coupon cou;
		try {
			// could be in wait state if no connection available!
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			conn.prepareStatement(CustomerSQLQueries.get_All_Coupons_By_Price);
			
			ResultSet rs;

			
			preparedStatement.setLong(1, Custid);
			preparedStatement.setFloat(2,Price);
			rs= preparedStatement.executeQuery();
			while (rs.next())
			{
				cou=new Coupon();
				cou.setId(rs.getLong("Coupon_Id"));
				cou.setTitle(rs.getString("Title"));
				cou.setAmount(rs.getInt("Amount"));
				cou.setType(CouponType.valueOf(rs.getString("Type")));
				cou.setPrice(rs.getFloat("Price"));
				result.add(cou);
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
	/**create a coupon for a company and save the id of the company and the id of the coupon into the company_coupon table in the database*/
	public void createCoupon(long Compid, Coupon c) {      
		// TODO Auto-generated method stub
		Connection con=null;
		this.createCoupon(c);
		Coupon returnedIdOfCoupon=this.getCoupon(c.getTitle());
		try {
			System.out.println("purchase coupon succeed");
			
			con=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			con.prepareStatement(CompanySQLQueries.INSERT_COUPON);
			preparedStatement.setLong(1,Compid);
			preparedStatement.setLong(2, returnedIdOfCoupon.getId());
			int rows=preparedStatement.executeUpdate();
	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		System.out.println("dataexisted");    
	}
	}
	@Override
    /**get coupons of company by type*/
	public Collection<Coupon> getCouponByTypeCompany(long id, CouponType type) {   
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		Coupon cou;
		try {
			// could be in wait state if no connection available!
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			conn.prepareStatement(CompanySQLQueries.get_All_Coupons_By_Type);
			
			ResultSet rs;

			
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, String.valueOf(type));
			rs= preparedStatement.executeQuery();
			while (rs.next())
			{
				cou=new Coupon();
				cou.setId(rs.getLong("Id"));
				cou.setTitle(rs.getString("Title"));
				cou.setAmount(rs.getInt("Amount"));
				cou.setType(CouponType.valueOf(rs.getString("Type")));
				cou.setStartDate(rs.getDate("Start_date"));
				cou.setEndDate(rs.getDate("End_Date"));
				cou.setType(CouponType.valueOf(rs.getString("Type")));
							
				result.add(cou);
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
	public ArrayList<Long> get_expired_coupon() {
		Connection conn = null;
		ArrayList<Long> result = new ArrayList<Long>();
		Coupon cou;
		try {
			// could be in wait state if no connection available!
			conn=connectionPool.getConnection();
			PreparedStatement preparedStatement =
			conn.prepareStatement(CouponSQLQueries.GET_EXPIRED_COUPON);
			ResultSet rs;
			rs= preparedStatement.executeQuery();
			while (rs.next())
			{				
				((Collection<Long>) result).add(rs.getLong("Id"));
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
	public void delete_expired_coupon(ArrayList<Long> IdCoupon) {
		Connection conn = null;
		long id=0;
		Coupon c=new Coupon();
		try
		{
			
		conn = connectionPool.getConnection();
		
		for(int i=0;i<IdCoupon.size();i++)
		{
			c=this.getCoupon(IdCoupon.get(i));
			this.removeCoupon(c);
		}
		
		
//		Object[] arr=IdCoupon.toArray();
//		//Remove a coupon for the company in company_coupon table
//		PreparedStatement preparedStatement1 =
//				conn.prepareStatement(CouponSQLQueries.DELETE_EXPIRED_COUPON_COUPON);
//		preparedStatement1.setArray(1,(java.sql.Array) IdCoupon);
//	    preparedStatement1.executeUpdate();
//		//remove a coupon that the company want to remove from the customer_coupon table
//		 PreparedStatement RemoveCouForCust =
//					conn.prepareStatement(CouponSQLQueries.DELETE_EXPIRED_COUPON_CUS_COU);
//			RemoveCouForCust.setArray(1, (java.sql.Array) IdCoupon);
//			RemoveCouForCust.executeUpdate();
//		
//		 
//		 
//		 
//		//remove a coupon from coupon table
//		PreparedStatement preparedStatement =
//				conn.prepareStatement(CouponSQLQueries.DELETE_EXPIRED_COUPON_COM_COU);
//		preparedStatement.setArray(1,(java.sql.Array) IdCoupon );
//		int rowsAffected = preparedStatement.executeUpdate();
//		System.out.println(rowsAffected + " rows affected");
//		
			
		
		
		
		}
		catch (InterruptedException interrupt) {
		System.out.println(interrupt.getMessage());
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

		
	}
	

	
	
}
