package com.hibernate.concepts;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.concepts.entity.Product;

public class TestPersistMethod_2 {

	public static void main(String[] args) {
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Product product = null;
		
		config = new Configuration();
		System.out.println(config.getProperties());
		
		config.configure("com/hibernate/concepts/config/hibernate.cfg.xml");
		System.out.println(config.getProperties());
		
		sessionFactory = config.buildSessionFactory();
		System.out.println(sessionFactory.getClass());
		
		session = sessionFactory.openSession();
		System.out.println(session.getClass());
		
		product = new Product(2,"Laptop",1000f, 10f);
		
		try {
			
			// begins the transaction - can be of multiple types
			transaction = session.beginTransaction();
			
			// doesn't return anything
			// it is a JPA method
			// doesn't  supports generators
			// available in every ORM based framework
			session.persist(product);
			
			// all non select transactions should be committed 
			// actual query gets generated we commit function is called
			transaction.commit();
			System.out.println("Object is saved !!");
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.clear();
			sessionFactory.close();
		}
	}
}
