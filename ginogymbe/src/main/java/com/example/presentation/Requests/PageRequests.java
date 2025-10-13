package com.example.presentation.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequests {
	
private	int pageIndex = 0;
private	int pageSize= 15;
private	String searchFilter= "";

}
