package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;

public class CustomerService {
    private static CustomerService instance = null;
    private HashMap<String, Customer> customers = new HashMap<>();

    private CustomerService(){}
    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName){
        if(!customers.containsKey(email)) {
            customers.put(email, new Customer(firstName, lastName, email));
        }else{
            System.out.println("Customer with the email = " + email + " exists. Try a different email id.");
        }
    }

    public Customer getCustomer(String customerEmail){
        Customer customer = customers.get(customerEmail);
        if(customer.equals(customerEmail)){
            return customer;
        }else {
            //return null;
            throw new NullPointerException("Customer not registered!");
        }
    }

    public Collection<Customer> getAllCustomers(){
        return customers.values();
    }


}
