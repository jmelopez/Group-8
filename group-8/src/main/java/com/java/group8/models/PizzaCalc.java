package com.java.group8.models;

public class PizzaCalc {
    
	public Double calculatePrice(String deliveryMethod, String size, String crust, Integer qty, String state) {
		Double totalPrice = 0.0;
		
		if (deliveryMethod.equals("Delivery")) {
			totalPrice += 7.00;
		}

		if (size.equals("Small")) {
			totalPrice += 5.00;
		} else if (size.equals("Medium")) {
			totalPrice += 7.50;
		} else if (size.equals("Large")) {
			totalPrice += 10.00;
		}

		if (crust.equals("Thin")) {
			totalPrice += 1.00;
		} else if (crust.equals("Thick")) {
			totalPrice += 2.00;
		} else if (crust.equals("Stuffed")) {
			totalPrice += 3.00;
		}

		if (!state.equals("DE")) {
			totalPrice += totalPrice * .04;
		}

		totalPrice *= qty;
		return totalPrice;
	}

	public PizzaCalc() {

	}
    
}
