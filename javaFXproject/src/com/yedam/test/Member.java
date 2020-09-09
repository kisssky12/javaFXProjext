package com.yedam.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Member {
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty address;
	private SimpleIntegerProperty height;
	private SimpleIntegerProperty weight;

	public Member(String name, int age, String address, int height, int weight) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.address = new SimpleStringProperty(address);
		this.height = new SimpleIntegerProperty(height);
		this.weight = new SimpleIntegerProperty(weight);
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public int getAge() {
		return this.age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public String getAddress() {
		return this.address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public int getHeight() {
		return this.height.get();
	}

	public void setHeight(int height) {
		this.height.set(height);
		;
	}

	public int getWeight() {
		return this.weight.get();
	}

	public void setWeight(int weight) {
		this.weight.set(weight);
		;
	}

}
