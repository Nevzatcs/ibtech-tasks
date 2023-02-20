package com.ibtech.task;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.ibtech.task.enumeration.AdressType;
import com.ibtech.task.enumeration.Currency;
import com.ibtech.task.enumeration.Status;
import com.ibtech.task.model.Account;
import com.ibtech.task.model.Address;
import com.ibtech.task.model.Customer;
import com.ibtech.task.model.Phone;

public class TaskDemo {
	public static void main(String[] args) {

		String confFile = "resources/hibernate.cfg.xml";
		ClassLoader classLoader = TaskDemo.class.getClassLoader();
		File f = new File(classLoader.getResource(confFile).getFile());
		SessionFactory sessionFactory = new AnnotationConfiguration().configure(f).buildSessionFactory();
		Session session = sessionFactory.openSession();

		//saveCustomerRecord(session);
		//updateCustomerRecord(session);
		//deleteCustomerRecord(session);

	}

	private static void saveCustomerRecord(Session session) {
		Customer customer = new Customer();
		customer.setFirstName("Mehmet");
		customer.setLastName("Can");
		customer.setCreateDate(new Date());
		customer.setTckn("11111111110");

		Address homeAddress = new Address();
		homeAddress.setCity("Istanbul");
		homeAddress.setCountry("Turkey");
		homeAddress.setDistrict("Kadikoy");
		homeAddress.setCustomer(customer);
		homeAddress.setType(AdressType.HOME);

		Address workAddress = new Address();
		workAddress.setCity("Kocaeli");
		workAddress.setCountry("Turkey");
		workAddress.setDistrict("Gebze");
		workAddress.setCustomer(customer);
		workAddress.setType(AdressType.WORK);

		Phone phone = new Phone();
		phone.setCountryCode("+90");
		phone.setNumber("5312341234");
		phone.setCustomer(customer);

		Account account = new Account();
		account.setBalance(2000d);
		account.setCurrency(Currency.TRY);
		account.setCustomer(customer);
		account.setIban("1111");
		account.setStatus(Status.ACTIVE);

		customer.setAccounts(Collections.singletonList(account));
		customer.setPhones(Collections.singletonList(phone));
		customer.setAdresses(Arrays.asList(homeAddress, workAddress));

		org.hibernate.Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(customer);
			session.beginTransaction().commit();
			System.out.println("Record saved succesfully...");

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
			} 

	}

	private static void deleteCustomerRecord(Session session) {
		int id = 2;
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		
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

	private static void updateCustomerRecord(Session session) {
		int id = 3;
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		customer.setFirstName("Ali");
		customer.setModifyDate(new Date());
		
		org.hibernate.Transaction tx = null;

		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(customer);
			session.beginTransaction().commit();
			System.out.println("Record updated succesfully...");

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
			} 
	}

}
