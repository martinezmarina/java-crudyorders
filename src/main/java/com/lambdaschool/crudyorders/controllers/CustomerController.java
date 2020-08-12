package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomerService;
import com.lambdaschool.crudyorders.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomerOrders() {
        List<Customer> customersOrders = customerService.listAllCustomerOrders();
        return new ResponseEntity<>(customersOrders, HttpStatus.OK);
    }
    // http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long id) {
        Customer c = customerService.findCustomerById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    // http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{name}", produces = {"application/json"})
    public ResponseEntity<?> listCustomersByLikeName(@PathVariable String name) {
        List<Customer> customers = customerService.listCustomersByLikeName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    //http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?>getOrderCount(){
        List<OrderCount> orderCount = customerService.getOrderCount();
        return new ResponseEntity<>(orderCount, HttpStatus.OK);
    }
}
