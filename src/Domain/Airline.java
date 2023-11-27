package Domain;
import java.util.LinkedList;
import java.sql.Date;
import java.sql.Time;

public class Airline {

	private String name;
	private LinkedList<Flight> flights;
	private LinkedList<Aircraft> aircrafts;
	private LinkedList<Employee> employees;
	private LinkedList<Customer> customers;
	
	public Airline(String name) {
		this.name = name;
		flights = new LinkedList<Flight>();
		aircrafts = new LinkedList<Aircraft>();
		employees = new LinkedList<Employee>();
		customers = new LinkedList<Customer>() ;
		
	}
	
	public void addFlight(Flight f) {
		flights.add(f);
	}
	
	public void addFlight(String ID, String org, String des, Date dep, Time dept, Date arr, Time arrt, Aircraft air) {
		Flight f = new Flight(ID, org, des, dep, dept, arr, arrt, air);
		flights.add(f);
	}
	
	public void removeFlight(Flight f) {
		flights.remove(f);
	}
	
	public Flight getFlight(Date date, String destination, String origin) {
		
	}
	
}
