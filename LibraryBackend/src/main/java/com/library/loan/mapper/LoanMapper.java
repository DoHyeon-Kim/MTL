package com.library.loan.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.library.loan.dto.LoanDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoanMapper {

	List<LoanDTO> selectLoanList(int memberNo);
	
	void insertLoan(LoanDTO loanDTO);
	
	//임시체크용
	Boolean selectBookLoanAvailable(int bookNumber);
	
    String selectTopCategory(int memberNo);

	
		}
