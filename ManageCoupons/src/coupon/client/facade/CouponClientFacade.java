package coupon.client.facade;

import java.sql.SQLException;

import beans.ClientType;
/**
 * 
 * @author my
 *abstract class for login will be inherited by the admin and customer login
 */
public interface CouponClientFacade  {
	CouponClientFacade login(String name, String password, ClientType clientType) throws SQLException;
	
}
