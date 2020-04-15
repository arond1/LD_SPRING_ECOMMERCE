package com.scholanova.ecommerce.cart.service;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService{

    @Autowired
    ProductRepository repository_product;

    @Override
    public Cart addProductToCart(Cart cart, Long productId, int quantity) {
        cart.addProduct(repository_product.findById(productId).get(), quantity);
        return cart;
    }

    @Override
    public Cart changeProductQuantity(Cart cart, Long productId, int quantity) {
        //TODO
        return null;
    }
}
