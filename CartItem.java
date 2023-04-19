package shoppingAppV4;

public class CartItem {
	
	private Product product;
	private int quantity;
	
	public CartItem(Product product, int quantity) {
		this.product=product;
		this.quantity=quantity;
	}
	
	public double getSubTotal() {
		return product.getProductPrice()*quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}

	@Override
	public String toString() {
		return product.toString() + ", quantity=" + quantity + ", sub total= $" + getSubTotal() ;
	}
	
	
	
}
