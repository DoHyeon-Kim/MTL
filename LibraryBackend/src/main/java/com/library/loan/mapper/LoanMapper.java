package com.library.loan.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.library.loan.dto.LoanDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoanMapper {

	List<LoanDTO> selectLoanList(@Param("memberNo") int memberNo,
            @Param("state") Integer state);
	
	List<LoanDTO> selectLoanList2(@Param("memberNo") int memberNo,
            @Param("state") Integer state);
	
	void insertLoan(LoanDTO loanDTO);
	
	//임시체크용
	Boolean selectBookLoanAvailable(int bookNumber);
	
    String selectTopCategory(int memberNo);

    int changeState(@Param("loanNo") int loanNo);
    
    void extensionLoan(@Param("loanNo") int loanNo);
    
    void checkLoan(@Param("loanNo") int loanNo);
    
    void returnLoan(@Param("loanNo") int loanNo);
}