package com.danamon.challengehibermart.dao;

import com.danamon.challengehibermart.config.HibernateConfig;
import com.danamon.challengehibermart.model.Transaction;
import com.danamon.challengehibermart.model.TransactionDetail;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public class TransactionDao {
    public Session session;

    public void insertTransaction(Transaction transaction) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(transaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void updateTransaction(Transaction transaction) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(transaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteTransaction(Transaction transaction) {
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(transaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Transaction getTransactionByID(int id) {
        Transaction transaction = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.get(Transaction.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return transaction;
    }

    public List getAllTransaction() {
        List<Transaction> list = null;
        try {
            session = HibernateConfig.getSessionFactory().openSession();
            //list = session.createQuery("FROM Costumer", Costumer.class).list();
            list = session.createCriteria(Transaction.class).addOrder(Order.asc("id")).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }
}
