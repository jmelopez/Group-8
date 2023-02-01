package com.java.group8.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Past_Orders")
public class PastOrder {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // creates unique ID in MySQL
    private Long Id;
    
    @NotEmpty(message = "Must select a delivery option")
    private String deliveryMethod;
    
    @NotEmpty(message = "Select a Size")
    private String size;
    
    @NotEmpty(message = "Select a Crust Type")
    private String crust;
    
    @NotNull(message = "Must select at least 1")
    private Integer quantity;
    
    @NotNull
    private Boolean favorite;
    
    @NotNull
    private Boolean hasPepperoni;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;
	
	public PastOrder() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCrust() {
		return crust;
	}

	public void setCrust(String crust) {
		this.crust = crust;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Boolean getHasPepperoni() {
		return hasPepperoni;
	}

	public void setHasPepperoni(Boolean hasPepperoni) {
		this.hasPepperoni = hasPepperoni;
	}
	
	

}
