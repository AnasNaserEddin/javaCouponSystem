package coupon.client.facade;

import java.sql.SQLException;
import java.util.Collection;

import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import beans.ClientType;
import beans.Company;
import beans.Customer;



/**
 * @author my
 *Admin control the company and coupon classes by remove or add company or coupon ....
 */
public class AdminFacade implements CouponClientFacade {

	private CompanyDBDAO companyDBdao;
	private CustomerDBDAO CustomerDBdao;
	
	
	
	public AdminFacade()
	{
		
	}
	/* (non-Javadoc)
	 * @see coupon.client.facade.CouponClientFacade#login(java.lang.String, java.lang.String, beans.ClientType)
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		
		if (name.equals("admin") && password.equals("1234"))
		{
			System.out.println("successlogin");
			companyDBdao = new CompanyDBDAO();
			CustomerDBdao = new CustomerDBDAO();
			return this;
		}
		else
		{
			System.out.println("login failed");
		    return null;
		}
	}
	
	/**
	 * @param c 
	 * admin create a new company
	 */
	public void createCompany(Company c)
	{
		try {
			companyDBdao.createCompany(c);
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @return 
	 * to get all companies
	 * @throws SQLException 
	 */
	public Collection<Company> getAllCompanies() throws SQLException
	{
		return companyDBdao.getAllCompanies();	
	}
	
	/**
	 * @param c
	 * to remove a company
	 * @throws SQLException 
	 */
	public void removeCompany(Company c) throws SQLException
	{
		companyDBdao.removeCompany(c);
	}

	/**
	 * @param c
	 * to update a company
	 * @throws SQLException 
	 */
	public void updateCompany(Company c) throws SQLException
	{
		companyDBdao.updateCompany(c);
	}
	
	
	/**
	 * @param id
	 * to get a company
	 * @return 
	 * @throws SQLException 
	 */
	public Company getCompany(long id) throws SQLException
	{
		return companyDBdao.getCompany(id);
	}
	
	/**
	 * @param c
	 * to create a customer
	 */
	public void createCustomer(Customer c)throws SQLException
	{
		CustomerDBdao.createCustomer(c);
	}
	
	/**
	 * @param c
	 * to remove a customer
	 */
	public void removeCustomer(Customer c)throws SQLException
	{
		CustomerDBdao.removeCustomer(c);
	}
	
	/**
	 * @param CustId
	 * to get a customer
	 */
	public void getCustomer(long CustId)throws SQLException
	{
		CustomerDBdao.getCustomer(CustId);
	}
	
	/**
	 * @return
	 * to get all customers
	 */
	public Collection<Customer> getAllCustomers()throws SQLException
	{
		return CustomerDBdao.getAllCustomer();	
	}
	
	
	
	
	

}