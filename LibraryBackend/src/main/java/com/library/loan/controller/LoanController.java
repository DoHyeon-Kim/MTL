package com.library.loan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.library.loan.dto.LoanDTO;
import com.library.loan.service.LoanService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoanController {
	
	private final LoanService loanservice;
	//一般顧客用DB照会
	@GetMapping("/loans/{memberNo}")
	public List<LoanDTO> getLoanList(@PathVariable("memberNo") int memberNo) {
		
		return loanservice.getLoanList(memberNo);
	}
	
	//予約処理
	@PostMapping("/loans/{memberNo}")
	public void createLoans(@PathVariable("memberNo") int memberNo,
	                        @RequestBody List<Integer> bookNumbers) {
	    loanservice.createLoans(memberNo, bookNumbers);
	}
	
}
