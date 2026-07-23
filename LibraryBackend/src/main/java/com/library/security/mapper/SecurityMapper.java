package com.library.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.signupview.dto.MemberDTO;

@Mapper
public interface SecurityMapper {

    MemberDTO selectLoginMember(
            @Param("memberId") String memberId
    );

}