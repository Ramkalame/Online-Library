package com.libr.controller;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.libr.entity.Book;
import com.libr.entity.Ticket;
import com.libr.entity.User;
import com.libr.pojos.AddBook;
import com.libr.pojos.RegisterUser;
import com.libr.pojos.RequestListPojo;
import com.libr.pojos.Profile;
import com.libr.pojos.RaiseTicket;
import com.libr.repo.BookRepository;
import com.libr.repo.TicketRepository;
import com.libr.repo.UserRepository;
import com.libr.utils.CodeSet;
import com.libr.utils.Utilities;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class MainController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@PostMapping("/addbook")
	public String addbook(@RequestBody AddBook addbook) throws JsonProcessingException {
		if(userRepository.findById(addbook.getId()).isPresent())
		{
			ArrayList<String> isbnList =  Utilities.generateArray(userRepository.findById(addbook.getId()).get().getBookshelf());
			if(isbnList.contains(addbook.getIsbn()))
					{
				
						return "Book is already present in the bookself and book repository";
					}
			
			
			else
				{
					Book book = new Book();
					book.setIsbn(addbook.getIsbn());
					book.setName(addbook.getName());
					book.setCategory(addbook.getCategory());
					book.setAuthor(addbook.getAuthor());
					book.setDescription(addbook.getDescription());
					book.setAvailability("Available");
					
					isbnList.add(addbook.getIsbn());
					User user =  new User();
					user = userRepository.findById(addbook.getId()).get();
					user.setBookshelf(Utilities.generateString(isbnList));
					bookRepository.save(book);
					return "Book is saved in book repository and also added in book self";
				}
			
		}
		else
		{
			return "User doesn't exist.";
		}
	}
	
	
	
	@GetMapping("/getbooklist")
	public List<Book> getbooklist()
	{
		return bookRepository.findAll();
	}
	
	
	
	
	@GetMapping("/getbook/{id}")
	public Book getbook(@PathVariable String id) {
		
		return bookRepository.findById(id).get();
	}
	
	@GetMapping("getlibname/{userid}")
	public String getLibName(@PathVariable String userid) {
		
		User user = userRepository.findById(userid).get();
		String res = user.getName();
		return res;
	}
	
	
	@DeleteMapping("/deletebookbyid/{isbn}")
	public String deletebook(@PathVariable String isbn) {
		if(bookRepository.findById(isbn).isPresent()) {
			bookRepository.deleteById(isbn);
		}
		return "Book has been successfully deleted";
		
	}

	
	
	
	
	@PutMapping("/updatebook/{id}")
	public String updatebook(@PathVariable String id,@RequestBody Book book) {
		
		Book existingbook = bookRepository.findById(id).orElse(book);
		existingbook.setName(book.getName());
		existingbook.setAuthor(book.getAuthor());
		existingbook.setCategory(book.getCategory());
		existingbook.setDescription(book.getDescription());
		existingbook.setAvailability(book.getAvailability());
		bookRepository.save(existingbook);
		return "Book "+book.getName()+" has been successfully updated.";
		
	}
	
	
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterUser adduser) {
		if(userRepository.findById(adduser.getUserid()).isPresent())
			return "user "+adduser.getUserid()+" already exists.";
		else
			{
			User user = new User();
			user.setUserid(adduser.getUserid());
			user.setPassword(adduser.getPassword());
			user.setName(adduser.getName());
			user.setContact(adduser.getContact());
			user.setAddress(adduser.getAddress());
			user.setRole(adduser.getRole());
			user.setBookshelf("");
			userRepository.save(user);
			return "user "+adduser.getUserid()+" successfully registered";
			}
	}
	
	
	@GetMapping("/getuserprofile/{id}")
	public Profile getuser(@PathVariable String id) {
		User user = userRepository.findById(id).get();
		Profile profile = new Profile();
		profile.setName(user.getName());
		profile.setContact(user.getContact());
		profile.setAddress(user.getAddress());
		profile.setBookshelf(user.getBookshelf());
		
		return profile;
	}
	
	@GetMapping("/login/{userid}")
	public User login(@PathVariable String userid) {
		return userRepository.findById(userid).get();
		
	}
	
	
	@PostMapping("/raiseticket")
	public String raiseticket(@RequestBody RaiseTicket raiseticket) throws JsonProcessingException {
		if(userRepository.findById(raiseticket.getSender()).isPresent() && userRepository.findById(raiseticket.getSender()).get().getPassword().contentEquals(raiseticket.getAuthkey()) && userRepository.findById(raiseticket.getReceiver()).isPresent())
		{
			Ticket ticket = new Ticket();
			ticket.setSender(raiseticket.getSender());
			ticket.setReceiver(raiseticket.getReceiver());
			ticket.setStatus(CodeSet.REQUESTED);
			ticket.setIsbn(raiseticket.getIsbn());
			//ticket.setDescription("Sender "+ticket.getSender()+" has successfully requested to Receiver "+ticket.getReceiver()+" for Book "+ticket.getIsbn());
			
			Map<String,String> readerbook = Utilities.generateStringMap(userRepository.findById(raiseticket.getSender()).get().getBookshelf());
			
			readerbook.put(raiseticket.getIsbn(), raiseticket.getReceiver());
			
			User user = userRepository.findById(raiseticket.getSender()).get();
			user.setBookshelf(Utilities.generateJson1(readerbook));
			ticketRepository.save(ticket);
			
			//return ticket.getDescription();
			return " requested successfully";
		}
		return "Users not present or authenticated.";
	}
	
	
	
	@GetMapping("/userbookshelf/{id}")
	public List<String> userbookshelf(@PathVariable String id){
		if(userRepository.findById(id).isPresent()) {
			Map<String,String> map = Utilities.generateStringMap(userRepository.findById(id).get().getBookshelf());
			List<String> ubookshelf = new ArrayList<>();
			for(String bookid:map.keySet()) {
				Book book = bookRepository.findById(bookid).get();
				String st = ticketRepository.findById(bookid).get().getStatus();
				String show = bookid+" ["+book.getName()+"] to "+map.get(bookid) +"  Status: "+st;
				ubookshelf.add(show);
				
			}
			return ubookshelf;
		}
		
			return new ArrayList<String>();
		
	}
	
	@GetMapping("/category/{category}")
	public List<Book> getBookByCategory(@PathVariable String category){
		
		return bookRepository.findByCategory(category);
	}
	
	
	
	
	
	@PutMapping("aprove/{isbn}")
	public void aproveButton(@PathVariable String isbn) {
		
		Book book = bookRepository.findById(isbn).get();
		book.setAvailability(CodeSet.REQUESTED);
		bookRepository.save(book);
		
		int ticketId = ticketRepository.findByIsbn(isbn).getTicketid();
		String strTicketId = Integer.toString(ticketId);
		Ticket ticket = ticketRepository.findById(strTicketId).get();
		ticket.setStatus("aproved");
		ticketRepository.save(ticket);
		
		
	}
	
	@GetMapping("aprovedlistapi/{receiver}")
	public List<RequestListPojo> onlyAproved(@PathVariable String receiver){
		List<Ticket> ticket = ticketRepository.findByReceiver(receiver);
		
		List<RequestListPojo> updatedRequestList = new ArrayList<>();
		
		for(Ticket value:ticket) {
			
			if(value.getStatus().equalsIgnoreCase("aproved")) 
			{
				RequestListPojo requestList = new RequestListPojo();
				
				requestList.setIsbn(value.getIsbn());
				requestList.setBookname(bookRepository.findById(value.getIsbn()).get().getName());
				requestList.setSendername(userRepository.findById(value.getSender()).get().getName());
				requestList.setUserid(value.getSender());
				
				updatedRequestList.add(requestList);
			}
			
		}
		
		
		return updatedRequestList;
		
		
	}
	
	
	
	@GetMapping("requestlistapi/{receiver}")
	public List<RequestListPojo> onlyRequested(@PathVariable String receiver){
		List<Ticket> ticket = ticketRepository.findByReceiver(receiver);
		
		List<RequestListPojo> updatedRequestList = new ArrayList<>();
		
		for(Ticket value:ticket) {
			
			if(value.getStatus().equalsIgnoreCase("requested")) 
			{
				RequestListPojo requestList = new RequestListPojo();
				
				requestList.setIsbn(value.getIsbn());
//				requestList.setBookname(bookRepository.findById(value.getIsbn()).get().getName());
				requestList.setBookname(bookRepository.findById(value.getIsbn()).get().getName());
				requestList.setSendername(userRepository.findById(value.getSender()).get().getName());
				requestList.setUserid(value.getSender());
				
				updatedRequestList.add(requestList);
			}
			
		}
		
		
		return updatedRequestList;
		
		
	}
	
	
	@DeleteMapping("/deleteticket/{isbn}")
	public String deleteTicket(@PathVariable String isbn) {
		
		int ticketId = ticketRepository.findByIsbn(isbn).getTicketid();
		String strTicketId = Integer.toString(ticketId);
		Ticket ticket = ticketRepository.findById(strTicketId).get();
		ticketRepository.deleteById(strTicketId);
		return "has been successfully deleted";
		
		
	}
	
	
	@GetMapping("libbookshelf/{userid}")
	public List<Book> getLibBookshelf(@PathVariable String userid){
		
		ArrayList<String> libBookshelf = Utilities.generateArray(userRepository.findById(userid).get().getBookshelf());
		ArrayList<Book> updatedlibBookshelf = new ArrayList<>();
		for(String isbn:libBookshelf) {
			Book book = bookRepository.findById(isbn).get();
			updatedlibBookshelf.add(book);
			
		}
		return updatedlibBookshelf;
	}
	
	
	@PutMapping("/updatebookshelf/{userid}/{isbn}")
	public List<String> updateBookshelf(@PathVariable String userid, @PathVariable String isbn){
		
		
		ArrayList<String> bookshelf = Utilities.generateArray(userRepository.findById(userid).get().getBookshelf());
		ArrayList<String> updatedbookshelf = new ArrayList<>();
		for(int i = 0; i<bookshelf.size();i++) {
			
			if(isbn.equals(bookshelf.get(i))) {	
			}
			else{
				updatedbookshelf.add(bookshelf.get(i));
			}

		}
		User user = userRepository.findById(userid).get();
		user.setBookshelf(Utilities.generateString(updatedbookshelf));
		userRepository.save(user);
		return updatedbookshelf;
		
	}
	
	
	
	
	 
		
	
	
	
}
