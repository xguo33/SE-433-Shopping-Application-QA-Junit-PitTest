package shoppingAppV4;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Computer;



public class ShoppingAppTest {

	
	
	

/*
 * Xu : test cartitem class
 */
	    @Test
	    public void testGetSubTotal() {
	        Product product = new Product("Apple", 1.5);
	        CartItem item = new CartItem(product, 3);
	        double expectedSubTotal = 4.5;
	        assertEquals(expectedSubTotal, item.getSubTotal(), 0.0);
	    }

	    @Test
	    public void testGetProduct() {
	        Product product = new Product("Apple", 1.5);
	        CartItem item = new CartItem(product, 3);
	        assertEquals(product, item.getProduct());
	    }

	    @Test
	    public void testGetQuantity() {
	        Product product = new Product("Apple", 1.5);
	        CartItem item = new CartItem(product, 3);
	        assertEquals(3, item.getQuantity());
	    }

	    @Test
	    public void testSetQuantity() {
	        Product product = new Product("Apple", 1.5);
	        CartItem item = new CartItem(product, 3);
	        item.setQuantity(5);
	        assertEquals(5, item.getQuantity());
	    }

	    @Test
	    public void testToString1() {
	        Product product = new Product("Apple", 1.5);
	        CartItem item = new CartItem(product, 3);
	        String expectedString = "product name=Apple, price=$1.5, quantity=3, sub total= $4.5";
	        assertEquals(expectedString, item.toString());
	    }
	    
	    /*
	     * Xu: test Product class
	     */
	    
	    @Test
	    public void testProduct() {
	        String expectedName = "Computer";
	        double expectedPrice = 423.22;
	        Product p1 = new Product(expectedName, expectedPrice);

	        
	        String actualName = p1.getProductName();
	        double actualPrice = p1.getProductPrice();

	      
	        assertEquals(expectedName, actualName);
	        assertEquals(expectedPrice, actualPrice, 0.001);
	    }
	    
	    @Test
		public void testGetProductPrice() {
			Product product = new Product("Apple", 1.5);
			assertEquals(1.5, product.getProductPrice(),0.01);
			
		}

	    @Test
	    public void testSetProductName() {
	       
	        String expectedName = "Television";
	        double price = 800.00;
	        Product p1 = new Product("Computer", 423.22);
	        p1.setProductName(expectedName);
	        String actualName = p1.getProductName();
	        assertEquals(expectedName, actualName);
	    }

	    @Test
	    public void testSetProductPrice() {
	        String name = "Computer";
	        double expectedPrice = 500.00;
	        Product p1 = new Product(name, 423.22);
	        p1.setProductPrice(expectedPrice);
	        double actualPrice = p1.getProductPrice();
	        assertEquals(expectedPrice, actualPrice, 0.001);
	    }

	    @Test
	    public void testToString() {
	        
	        String expectedString = "product name=Computer, price=$423.22";
	        Product p1 = new Product("Computer", 423.22);
	        String actualString = p1.toString();
	        assertEquals(expectedString, actualString);
	    }

	    @Test
	    public void testCompareTo() {
	        
	        Product p1 = new Product("Computer", 423.22);
	        Product p2 = new Product("Mouse", 15.99);
	        Product p3 = new Product("Computer", 799.99);

	       
	        int result1 = p1.compareTo(p2);
	        int result2 = p1.compareTo(p3);
	        int result3 = p2.compareTo(p3);

	        
	        assertTrue(result2 == 0);
	        
	    }
	    
	    /*
	     * xu: shopping cart
	     */
	    private ShoppingCart shoppingCart;
	    private Product p1;
	    private Product p2;

	    @Before
	    public void setUp() throws Exception {
	        Customer customer = new Customer("John Doe", "IL");
	        shoppingCart = new ShoppingCart(customer);
	        p1 = new Product("Computer", 889.22);
	        p2 = new Product("Phone", 799.00);
	    }

	    @Test
	    public void addItem_validProduct_addsItemToCart() throws ShoppingCartException {
	        shoppingCart.addItem(p1, 1);
	        assertEquals(1,shoppingCart.getCount() );
	    }
	    


	    /*
	    @Test(expected = ShoppingCartException.class)
	    public void addItem_nullProduct_throwsException() throws ShoppingCartException {
	        shoppingCart.addItem(null, 1);
	    }

	    @Test(expected = ShoppingCartException.class)
	    public void addItem_negativeQuantity_throwsException() throws ShoppingCartException {
	        shoppingCart.addItem(p1, -1);
	    }*/

	    @Test
	    public void removeItem_validProductName_removesItemFromCart() throws ShoppingCartException {
	        shoppingCart.addItem(p1, 1);
	        shoppingCart.removeItem("Computer");
	        assertFalse(shoppingCart.getShoppingList().contains(new CartItem(p1, 1)));
	    }

	 

	   

	    @Test
	    public void getTax_validCartItems_calculatesCorrectTax() throws ShoppingCartException {
	        shoppingCart.addItem(p1, 1);
	        shoppingCart.addItem(p2, 1);
	        assertEquals(101.2932, shoppingCart.getTax(), 0.01);
	    }

	    @Test
	    public void getTotal_validCartItems_calculatesCorrectTotal() throws ShoppingCartException {
	        shoppingCart.addItem(p1, 2);
	        shoppingCart.addItem(p2, 1);
	        assertEquals(2577.44, shoppingCart.getTotal(), 0.01);
	    }

	    @Test /*XU: test nextday*/
	    public void setShippingMethod_Nextday() throws ShoppingCartException {
	        
	    	shoppingCart.setSend("NEXTDAY");
	        assertEquals(ShoppingCart.Shipping.NEXTDAY,shoppingCart.getTime());
	        assertEquals(25,shoppingCart.getTrans());
	    }
	    
	    @Test /*xu: TEST STANDARD*/
	    public void setShippingMethod_Standard() throws ShoppingCartException {
	        shoppingCart.setSend("STANDARD");
	        assertEquals(ShoppingCart.Shipping.STANDARD,shoppingCart.getTime());
	    }
	    
	    @Test/*xu:when costumer input invalid shipping method*/
	    
	    public void setShippingMethod_Null() {
	    	try {
				shoppingCart.addItem(p1, 1);
				shoppingCart.addItem(p2, 2);
				shoppingCart.setSend("oioi");
			} catch (Exception e) {
				assertEquals(e.getMessage(), "invalid shipping method");
			}
	    }
	    @Test /*xu: test when total over 50, the trans should be 0*/
	    public void setShippingMethod_Standard_fee() throws ShoppingCartException {
	    	shoppingCart.addItem(p1, 1);
	        shoppingCart.addItem(p2, 1);
	    	shoppingCart.setSend("STANDARD");
	        assertEquals(0,shoppingCart.getTrans());
	    }
	    
	    /*public void setShippingMethod_Standard_fee1() throws ShoppingCartException {
	    	 p1.setProductPrice(45.00);
	    	shoppingCart.addItem(p1, 1);
	       
	    	shoppingCart.setSend("STANDARD");
	        assertEquals(10,shoppingCart.getTrans());
	    }*/



	   /* @Test(expected = IllegalArgumentException.class)
	    public void setShippingMethod_null() throws ShoppingCartException {
	        shoppingCart.setSend(null);
	        
	    }*/
	    /*xu test standard fee*/
	    
	    /*public void setShippingMethod_Standard_fee1() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart shoppingCart = new ShoppingCart(customer);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        shoppingCart.addItem(apple, 1);
	        shoppingCart.addItem(cereal,2);
	        shoppingCart.setSend("STANDARD");
	        assertEquals(10, shoppingCart.getTrans());
	    
	    }*/
	    
	    
	    @Test/*xu test when Item is null*/
		public void testAddNullItem() {
			//when Item is null
			Customer customer=new Customer("July","il");
			ShoppingCart shoppingCart=new ShoppingCart(customer);
			
			try {
				shoppingCart.addItem(null, 2);
				fail();
			} catch (ShoppingCartException e) {
				assertEquals(e.getMessage(),"Invaild product!");
			}
		}
	    
	    @Test/*xu：test shoppingcart calss editQuantity（） method*/
	    public void edit() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart shoppingCart = new ShoppingCart(customer);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(output));
	        shoppingCart.addItem(apple, 1);
	        shoppingCart.addItem(cereal,2);
	        shoppingCart.editQuantity("Test Item 1: Apple", 2);
	        assertEquals(4, shoppingCart.getCount());
	        assertEquals("You have 1 Test Item 1: Apple in the cart."+ System.lineSeparator()
	        		+ "You have 2 Test Item 2: Cereal in the cart."+ System.lineSeparator()
	        		+ "You have update: 2 Test Item 1: Apple in the cart."+ System.lineSeparator(), output.toString());
	        
	    }
	    
	    @Test /*xu：test shoppingcart calss print（） method*/
	    public void testprint() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart shoppingCart = new ShoppingCart(customer);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        
	        shoppingCart.getCustomer();
	        shoppingCart.addItem(apple, 1);
	        shoppingCart.addItem(cereal,2);
	        shoppingCart.setSend("NEXTDAY");
	        shoppingCart.getTax();
	        shoppingCart.getTime();
	        shoppingCart.getTrans();
	        shoppingCart.checkOut();
	        
	        
	        
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(output));
	        shoppingCart.print();
	        /*
	         * if you are using ox or linux, try first one
	         * or simply move /r
	         */
	        /*
	         * assertEquals("product name=Test Item 1: Apple, price=$10.0, quantity=1, sub total= $10.0\n"
	         
	        		+ "product name=Test Item 2: Cereal, price=$25.0, quantity=2, sub total= $50.0\n"
	        		+ "count= 3 , total= $ 60.00 , tax= 3.60 , trans= 25, cost= $88.60\n", output.toString());
	    	*/
	        assertEquals("product name=Test Item 1: Apple, price=$10.0, quantity=1, sub total= $10.0"+ System.lineSeparator()
	        		+ "product name=Test Item 2: Cereal, price=$25.0, quantity=2, sub total= $50.0"+ System.lineSeparator()
	        		+ "count= 3 , total= $ 60.00 , tax= 3.60 , trans= 25, cost= $88.60"+System.lineSeparator(),output.toString());

	    
	    }
	    
	    
	    @Test//test clear（）
	    public void testEmpty() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart emptycart = new ShoppingCart(customer);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        emptycart.addItem(apple, 1);
	        emptycart.addItem(cereal,2);
	        emptycart.clearCart();
	        
	        assertEquals(0, emptycart.getCount());// we empty cart so nothing inside
	        
	    
	    }
	    
	   
	    
	    
	    @Test/*
	    xu:test Gettrans()*/
	    public void testGettrans() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart cart = new ShoppingCart(customer);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        cart.addItem(apple, 1);
	        cart.addItem(cereal, 1);
	        cart.setSend("NEXTDAY");
	        
	    	
	    }
	    
	   
		@Test//xu: test Customer class:100% coverage
	    public void testCustomer() {
	    	Customer customer =new Customer("July", "IL");
	    	
	    	assertEquals("July", customer.getName());
	    	assertEquals("IL", customer.getState());
	    	assertEquals(0.06, customer.tax(),0.01);
	    	
	   
	    	
	    }
		@Test//test Customer class
	    public void testCustomer2() {
	    	Customer customer =new Customer("July", "IL");
	    	customer.setName("Mark");
	    	customer.setState("NY");
	    	assertEquals("Mark", customer.getName());
	    	assertEquals("NY", customer.getState());
	    	assertEquals(0.06, customer.tax(),0.01);
	    	
	    }
			
		
		@Test/*xu:test the state don't need to pay tax*/
		public void testCustomerTaxWithInvalidState() {
			Customer customer = new Customer("June", "TX");
			assertEquals(0.00, customer.tax(), 0.001);
		}   
		
		@Test//xu:test Order class
	    public void testOrder() throws ShoppingCartException {
	    	Customer customer = new Customer("Josh", "IL");
	    	ShoppingCart shoppingCart = new ShoppingCart(customer);
	    	Order order =new Order(customer, shoppingCart);
	    	Product apple = new Product("Test Item 1: Apple", 10.00);
	        Product cereal = new Product("Test Item 2: Cereal", 25.00);
	        Order order2 =null;
	        try {
				order2 = shoppingCart.checkOut();
			} catch (Exception e) {
				e.getMessage();
			}
	       
	    }
		    
		/**
		 *   Lin:Test with Checkout exception. The minium purchase of sp should be $11 
		 *   @throws ShoppingCartException
		 */
		/*@Test(expected= ShoppingCartException.class)
		public void testCheckoutException() throws ShoppingCartException {
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 0.20);
	        
	        sc.addItem(apple,1);
	       
	        sc.getCost();
	        
	        ShoppingCartException thrown=assertThrows(ShoppingCartException.class,() -> sc.checkOut(), "cost  must be in 1 to 99999.99!");

		}*/
		
		@Test/*xu modify*/
		public void testCheckoutException()  {
		    Customer customer = new Customer("Steven", "IL");
		    ShoppingCart sc = new ShoppingCart(customer);
		    Product apple = new Product("Apple", 0.20);
		    
		     
		    try {
		    	sc.addItem(apple, 1);
				sc.checkOut();
			} catch (Exception e) {
				// TODO: handle exception
				assertEquals(e.getMessage(),"cost must be in 1 to 99999.99!") ;
			}
		    
		    
		   
		    
		}
		
		
		/**
		 * 	Lin:Test with checkout with more than 99999.99 checkout price 
		 */
		@Test(expected= ShoppingCartException.class)
		public void testCheckOutLargeIn() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.checkOut();
		}
		
		
		/**
		 * 	Lin:Test editQuantity with null input, Expected exceptions
		 */
		@Test(expected= ShoppingCartException.class)
		public void testEditQuantitywNull() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.editQuantity(null,1);
		}
		
		/**
		 * 	Lin:Test editQuantity with String input but negative quantity, Expected exceptions
		 */
		@Test(expected= ShoppingCartException.class)
		public void testEditQuantitwNQ() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.editQuantity("Apple",-1);
		}
		
		
		/**
		 * 	Lin:Test editQuantity with Not found exceptions , Expected exceptions
		 */
		@Test(expected= ShoppingCartException.class)
		public void testEditQuantitwNotFound() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.editQuantity("Banana",1);
		}
		
		/**
		 * 	Lin:Test removeItem() with input null, Expected exceptions
		 */
		@Test(expected= ShoppingCartException.class)
		public void testremoveItemWnull() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.removeItem(null);
		}
		
		
		
		/**
		 * 	Lin:Test removeItem() with not found exceptions, Expected exceptions
		 */
		@Test(expected= ShoppingCartException.class)
		public void testremoveItemWnotFound() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.removeItem("Banana");
		}
		
		/**
		 * 	Lin: Test addItem() with 0 quantity exceptions, expected exceptions
		 */
		
		@Test(expected= ShoppingCartException.class)
		public void testaddItemWbadQuantity() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        Product banana= new Product("Banana",1000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.addItem(banana,0);
		}
		
		
		/**
		 * 	Lin: Test addItem() with repeat item plus bad quantity, expected exceptions
		 */
		
		@Test(expected= ShoppingCartException.class)
		public void testaddItemWrepeateBadQuantity() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        Product banana= new Product("Banana",1000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.addItem(apple,-2);
		}
		
		
		/**
		 * 	Lin: Test addItem() with repeat item plus good quantity, expected exceptions
		 */
		
		@Test
		public void testaddItemWrepeatGoodQuantity() throws ShoppingCartException{
			Customer customer = new Customer("Steven", "IL");
	    	ShoppingCart sc = new ShoppingCart(customer);
	    	Order order =new Order(customer, sc);
	    	Product apple = new Product("Apple", 50000);
	        Product cereal = new Product("Cereal", 50000);
	        Product banana= new Product("Banana",1000);
	        sc.addItem(apple,1);
	        sc.addItem(cereal,1);
	        sc.addItem(apple,3);
		}
		
		/*
		 * xu:base on pit test add test case
		 * 1.replaced return value with Collections.emptyList for shoppingAppV3/ShoppingCart::getShoppingList → SURVIVED
		 */
		 @Test
		    public void testGetShoppingList() {
		      
			 Customer customer = new Customer("Steven", "IL");
		    	ShoppingCart cart = new ShoppingCart(customer);
		        cart.getShoppingList();
		        List<CartItem> result = cart.getShoppingList();
		        
		        // Verify that the result is an empty list
		        assertEquals(0, result.size());
		    }
		 /*
		  * Xu： PIT line87：change conditional boundary
		  * 
		  */
		 @Test
		 public void testEditQuantity() throws ShoppingCartException {
		     // Add some items to the cart
		     Product apple = new Product("Apple", 1.00);
		     shoppingCart.addItem(apple, 1);

		     // Test negative quantity
		     assertThrows(ShoppingCartException.class, () -> shoppingCart.editQuantity("Apple", -1));

		     // Test non-existent product
		     assertThrows(ShoppingCartException.class, () -> shoppingCart.editQuantity("Banana", 2));

		     // Test valid edit
		     shoppingCart.editQuantity("Apple", 2);
		     assertEquals(2, shoppingCart.getCount());
		     
		 }

}

	

	

	
	
	
	




