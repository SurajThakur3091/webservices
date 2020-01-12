package com.qa.data;

//POJO: Plain old java object
public class JasonPayload {
	
	String name;
	int salary;
	int age;
	String status;
	
	public JasonPayload(){
		
	}
	public JasonPayload(String name,int sal,int age){
		this.name = name;
		this.salary = sal;
		this.age = age;
	}
	//create getter and setter methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
}
