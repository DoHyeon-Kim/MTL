package com.library.loan.service;

import java.util.List;
import com.library.loan.dto.LoanDTO;

public interface LoanService {
	public List<LoanDTO> getLoanList(int memberNo, Integer state);
	
	public void createLoans(int memberNo, List<Integer> bookNumbers);
	
	public void ChangeStatement(int loanNo,Integer state);
}
