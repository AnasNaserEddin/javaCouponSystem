package coupon.client.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import coupon.system.ConnectionPool;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.CustomerSQLQueries;

public class CustomerFacade implements CouponClientFacade {     /** control the functions that the user can do it */
	
	CustomerDBDAO customerDBdao=new CustomerDBDAO();
	CouponDBDAO couponDBdao;
	ConnectionPool connPool;
	Connection connection;
	Statement stmt ;
    ResultSet rs;
    long CustomerId;
    public long getId() {
		return CustomerId;
	}
	public void setId(long id) {
		this.CustomerId = id;
	}

  
	/**
	 * to check the login of the user
	 */
	@Override
	
	public CouponClientFacade login(String name, String password, ClientType clientType) { 
		if (customerDBdao.login(name, password))
		{
			//companyDBdao = new CompanyDBDAO();
//			try {
//				customerDBdao	=new CouponDBDAO() ;
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			this.setId(customerDBdao.getId());
			return this;
		}
		else
		{
			System.out.println("login customer is failed");
			return null;
		}
		
	}
	
	/**
	 * constructor for customer facade to start the connection pool
	 */
	public CustomerFacade() 
	{
		try {
			connPool=ConnectionPool.getInstance();
			connection=connPool.getConnection();
			stmt=connection.createStatement();
			 couponDBdao=new CouponDBDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
	}
	

	
	/**
	 * @param c
	 * @param coupon
	 * to purchase a coupon 
	 */
	public void purchaseCoupon(long c,Coupon coupon) 
	{
		customerDBdao.purchaseCoupon(c, coupon);
	       
	}
	
	
	
	/**
	 * @param Custid
	 * @return
	 * get all coupons that the customer have bought 
	 */
	public Collection<Coupon> getAllPurchsasedCoupons(Long Custid)  
	{

	return couponDBdao.getAllPurchsasedCoupons(Custid);
	
	}
	
	/**
	 * @param Custid
	 * @param type
	 * @return
	 * return all coupons by type
	 */
	public Collection<Coupon> getAllPurchsasedCouponsByType(Long Custid,CouponType type) 
	{
	return couponDBdao.getAllPurchsasedCouponsByType(Custid, type);
	}
	
	/**
	 * @param Custid
	 * @param Price
	 * @return
	 * get all coupon of the company by its price
	 */
	public Collection<Coupon> getAllPurchsasedCouponsByPrice(long Custid,float Price)
	{
		return couponDBdao.getAllPurchsasedCouponsByPrice(Custid, Price);
	}
	
	
	
}
