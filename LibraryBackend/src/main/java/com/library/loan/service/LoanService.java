package com.library.loan.service;

import java.util.List;
import com.library.loan.dto.LoanDTO;

public interface LoanService {
	List<LoanDTO> getLoanList(int memberNo);
	
    void createLoans(int memberNo, List<Integer> bookNumbers);
}
