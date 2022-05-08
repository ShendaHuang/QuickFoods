package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.dao.CartDao;
import com.laioffer.onlineorder.dao.OrderItemDao;
import com.laioffer.onlineorder.entity.Customer;
import com.laioffer.onlineorder.entity.MenuItem;
import com.laioffer.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private MenuInfoService menuInfoService;

    private CustomerService customerService;

    private OrderItemDao orderItemDao;

//    private CartDao cartDao;

    @Autowired
    public OrderItemService(MenuInfoService menuInfoService, CustomerService customerService,
                            OrderItemDao orderItemDao) {
        this.menuInfoService = menuInfoService;
        this.customerService = customerService;
        this.orderItemDao = orderItemDao;
//        this.cartDao = cartDao;
    }

    public void saveOrderItem(int menuId) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Customer customer = customerService.getCustomer(email);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());

        orderItemDao.save(orderItem);
//        cartDao.saveItem(orderItem);
    }
}
