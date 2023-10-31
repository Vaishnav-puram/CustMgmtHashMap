package model;

import java.util.Comparator;

public class SortCustomers implements Comparator<Customer>{

	@Override
	public int compare(Customer o1, Customer o2) {
		if((o1.getDob().compareTo(o2.getDob()))==0) {
			if(o1.getCharges()<o2.getCharges()) {
				return -1;
			}else if(o1.getCharges()>o2.getCharges()) {
				return 1;
			}else {
				return 0;
			}
		}
		return o1.getDob().compareTo(o2.getDob());		
	}

}
