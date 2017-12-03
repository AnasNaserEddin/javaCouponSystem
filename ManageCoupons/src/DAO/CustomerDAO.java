package DAO;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.Customer;
public interface CustomerDAO {

	/**
	 * @param c
	 * create a customer
	 */
	public void createCustomer(Customer c);
	//Collection<Company> getAllCompanies();
	/**
	 * @param c
	 * update an existed customer
	 */
	public void updateCustomer(Customer c);
	/**
	 * @param c
	 * to remove a customer
	 */
	public void removeCustomer(Customer c);
	/**
	 * @param Id
	 * @return
	 * to get a customer by id
	 */
	public Customer getCustomer(long Id);
	/**
	 * @return
	 * to get all the customers
	 */
	public Collection<Customer> getAllCustomer();
	/**
	 * @return
	 * to get the coupons of a specified custoemr
	 */
	public Collection<Coupon> getCoupons();
	/**
	 * @param custName
	 * @param password
	 * @return
	 * check the login of customer
	 */
	public boolean login(String custName,String password);
	/**
	 * @param c
	 * for test purpose to add a customer with Id
	 */
	public void createCustomerWithId(Customer c);
	/**
	 * @param c
	 * @param coupon
	 * customer purchase a coupon
	 */
	public void purchaseCoupon(long c,Coupon coupon);
}
