package com.xyz;

public class InputData {

	private String id;
	private String name;
	private String sirname;
	private String salary;
	private String city;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSirname() {
		return sirname;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object obj) {
		InputData otherInputData = (InputData) obj;
		boolean idCheck = this.id.equalsIgnoreCase(otherInputData.getId());
		boolean nameCheck = this.name.equalsIgnoreCase(otherInputData.getName());
		boolean sirnameCheck = this.sirname.equalsIgnoreCase(otherInputData.getSirname());
		boolean salaryCheck = this.salary.equalsIgnoreCase(otherInputData.getSalary());
		boolean cityCheck = this.city.equalsIgnoreCase(otherInputData.getCity());

		boolean equal = idCheck && nameCheck && sirnameCheck && salaryCheck && cityCheck;

		return equal;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", sirname=" + sirname + ", salary=" + salary + ", city=" + city + "]";
	}

}
