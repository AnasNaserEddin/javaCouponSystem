package coupon.system;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import DAO.CompanyDAO;
import DAO.CouponDAO;
import DAO.CustomerDAO;
import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;

public class DailyCouponExpirationTask implements Runnable {

	private boolean quit=true;

	private CouponDBDAO CouDBDAO;

	private ArrayList<Long> listofCoupons;
	public DailyCouponExpirationTask() throws ClassNotFoundException, SQLException
	{
		this.CouDBDAO=new CouponDBDAO();
		
	}

	@Override
	public void run() {
		while(quit)
		{
			listofCoupons = new ArrayList<Long>();
			listofCoupons =CouDBDAO.get_expired_coupon();
			CouDBDAO.delete_expired_coupon(listofCoupons);
		}
		
	}
	
	public void stopTask()
	{
		this.stopTask();
	}
	
}
