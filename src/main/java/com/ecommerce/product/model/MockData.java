package com.ecommerce.product.model;

import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.coyote.BadRequestException;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MockData {

	public static Employee getEmployee() {

		Department department = new Department(1L, "Engineering", "Bangalore");
		Address address = new Address("Bangalore", "Karnataka", "India", "560001");
		return new Employee(101L, "Revan", 20, 85000.0, "revan@gmail.com", "9876543210", true, department, address);
	}

	public static Product getProduct() {
		return new Product(1L, "iPhone 17", 95000.0, 20, true);
	}

	public static Customer getCustomer() {
		return new Customer(101L, "John", "john@gmail.com", "9999999999", 500000.0);
	}

	public static Category getCategory() {
		return new Category(1L, "MOB", "Mobiles", true);
	}

	public static Order getOrder() {
		return new Order(10L, 12000.0, "PAID", getCustomer());
	}

		public static void main(String[] args) throws BadRequestException {
	
			Pattern MOBILE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");
	
			Employee employee = getEmployee();
	
			Optional.ofNullable(employee).filter(emp -> emp.getSalary() > 0)
					.orElseThrow(() -> new BadRequestException("No value present"));
	
			Optional.ofNullable(employee.getEmail()).filter(email -> email.contains("@"))
					.orElseThrow(() -> new BadRequestException("Invalid email"));
	
			Optional.ofNullable(employee.getAge()).filter(age -> age >= 18)
					.orElseThrow(() -> new BadRequestException("No value present"));
	
			Optional.ofNullable(employee).map(Employee::getMobile)
					.filter(mobile -> MOBILE_PATTERN.matcher(mobile).matches())
					.orElseThrow(() -> new BadRequestException("Invalid Mobile Number"));
	
			Optional.ofNullable(employee).map(Employee::getName).filter(Name -> !Name.trim().isEmpty())
					.orElseThrow(() -> new BadRequestException("No Valid Name"));
	
			Optional.ofNullable(employee).map(Employee::getDepartment).map(Department::getLocation)
					.filter(location -> location.equalsIgnoreCase("Bangalore"))
					.orElseThrow(() -> new BadRequestException("No Valid Location"));
	
			Optional.ofNullable(employee).map(Employee::getAddress).map(Address::getCity)
					.orElseThrow(() -> new BadRequestException("KN"));
	
			Optional.ofNullable(employee).map(Employee::getSalary).filter(salary -> salary >= 30000 && salary <= 200000)
					.orElseThrow(() -> new BadRequestException("Salary should be between 30,000 and 2,00,000"));
	
			Optional.ofNullable(employee).map(Employee::getAddress).map(Address::getCity)
					.filter(city -> city.equalsIgnoreCase("KMKM")).ifPresent(System.out::println);
	
		}

}