package com.scholanova.ecommerce.cart.service;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.cart.exception.CartException;
import com.scholanova.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService{

    @Autowired
    ProductRepository repository_product;

    @Override
    public Cart addProductToCart(Cart cart, Long productId, int quantity) throws CartException{
        try{
            cart.addProduct(repository_product.findById(productId).get(), quantity);
            return cart;
        }
        catch (Exception e) {
            throw new CartException("erreur");
        }
    }

    @Override
    public Cart changeProductQuantity(Cart cart, Long productId, int quantity) throws CartException {
        try{
            cart.changeProductQuantity(cart.getCartItemByProductName(repository_product.findById(productId).get().getName()).getProduct(), quantity);
            return cart;
        }
        catch(Exception e)
        {
            throw new CartException("erreur");
        }

    }
}
