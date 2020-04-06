package com.scholanova.ecommerce.cart.entity;

import com.scholanova.ecommerce.product.entity.Product;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    void getCartItemByProductIdShouldReturnCorrectCartItem(){
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        Product decoy = Product.create("decoy", "decoy", 10.0f, 0.2f, "EUR");
        decoy.setId((long)14);
        cart.getCartItems().add(CartItem.create(target, 1));
        cart.getCartItems().add(CartItem.create(decoy, 2));
        //when
        CartItem tested = cart.getCartItemByProductId((long)12);
        //then
        assertThat(tested.getProduct().getName()).isEqualTo("target");
    }

    void getCartItemByProductIdShouldThrowNoSuchElementIfNotFound(){
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        cart.getCartItems().add(CartItem.create(target, 1));

        //then
        assertThrows(NoSuchElementException.class, () -> {cart.getCartItemByProductId((long)1234);});
    }

    void addProductShouldCreateANewCartItem() {
        //given
        Product fakeProduct = Product.create("fake", "a fake product", 10.0f, 0.2f, "EUR");
        fakeProduct.setId((long)12);
        Cart tested = new Cart();
        //when
        tested.addProduct((long)12, 2);
        //then
        assertThat(tested.getCartItemByProductId((long)12)).isInstanceOf(CartItem.class);
    }

    void addProductShouldAddProduct(){
        //given
        Cart cart = new Cart();
        //Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        //target.setId((long) 12);
        //when
        cart.addProduct((long)12, 3);
        //then
        assertThat(cart.getCartItemByProductId((long)12).getQuantity()).isEqualTo(3);
    }

    void addProductShouldThrowIllegalArgExcIfQuantityUnderOne() {
        //given
        Cart tested = new Cart();
        //then
        assertThrows(IllegalArgumentException.class, () -> {tested.addProduct((long)12, 0);});
        assertThrows(IllegalArgumentException.class, () -> {tested.addProduct((long)12, -3);});
    }

    void addProductShouldAddquantityIfProductAlreadyExists() {
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        cart.getCartItems().add(CartItem.create(target, 2));
        //when
        cart.addProduct((long)12, 3);
        //then
        assertThat(cart.getCartItemByProductId((long)12).getQuantity()).isEqualTo(5);
    }

    void removeProductShouldRemoveCartItem() {
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        cart.getCartItems().add(CartItem.create(target, 2));
        //when
        cart.removeProduct((long)12);
        //then
        assertThrows(NoSuchElementException.class, () -> {cart.getCartItemByProductId((long)12);});
    }

    void changeProductQuantityShouldChangeQuantity() {
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        cart.getCartItems().add(CartItem.create(target, 2));
        //when
        cart.changeProductQuantity((long)12, 3);
        //then
        assertThat(cart.getCartItemByProductId((long)12).getQuantity()).isEqualTo(3);
    }

    void changeProductQuantityShouldThrowIllegalArgExcIfQuantityUnderOne() {
        //given
        Cart tested = new Cart();
        //then
        assertThrows(IllegalArgumentException.class, () -> {tested.changeProductQuantity((long)12, 0);});
        assertThrows(IllegalArgumentException.class, () -> {tested.changeProductQuantity((long)12, -3);});
    }

    void getTotalPriceShouldReturnTotalPriceWithVATPerProduct() {
        //given
        Cart cart = new Cart();
        Product target = Product.create("target", "target", 10.0f, 0.2f, "EUR");
        target.setId((long) 12);
        Product decoy = Product.create("decoy", "decoy", 12.0f, 0.05f, "EUR");
        decoy.setId((long)14);
        cart.getCartItems().add(CartItem.create(target, 3));
        cart.getCartItems().add(CartItem.create(decoy, 5));
        //when
        BigDecimal tested = cart.getTotalPrice();
        //then
        assertThat(tested).isEqualTo(BigDecimal.valueOf((10.0*1.2*3)+(12.0*1.05*5)));
    }
}