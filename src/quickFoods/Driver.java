package quickFoods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	// Attributes
	private String driverName;
	private String location;
	private int deliveriesLoad;

	// Constructor used to create drivers
	public Driver(String driverName, String location, int deliveriesLoad) {
		this.driverName = driverName;
		this.location = location;
		this.deliveriesLoad = deliveriesLoad;
	}

	// Getters
	public String getDriverName() {
		return driverName;
	}

	public String getLocation() {
		return location;
	}

	public int getDeliveriesLoad() {
		return deliveriesLoad;
	}

//Method used to read drivers from the resource file 'drivers.txt'.
	public static List<Driver> readDrivers(String fileName) {
		List<Driver> drivers = new ArrayList<>();

		// Loading the file from the classpath and handling exceptions
		try (InputStream is = Driver.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			if (is == null) {
				System.out.println("Resource file not found: " + fileName);
				return drivers;
			}

			String line;
			while ((line = br.readLine()) != null) {
				String[] driverData = line.split(",");
				if (driverData.length == 3) {
					String driverName = driverData[0].trim();
					String location = driverData[1].trim();
					int deliveriesLoad = Integer.parseInt(driverData[2].trim());
					drivers.add(new Driver(driverName, location, deliveriesLoad));
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading drivers from file: " + e.getMessage());
		}

		return drivers;
	}

	// toString method to print the object's information
	@Override
	public String toString() {
		return "Driver{" + "driverName='" + driverName + '\'' + ", location='" + location + '\'' + ", deliveriesLoad="
				+ deliveriesLoad + '}';
	}
}
