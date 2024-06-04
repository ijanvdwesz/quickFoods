package quickFoods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

public class InvoiceWriter {
//Method for writing an invoice with detailed order information to a file.

	public static void writeInvoice(int orderNo, Customer customer, Restaurant restaurant, double totalAmountToPay,
			Driver closestDriver) {
		String invoiceFilePath = "invoice.txt";
		DecimalFormat df = new DecimalFormat("0.00");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(invoiceFilePath))) {
			// Writes order details to the invoice
			writer.write("Order Number: " + orderNo);
			writer.newLine();
			writer.write("Customer: " + customer.getCustomerName());
			writer.newLine();
			writer.write("Email: " + customer.getEmail());
			writer.newLine();
			writer.write("Phone Number: " + customer.getContactNo());
			writer.newLine();
			writer.write("Location: " + customer.getLocation());
			writer.newLine();
			writer.newLine();
			writer.write(
					"You have ordered the following from " + restaurant.getRestaurantName() + " in " + restaurant.getLocation());
			writer.newLine();

			// Writes meals ordered
			for (Map.Entry<String, Integer> entry : restaurant.getMealsOrdered().entrySet()) {
				String mealName = entry.getKey();
				int quantity = entry.getValue();
				double price = restaurant.getMealPrices().get(mealName);
				writer.write("\t" + quantity + "x " + mealName + " (R" + df.format(price) + ")");
				writer.newLine();
			}

			writer.newLine();
			// Writes special instructions
			writer.write("Special Instructions: " + restaurant.getSpecialInstructions());
			writer.newLine();
			writer.newLine();
			// Writes total amount
			writer.write("Total: R" + df.format(totalAmountToPay));
			writer.newLine();
			writer.newLine();
			// Write confirmation with closest driver if available
			if (closestDriver != null) {
				writer.write(
						"The closest driver, " + closestDriver.getDriverName() + ", will be delivering your order to you at:");
				writer.newLine();
				writer.write(customer.getAddress());
				writer.newLine();
				writer.write("Restaurant contact Number: " + restaurant.getContactNo());
			} else {
				writer.write("Sorry, our drivers are too far away from you to be able to deliver to your location.");
			}
			System.out.println("Invoice created successfully. Check invoice.txt for details.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing the invoice: " + e.getMessage());
		}
	}
}