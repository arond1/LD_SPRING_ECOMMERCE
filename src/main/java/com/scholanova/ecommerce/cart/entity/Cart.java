package com.scholanova.ecommerce.cart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public CartItem getCartItemByProductId(Long productId){
        return null;
    }

    public Cart addProduct(Long productId, int quantity){
        return null;
    }

    public Cart removeProduct(Long productId){
        return null;
    }

    public Cart changeProductQuantity(Long productId, int quantity){
        return null;
    }

    public BigDecimal getTotalPrice(){
        return null;
    }

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {return cartItems;}

    public void setCartItems(List<CartItem> products) {
        this.cartItems = products;
    }
}
