package com.danamon.challengehibermart.dao;

import com.danamon.challengehibermart.config.HibernateConfig;
import com.danamon.challengehibermart.model.Customer;
import com.danamon.challengehibermart.model.Product;
import com.danamon.challengehibermart.model.Transaction;
import com.danamon.challengehibermart.model.TransactionDetail;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public class TransactionDetailDao {
    public Session sessionTD;
    public Session sessionTDGet;

    public void insertTransactionDetail(TransactionDetail transactionDetail) {
        try {
            sessionTD = HibernateConfig.getSessionFactory().openSession();
            sessionTD.beginTransaction();

            Transaction currentT = transactionDetail.getTransaction();
            Product currentP = transactionDetail.getProduct();

            int currentTTotalQuality = currentT.getTotalQuantity();
            int currentTTotalPrice = currentT.getTotalPrice();

            int currentPPrice = currentP.getProductPrice();
            int currentPStock = currentP.getStock();

            transactionDetail.setCurrentProductPrice(currentPPrice);
            transactionDetail.setSubtotal(currentPPrice*transactionDetail.getQuantity());

            sessionTD.save(transactionDetail);

            //updating current transaction
            currentT.setTotalPrice(currentTTotalPrice + (currentPPrice*transactionDetail.getQuantity()));
            currentT.setTotalQuantity(currentTTotalQuality + transactionDetail.getQuantity());

            //updating current product
            currentP.setStock(currentPStock - transactionDetail.getQuantity());

            ProductDao productDao = new ProductDao();
            TransactionDao transactionDao = new TransactionDao();

            productDao.updateProduct(currentP);
            transactionDao.updateTransaction(currentT);

            sessionTD.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionTD.getTransaction().rollback();
        } finally {
            sessionTD.close();
        }
    }

    public void updateTransactionDetail(TransactionDetail transactionDetail) {
        try {
            sessionTD = HibernateConfig.getSessionFactory().openSession();
            sessionTD.beginTransaction();

            TransactionDetailDao transactionDetailDao = new TransactionDetailDao();

            int referencedID = transactionDetail.getId();
            TransactionDetail referencedTD = transactionDetailDao.getTransactionDetailByID(referencedID);

            Transaction previousT = referencedTD.getTransaction();
            Transaction nextT = transactionDetail.getTransaction();
            Product previousP = referencedTD.getProduct();
            Product nextP = transactionDetail.getProduct();


            int previousTTotalQuality = previousT.getTotalQuantity();
            int previousTTotalPrice = previousT.getTotalPrice();
            int nextTTotalQuality = nextT.getTotalQuantity();
            int nextTTotalPrice = nextT.getTotalPrice();

            int previousPPrice = previousP.getProductPrice();
            int previousPStock = previousP.getStock();
            int nextPPrice = nextP.getProductPrice();
            int nextPStock = nextP.getStock();

            transactionDetail.setCurrentProductPrice(nextPPrice);
            transactionDetail.setSubtotal(nextPPrice*transactionDetail.getQuantity());

            sessionTD.update(transactionDetail);

            //updating previous and next transaction
            int previousTNewTotalPrice = previousTTotalPrice - (previousPPrice*referencedTD.getQuantity());
            int previousTNewTotalQuality = previousTTotalQuality - referencedTD.getQuantity();
            previousT.setTotalPrice(previousTTotalPrice);
            previousT.setTotalQuantity(previousTNewTotalQuality);
            //Check if the updated Transaction Detail still refer the same Transaction
            if(previousT.getId()==nextT.getId()){
                nextT.setTotalPrice(previousTNewTotalPrice + (nextPPrice*transactionDetail.getQuantity()));
                nextT.setTotalQuantity(previousTNewTotalQuality + transactionDetail.getQuantity());
            }
            else{
                nextT.setTotalPrice(nextTTotalPrice + (nextPPrice*transactionDetail.getQuantity()));
                nextT.setTotalQuantity(nextTTotalQuality + transactionDetail.getQuantity());
            }


            //updating previous and next product            nextP.setStock(nextPStock - transactionDetail.getQuantity());
            int previousPNewStock = previousPStock + referencedTD.getQuantity();
            previousP.setStock(previousPNewStock);
            //Check if the updated Transaction Detail still refer the same Product
            if(previousP.getId()==nextP.getId()){
                nextP.setStock(previousPNewStock - transactionDetail.getQuantity());
            }
            else{
                nextP.setStock(nextPStock - transactionDetail.getQuantity());
            }

            ProductDao productDao = new ProductDao();
            TransactionDao transactionDao = new TransactionDao();

            productDao.updateProduct(previousP);
            transactionDao.updateTransaction(previousT);
            productDao.updateProduct(nextP);
            transactionDao.updateTransaction(nextT);

            sessionTD.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionTD.getTransaction().rollback();
        } finally {
            sessionTD.close();
        }
    }

    public void deleteTransactionDetail(TransactionDetail transactionDetail) {
        try {
            sessionTD = HibernateConfig.getSessionFactory().openSession();
            sessionTD.beginTransaction();

            Transaction currentT = transactionDetail.getTransaction();
            Product currentP = transactionDetail.getProduct();

            int currentTTotalQuality = currentT.getTotalQuantity();
            int currentTTotalPrice = currentT.getTotalPrice();

            int currentPPrice = currentP.getProductPrice();
            int currentPStock = currentP.getStock();

            transactionDetail.setCurrentProductPrice(currentPPrice);
            transactionDetail.setSubtotal(currentPPrice*transactionDetail.getQuantity());

            sessionTD.delete(transactionDetail);

            //updating current transaction
            currentT.setTotalPrice(currentTTotalPrice - (currentPPrice*transactionDetail.getQuantity()));
            currentT.setTotalQuantity(currentTTotalQuality - transactionDetail.getQuantity());

            //updating current product
            currentP.setStock(currentPStock + transactionDetail.getQuantity());

            ProductDao productDao = new ProductDao();
            TransactionDao transactionDao = new TransactionDao();

            productDao.updateProduct(currentP);
            transactionDao.updateTransaction(currentT);

            sessionTD.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sessionTD.getTransaction().rollback();
        } finally {
            sessionTD.close();
        }
    }

    public TransactionDetail getTransactionDetailByID(int id) {
        TransactionDetail transactionDetail = null;
        try {
            sessionTDGet = HibernateConfig.getSessionFactory().openSession();
            transactionDetail = sessionTDGet.get(TransactionDetail.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sessionTDGet.close();
        }
        return transactionDetail;
    }

    public List getAllTransactionDetail() {
        List<TransactionDetail> list = null;
        try {
            sessionTDGet = HibernateConfig.getSessionFactory().openSession();
            //list = session.createQuery("FROM Costumer", Costumer.class).list();
            list = sessionTDGet.createCriteria(TransactionDetail.class).addOrder(Order.asc("id")).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sessionTDGet.close();
        }
        return list;
    }
}

