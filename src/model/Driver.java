package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domin.com");
        System.out.println(customer);
        Customer customer1 = new Customer("first1", "second1", "email1");
        System.out.println(customer1);
    }
}
