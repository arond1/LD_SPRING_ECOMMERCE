package com.scholanova.ecommerce.cart.repository;

import com.scholanova.ecommerce.cart.entity.Cart;

import java.math.BigDecimal;

public interface CustomCartRepository {

    public Cart addProduct(Long productId, int quantity);

    public Cart removeProduct(Long productId);

    public Cart changeProductQuantity(Long productId, int quantity);

    public BigDecimal getTotalPrice();
}
