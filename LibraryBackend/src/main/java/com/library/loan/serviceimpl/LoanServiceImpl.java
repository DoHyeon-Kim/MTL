package com.library.loan.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.loan.dto.LoanDTO;
import com.library.loan.mapper.LoanMapper;
import com.library.loan.service.LoanService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{

	private final LoanMapper loanMapper;
	
	@Override
	public List<LoanDTO> getLoanList(int menberNo){
		return loanMapper.selectLoanList(menberNo);
	}
	
	@Override
    public void createLoans(int memberNo, List<Integer> bookNumbers) {
		
        for (Integer bookNumber : bookNumbers) {
        	if(loanMapper.selectBookLoanAvailable(bookNumber) == false )
            {
        		throw new IllegalStateException("予約できない！！！");
            }
        }
        
      for (Integer bookNumber : bookNumbers) {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setMemberNo(memberNo);
            loanDTO.setBookNumber(bookNumber);
            loanDTO.setRequestState(1);
            loanMapper.insertLoan(loanDTO);
        }
    }
}
