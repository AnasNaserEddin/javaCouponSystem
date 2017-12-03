

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import coupon.client.facade.AdminFacade;
import coupon.client.facade.CompanyFacade;
import coupon.client.facade.CouponClientFacade;
import coupon.client.facade.CustomerFacade;
import coupon.system.CouponSystem;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Connect to DataBase using MSSQL JDBC driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		String dbURL = "jdbc:sqlserver://localhost;databaseName=CouponDB;integratedSecurity=true;";
		Connection conn = DriverManager.getConnection(dbURL);
		if (conn != null) {
		    System.out.println("Connected");
		}
        
		
		
		
        Statement stmt = conn.createStatement();
        ResultSet rs;

        
        
        // (1) INSERT
    	String insertTableSQL = "INSERT INTO test1"
				+ "(ID, name) " + "VALUES"
				+ "(3, 'Or')";
    	stmt.executeUpdate(insertTableSQL);   	
    	
    	// -- Print
    	System.out.println("-----");
        rs = stmt.executeQuery("SELECT * FROM test1");
        while ( rs.next() ) {
            String Id = rs.getString("ID");
            String name = rs.getString("name");
            System.out.println(Id + " , " + name);
        }    	
    	              
        // (2) UPDATE
        String sql = "UPDATE test1 " +
                "SET name = 'new1' WHERE id=3";
        stmt.executeUpdate(sql);
        
    	// -- Print
    	System.out.println("-----");
        rs = stmt.executeQuery("SELECT * FROM test1");
        while ( rs.next() ) {
            String Id = rs.getString("ID");
            String name = rs.getString("name");
            System.out.println(Id + " , " + name);
        }        
        
        // (3) REMOVE
    	String removeTableSQL = "DELETE FROM test1 where name LIKE 'new1'";
    	stmt.executeUpdate(removeTableSQL);
    	       
        stmt = conn.createStatement();        
        
    	// -- Print
    	System.out.println("-----");
        rs = stmt.executeQuery("SELECT * FROM test1");
        while ( rs.next() ) {
            String Id = rs.getString("ID");
            String name = rs.getString("name");
            System.out.println(Id + " , " + name);
        }
        
		System.out.println(123);
		
		Customer c=new Customer("ansa","1234");
		CustomerDBDAO c1=new CustomerDBDAO();
		Customer c2=new Customer("Sameh","2345");
		// c1.createCustomer(c);
		// c1.createCustomer(c2);
		
		 Collection<Customer> arrlist=c1.getAllCustomer();
		 for(Customer a : arrlist)
		 {
			 
			System.out.println(a.getId()+" "+a.getCustName()+" "+a.getPassword());
			
		 }
		 
//		 Customer c3=new Customer(1,"anas","1234");
//		 c1.updateCustomer(c3);
//		Customer c4= c1.getCustomer(2);
//		System.out.println(c4.getId()+" "+c4.getCustName()+" "+c4.getPassword() );
		Date sd=new Date(17/9/2018);

		 Coupon cou=new Coupon("new coupon without getmax", sd, sd, 150, "hello", 219, "",CouponType.Health);
		 Collection list=new ArrayList<Coupon>();
		 list.add(cou);
		 CouponDBDAO cop=new CouponDBDAO();
		 cop.createCoupon(cou);
		 System.out.println("coupon added successfully");
		 Company c6=new Company("check adm login","a234","ans@gmail.com",list);	 
		 CompanyDBDAO com=new CompanyDBDAO();
		 com.createCompany(c6);
		 System.out.println(String.valueOf(ClientType.COMPANY));
		 System.out.println(CouponType.Health);
		 try
		 {
		 AdminFacade adm=new AdminFacade();
		 adm=(AdminFacade)adm.login("admin", "1234", ClientType.ADMINISTRATOR);
		 adm.createCompany(c6);
		 Company todelete=adm.getCompany(6);
		 adm.removeCompany(todelete);
		 }
		
		 catch(NullPointerException e)
		 {
			System.out.println("check admin name and password");
		 }
		
		 try
		 {
			 System.out.println("***************************************************************enter company login***********************************************************************");
			 CouponSystem Companysy=CouponSystem.getInstance();
		     CouponClientFacade cou2=(CompanyFacade) Companysy.login("check adm login", "a234", ClientType.COMPANY);
			   Coupon coompanycoupon=  ((CompanyFacade)cou2).getCoupon(1);
		  ((CompanyFacade)cou2).removeCoupon(coompanycoupon);
		   System.out.println("removed successfully*********************************************************");
//			System.out.println(coompanycoupon.getTitle()+ " amount "+coompanycoupon.getAmount()+" "+coompanycoupon.getType());
			 Coupon couponCom=new Coupon("you will do it ", sd, sd, 100, "yes", 100, "",CouponType.Electricity);
			 ((CompanyFacade)cou2).updateCoupon(1, couponCom);
			//((CompanyFacade)cou2).createCoupon(((CompanyFacade)cou2).getId(),couponCom);
			Collection<Coupon> coupons=((CompanyFacade)cou2).getAllCoupon(((CompanyFacade)cou2).getId());
			for(Coupon a : coupons)
			{
				
				System.out.println(a.getTitle());
			}
			
			Collection<Coupon> coupon=((CompanyFacade)cou2).getCouponByTypeCompany(((CompanyFacade)cou2).getId(),CouponType.Electricity);
			for(Coupon a : coupon)
			{
				
				System.out.println(a.getTitle()+" "+a.getType());
			}
			 System.out.println("***************************************************************************************************************************************");

			// comp.createCoupon(cou);
		 }
		catch(NullPointerException e)
		 {
			System.out.println(e.getMessage());
			 System.out.println("check company name and password");
		 }
		 
		 
		 try
		 {
//			 CustomerFacade cust=new CustomerFacade();
//			 cust=(CustomerFacade) cust.login("Sameh", "2345", ClientType.CUSTOMER);
			 CouponSystem customersy=CouponSystem.getInstance();
		     CouponClientFacade cou2= customersy.login("ansa", "1234", ClientType.CUSTOMER);
		     System.out.println("try wrong login *************************************************************************");
		     //((CustomerFacade)cou2).getAllPurchsasedCoupons( ((CustomerFacade)cou2).getId());
		     System.out.println("try wrong login *************************************************************************");

		     Long id= ((CustomerFacade)cou2).getId();
		     System.out.println("the id of this customer is "+id);
		   Coupon coup=cop.getCoupon(8);
		     ((CustomerFacade)cou2).purchaseCoupon(id,coup);
		     System.out.println("success login"+" customer "+id+" purchase coupon "+ coup.getId());
		    ArrayList<Coupon> coupons= (ArrayList<Coupon>) ((CustomerFacade)cou2).getAllPurchsasedCoupons(id);
	    	 for(int i=0;i<coupons.size();i++)
	    	{
	    		 System.out.println(coupons.get(i).getId()+" "+coupons.get(i).getTitle()+" "+coupons.get(i).getAmount()+" "+coupons.get(i).getType());
	    	}
	    	 
	    	 ArrayList<Coupon> couponsType= (ArrayList<Coupon>) ((CustomerFacade)cou2).getAllPurchsasedCouponsByType(id, CouponType.Health);
	    	 for(int i=0;i< couponsType.size();i++)
	    	{
	    		 System.out.println(couponsType.get(i).getId()+" "+couponsType.get(i).getTitle()+" "+couponsType.get(i).getAmount()+" "+couponsType.get(i).getType());
	    	}
	    	 
	    	 ArrayList<Coupon> couponsPrice= (ArrayList<Coupon>) ((CustomerFacade)cou2).getAllPurchsasedCouponsByPrice(id,219);
	    	 for(int i=0;i< couponsPrice.size();i++)
	    	{
	    		 System.out.println(couponsPrice.get(i).getId()+" "+couponsPrice.get(i).getTitle()+" "+couponsPrice.get(i).getAmount()+" "+couponsPrice.get(i).getType()+" "+couponsPrice.get(i).getPrice());
	    	}
	//	cust.purchaseCoupon(cust1,coup);
	     
		 }
		 catch(NullPointerException e)
		 {
			System.out.println("check customer name and password");
		 }
		 
		 
		 
		 
		 
	}

}