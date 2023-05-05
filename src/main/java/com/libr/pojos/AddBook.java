package com.libr.pojos;

public class AddBook {
		
	private String id;
	private String password;
	private String isbn;
	private String name;
	private String author;
	private String description;
	private String availability;
	
	public AddBook(String id, String password, String isbn, String name, String author, String description,
			String category, String availabilty) {
		super();
		this.id = id;
		this.password = password;
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.description = description;
		this.category = category;
	}
	@Override
	public String toString() {
		return "AddBook [id=" + id + ", password=" + password + ", isbn=" + isbn + ", name=" + name + ", author="
				+ author + ", description=" + description + ", category=" + category + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private String category;
}
