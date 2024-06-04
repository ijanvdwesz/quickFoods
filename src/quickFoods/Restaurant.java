package quickFoods;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
	private String restaurantName;
	private String location;
	private String contactNo;
	private Map<String, Integer> mealsOrdered;
	private Map<String, Double> mealPrices;
	private String specialInstructions;
	private double totalAmountToPay;

//Constructor used for creating a restaurant object with initial values.
	public Restaurant(String restaurantName, String location, String contactNo) {
		this.restaurantName = restaurantName;
		this.location = location;
		this.contactNo = contactNo;
		this.mealsOrdered = new HashMap<>();
		this.mealPrices = new HashMap<>();
		this.specialInstructions = "";
		this.totalAmountToPay = 0.0;
	}

	// toString method to print the object's information
	@Override
	public String toString() {
		return "Restaurant{" + "restaurantName='" + restaurantName + '\'' + ", location='" + location + '\''
				+ ", contactNo='" + contactNo + '\'' + ", mealsOrdered=" + mealsOrdered + ", mealPrices=" + mealPrices
				+ ", specialInstructions='" + specialInstructions + '\'' + ", totalAmountToPay=" + totalAmountToPay + '}';
	}

	// Getters and setters
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Map<String, Integer> getMealsOrdered() {
		return mealsOrdered;
	}

	public Map<String, Double> getMealPrices() {
		return mealPrices;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public double getTotalAmountToPay() {
		return totalAmountToPay;
	}

	public void setTotalAmountToPay(double totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}
}