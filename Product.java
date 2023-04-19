package shoppingAppV4;

import java.util.List;

public class Product implements Comparable<Product> {
	private String productName;
	private double productPrice;
	
	public Product( String productName, double productPrice) {
		this.productName=productName;
		this.productPrice=productPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	

	public double getProductPrice() {
		return productPrice;
	}
	
	public void setProductName(String newName) {
		this.productName=newName;
	}
	
	public void setProductPrice(double newPrice) {
		this.productPrice=newPrice;
	}
	
	
	
	@Override
	public String toString() {
		return "product name=" + productName + ", price=$" + productPrice;
	}

	/*public static void main(String[] args) {
		Product p1=new Product("Computer", 423.22);
		String x=p1.getProductName();
		Double y=p1.getProductPrice();
		System.out.println("PName: "+x+", Price: "+y);
	}*/

	@Override
	public int compareTo(Product o) {
		if(this.productName.equals(o.productName)) {
			return 0;
		}
		return this.productName.compareTo(o.productName);
	}
}
