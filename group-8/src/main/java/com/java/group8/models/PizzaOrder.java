package com.java.group8.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pizza_Orders")
public class PizzaOrder {
	
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
    
    @NotNull
    private Boolean hasSausage;
    
    @NotNull
    private Boolean hasMushrooms;
    
    @NotNull
    private Boolean hasExtraCheese;
    
    @NotNull
    private Boolean hasOnions;
    
    @NotNull
    private Boolean hasAnchovies;
    
    @NotNull
    private Boolean hasEggplant;
    
    @NotNull
    private Boolean hasArtichokes;
    
    @NotNull
    private Boolean hasBroccoli;
    
    @NotNull
    private Boolean hasPineApple;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;

	public PizzaOrder() {
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

	public Boolean getHasSausage() {
		return hasSausage;
	}

	public void setHasSausage(Boolean hasSausage) {
		this.hasSausage = hasSausage;
	}

	public Boolean getHasMushrooms() {
		return hasMushrooms;
	}

	public void setHasMushrooms(Boolean hasMushrooms) {
		this.hasMushrooms = hasMushrooms;
	}

	public Boolean getHasExtraCheese() {
		return hasExtraCheese;
	}

	public void setHasExtraCheese(Boolean hasExtraCheese) {
		this.hasExtraCheese = hasExtraCheese;
	}

	public Boolean getHasOnions() {
		return hasOnions;
	}

	public void setHasOnions(Boolean hasOnions) {
		this.hasOnions = hasOnions;
	}

	public Boolean getHasAnchovies() {
		return hasAnchovies;
	}

	public void setHasAnchovies(Boolean hasAnchovies) {
		this.hasAnchovies = hasAnchovies;
	}

	public Boolean getHasEggplant() {
		return hasEggplant;
	}

	public void setHasEggplant(Boolean hasEggplant) {
		this.hasEggplant = hasEggplant;
	}

	public Boolean getHasArtichokes() {
		return hasArtichokes;
	}

	public void setHasArtichokes(Boolean hasArtichokes) {
		this.hasArtichokes = hasArtichokes;
	}

	public Boolean getHasBroccoli() {
		return hasBroccoli;
	}

	public void setHasBroccoli(Boolean hasBroccoli) {
		this.hasBroccoli = hasBroccoli;
	}

	public Boolean getHasPineApple() {
		return hasPineApple;
	}

	public void setHasPineApple(Boolean hasPineApple) {
		this.hasPineApple = hasPineApple;
	}
	
	
	
}
