package com.libr.pojos;

public class RaiseTicket {
	
	private String sender;
	private String authkey;
	private String receiver;
	private String status;
	private String isbn;
	public RaiseTicket(String sender, String authkey, String receiver, String status, String isbn) {
		super();
		this.sender = sender;
		this.authkey = authkey;
		this.receiver = receiver;
		this.status = status;
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "RaiseTicket [sender=" + sender + ", authkey=" + authkey + ", receiver=" + receiver + ", status="
				+ status + ", isbn=" + isbn + "]";
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
		}
