package model;

import java.time.LocalDate;
import java.util.Objects;

import model.ServicePlan.enumServicePlan;

public class Customer{
	private int id;
	private static int idCounter=1;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double regAmt;
	private LocalDate dob;
	private LocalDate lastSubDate ;
	private enumServicePlan plan;
	public Customer(String email) {
		this.email=email;
	}
	public Customer(String firstName, String lastName, String email, String password, double regAmt,
					LocalDate dob, enumServicePlan plan) {
		this.id = idCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.dob = dob;
		this.plan = plan;
		subDateInitialize();
	}
	public Customer(String firstName, String lastName, String email, String password, double regAmt,
					LocalDate dob, enumServicePlan plan, LocalDate lastSubDate ) {
		this.id = idCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.dob = dob;
		this.plan = plan;
		this.lastSubDate=lastSubDate;
	}
	private void subDateInitialize() {
		if(this.plan.toString().equals("NONE")) {
			this.lastSubDate=null;
		}else {
			this.lastSubDate=LocalDate.now();
		}

	}
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public double getRegAmt() {
		return regAmt;
	}
	public LocalDate getDob() {
		return dob;
	}
	public LocalDate getLastSubDate() {
		return lastSubDate;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPlan(enumServicePlan plan) {
		this.plan = plan;
	}
	public int getCharges() {
		return this.plan.getPlan();
	}
	public String getPlan() {
		return plan.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			if(this.getEmail().equals(((Customer)obj).getEmail())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", regAmt=" + regAmt + ", dob=" + dob + ", plan=" + plan + " PlanCharges :"+this.plan.getPlan()+"Last Subscribed date = "+this.lastSubDate+"]";
	}
}
