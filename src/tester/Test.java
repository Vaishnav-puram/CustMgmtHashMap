package tester;

import java.time.LocalDate;
import static java.time.LocalDate.parse;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import Exceptions.*;
import model.Admin;
import model.Customer;
import model.ServicePlan.enumServicePlan;
import model.SortCustomers;
import validations.ValidateInputs;
import Dao.CustRepo;

public class Test {
	public static void main(String[] args)
			throws InvalidPlanException, InvalidRegtAmt, InvalidEmail, UserNotFound, InvalidCredentials {
		String firstName, lastName, name, email, plan;
		String password, newPassword;
		double regAmt;
		LocalDate dob;
		int choice;
		Customer c, cust = null;
		List<Customer> custs;
		HashMap<Integer,Customer> custMap=new HashMap<>();
		c=new Customer("vaishnav", "puram", "a@gmail.com", "1223", 1000, LocalDate.of(1999, 02, 10),
				enumServicePlan.valueOf("SILVER"), parse("2023-06-01"));
		custMap.put(c.getId(),c);
		c=new Customer("shubham", "panwar", "s@gmail.com", "12367", 1000, LocalDate.of(1999, 01, 01),
				enumServicePlan.valueOf("GOLD"), parse("2023-07-01"));
		custMap.put(c.getId(),c);
		c=new Customer("nikhil", "gaikwad", "nikhil@gmail.com", "12345", 1000, LocalDate.of(1999, 10, 01),
				enumServicePlan.valueOf("PLATINUM"), parse("2023-05-01"));
		custMap.put(c.getId(),c);
		c=new Customer("rajat", "kurlkar", "rajat@gmail.com", "1231", 10000, LocalDate.of(1999, 03, 01),
				enumServicePlan.valueOf("SILVER"), parse("2023-05-01"));
		custMap.put(c.getId(),c);
		c=new Customer("rushi", "sankalp", "rushi@gmail.com", "1213", 5000, LocalDate.of(1999, 05, 01),
				enumServicePlan.valueOf("DIAMOND"), parse("2023-08-01"));
		custMap.put(c.getId(),c);
		c=new Customer("pooja", "panwar", "pooja@gmail.com", "1236", 2000, LocalDate.of(1999, 04, 01),
				enumServicePlan.valueOf("GOLD"), parse("2023-09-01"));
		custMap.put(c.getId(),c);
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1.SignUp customer");
			System.out.println("2.Login");
			System.out.println("3.Display Customers");
			System.out.println("4.Check available plans");
			System.out.println("5.Find Specific Customer");
			System.out.println("6.Change Password");
			System.out.println("7.Unsubscribe Plan");
			System.out.println("8.Delete Customer");
			System.out.println("9.Login as admin");
			System.out.println("10.Custom Sort");
			System.out.println("11.Natural Sort");
			System.out.println("12.Exit");
			choice = s.nextInt();
			try {
				switch (choice) {
					case 1:
						System.out.println("Enter first name:");
						firstName = s.next();
						System.out.println("Enter last name:");
						lastName = s.next();
						System.out.println("Enter email:");
						email = s.next();
						System.out.println("Enter password:");
						password = s.next();
						System.out.println("Enter reg amt:");
						regAmt = s.nextDouble();
						System.out.println("Enter dob:");
						dob = LocalDate.parse(s.next());
						System.out.println("Enter plan(SILVER/GOLD/DIAMOND/PLATINUM)");
						plan = s.next();

						c = ValidateInputs.validateAll(firstName, lastName, email, password, regAmt, dob,
								plan.toUpperCase(), custMap);
//						customers.add(c);
						custMap.put(c.getId(),c);
						break;
					case 2:
						System.out.println("Enter email");
						email = s.next();
						CustRepo.findByEmail(email, custMap);
						System.out.println("Enter password");
						password = s.next();
						cust = CustRepo.credentialsCheck(email, password, custMap);
						System.out.println("Login successful");
						System.out.println("want to edit your subscription ? (Y/N):");
						char val = s.next().charAt(0);
						String p=cust.getPlan();
						if (Character.toUpperCase(val) == 'Y') {
							System.out.println("Enter new plan name");
							plan = s.next();
							cust.setPlan(enumServicePlan.valueOf(plan.toUpperCase()));
							System.out.println("Plan updated successfully from "+p+" to "+ plan.toUpperCase());
						} else {
							System.out.println("Loggin off....");
						}

						break;
					case 3:
						if (custMap.isEmpty()) {
							System.out.println("No users found!");
							break;
						}
						for (Customer c1 : custMap.values()) {
							System.out.println(c1);
						}
						break;
					case 4:
						for (enumServicePlan e : enumServicePlan.values()) {
							if(!(e.name().equals("NONE"))) {
								System.out.println(e.toString() + "---->" + e.getPlan());
							}
						}
						break;
					case 5:
						System.out.println("Enter admin credentials");
						System.out.println("Enter username:");
						name = s.next();
						System.out.println("Enter password:");
						password = s.next();
						if (name.equals(Admin.getUsername()) && password.equals(Admin.getPassword())) {
							System.out.println("Logged in as admin");
						} else {
							throw new InvalidCredentials("Authentication failed!");
						}
						System.out.println("Enter email :"); {
						email = s.next();
						System.out.println(CustRepo.findByEmail(email, custMap));
						break;
					}
					case 6:
						System.out.println("Enter email :");
						email = s.next();
						CustRepo.findByEmail(email, custMap);
						System.out.println("Enter old password:");
						password = s.next();
						cust = CustRepo.credentialsCheck(email, password, custMap);
						System.out.println("Enter new password");
						newPassword = s.next();
						cust.setPassword(newPassword);
						System.out.println("Password updated successfully.");
						break;
					case 7:
						System.out.println("Enter email");
						email = s.next();
						CustRepo.findByEmail(email, custMap);
						System.out.println("Enter password");
						password = s.next();
						cust = CustRepo.credentialsCheck(email, password, custMap);
						System.out.println("Login successful");
						cust.setPlan(enumServicePlan.NONE);
						System.out.println("Unsubscribed");
						break;
					case 8:
						System.out.println("Enter admin credentials");
						System.out.println("Enter username:");
						name = s.next();
						System.out.println("Enter password:");
						password = s.next();
						if (name.equals(Admin.getUsername()) && password.equals(Admin.getPassword())) {
							System.out.println("Logged in as admin");
						} else {
							throw new InvalidCredentials("Authentication failed!");
						}
						System.out.println("Enter email");
						cust = CustRepo.findByEmail(s.next(), custMap);
						custMap.remove(cust.getId(),cust);
						System.out.println("Deleted customer");
						break;
					case 9:
						System.out.println("Enter admin credentials");
						System.out.println("Enter username:");
						name = s.next();
						System.out.println("Enter password:");
						password = s.next();
						if (name.equals(Admin.getUsername()) && password.equals(Admin.getPassword())) {
							System.out.println("Logged in as admin");
						} else {
							throw new InvalidCredentials("Authentication failed!");
						}
						do {
							System.out.println("1.Check customers with expired plan");
							System.out.println("2.Delete customers");
							System.out.println("3.Exit");
							choice = s.nextInt();
							switch (choice) {
								case 1:
									custs = CustRepo.expiredPlanCust(custMap);
									for (Customer cu : custs) {
										System.out.println(cu);
									}
									break;
								case 2:
									custs = CustRepo.expiredPlanCust(custMap);
									CustRepo.removeCustomers(custs, custMap);
									System.out.println("Deleted customers");
									break;
								case 3:
									System.out.println("Qutting...");
									break;
							}
						} while (choice!=3);
						break;
					case 10: //Collections.sort(customers);
						System.out.println("Natural Sort");
						TreeMap<Integer,Customer> ts=new TreeMap<>(custMap);
						for(Customer cts:ts.values()) {
							System.out.println(cts);
						}
						break;
					case 11:
						//Collections.sort(customers,new SortCustomers());
						System.out.println("Custom Sort");
						List<Customer> ls=new ArrayList<>(custMap.values());
						Collections.sort(ls, new Comparator<Customer>() {
							@Override
							public int compare(Customer c1,Customer c2) {
								return c1.getDob().compareTo(c2.getDob());
							}
						});
						for(Customer cs:ls) {
							System.out.println(cs);
						}
						
						
						break;
					case 12:
						System.out.println("Qutting...");
						break;
					default:
						System.out.println("Invalid option!");
						break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (choice != 12);
		s.close();
	}
}
