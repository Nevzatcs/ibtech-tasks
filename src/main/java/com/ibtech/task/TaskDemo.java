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
	public static void main(String[] args) throws Exception {

		String confFile = "resources/hibernate.cfg.xml";
		ClassLoader classLoader = TaskDemo.class.getClassLoader();
		File f = new File(classLoader.getResource(confFile).getFile());
		SessionFactory sessionFactory = new AnnotationConfiguration().configure(f).buildSessionFactory();
		Session session = sessionFactory.openSession();	

		DatabaseWriteOperations dbWriteOperations = new DatabaseWriteOperations(session);
		
		dbWriteOperations.createEntity(createCustomerObject());
		
		//Customer fromDb = dbWriteOperations.getEntity(Customer.class, 5L);
		
		//System.out.println("Read customer: " + fromDb.getFirstName());

		//dbWriteOperations.updateEntity(3L);
		
		//dbWriteOperations.deleteEntity(4L);

	}

	public static Customer createCustomerObject() {
		Customer customer = new Customer();
		customer.setFirstName("Vahdet");
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
		
		return customer;
	

	}
}
