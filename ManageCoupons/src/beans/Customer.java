package beans;

import java.util.Collection;

public class Customer {
/**
 * 
 * class for the customer which contains all the information of the customer
 * and the function that is needed to get and set values of this object
 * 
 */
	private long id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;
	public Customer(long id, String custName, String password, Collection<Coupon> coupons) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}
	public Customer(long id, String custName, String password) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;

	}
	/**
	 * @param custName
	 * @param password
	 * a constructor for the customer
	 */
	public Customer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;

	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return
	 * get id of the customer
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id
	 * set id for the customer
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * getter and setters
	 * @return
	 */
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}
	
	
	
}
