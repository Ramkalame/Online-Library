package com.libr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libr.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, String>{
	
	public List<Book> findByCategory(String category);
	

}
