package com.libr.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestListPojo {
	private String isbn;
	private String bookname;
	private String sendername;
	private String userid;
}
