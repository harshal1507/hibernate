package com.hibernate.concepts;

public class HibernateDialect_3 {

	/*
	 * Dialect 
	 * 		- responsible for query generation (query is generated while commiting the transaction)
	 * 		- component of hibernate framework which genrates below types of queries
	 * 
	 * There are two types of queries 
	 * 		1. Pre generated query / static queries
	 * 		2. Dynamically generated query
	 * 
	 * When SessionFactory object is created,
	 * dialect generates pre-generated SQL queries 
	 * for all the database tabls which are specified in hibernate mapping file
	 * 
	 * for example if we have 10 tables mentioned in mapping file the dialect will generate 10 query each of
	 * insert, update and delete having ID (primary key) as a criteria value
	 * 
	 * the queries which are printed in console which are generated by hibernate
	 * are known as pre-generated queries
	 * 
	 * Even if we insert 2 columns only then also pre generated query is executed and all th column values are inserted
	 * Default value in inserted for those columns which are not specified or set
	 * 
	 * 
	 * PROBLEM WITH PRE-GENERATED QUERY
	 * is that it involves all the column specified in the database
	 * 
	 * to solve, the above problem, we can ask dialect to generate dynamic SQL query
	 * 
	 * HOW TO GENERATE DYNAMIC SQL QUERIES?
	 * set dynamic-insert / dynamic-update = true
	 * 
	 * BUT THIS DYNAMIC QUERY GENERATION ONLY WORKS WITH WRAPPER CLASSES AND NOT WITH PRIMITIVE DATATYPES
	 * 
	 */
	
	/*
	 * Que - why we don't have dynamic-delete and dynamic-select attribute?
	 * Ans - Here to delete a record, we tend to delete all the column of that record, 
	 * and this delete operation performed on conditions, so that's why we don't need dyanmic-delete
	 * 
	 * For dynamic-select
	 * here session.get(--) , session.select(--) selects all the columns of that record
	 * so that's why we don't need dyanmic-select
	 * 
	 * But by using HQL, Native SQL, Criteria API, we can go for dynamic select
	 * 
	*/
	
	/*
	 * NOTE - to enjoy dynamic query generation feature, take your properties as wrapper classes in entity class
	 * 
	 * Dialect is useful in :- 
	 * 		1. generating optimized sql queries as required for underlying database.
	 * 		2. in assigning some important default properties to the hibernate configuration file 
	 * 		   that we are not specifying manually.
	 * 
	 * In most of the cases, dialect automatically picks the information based on the given JDBC details.
	 * 
	 * All dialects are hibernate-api supplied classes that are extending org.hibernate.dialect.Dialect
	 * 
	 * 
	 * How to configure dialect?
	 * put property as dialect in hibernate config file , specifying value of dialect
	 * if value specified the it will run with that compatible version otherwise it will run with latest version
	 * 
	 * 
	 * Dialect class changes from database and its versions that we are using
	 * 
	*/
}
