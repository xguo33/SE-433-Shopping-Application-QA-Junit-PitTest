package shoppingAppV4;

import java.util.Date;
import java.util.List;


public class Order {
	private Customer customer;	
	private List<CartItem> shoppingList;
	private String time;
	private int count;
	private double total;
	private double tax;
	private int trans;
	private double cost;
	private Date date;
	
	public Order(Customer customer,ShoppingCart shoppingCart) {
		this.customer=customer;
		this.shoppingList=shoppingCart.getShoppingList();
		this.time=shoppingCart.getTime().name();
		count=shoppingCart.getCount();
		total=shoppingCart.getTotal();
		tax=shoppingCart.getTax();
		trans=shoppingCart.getTrans();
		cost=shoppingCart.getCost();
		date=new Date();
	}
}
