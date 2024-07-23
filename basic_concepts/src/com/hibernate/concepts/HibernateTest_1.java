package com.hibernate.concepts;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.concepts.entity.Product;

public class HibernateTest_1 {

	public static void main(String[] args) {
		
		// configuration
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Product product = null;
		
		config = new Configuration();
		System.out.println(config.getProperties());
		
		
		// loads only configuration file and not mapping file
		// if path not provided then it will check in source folder by name - hibernate.cfg.xml
		config.configure("com/hibernate/concepts/config/hibernate.cfg.xml");
		System.out.println(config.getProperties());
		
		
		// based on builder design pattern
		// checks the mapping file and loads it, also collects name of mapping file and map the properties to object
		// dialect is responsible for generating queries
		// generates objects related to data source, dialect, transaction manager, generators etc
		// SessionFactory is interface and we get implementation of SessionFactoryImpl
		sessionFactory = config.buildSessionFactory();
		System.out.println(sessionFactory.getClass());
		
		
		// Session is also interface and provides implementation of class type SessionImpl
		// A Session is used to get a physical connection with a database
		
		// The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed
		// collects connection object through data source object from session factory from its JDBC connection pool
		// also collects mapping file meta data and dialect info to create SessionImpl object internally having all specified objects
		session = sessionFactory.openSession();
		System.out.println(session.getClass());
		
		product = new Product(21,"Laptop",1000f, 10f);
		boolean flag = false;
		
		try {
			
			// begins the transaction - can be of multiple types
			transaction = session.beginTransaction();
			
			// product gets saved in session (L1 cache)
			// supports generators (12 types)
			// Returns Serializable Wrapper class
			
			// Once generator is defined then user defined primary key ID is of no use
			// put entity in L1 cache
			// it is a hibernate method
			int retunedValue = (int) session.save(product);
			System.out.println(retunedValue);
			flag = true;
			System.out.println("Object is saved !!");
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(flag) {
				// all non select transactions should be committed 
				// actual query gets generated we commit function is called
				System.out.println("Below commiting transaction");
				transaction.commit();
				System.out.println("After commiting transaction the query will be generated");
			}else {
				transaction.rollback();
			}
			session.clear();
			sessionFactory.close();
		}
		
	}
}
