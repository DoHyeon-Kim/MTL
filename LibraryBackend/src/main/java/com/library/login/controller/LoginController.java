package com.library.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.signupview.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(HttpSession session) {

        MemberDTO member = new MemberDTO();
        member.setMemberNo(6);
        member.setMemberId("admin");
        member.setRole("ROLE_ADMIN");

        session.setAttribute("loginMember", member);

        return "success";
    }

    @GetMapping("/session")
    public MemberDTO session(HttpSession session) {
        return (MemberDTO) session.getAttribute("loginMember");
    }
}