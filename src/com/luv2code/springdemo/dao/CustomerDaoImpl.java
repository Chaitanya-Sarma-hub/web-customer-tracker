package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springdemo.entity.Customer;

@SuppressWarnings("deprecation")
@Repository
public class CustomerDaoImpl implements CustomerDao {

//	need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

//		get current hibernate session
		Session session = sessionFactory.getCurrentSession();

//		create a query
		Query<Customer> query = session.createQuery("from Customer order by firstName", Customer.class);

//		execute query and get a result
		List<Customer> customers = query.getResultList();

//		return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

//		get current hibernate session
		Session session = sessionFactory.getCurrentSession();

//		save/update the customer
		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int customerId) {

//		get hibernate session
		Session session = sessionFactory.getCurrentSession();

//		now retrieve/read data from database using primary key
		Customer customer = session.get(Customer.class, customerId);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", theId);
		query.executeUpdate();
	}

}
