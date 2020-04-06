package com.scholanova.ecommerce.cart.repository;

import com.scholanova.ecommerce.cart.entity.Cart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartRepositoryImpl implements CustomCartRepository {

    @Override
    public Cart addProduct(Long productId, int quantity) {
        return null;
    }

    @Override
    public Cart removeProduct(Long productId) {
        return null;
    }

    @Override
    public Cart changeProductQuantity(Long productId, int quantity) {
        return null;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }
}
