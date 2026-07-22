package com.library.loan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.loan.dto.LoanDTO;
import com.library.loan.service.LoanService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoanController {
	
	private final LoanService loanservice;
	//一般顧客用DB照会
	@GetMapping("/member/loans/{memberNo}")
	public List<LoanDTO> getLoanList(@PathVariable("memberNo") int memberNo,
	                                 @RequestParam(name = "state", required = false) Integer state) {
	    return loanservice.getLoanList(memberNo, state);
	}
	
	//予約処理
	@PostMapping("/user/loans/{memberNo}")
	public void createLoans(@PathVariable("memberNo") int memberNo,
	                        @RequestBody List<Integer> bookNumbers) {
	    loanservice.createLoans(memberNo, bookNumbers);
	}
	
	@PutMapping("/member/loans/{loanNo}")
	public void changeStatement(@PathVariable("loanNo") int loanNo,
            @RequestParam(name = "state", required = false) Integer state) {
		loanservice.ChangeStatement(loanNo,state);
	}
}
