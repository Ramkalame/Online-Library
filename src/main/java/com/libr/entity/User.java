package com.libr.entity;

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
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String userid;
	private String password;
	private String name;
	private String contact;
	private String address;
	private String bookshelf;
	private String role;

}
