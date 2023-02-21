package com.ibtech.task;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibtech.task.model.Customer;

public class DatabaseWriteOperations {
	
	 private Session session;

	public DatabaseWriteOperations(Session session) {
		this.session = session;
	}
	 

	  public <T> void createEntity(T entity) throws Exception {
		  Transaction tx = null;
		  try {
				tx = session.beginTransaction();
				session.save(entity);
				tx.commit();
				System.out.println("Record saved succesfully...");

			} catch (Exception e) {
				tx.rollback();
				throw e;
			} finally {
				session.close();
				} 
		  
	  }

	  public <T> void updateEntity(Long id) throws Exception {
	    
	    Customer customer = (Customer) session.get(Customer.class, new Long(id));
		customer.setFirstName("Veli");
		customer.setModifyDate(new Date());
		
		org.hibernate.Transaction tx = null;

		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(customer);
			tx.commit();
			System.out.println("Record updated succesfully...");

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
			} 
	  }

	  public <T> void deleteEntity(Long id) throws Exception {
	
	    Customer customer = (Customer) session.get(Customer.class, new Long(id));
		
		org.hibernate.Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(customer);
			session.beginTransaction().commit();
			System.out.println("Record deleted succesfully...");

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
			}  
	  }
	  
	  public <T> T getEntity(Class<T> clazz, Long id) {
		    return (T) session.get(clazz, id);
		  }
	  
	  public Customer read(Long id) {
			return getEntity(Customer.class, id);
		}
}
