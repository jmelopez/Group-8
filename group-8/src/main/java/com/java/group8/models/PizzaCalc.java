package com.java.group8.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PizzaCalc {
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	private PizzaOrder pizzaOrder;
	
	private String customerState;
	
	private Double deliveryPrice = 7.00;
	
	private Double smallPrice = 5.00;
	private Double mediumPrice = 7.50;
	private Double largePrice = 10.00;
	
	private Double thinCrustPrice = .50;
	private Double thickCrustPrice = 1.0;
	private Double stuffedCrustPrice = 2.5;

	private Double pepperoniPrice = 1.90;
	private Double sausagePrice = 2.00;
	private Double mushroomPrice = 1.00;
	private Double extraCheesePrice = 1.50;
	private Double onionPrice = .75;
	private Double anchoviesPrice = .80;
	private Double eggplantPrice = 1.25;
	private Double artichokesPrice = 1.33;
	private Double broccoliPrice = .63;;
	private Double pineapplePrice = 1.20;
	
	private Double otherStateTax = .02;

	
	public Double calculatePrice() {
		
		double orderPrice = 0.0;

		if (this.pizzaOrder.getCrust().equals("Thin")) {
			orderPrice += thinCrustPrice;
		}
		if (this.pizzaOrder.getCrust().equals("Thick")) {
			orderPrice += thickCrustPrice;
		}
		if (this.pizzaOrder.getCrust().equals("Stuffed")) {
			orderPrice += stuffedCrustPrice;
		}

		if (this.pizzaOrder.getHasPepperoni().booleanValue() == true) {
			orderPrice += pepperoniPrice;
		}
		if (this.pizzaOrder.getHasSausage().booleanValue() == true) {
			orderPrice += sausagePrice;
		}
		if (this.pizzaOrder.getHasMushrooms().booleanValue() == true) {
			orderPrice += mushroomPrice;
		}
		if (this.pizzaOrder.getHasExtraCheese().booleanValue() == true) {
			orderPrice += extraCheesePrice;
		}
		if (this.pizzaOrder.getHasOnions().booleanValue() == true) {
			orderPrice += onionPrice;
		}
		if (this.pizzaOrder.getHasAnchovies().booleanValue() == true) {
			orderPrice += anchoviesPrice;
		}
		if (this.pizzaOrder.getHasEggplant().booleanValue() == true) {
			orderPrice += eggplantPrice;
		}
		if (this.pizzaOrder.getHasArtichokes().booleanValue() == true) {
			orderPrice += artichokesPrice;
		}
		if (this.pizzaOrder.getHasBroccoli().booleanValue() == true) {
			orderPrice += broccoliPrice;
		}
		if (this.pizzaOrder.getHasPineApple().booleanValue() == true) {
			orderPrice += pineapplePrice;
		}

		if (this.pizzaOrder.getDeliveryMethod().equals("Delivery")) {
			orderPrice += deliveryPrice;
		}

		if (this.pizzaOrder.getSize().equals("Small")) {
			orderPrice += smallPrice;
		}
		if (this.pizzaOrder.getSize().equals("Medium")) {
			orderPrice += mediumPrice;
		}
		if (this.pizzaOrder.getSize().equals("Large")) {
			orderPrice += largePrice;
		}

		if (this.customerState != "DE") {
			orderPrice += (orderPrice * otherStateTax);
		}

		orderPrice *= this.pizzaOrder.getQuantity();

		return orderPrice;
	}
//		if (deliveryMethod.equals("Delivery")) {
//			totalPrice += 7.00;
//		}
//
//		if (size.equals("Small")) {
//			totalPrice += 5.00;
//		} else if (size.equals("Medium")) {
//			totalPrice += 7.50;
//		} else if (size.equals("Large")) {
//			totalPrice += 10.00;
//		}
//
//		if (crust.equals("Thin")) {
//			totalPrice += 1.00;
//		} else if (crust.equals("Thick")) {
//			totalPrice += 2.00;
//		} else if (crust.equals("Stuffed")) {
//			totalPrice += 3.00;
//		}
//
//		if (!state.equals("DE")) {
//			totalPrice += totalPrice * .04;
//		}
//
//		totalPrice *= qty;
//		return totalPrice;
//	}

	public PizzaCalc(PizzaOrder pizzaOrder, String customerState) {
			this.pizzaOrder = pizzaOrder;
			this.customerState = customerState;
	}

	public PizzaOrder getPizzaOrder() {
		return pizzaOrder;
	}

	public void setPizzaOrder(PizzaOrder pizzaOrder) {
		this.pizzaOrder = pizzaOrder;
	}
	public String getCustomerState() {
		return customerState;
	}
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	
	
	
	
	
	
    
}
