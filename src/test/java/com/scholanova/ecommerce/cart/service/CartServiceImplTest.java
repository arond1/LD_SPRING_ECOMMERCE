package com.scholanova.ecommerce.cart.service;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.cart.entity.CartItem;
import com.scholanova.ecommerce.cart.exception.CartException;
import com.scholanova.ecommerce.product.entity.Product;
import com.scholanova.ecommerce.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class CartServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    private CartServiceImpl service;

    public CartServiceImplTest(CartServiceImpl service) {
        this.service = service;
    }

    @Test
    public void addProductToCartShouldAddTheProductToTheCart() {
        //given
        Cart cart = new Cart();
        Product product = Product.create("tested", "tested", 10.5f, 0.1f, "EUR");
        product.setId((long) 12);
        given(productRepository.findById(Long.valueOf(12))).willReturn(java.util.Optional.of(product));
        //when
        service.addProductToCart(cart, (long) 12, 3);
        //then
        assertThat(cart.getCartItemByProductName("tested").getQuantity() == 3);
    }

    @Test
    public void addProductToCartShouldHandleExceptions() {
        //given
        Cart cart = new Cart();
        Product product = Product.create("tested", "tested", 10.5f, 0.1f, "EUR");
        product.setId((long) 12);
        given(productRepository.findById(Long.valueOf(12))).willReturn(java.util.Optional.of(product));
        //then
        assertThrows(CartException.class, () -> service.addProductToCart(cart, (long) 123, 2));
        assertThrows(CartException.class, () -> service.addProductToCart(cart, (long) 12, -3));
    }

    @Test
    public void changeProductQuantityShouldChangeQuantity(){
        //given
        Cart cart = new Cart();
        Product product = Product.create("tested", "tested", 10.5f, 0.1f, "EUR");
        product.setId((long) 12);
        cart.getCartItems().add(CartItem.create(product, 4));
        given(productRepository.findById(Long.valueOf(12))).willReturn(java.util.Optional.of(product));
        //when
        service.changeProductQuantity(cart, (long) 12, 3);
        //then
        assertThat(cart.getCartItemByProductName("tested").getQuantity() == 3);
    }

    @Test
    public void changeProductQuantityShouldHandleExceptions(){
        //given
        Cart cart = new Cart();
        Product product = Product.create("tested", "tested", 10.5f, 0.1f, "EUR");
        product.setId((long) 12);
        cart.getCartItems().add(CartItem.create(product, 4));
        given(productRepository.findById(Long.valueOf(12))).willReturn(java.util.Optional.of(product));
        //then
        assertThrows(CartException.class, () -> service.changeProductQuantity(cart, (long) 123, 2));
        assertThrows(CartException.class, () -> service.changeProductQuantity(cart, (long) 12, -3));
    }
}