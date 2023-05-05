package com.libr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libr.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {

	public List<Ticket> findByReceiver(String receiver);

	public Ticket findByIsbn(String isbn);
	


}
