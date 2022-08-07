package com.danamon.challengehibermart;

import com.danamon.challengehibermart.dao.CustomerDao;
import com.danamon.challengehibermart.dao.ProductDao;
import com.danamon.challengehibermart.dao.TransactionDao;
import com.danamon.challengehibermart.dao.TransactionDetailDao;
import com.danamon.challengehibermart.model.Customer;
import com.danamon.challengehibermart.model.Product;
import com.danamon.challengehibermart.model.Transaction;
import com.danamon.challengehibermart.model.TransactionDetail;


public class Main {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        TransactionDao transactionDao = new TransactionDao();
        TransactionDetailDao transactionDetailDao = new TransactionDetailDao();
        ProductDao productDao = new ProductDao();

        /*
        Picking "Alpha" as the selected costumer for the first transaction:
        */
//        Customer costumer =  customerDao.getCustomerByID(1);
//        Transaction transaction = new Transaction(costumer);
//        transactionDao.insertTransaction(transaction);

        /*
        (CREATE) Picking the transaction that has been created before.
        In this case, the first transaction detail will be purchasing 10 Iguana Soup
        */
//        Transaction transaction1 = transactionDao.getTransactionByID(1);
//        Product product1 = productDao.getProductByID(8);
//        TransactionDetail transactionDetail = new TransactionDetail(transaction1,product1,10);
//        transactionDetailDao.insertTransactionDetail(transactionDetail);

        /*
        (CREATE) How about purchasing 3 stimpaks before we go?.
        Going unarmed in the wasteland without any med preps sounds like a bad idea
        */
//        Transaction transaction1 = transactionDao.getTransactionByID(1);
//        Product product2 = productDao.getProductByID(5);
//        TransactionDetail transactionDetail2 = new TransactionDetail(transaction1,product2,3);
//        transactionDetailDao.insertTransactionDetail(transactionDetail2);

        /*
        (UPDATE) Maybe i prefer MRE over Iguana Soup for long term reasons.
        How about doin' some kind of re-trade for 5 MRE over all of my purchased soup?
        */
//        TransactionDetail transactionDetail = transactionDetailDao.getTransactionDetailByID(1);
//        Product product2 = productDao.getProductByID(7);
//
//        transactionDetail.setProduct(product2);
//        transactionDetail.setQuantity(5);
//
//        transactionDetailDao.updateTransactionDetail(transactionDetail);

        /*
        (DELETE) Delete the stimpacks purchasing record (id=2). In this case, delete record means
        as canceled transaction. So, the referred products will get its stock filled up as many
        as the quantity of the deleted record.
        */
        TransactionDetail transactionDetail2 = transactionDetailDao.getTransactionDetailByID(2);
        transactionDetailDao.deleteTransactionDetail(transactionDetail2);
    }
}
