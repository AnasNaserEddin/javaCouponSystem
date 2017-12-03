package DAO;

import java.sql.SQLException;
import java.util.Collection;

import beans.Company;
import beans.Coupon;

public interface CompanyDAO {

	/**
	 * @param c
	 * to create a company (abstract class)
	 * @throws SQLException 
	 */
	public void createCompany(Company c) throws SQLException;
	/**
	 * @param c
	 * to remove a company (abstract class)
	 */
	public void removeCompany(Company c) throws SQLException;
	/**
	 * @param c
	 * to update a company (abstract class)
	 */
	public void updateCompany(Company c) throws SQLException;
	/**
	 * @param Id
	 * @return Company
	 *  to return a company (abstract class)
	 */
	public Company getCompany(long Id) throws SQLException;
	/**
	 * @return
	 *  to get all companies (abstract class)
	 */
	public Collection<Company> getAllCompanies() throws SQLException;
	/**
	 * @param CompId
	 * @return
	 * get a coupons purchased by a company
	 */ 
	public Collection<Coupon> getCoupons(long CompId) throws SQLException;
	/**
	 * @param compName
	 * @param password
	 * @return 
	 * login for the company
	 */
	public boolean login(String compName,String password) throws SQLException;
	long getCompanyId(String Title) throws SQLException;

}