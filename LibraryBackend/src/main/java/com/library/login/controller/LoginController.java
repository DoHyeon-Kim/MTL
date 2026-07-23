package com.library.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.security.CustomUserDetails;
import com.library.signupview.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody MemberDTO memberDTO,
            HttpServletRequest request) {
    	
    	Authentication authentication;

    	try {
    	    authentication =
    	        authenticationManager.authenticate(
    	            new UsernamePasswordAuthenticationToken(
    	                memberDTO.getMemberId(),
    	                memberDTO.getMemberPw()
    	            )
    	        );
    	} catch (Exception e) {
    	    e.printStackTrace();

    	    return ResponseEntity
    	            .status(401)
    	            .body("IDまだはPW.IDまたはpwが異なります。");
    	}
    	
        SecurityContext context =
                SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(
                "SPRING_SECURITY_CONTEXT",
                context
        );

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        session.setAttribute(
                "loginMember",
                user.getMember()
        );

        Map<String, Object> result = new HashMap<>();
        result.put("memberNo", user.getMember().getMemberNo());
        result.put("memberId", user.getMember().getMemberId());
        result.put("role", user.getMember().getRole());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/session")
    public MemberDTO session(HttpSession session) {
        return (MemberDTO) session.getAttribute("loginMember");
    }
}	