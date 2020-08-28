package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Customer order by lastName", Customer.class);

        List<Customer> customers= query.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session session = sessionFactory.getCurrentSession();

        //save if new update if id exist
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {

        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, theId);

        return customer;
    }

    @Override
    public void deleteCustomer(int theId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Customer where id =: customerId");

        query.setParameter("customerId", theId);

        query.executeUpdate();

    }
}