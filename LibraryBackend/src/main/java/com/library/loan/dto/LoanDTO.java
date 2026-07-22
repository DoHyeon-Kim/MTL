package com.library.loan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LoanDTO {
	private Integer loanNo;
    private Integer bookNumber;
    private Integer memberNo;
    private String memberId;
    private String bookTitle;
    private String Writer;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Boolean extension;
    private Integer requestState;
}
