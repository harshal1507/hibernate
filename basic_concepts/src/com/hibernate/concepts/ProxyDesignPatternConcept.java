package com.hibernate.concepts;

import java.util.Arrays;

public class ProxyDesignPatternConcept {

	// java.lang.Class
	
	/* 
	 * Class is a java class present in java.lang.package
	 * Class don't have public constructor so we can't create its object
	 * Class c = new Class(); -- not allowed
	 * 
	 * The object of "Class" class can hold any class name, interface name,
	 * enum name, annotation as data of the object in a running java application.
	 * 
	 */
	
	
	/*
	 * Ways to create object of java.lang.Class
	 * 
	 * a).  By using Class.forName(---)
	 * 		Class c1 = Class.forName("java.util.Date");
	 * 
	 * b).  By using getClass() method of java.lang.Object
	 * 		String str = new String("Hello World");
	 * 		Class c2 = str.getClass();
	 * 
	 * c).  By using class property
	 * 		Class c3 = System.class;
	*/
	
	
	
	/*
	 * PROXY DESIGN PATTERN
	 * 
	 * Proxy - It means dummy that looks like real. proxy internally uses real if required.
	 * 
	 * Components of proxy design pattern
	 * 		1. Component Interface
	 * 		2. Component concrete class
	 * 		3. Proxy class (Implements Component Interface and has component concrete class object)
	 * 		4. Factory class (Factory class will give the object of either proxy class or real class)
	*/
	
	public static void main(String[] args) {
		
		ProxyDesignPatternConcept obj = new ProxyDesignPatternConcept();
		ECommerceFactory eCommerceApplication = obj.new ECommerceFactory();
		
		String couponCode = "coder20";
		FlipkartService service = eCommerceApplication.getInstance(couponCode);
		float billAmount = service.buyNow(new String[] {"TV","Speaker"}, new float[] {40000.0f,5000.0f} );
		
		System.out.println(billAmount);
	}
	
	class FlipkartServiceProxy implements FlipkartService{

		private FlipkartService flipkartService;
		private float discountAmount;
		
		public FlipkartServiceProxy(float discountAmount) {
			flipkartService = new FlipkartServiceImpl();
			this.discountAmount = discountAmount;
		}
		
		@Override
		public float buyNow(String[] items, float[] prices) {
			float bill = 0;
			for(float p : prices) {
				bill += p;
			}
			return bill - discountAmount;
		}
		
	}
	
	interface FlipkartService{
		public float buyNow(String[] items, float[] prices);
	}
	
	class FlipkartServiceImpl implements FlipkartService{

		@Override
		public float buyNow(String[] items, float[] prices) {
			float bill = 0;
			for(float p : prices) {
				bill += p;
			}
			return bill;
		}
		
	}
	
	class ECommerceFactory {
		
		// it will either give real or proxy object
		public FlipkartService getInstance(String couponCode) {
			
			if(couponCode == null)
				return new FlipkartServiceImpl();
			
			if(couponCode.equals("") || couponCode.trim().length()==0) {
				return new FlipkartServiceImpl();
			}else {
				if(couponCode.equalsIgnoreCase("coder20")) {
					return new FlipkartServiceProxy(20);
				}else if(couponCode.equalsIgnoreCase("coder10")) {
					return new FlipkartServiceProxy(10);
				}else {
					return new FlipkartServiceImpl();
				}
			}
		}
	}
}
