package com.library.membermanage.dto;

import lombok.Data;

@Data
public class MemberManageDTO {

	private Long memberNo;

	private String memberId;

	private String memberPw;

	private String role;

	private String memberStatus;
}
