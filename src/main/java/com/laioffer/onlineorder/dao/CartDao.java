package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CartDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void removeCartItem(int orderItemId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            OrderItem orderItem = session.get(OrderItem.class, orderItemId);
            Cart cart = orderItem.getCart();
            cart.getOrderItemList().remove(orderItem);
//            if (cart.getTotalPrice() > 0) {
//                cart.setTotalPrice(cart.getTotalPrice() - orderItem.getPrice());
//            }

            session.beginTransaction();
            session.delete(orderItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem item : cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }
    }

//    public void saveItem(OrderItem orderItem) {
//        Session session = null;
//
//        try {
//            session = sessionFactory.openSession();
//            Cart cart = orderItem.getCart();
//            cart.setTotalPrice(cart.getTotalPrice() + orderItem.getPrice());
//
//            session.beginTransaction();
//            session.update(cart);
//            session.getTransaction().commit();
//        } catch (Exception ex) {
//            if (session != null) {
//                session.getTransaction().rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
}
