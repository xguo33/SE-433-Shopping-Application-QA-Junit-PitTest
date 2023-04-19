package shoppingAppV4;

public class Customer {
	private String name;
	private String state;

	public Customer(String name,String state) {
		this.name=name;
		this.state=state;
	}
	
	public String getName() {
		return name;
	}
	
	public String getState() {
		return state;
	}
	
	public void setName(String newName) {
		this.name=newName;
	}
	
	public void setState(String newState) {
		this.state=newState;
	}
	
	
	public double tax() {
		if(state.equalsIgnoreCase("il") || state.equalsIgnoreCase("illnois") 
				|| state.equalsIgnoreCase("ny") || state.equalsIgnoreCase("newyork") 
				|| state.equalsIgnoreCase("ca") || state.equalsIgnoreCase("california") ) {
			return 0.06;
		}else {
			return 0.00;
		}
	}
	
}
