package com.danamon.challengehibermart.dao;

import com.danamon.challengehibermart.config.HibernateConfig;
import com.danamon.challengehibermart.model.Customer;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public class CustomerDao {
    public Session session;

    public void insertCustomer(Customer customer) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteCustomer(Customer customer) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Customer getCustomerByID(int id) {
        Customer customer = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            customer = session.get(Customer.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return customer;
    }

    public List getAllCustomer() {
        List<Customer> list = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            //list = session.createQuery("FROM Costumer", Costumer.class).list();
            list = session.createCriteria(Customer.class).addOrder(Order.asc("id")).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {            //return session.createQuery("FROM Costumer", Costumer.class).list();
            session.close();
        }
        return list;
    }
}
