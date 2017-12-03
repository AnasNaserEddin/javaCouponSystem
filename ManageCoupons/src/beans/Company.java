package beans;

import java.util.Collection;

public class Company {
	
	/**
	 * parameters for class company
	 */
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	public Company() {
		super();
	}
	/**
	 * @param id
	 * @param compName
	 * @param password
	 * @param email
	 * @param coupons
	 * company constructor
	 */
	public Company(long id, String compName, String password, String email, Collection<Coupon> coupons) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}
	public Company(String string, String string2, String string3, Collection<Coupon> list) {
		// TODO Auto-generated constructor stub
		super();
		this.compName = string;
		this.password = string2;
		this.email = string3;
		this.coupons = list;
	}
	/**
	 * @return
	 * to get the id of the object
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id
	 * to set the id of the object
	 */
	public void setId(long id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}

	
	
	
}
