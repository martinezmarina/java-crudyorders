package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository orderrepos;

    @Override
    public Order findOrderById(long id) {
        return orderrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
    }
    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();
        if(order.getOrdnum() != 0){
            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setOrdamount(order.getOrdamount());
       // newOrder.setOrderdescription(order.setOrderdescription());
        return orderrepos.save(newOrder);
    }
    @Transactional
    @Override
    public void delete(long id) {
        if(orderrepos.findById(id).isPresent()){
            orderrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order " + id + " Not Found");
        }
    }
}
