package coupon.client.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import beans.ClientType;
import beans.Coupon;
import beans.CouponType;
import coupon.system.ConnectionPool;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.CustomerSQLQueries;

public class CompanyFacade implements CouponClientFacade {

	/**
	 * the company facade which control the functions that are control
	 */
	private CompanyDBDAO companyDBdao= new CompanyDBDAO() ;
	ConnectionPool connPool;
	Connection connection;
	Statement stmt ;
    
	private CouponDBDAO couponDBdao	;
	long CompanyId;
    public long getId() {
		return CompanyId;
	}
	public void setId(long id) {
		this.CompanyId = id;
	}
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType)throws SQLException{    /** check the login of the customer and check the user name and password */

			if (companyDBdao.login(name, password))
			{
				//companyDBdao = new CompanyDBDAO();
				this.setId(companyDBdao.getId());

				try {
					couponDBdao	=new CouponDBDAO() ;
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
				return this;
			}
			else
			return null;
		
	}
	

	/**
	 * @throws SQLException
	 * a constructor for build the connection pool
	 */
	public CompanyFacade() throws SQLException       
	{
		try {
			connPool=ConnectionPool.getInstance();
			connection=connPool.getConnection();
			stmt=connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.getMessage();
		}
		catch(InterruptedException e)
		{
			
		}
	}
	
	public void createCoupon(long id,Coupon c)        /** create a coupon for this company and attach the id of the company with the id of the coupon in the table CompanyCoupon*/
	{
		 couponDBdao.createCoupon(id, c);
    }
	
	public void removeCoupon(Coupon c)   /**remove a coupon from the company */
	{
		couponDBdao.removeCoupon(c);
	}
	
	public void updateCoupon(long couponId,Coupon c)        /**update a coupon of the company */
	{
		couponDBdao.updateCoupon(couponId,c);
	}
	
	public Coupon getCoupon(long id)         /**get a data of coupon by id  */
	{
	return couponDBdao.getCoupon(id);	
	}
	
	public Collection<Coupon> getAllCoupon(long CompId) throws SQLException      /** get all coupons of the company*/
	{
		return companyDBdao.getCoupons(CompId);
		
	}
	
	public Collection<Coupon> getCouponByTypeCompany(long id,CouponType type)   /**return a coupon for the company by type */
	{
		return couponDBdao.getCouponByTypeCompany(id, type);
	}
	
	 
	
	
}