package com.libr.entity;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name="Book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
	private String isbn;
	private String name;
	private String author;
	private String description;
	private String category;
	private String availability;
	private String imgurl;
	private String libid;

	
	

}
