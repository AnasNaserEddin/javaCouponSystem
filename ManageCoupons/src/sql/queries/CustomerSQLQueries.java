package sql.queries;

/**
 * @author my
 * contains all the queries that are needed by customer class
 */
public class CustomerSQLQueries {						
	public static  String GET_COUPONS ="";
	public static String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
	public static String CREATE_CUSTOMER = "INSERT INTO CUSTOMER"
				+ "(CUST_NAME, PASSWORD) VALUES (?, ?)";
	public static String REMOVE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUST_NAME=?";
	public static String GET_CUSTOMER="SELECT * FROM Customer WHERE Id=?";
	public static String UPDATE_CUSTOMER="UPDATE CUSTOMER SET CUST_NAME=?,PASSWORD=? WHERE ID=?";
	public static String GET_MAX="SELECT  ISNULL(Max(Id),0)+1 AS ID from Customer";
	public static String chk_CUSTOMER="SELECT * FROM CUSTOMER WHERE CUST_NAME=? AND PASSWORD=?";
	public static String insert_COUPON="INSERT INTO CUSTOMER_COUPON (CUST_ID,COUPON_ID) VALUES(?,?)";
	public static String get_All_Coupons="SELECT    Customer_Coupon.Cust_Id,Customer_Coupon.Coupon_Id  ,Coupon.Title, Coupon.Amount, Coupon.Type, Coupon.Message, Coupon.Price\r\n" + 
			"FROM  Coupon INNER JOIN Customer_Coupon ON Coupon.Id = Customer_Coupon.Coupon_Id where  Customer_Coupon.Cust_Id=?";
	public static String get_All_Coupons_By_Type="SELECT    Customer_Coupon.Cust_Id,Customer_Coupon.Coupon_Id  ,Coupon.Title, Coupon.Amount, Coupon.Type, Coupon.Message, Coupon.Price\r\n" + 
			"FROM  Coupon INNER JOIN Customer_Coupon ON Coupon.Id = Customer_Coupon.Coupon_Id where  Customer_Coupon.Cust_Id=? and Type=?";
	public static String get_All_Coupons_By_Price="SELECT    Customer_Coupon.Cust_Id,Customer_Coupon.Coupon_Id  ,Coupon.Title, Coupon.Amount, Coupon.Type, Coupon.Message, Coupon.Price\r\n" + 
			"FROM  Coupon INNER JOIN Customer_Coupon ON Coupon.Id = Customer_Coupon.Coupon_Id where  Customer_Coupon.Cust_Id=? and Price=?";
}
