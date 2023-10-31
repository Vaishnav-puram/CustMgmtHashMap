package Dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Exceptions.InvalidCredentials;
import Exceptions.UserNotFound;
import model.Customer;

public class CustRepo {

	public static Customer findByEmail(String email, HashMap<Integer,Customer> custMap ) throws UserNotFound {
		boolean flag = false;
		Customer c=null;
		for(Customer ce:custMap.values()) {
			if(ce.getEmail().equals(email)) {
				flag=true;
				c=ce;
				break;
			}
		}
		if(!flag) {
			throw new UserNotFound("User Not Found !");
		}
		return c;
		
	}

	public static Customer credentialsCheck(String email, String password, HashMap<Integer,Customer> custMap) throws InvalidCredentials {
		boolean flag = false;
		Customer c=null;
		for(Customer ce:custMap.values()) {
			if(ce.getEmail().equals(email)&&ce.getPassword().equals(password)) {
				flag=true;
				c=ce;
				break;
			}
		}
		if(!flag) {
			throw new InvalidCredentials("Invalid Credentials!");
		}
		return c;
	}
	public static List<Customer> expiredPlanCust(HashMap<Integer,Customer> custMap){
		List<Customer> custs=new ArrayList<>();
		for(Customer cst:custMap.values()) {
			Period p=Period.between(cst.getLastSubDate(), LocalDate.now());
			if(p.getMonths()>=3) {
				custs.add(cst);
			}
		}
		return custs;
	}
	public static void removeCustomers(List<Customer> custs,HashMap<Integer,Customer> custMap) {
//		Iterator<Map.Entry<Integer,Customer>> e=custMap.entrySet().iterator();
//		Customer c;
//		while(e.hasNext()) {
//			c=e.next().getValue();
//			if(custs.contains(c)) {
//				e.remove();
//			}
//		}
		for(Customer csts:custs) {
			custMap.remove(csts.getId(),csts);
		}
	}
}
