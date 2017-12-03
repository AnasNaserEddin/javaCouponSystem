package sql.queries;

public class CompanySQLQueries {   
	
/**contains all the queries that are needid by company class */
	/**
	 * to get a company id by its title
	 */
	public static String GET_COMPANY_BY_TITLE = "SELECT Id FROM COMPANY WHERE COMP_NAME=?"; 
	/**
	 * select all coupons for a company
	 */
	public static String SELECT_ALL_COUPON = "SELECT Coupon.* FROM Coupon INNER JOIN Company_Coupon ON Coupon.Id = Company_Coupon.Coupon_Id WHERE COMP_ID=?";
	/**
	 * return all companies
	 */
	public static String SELECT_ALL_COMPANIES = "SELECT * FROM COMPANY";
	/**
	 * create a company
	 */
	public static String CREATE_COMPANY = "INSERT INTO COMPANY"
				+ "(COMP_NAME, PASSWORD, EMAIL) VALUES (?, ?, ?)";
	/**
	 * to remove a company by its name
	 */
	public static String REMOVE_COMPANY = "DELETE FROM COMPANY WHERE COMP_NAME=?";
	/**
	 * to return a company by its id
	 */
	public static String GET_COMPANY="SELECT * FROM COMPANY WHERE Id=?";
	/**
	 * to update a company
	 */
	public static String UPDATE_COMPANY="UPDATE COMPANY SET COMP_NAME=?,PASSWORD=?,EMAIL=? WHERE ID=?";
	/**
	 * to get a company by the logged in information and check if it is existed
	 */
	public static String chk_COMPANY="SELECT * FROM COMPANY WHERE COMP_NAME=? AND PASSWORD=?";
	/**
	 * insert coupon for the company in the table company_coupon
	 */
	public static String INSERT_COUPON="INSERT INTO COMPANY_COUPON (CUST_ID,COUPON_ID) VALUES(?,?)";
	/**
	 * return all coupons by its type
	 */
	public static String get_All_Coupons_By_Type="SELECT Coupon.*\r\n" + 
			"FROM  Coupon INNER JOIN Company_Coupon ON Coupon.Id = Company_Coupon.Coupon_Id where  Company_Coupon.Comp_Id=? and Type=?";
	
	}