package com.scholanova.ecommerce.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.cart.entity.CartItem;
import com.scholanova.ecommerce.order.NotAllowedException;
import com.sun.xml.bind.v2.TODO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity(name="orders")
public class Orders {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column
    private String number;

    @Column
    private Date issueDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart;

    public Orders() {
    }

    public static Orders createOrder(Cart c) throws NotAllowedException {
        Orders order = new Orders();
        order.setCart(c);
        order.setStatus(OrderStatus.CREATED);
        return order;
    }

    public void checkout() throws NotAllowedException,IllegalArgumentException {
        if(this.getCart() != null && this.getCart().getCartItems().size() == 0)
            throw new IllegalArgumentException();
        if(this.getStatus() == OrderStatus.CLOSED)
        {
            throw new NotAllowedException();
        }
        else{
            this.setIssueDate(new Date(Calendar.getInstance().getTime().getTime()));
            this.status = OrderStatus.PENDING;
        }
    }

    public int getDiscount(){
        if(this.cart.getTotalPrice().intValue() < 100)
            return 0;
        else
            return 5;
    }

    public double getOrderPrice(){
        return (1.00 - this.getDiscount()) * this.cart.getTotalPrice().doubleValue();
    }

    public void close(){
        this.setStatus(OrderStatus.CLOSED);
    }


    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {return number;}

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getIssueDate() {return issueDate;}

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public OrderStatus getStatus() {return status;}

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Cart getCart() {return cart;}

    public void setCart(Cart cart) throws NotAllowedException {
        if(this.getStatus() == OrderStatus.CLOSED)
            throw new NotAllowedException();
        this.cart = cart;
    }
}
