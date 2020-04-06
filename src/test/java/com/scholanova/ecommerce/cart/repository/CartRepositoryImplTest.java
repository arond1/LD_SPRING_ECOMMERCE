package com.scholanova.ecommerce.cart.repository;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.product.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;


class CartRepositoryImplTest {

    void addProductShouldCreateANewCartItem() {
        //given
        Product fakeProduct = Product.create("fake", "a fake product", 10.0f, 0.2f, "EUR");
        Cart tested = new Cart();
        assertThat(true).isTrue();
    }

    void addProductShouldThrowIllegalArgExcIfQuantityUnderOne() {

    }

    void addProductShouldAddquantityIfProductAlreadyExists() {

    }

    void removeProductShouldRemoveCartItem() {

    }

    void changeProductQuantityShouldChangeQuantity() {

    }

    void changeProductQuantityShouldThrowIllegalArgExcIfQuantityUnderOne() {

    }

    void getTotalPriceShouldReturnTotalPriceWithVATPerProduct() {

    }


}