package com.library.cart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.library.cart.dto.CartDTO;
import com.library.cart.service.CartService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService; 
	
	@GetMapping("/carts/{memberNo}")
	public List<CartDTO> getCartList(@PathVariable("memberNo") int memberNo) {
		
		return cartService.getCartList(memberNo);
	}
	
	@DeleteMapping("/carts/{cartItemNo}")
	public void deleteCart(@PathVariable ("cartItemNo")int cartItemNo ) {
		
		cartService.deleteCart(cartItemNo);
	}
	
	@DeleteMapping("/carts")
	public void deleteCarts(@RequestBody int[] cartItemNos) {
	    cartService.deleteCart(cartItemNos);
	}
	
}
