package beans;

import java.util.Date;

public class Coupon {
	/**
	 * contains all the data that is needed by the coupon 
	 * and get and set values of the object
	 * getter and setter
	 */
	
	
	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String Message;
	private double price;
	private String Image;
	public Coupon(long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	/**
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param message
	 * @param price
	 * @param image
	 * @param type
	 * constructor for the coupon
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, String message, double price,
			String image,CouponType type) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.Message = message;
		this.price = price;
		this.Image = image;
		this.type=type;
	}

	public Coupon( String title, Date startDate, Date endDate, int amount, String message, double price,
			String image,CouponType type) {
		super();
		
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		Message = message;
		this.price = price;
		Image = image;
		this.type=type;
	}
	
	public Coupon() {
		// TODO Auto-generated constructor stub
	}

		
	public CouponType getType() {
		return type;
	}

	/**
	 * @param type
	 * getter and setter
	 */
	public void setType(CouponType type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", Message=" + Message + ", price=" + price + ", Image=" + Image + "]";
	}
	
}
