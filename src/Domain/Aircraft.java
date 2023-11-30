package Domain;

public class Aircraft {
	private String ID;
	private int capacity;
	private String model;
	
	public Aircraft(String id, int cap, String mod) {
		ID = id;
		capacity = cap;
		model = mod;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void display() {
		System.out.println("Aircraft ID: " + ID);
		System.out.println("Capacity: " + capacity);
		System.out.println("Model: " + model);
	}

}
