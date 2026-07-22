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
	public List<LoanDTO> getLoanList(int memberNo, Integer state) {
		if(state ==1) return loanMapper.selectLoanList2(memberNo, state);
		else return loanMapper.selectLoanList(memberNo, state);

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
	
	@Override
	public void ChangeStatement(int loanNo,Integer state) {
		
		switch(state) {
		case 1: //추가되었으므로 문제 없음
			break; 
		case 2:
			loanMapper.changeState(loanNo); // 여긴 날짜 넣는 시스템
			break;
		case 3:
			loanMapper.extensionLoan(loanNo); // 날짜 연장 해주는 시스템
			break;
		case 4:
			loanMapper.returnLoan(loanNo); // 반납일 기록.
			break;
		case 5:
			loanMapper.extensionLoan(loanNo);
			break;
		}
	}
	
}
