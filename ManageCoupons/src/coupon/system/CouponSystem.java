package coupon.system;

import java.sql.Array;
import java.sql.SQLException;

import beans.ClientType;
import coupon.client.facade.AdminFacade;
import coupon.client.facade.CompanyFacade;
import coupon.client.facade.CouponClientFacade;
import coupon.client.facade.CustomerFacade;

public class CouponSystem {

	private static CouponSystem INSTANCE;
	
	DailyCouponExpirationTask newTask;
	
	private CouponSystem() throws ClassNotFoundException, SQLException
	{
		
		this.newTask=new DailyCouponExpirationTask();
		Thread th = new Thread(newTask);
		
		th.start();
		
	}
	
	
	public synchronized static CouponSystem getInstance() throws ClassNotFoundException, SQLException
	{
		if (INSTANCE == null)
		{
			INSTANCE = new CouponSystem();
		}
		return INSTANCE;
	}
	
	public CouponClientFacade login(String name, String password, ClientType clientType) throws SQLException, ClassNotFoundException
	{
		CouponClientFacade result = null;
		switch (clientType)
		{
			case ADMINISTRATOR:
					result = new AdminFacade().login(name, password, clientType);
				break;
			case COMPANY:
				result = new CompanyFacade().login(name, password, clientType);
			break;
			case CUSTOMER:
				result = new CustomerFacade().login(name, password, clientType);
			break;				
				
		}
		return result;		
	}
	
	public void shutdown()
	{
		
		
	}
}