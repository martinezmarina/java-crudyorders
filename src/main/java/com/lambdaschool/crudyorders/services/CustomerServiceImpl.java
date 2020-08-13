package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import com.lambdaschool.crudyorders.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomersRepository custrepos;
    @Autowired
    private AgentsRepository agentrepos;

    @Override
    public List<Customer> listAllCustomerOrders() {
        List<Customer> rtnList = new ArrayList<>();

        custrepos.findAll()
                .iterator()
                .forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Override
    public Customer findCustomerById(long id) {
        return custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customer> listCustomersByLikeName(String name){
        List<Customer> customerList = new ArrayList<>();
        custrepos.findByCustnameContainingIgnoringCase(name).iterator().forEachRemaining(customerList::add);
        return customerList;
    }
    @Override
    public List<OrderCount> getOrderCount() {
        return custrepos.getOrderCount();
    }
    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0){
            custrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setAgent(customer.getAgent());

        return custrepos.save(newCustomer);
    }
    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
        if(customer.getCustcity() != null){
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if(customer.getCustcountry() != null){
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if(customer.getCustname() != null){
            currentCustomer.setCustname(customer.getCustname());
        }
        if(customer.getGrade() != null){
            currentCustomer.setGrade(customer.getGrade());
        }
        if(customer.getOpeningamt() != 0){
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if(customer.getOutstandingamt() != 0){
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if(customer.getPaymentamt() != 0){
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if(customer.getPhone() != null){
            currentCustomer.setPhone(customer.getPhone());
        }
        if(customer.getReceiveamt() != 0){
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if(customer.getWorkingarea() != null){
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if(customer.getAgent() != null){
            currentCustomer.setAgent(customer.getAgent());
        }
        return custrepos.save(currentCustomer);
    }
    @Transactional
    @Override
    public void delete(long id) {
        if (custrepos.findById(id).isPresent()){
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Restaurant " + id + " Not Found");
        }
    }
}
