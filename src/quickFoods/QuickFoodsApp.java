package quickFoods;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuickFoodsApp {

	public static void main(String[] args) {
		String filePath = "drivers.txt";
		List<Driver> drivers = Driver.readDrivers(filePath);

		if (drivers.isEmpty()) {
			System.out.println("No drivers found. Please check the drivers.txt file.");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		try {
			Customer customer = createCustomer(scanner);
			Restaurant restaurant = createRestaurant(scanner);

			promptForMealsOrdered(scanner, restaurant);

			double totalAmountToPay = calculateTotalAmountToPay(restaurant);
			restaurant.setTotalAmountToPay(totalAmountToPay);
			System.out.println("Restaurant created: " + restaurant);

			Driver closestDriver = findClosestDriver(drivers, restaurant.getLocation());
			if (closestDriver != null) {
				System.out.println("Closest driver: " + closestDriver);
				InvoiceWriter.writeInvoice(customer.getOrderNo(), customer, restaurant, totalAmountToPay, closestDriver);
			} else {
				System.out.println("Sorry, our drivers are too far away from you to be able to deliver to your location.");
				InvoiceWriter.writeInvoice(customer.getOrderNo(), customer, restaurant, totalAmountToPay, null);
			}
		} finally {
			scanner.close();
		}
	}

//Prompts the user to enter details and create a customer and restaurant objects.
	public static Customer createCustomer(Scanner scanner) {
		int orderNo = promptForInt(scanner, "Enter order number: ");
		scanner.nextLine(); // consume newline
		System.out.println("Enter customer name: ");
		String customerName = scanner.nextLine();
		System.out.println("Enter contact number: ");
		String contactNo = scanner.nextLine();
		System.out.println("Enter address: ");
		String address = scanner.nextLine();
		System.out.println("Enter location: ");
		String location = scanner.nextLine();
		System.out.println("Enter email: ");
		String email = scanner.nextLine();

		return new Customer(orderNo, customerName, contactNo, address, location, email);
	}

	public static Restaurant createRestaurant(Scanner scanner) {
		System.out.println("Enter restaurant name: ");
		String restaurantName = scanner.nextLine();
		System.out.println("Enter restaurant location: ");
		String restaurantLocation = scanner.nextLine();
		System.out.println("Enter restaurant contact number: ");
		String restaurantContactNo = scanner.nextLine();

		return new Restaurant(restaurantName, restaurantLocation, restaurantContactNo);
	}

	public static void promptForMealsOrdered(Scanner scanner, Restaurant restaurant) {
		int numMeals = promptForInt(scanner, "Enter number of different meals ordered: ");
		scanner.nextLine(); // consume newline
		for (int i = 0; i < numMeals; i++) {
			System.out.println("Enter meal name: ");
			String mealName = scanner.nextLine();
			int quantity = promptForInt(scanner, "Enter quantity: ");
			double price = promptForDouble(scanner, "Enter price: ");
			scanner.nextLine(); // consume newline
			restaurant.getMealsOrdered().put(mealName, quantity);
			restaurant.getMealPrices().put(mealName, price);
		}

		System.out.println("Enter special instructions: ");
		String specialInstructions = scanner.nextLine();
		restaurant.setSpecialInstructions(specialInstructions);
	}

	public static int promptForInt(Scanner scanner, String message) {
		while (true) {
			try {
				System.out.println(message);
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.next(); // consume invalid input
			}
		}
	}

	public static double promptForDouble(Scanner scanner, String message) {
		while (true) {
			try {
				System.out.println(message);
				return scanner.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid double.");
				scanner.next(); // consume invalid input
			}
		}
	}

	public static double calculateTotalAmountToPay(Restaurant restaurant) {
		double totalAmountToPay = 0.0;
		for (Map.Entry<String, Integer> entry : restaurant.getMealsOrdered().entrySet()) {
			String mealName = entry.getKey();
			int quantity = entry.getValue();
			double price = restaurant.getMealPrices().get(mealName);
			totalAmountToPay += quantity * price;
		}
		return totalAmountToPay;
	}

//Finds and returns the closest driver based on the restaurant's location.
	public static Driver findClosestDriver(List<Driver> drivers, String restaurantLocation) {
		Driver closestDriver = null;
		String normalizedRestaurantLocation = restaurantLocation.toLowerCase().replaceAll("\\s+", "");

		for (Driver driver : drivers) {
			String normalizedDriverLocation = driver.getLocation().toLowerCase().replaceAll("\\s+", "");
			if (normalizedDriverLocation.equals(normalizedRestaurantLocation)) {
				if (closestDriver == null || driver.getDeliveriesLoad() < closestDriver.getDeliveriesLoad()) {
					closestDriver = driver;
				}
			}
		}
		return closestDriver;
	}
}