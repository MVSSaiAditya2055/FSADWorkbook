package com.klu.model;
import javax.persistence.*;

@Entity
@Table(name="prod")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	private int quantity;
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	public double getPrice() {
		return price;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public int getQuantity() {
		return quantity;
	}
}