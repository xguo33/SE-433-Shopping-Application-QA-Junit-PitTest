package shoppingAppV4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



import java.util.Iterator;


public class ShoppingCart {
	
	private Customer customer;	
	private List<CartItem> shoppingList;
	private Shipping time;
	enum Shipping{
		STANDARD,NEXTDAY
	}
	
	
	
	public ShoppingCart(Customer customer) {
		this.customer=customer;
		shoppingList=new ArrayList<CartItem>();
		time=Shipping.STANDARD;
	}
	
	
	public void addItem(Product product,int num) throws ShoppingCartException {
		//product ==null throw  Exception
		if(product==null)throw new ShoppingCartException("Invaild product!");
		boolean isFind=false;
		for(int i=0;i<shoppingList.size();i++) {
			CartItem curItem=shoppingList.get(i);
			if(curItem.getProduct().compareTo(product)==0) {
				//todo:throw  Exception
				if(curItem.getQuantity()+num <1)
					throw new ShoppingCartException("Invaild num!");
				curItem.setQuantity(curItem.getQuantity()+num);
				System.out.println("You have "+curItem.getQuantity()+" "+product.getProductName()+" in the cart.");
				isFind=true;
				break;
			}
		}
		if(!isFind) {
			//todo:throw  Exception
			if(num <1)
				throw new ShoppingCartException("invaild num!");
			CartItem item=new CartItem(product,num);
		    shoppingList.add(item);
		    System.out.println("You have "+item.getQuantity()+" "+product.getProductName()+" in the cart.");
		}
	}
	
	
	public void removeItem(String productName) throws ShoppingCartException {
		//productName ==null throw  Exception
		if(productName==null)
			throw new ShoppingCartException("invaild product name!");
		boolean isFind=false;
		for(int i=0;i<shoppingList.size();i++) {
			CartItem curItem=shoppingList.get(i);
			if(curItem.getProduct().getProductName().equals(productName)) {
				shoppingList.remove(curItem);
				isFind=true;
				System.out.println("You have remove"+" "+ productName +" in the cart.");				
				break;				
			}
		}
		
		if(!isFind) {
			//todo: throw  Exception
			throw new ShoppingCartException("Product name does not exist!");
		}
	}
	
	public void editQuantity(String productName,int quantity) throws ShoppingCartException {
		//productName ==null throw  Exception
		if(productName==null)
			throw new ShoppingCartException("Invaild product name!");
		if(quantity < 1)
			throw new ShoppingCartException("Quantity can not less than 1!");
		boolean isFind=false;
		for(int i=0;i<shoppingList.size();i++) {
			CartItem curItem=shoppingList.get(i);
			if(curItem.getProduct().getProductName().equals(productName)) {
				curItem.setQuantity(quantity);
				isFind=true;
				System.out.println("You have update: "+quantity+" "+ productName +" in the cart.");				
				break;				
			}
		}
		
		if(!isFind) {
			//todo: throw  Exception
			throw new ShoppingCartException("I could not find a product to modify the quantity!");
			//System.out.println("I could not find a product to modify the quantity.");
		}
		
	}
	
	public void setSend(String way) throws ShoppingCartException {
		if(way.equals("NEXTDAY")) {
			time=Shipping.NEXTDAY;
		}else if(way.equals("STANDARD")) {
			time=Shipping.STANDARD;
		}else {
			throw new ShoppingCartException("invalid shipping method");
		}
	}	
	public void clearCart() {
		shoppingList.clear();
	}	
	
	public void print() {
		Iterator<CartItem> iter=shoppingList.iterator();
		while(iter.hasNext()) {
			CartItem item=iter.next();
			System.out.println(item.toString());
		}
		System.out.printf("count= %d , total= $ %.2f , tax= %.2f , "
				+ "trans= %d, cost= $%.2f"+System.lineSeparator(),getCount(),getTotal(),getTax(),getTrans(),getCost());
		
	}
	
	public int getCount() {
		int count=0;
		for(int i=0;i<shoppingList.size();i++) {
			CartItem curItem=shoppingList.get(i);
			count+=curItem.getQuantity();
		} 
		return count;
	}
	
	public double getTotal() {
		double total=0;
		for(int i=0;i<shoppingList.size();i++) {
			CartItem curItem=shoppingList.get(i);
			total+=curItem.getSubTotal();
		}
		return total;
	}
	
	public double getTax() {
		return getTotal()*customer.tax();
	}
	
	public int getTrans() {
		if(time.equals(Shipping.NEXTDAY)) {
			return 25;
		}else {
			if(getTotal()>50) {
				return 0;
			}else {
				return 10;
			}
		}
	}
	
	public double getCost() {
		return getTotal()+getTax()+getTrans();
	}
	
	public Order checkOut() throws ShoppingCartException {
		if (getCost() >= 1 && getCost() <= 99999.99) {

			Order order = new Order(customer, this);
			System.out.println("TRANSACTION COMPLETED! THANK YOU FOR SHOPPING WITH US.");

			return order;
		}else {
			throw new ShoppingCartException("cost  must be in 1 to 99999.99!");		
		}
	}


	public Customer getCustomer() {
		return customer;
	}


	public List<CartItem> getShoppingList() {
		return shoppingList;
	}


	public Shipping getTime() {
		return time;
	}
	

	public static void main(String[] args) throws ShoppingCartException {
		ArrayList<String> pList=new ArrayList<String>();
		pList.add("MacBook");
		pList.add("iPhone");
		pList.add("AirPod");
		pList.add("iPad");
		pList.add("iWatch");
		pList.add("iTV");
		
		
		Scanner scan = new Scanner( System.in );
		System.out.println("HELLO! PLEASE ENTER YOUR FIRST NAME TO START: ");
		String userIn=scan.nextLine();
		System.out.println("HI "+userIn.toUpperCase()+", PLEASE ENTER YOUR STATE: ");
		String userSt=scan.nextLine();
		System.out.println("Let's get start, "+userIn.toUpperCase()+" from "+userSt.toUpperCase()+". ");
		
		Customer c1=new Customer(userIn,userSt);
		ShoppingCart sc=new ShoppingCart(c1);
		Order o1=new Order(c1,sc);
		Product p1=new Product("MacBook",1399.99);
		Product p2=new Product("iPhone",999.99);
		Product p3=new Product("AirPod",123.99);
		Product p4=new Product("iPad",621.99);
		Product p5=new Product("iWatch",399.00);
		Product p6=new Product("iTV",129.00);
		
	
		boolean menu=true;
		while(menu) {
			System.out.println("\n Now, you can do the following options: \n [A] - Add an item to ShoppingCart "
					+ "\n [B] - Remove an item from your ShoppingCart \n [C] - Edit an item's quantity \n [D] - View All items in Cart"
					+ "\n [E] - Remove everything from your Cart \n [F] - Shipping \n [G] - Checkout \n [S] - EXIT ");
			
			String userSelect=scan.nextLine();
			if(userSelect.equals("S")) {
				menu=false;
				System.exit(0);
				System.out.println("-------------------------------");
				System.out.println("EXIT FROM THE SHOPPING PROGRMA.");
			}else if(userSelect.equals("A")) {
				System.out.println("FOLLOWING PRODUCTS ARE IN STOCK: ");
				for(int i=0; i<pList.size();i++) {
					System.out.println("["+(i+1)+"] - "+pList.get(i));
				}
				System.out.println("Adding item by entering number(1 to 6) or [-1] to EXIT:");
				int itemNum=scan.nextInt();
				if(itemNum==-1) {
					break;
				}else if(itemNum==1) {
					sc.addItem(p1,1);
				}else if(itemNum==2) {
					sc.addItem(p2,1);
				}else if(itemNum==3) {
					sc.addItem(p3,1);
				}else if(itemNum==4) {
					sc.addItem(p4,1);
				}else if(itemNum==5) {
					sc.addItem(p5,1);
				}else if(itemNum==6) {
					sc.addItem(p6,1);
				}else {
					System.out.println("Please enter valid number!");
				}
			}else if(userSelect.equals("B")){
				System.out.println("You have following items in the cart: ");
				List<CartItem> ls=sc.getShoppingList();
				int sizelength=ls.size();
				for(int i=0;i<sizelength;i++) {
					System.out.println("["+(i+1)+"] - "+ls.get(i));
				}
				System.out.println("To remove an item by entering (1 to "+sizelength+") or [-1] to EXIT:");
				int itemNum=scan.nextInt();
				if(itemNum==-1) {
					break;
				}
				String seleItem="";
				if(itemNum>=1 && itemNum<=sizelength) {
					CartItem item=ls.get(itemNum-1);
					Product p=item.getProduct();
					seleItem=p.getProductName();
					sc.removeItem(seleItem);
				}else {
					System.out.println("Please enter valid number!");
				}
			}else if(userSelect.equals("C")){
				System.out.println("You have following items in the cart: ");
				List<CartItem> ls=sc.getShoppingList();
				int sizelength=ls.size();
				for(int i=0;i<sizelength;i++) {
					System.out.println("["+(i+1)+"] - "+ls.get(i));
				}
				String seleItem="";
				int quantity=0;
				System.out.println("To Select an item by entering (1 to "+sizelength+") or [-1] to EXIT:");
				int itemNum=scan.nextInt();
				if(itemNum==-1) {
					break;
				}else {
					CartItem item=ls.get(itemNum-1);
					Product p=item.getProduct();
					seleItem=p.getProductName();
				}
				System.out.println("Please typing the new quantity from 1 - 999");
				quantity=scan.nextInt();
				sc.editQuantity(seleItem,quantity);
			}else if(userSelect.equals("D")){
				sc.print();
			}
				
			
			else if(userSelect.equals("E")) {
				sc.clearCart();
				System.out.println("You have remove everything from your cart, please start a new cart : )");
			}else if(userSelect.equals("G")) {
				if(sc.getTotal()>=1) {
					sc.print();
					
					System.out.println("-----------------------INVOICE-------------------------");
					
					sc.getTotal();
					
					
					sc.checkOut();

					menu=false;
					System.exit(0);
				}else {
					System.out.println("You cart are EMPTY! Please add ITEMS.");
				}
			}else if (userSelect.equals("F")) {
				System.out.println("Please enter the shipping method： NEXTDAY or STANDARD");
				String ship = scan.nextLine();
				sc.setSend(ship);
			
			}else {
		
			}
		}
	}
}
