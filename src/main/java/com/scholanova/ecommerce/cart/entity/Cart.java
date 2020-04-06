package com.scholanova.ecommerce.cart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scholanova.ecommerce.product.entity.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> products = new ArrayList<>();

    public Cart() {
    }

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getProducts() {return products;}

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }
}
