package DAO;

import java.util.ArrayList;
import java.util.Collection;

import com.mysql.fabric.xmlrpc.base.Array;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
public interface CouponDAO {                     /**coupon dao is an interface which contains a abstract methods which is implemented in couponDBdao*/
	/**
	 * @param c
	 * to create a coupon (abstract class)
	 */
	public void createCoupon(Coupon c);
	/**
	 * @param id
	 * @param c
	 * create  a coupon for a specifies company (abstract class)			
	 */
	public void createCoupon(long id,Coupon c);											
	/**
	 * @param c
	 * to cremove a coupon from coupon table(abstract class)
	 */
	public void removeCoupon(Coupon c);													
	/**
	 * @param couponId
	 * @param c
	 * to update a coupon (abstract class)
	 */
	public void updateCoupon(long couponId,Coupon c);		
	/**
	 * @param Id
	 * @return
	 * to get a coupon (abstract class)
	 */
	public Coupon getCoupon(long Id);		
	/**
	 * @return
	 * to get all coupons(abstract class)
	 */
	public Collection<Coupon> getAllCoupons();    
	/**
	 * @param Custid
	 * @return
	 * to get all purchased coupons (abstract class)
	 */
	public Collection<Coupon> getAllPurchsasedCoupons(Long Custid);						
	/**
	 * @param type
	 * @return
	 *to get a coupon filtered by type (abstract class)
	 */
	public Collection<Coupon> getCouponByType(CouponType type);                          
	/**
	 * @param Custid
	 * @param type
	 * @return
	 * to get all purchased coupons filtered by type (abstract class)
	 */
	public Collection<Coupon> getAllPurchsasedCouponsByType(Long Custid,CouponType type); 
	/**
	 * @param Custid
	 * @param Price
	 * @return
	* to get all purchased coupons filtered by price (abstract class)
	 */
	public Collection<Coupon> getAllPurchsasedCouponsByPrice(long Custid,float Price); 
	/**
	 * @param coup
	 *  to create a coupon with id  (abstract class)
	 */
	public void createCouponWithId(Coupon coup);
	/**
	 * @param Title
	 * @return a coupon
	 */
	public Coupon getCoupon(String Title);
	/**
	 * @param id
	 * @param type
	 * @return a coupons of company by its type
	 */
	public Collection<Coupon> getCouponByTypeCompany(long id,CouponType type);
	/**
	 * @param Title
	 * @return coupon id by its title
	 */
	public long getCouponId(String Title);
	
	/**
	 * @return
	 * get the expired coupon
	 */
	public ArrayList<Long> get_expired_coupon();
	
	/**
	 * delete expired coupon in coupon table and company_coupon and customer_coupon
	 */
	public void delete_expired_coupon(ArrayList<Long> list_Of_Expired_Coupon);
	

	
	
}
