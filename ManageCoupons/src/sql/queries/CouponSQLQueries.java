package sql.queries;

public class CouponSQLQueries {                              
	

/**contains all the queries that are needed by coupon class */
	public static  String REMOVE_COUPON_FOR_COMPANY_COUPON_COMPANY_ID = "delete from company_coupon where Comp_Id=?";
	public static String REMOVE_COUPON_FOR_CUSTOMER_COMPANY ="delete from customer_coupon where coupon_id in (select Coupon_Id from Company_Coupon where Comp_Id=?)";
	public static String SELECT_ALL_COUPON = "SELECT * FROM COUPON";
	public static String GET_COUPON = "SELECT * FROM COUPON WHERE Id=?";
	public static String GET_COUPON_BY_TITLE = "SELECT Id FROM COUPON WHERE Title=?";
	public static String CREATE_COUPON = "INSERT INTO COUPON"
			+ "(TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE) VALUES (?,?,?,?,?,?,?,?)";
	public static String REMOVE_COUPON = "DELETE FROM COUPON WHERE Id=?";
	public static String UPDATE_COUPON="UPDATE COUPON SET TITLE=?,MESSAGE=?,IMAGE=?,START_DATE=?,END_DATE=?,AMOUNT=?,PRICE=?,TYPE=? WHERE ID=?";
	public static String GET_MAX="SELECT  ISNULL(Max(Id),0)+1 AS ID from Coupon";
	public static String AmountLessOne="UPDATE COUPON SET AMOUNT=? WHERE ID=?";
	public static String REMOVE_COUPON_FOR_CUSTOMER = "DELETE FROM Customer_Coupon WHERE Coupon_Id=?";
	public static String REMOVE_COUPON_FOR_COMPANY_COUPON_COUPON_ID = "delete from company_coupon where COUPON_ID=?";
	public static String GET_EXPIRED_COUPON="SELECT Id FROM Coupon where GETDATE()>Coupon.End_Date";
	public static String DELETE_EXPIRED_COUPON_COM_COU="DELETE FROM CUSTOMER_COUPON WHERE COUPON_ID IN ("+String.join(",", "?") + ")";
	public static String DELETE_EXPIRED_COUPON_CUS_COU="DELETE FROM COMPANY_COUPON WHERE COUPON_ID IN ("+String.join(",", "?") + ")";
	public static String DELETE_EXPIRED_COUPON_COUPON="DELETE FROM COUPON WHERE ID IN ("+String.join(",", "?") + ")";
	public static String GET_COUPON_BY_TYPE= "SELECT Id FROM COUPON WHERE Type=?";
}
