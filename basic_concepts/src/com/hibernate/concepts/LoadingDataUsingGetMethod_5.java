package com.hibernate.concepts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.concepts.entity.Product;

public class LoadingDataUsingGetMethod_5 {

	// loading the data or record from database and storing it in entity class object
	
	// an entity is representing a database table
	// object of entity class is representing 1 record in database table
	
	// we give objects to hibernate then internally to JDBC and then save it to database 
	// in a similar fashion we get a record back from database
	
	
	/*
	 * To fetch data we can use two methods
	 * 
	 * 1. session.get(-- , --)
	 * 2. session.load(-- , --)
	 * 
	*/
	
	/*
	 * session.get(--,--)
	 * 
	 * 1. It performs the eager loading of the object  -- puts load on database
	 * 		a. eager loading meaning - whether you use the object or not, get method will load the object
	 * 
	 * 
	 * session.load(--,--)
	 * 
	 * 1. It can perform both eager or lazy loading of the object at once
	 * 		a. when you are using the object it will load
	 * 		b. by default lazy loading is true - we can change also
	 * 
	*/
	
	/*
	 * session.get(--,--) signature
	 * <T> T get(Class<T entityType, Serializable id)
	 * 
	 * This <T> means the type of class.
	 * When we need the entity object from the get(--,--) then we will pass the require Entity class
	 * name in our running java application and then it will process it and returns the instance of the same<T>
	 * (same entity class object which we have passed in get(--,--) method as paramter)
	 * 
	 * example - session.get(Product.class,1);
	 * 
	 * 
	 * session.get(--,--) method does not need object of entity class to pass in its param
	 * It only needs the name of Entity class as (java.lang.Class) object
	 * 
	 * 
	 * 
	 * example - session.get(Product.class,1);
	 * As the passed ID is of type int then it will automatically covert to Integer wrapper class (Autoboxing concept)
	 * As serializable ID type is required
	 * 
	 * Wrapper classes are seriazed by default
	 * 
	*/
	
	
	/* 
	 * INTERNAL CODE OVERVIEW
	 * 
	 * Product obj = session.get(Product.class,1);
	 * It hits the database, the moment when it runs and tries to find the record and store it into Entity class object
	 * 
	 * DB ---> JDBC ---> Entity Object
	 * 
	 * 
	 * PreparedStatement ps = con.prepareStatement(pregenerated select query);
	 * this query is generated at the time of SessionFactory obj creation
	 * 
	 * ps.setInt(1,1);
	 * 
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * Product product = Class.forName("Product").newInstance(); 
	 * 
	 * while(rs.next()){
	 * 		product.setInt(rs.getPId());
	 * 		product.setName(rs.getName()):
	 * 		product.setQty(rs.getQty());
	 * 		product.setPrice(rs.getPrice());
	 * }
	 * 
	 * 
	*/
	
	
	public static void main(String[] args) {
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction txn = null;
		
		config = new Configuration();
		config.configure("com/hibernate/concepts/config/hibernate.cfg.xml");
		
		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
		
		txn = session.beginTransaction();
		
		// At this line only query will hit the database
		Product productObject = session.get(Product.class, 1);
		
		System.out.println(productObject.toString());
	}
	
}
