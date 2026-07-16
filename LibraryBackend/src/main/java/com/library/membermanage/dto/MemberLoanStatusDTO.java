package com.library.membermanage.dto;

import lombok.Data;

@Data
public class MemberLoanStatusDTO {

	private String memberId;

	private String nameH;

	private int loanCount;
	private int entiCount;
}
