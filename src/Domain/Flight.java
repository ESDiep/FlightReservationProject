package Domain;

import java.sql.Date;
import java.sql.Time;

public class Flight {
	private String flightID;
	private String origin;
	private String destination;
	private Date departDate;
	private Time departTime;
	private Date arrivalDate;
	private Time arrivalTime;
	private Seat[][] seatMap;
	private Aircraft aircraft;
	//private final int capacity = aircraft.getCapacity();
	private int seatBooked;
	private int seatEmpty;
	
	
	public Flight(String ID, String org, String des, Date dep, Time dept, Date arr, Time arrt, Aircraft air) {
		flightID = ID;
		origin = org;
		destination = des;
		departDate = dep;
		departTime = dept;
		arrivalDate = arr;
		arrivalTime = arrt;
		aircraft = air;
		seatBooked = 0;
		seatEmpty = aircraft.getCapacity();
		seatMap = new Seat[seatEmpty/6][6];		
	}
	
	public void displaySeatmap() {
		String space = "";
		System.out.println("       " + "A B C    D E F");
		for(int row = 0; row < seatMap.length; row++) {
			System.out.println("Row " + row + ": ");
			for(int col = 0; col < 6; col++) {
				if(col == 2)
					space = "    ";
				else
					space = "";
				System.out.print( seatMap[row][col] + " " + space);
			}
			
		}
	}
	
	public void book(int row, char aisle, float price) {
		
		String seatNum = String.valueOf(row) + aisle;
		int col = aisle - 'A';
		
		
		if(row > 5) //assume the first five row is business class;
			seatMap[row][col] = new Seat(seatNum, price, "economy");
		else
			seatMap[row][col] = new Seat(seatNum, price, "business");
		
		seatMap[row][col].select();
		
		//may need to write in the database
		
	}
	
	public boolean isFull() {
		return seatBooked == aircraft.getCapacity();
	}
	
	
	
}
