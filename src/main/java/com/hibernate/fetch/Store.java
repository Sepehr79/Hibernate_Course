package com.hibernate.fetch;

import com.hibernate.fetch.eager.Product;
import com.hibernate.fetch.lazy.Customer;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "store_name",unique = true)
    private String storeName;

    @OneToMany(mappedBy = "store",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(mappedBy = "store", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<Customer> customers;

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addProduct(Product product){
        if (products == null)
            products = new LinkedList<>();

        products.add(product);

        product.setStore(this);
    }

    public void addCustomer(Customer customer){
        if (customers == null)
            customers = new LinkedList<>();

        customers.add(customer);

        customer.setStore(this);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", products=" + products +
                '}';
    }
}
