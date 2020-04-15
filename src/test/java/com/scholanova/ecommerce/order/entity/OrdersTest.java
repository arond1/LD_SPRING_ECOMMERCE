package com.scholanova.ecommerce.order.entity;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.cart.entity.CartItem;
import com.scholanova.ecommerce.cart.exception.CartException;
import com.scholanova.ecommerce.order.NotAllowedException;
import com.scholanova.ecommerce.product.entity.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    public void checkout_ShouldSetTheDateAndTimeOfTodayInTheOrder() throws NotAllowedException {
        //given
        Orders o = new Orders();
        //when
        o.checkout();
        //then
        assertThat(o.getIssueDate() == new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Test
    public void checkout_ShouldSetOrderStatusToPending() throws NotAllowedException {
        //given
        Orders o = new Orders();
        //when
        o.checkout();
        //then
        assertThat(o.getStatus() == OrderStatus.PENDING);
    }

    @Test
    public void checkout_ShouldThrowNotAllowedExceptionIfStatusIsClosed() throws NotAllowedException {
        //given
        Orders o = new Orders();
        o.setStatus(OrderStatus.CLOSED);
        //when
        //then
        assertThrows(NotAllowedException.class, () -> o.checkout());
    }

    @Test
    public void checkout_ShouldThrowIllegalArgExceptionIfCartTotalItemsQuantityIsZERO() throws NotAllowedException {
        Orders o = new Orders();
        o.setCart(new Cart());
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> o.checkout());
    }

    @Test
    public void setCart_ShouldThrowNotAllowedExceptionIfStatusIsClosed(){
        Orders o = new Orders();
        //when
        o.setStatus(OrderStatus.CLOSED);
        //then
        assertThrows(NotAllowedException.class, () -> o.setCart(new Cart()));
    }

    @Test
    public void createOrder_ShouldSetTheCartInTheOrder() throws NotAllowedException {
        Cart cart = new Cart();
        Orders o = Orders.createOrder(cart);
        assertThat(o.getCart().equals(cart));
    }

    @Test
    public void createOrder_ShouldSetStatusToCreated() throws NotAllowedException {
        Cart cart = new Cart();
        Orders o = Orders.createOrder(cart);
        assertThat(o.getStatus() == OrderStatus.CREATED);
    }

    @Test
    public void getDiscount_shouldReturnZEROIFCartTotalPriceIsLessThan100(){

    }

    @Test
    public void getDiscount_shouldReturn5percentIfCartTotalPriceIsMoreOrEqual100(){

    }

    @Test
    public void getOrderPrice_shouldReturnTotalPriceWithDiscount(){

    }

    @Test
    public void close_ShouldSetStatusToClose(){

    }

}