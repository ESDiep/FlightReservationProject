package Domain;

public class Seat {
	private String seatNum; //e.g. 16F	
	private float basedPrice;
	private boolean booked;
	private String sClass;
	private float discountRate;
	
	//constructors
	public Seat() {
		this(null, 0f, "economy");
	}
	
	public Seat(String num, float price, String sClass) {
		seatNum = num;
		basedPrice = price;
		this.sClass = sClass;
		booked = false;
		discountRate = 0;
	}
	
	//getter
	public String getSeatNum() {
		return seatNum;
	}
	
	public float getBasedPrice(){
		return basedPrice;
	}
	
	public String getsClass() {
		return sClass;
	}
	
	public boolean getStatus() {
		return booked;
	}
	
	//set booked to true when the seat is selected
	public void select() {
		booked = true;
	}
	
	public void setDiscountRate(float discount) {
		discountRate = discount;
	}
	public float price() {
		
		float premium  = 1.0f;
		
		//assume business class is 1.5 * base price
		if(sClass.toLowerCase().equals("business"))
			premium = 1.5f;
		
		return basedPrice * (1- discountRate) * premium;
	}
	
	public void display() {
		System.out.println("Seat number: " + seatNum);
		System.out.println("Class: " + sClass);
		System.out.println("Price: " + this.price());
	}
	
	public String toString() {
		if (booked)
			return "X";
		else
			return "_";
	}
}
