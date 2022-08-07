package com.danamon.challengehibermart.dao;

import com.danamon.challengehibermart.config.HibernateConfig;
import com.danamon.challengehibermart.model.Product;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public class ProductDao {
    public Session session;

    public void insertProduct(Product product) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void updateProduct(Product product) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteProduct(Product product) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Product getProductByID(int id) {
        Product product = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            product = session.get(Product.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return product;
    }

    public List getAllCostumer() {
        List<Product> list = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            //list = session.createQuery("FROM Costumer", Costumer.class).list();
            list = session.createCriteria(Product.class).addOrder(Order.asc("id")).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {            //return session.createQuery("FROM Costumer", Costumer.class).list();
            session.close();
        }
        return list;
    }
}
