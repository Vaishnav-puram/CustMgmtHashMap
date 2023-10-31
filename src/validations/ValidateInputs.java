package validations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import Dao.CustRepo;
import Exceptions.*;
import model.Customer;
import model.ServicePlan.enumServicePlan;

public class ValidateInputs {
	public static String validatePlan(String p) throws InvalidPlanException {
		boolean flag=false;
		for(enumServicePlan e:enumServicePlan.values()) {
			if(e.toString().equals(p)) {
				flag=true;
				break;
			}
		}
		if(!flag) {
			throw new InvalidPlanException("Invalid plan selected !");
		}
		return p;
	}
	public static double validateRegAmt(enumServicePlan plan,double amt) throws InvalidRegtAmt {
		
		if(plan.getPlan()!=amt) {
			throw new InvalidRegtAmt("Amount doesn't match with the plan selected !");
		}
		return amt;
	}
	public static String validatePassword(String password) throws InvalidPassword {
		if(!(Pattern.matches("^[A-Z]+[a-z0-9]+[@#$!].*", password))) {
			throw new InvalidPassword("Please enter a valid password!");
		}
		return password;
	}
	public static String validateEmail(String email) throws InvalidEmail {
		if(!(Pattern.matches("^[a-z].*@gmail.com$", email))) {
			throw new InvalidEmail("Invalid Email!");
		}
		return email;
	}
	public static void checkDupEmail(String email,HashMap<Integer,Customer> custMap) throws DuplicateEmailException, UserNotFound {
		//Customer cust=CustRepo.findByEmail(email, customers);
		for(Customer c:custMap.values()) {
			if(c.getEmail().equals(email)) {
				throw new DuplicateEmailException("Duplicate email!");
			}
		}
	}
	
	public static Customer validateAll(String firstName, String lastName, String email, String password, double regAmt,
			LocalDate dob, String plan, HashMap<Integer,Customer> custMap) throws InvalidPlanException, InvalidRegtAmt, InvalidEmail, DuplicateEmailException, UserNotFound, InvalidPassword {
		
		checkDupEmail(email, custMap);
		String validPlan=validatePlan(plan);
		double validAmt=validateRegAmt(enumServicePlan.valueOf(plan), regAmt);
		String validEmail=validateEmail(email);
		String validPassword=validatePassword(password);
		Customer c=new Customer( firstName,  lastName,  validEmail,  validPassword,  validAmt,
				 dob,  enumServicePlan.valueOf(validPlan));
		return c;
	}
}
