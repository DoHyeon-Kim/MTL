package com.library.cart.service;

import java.util.List;
import com.library.cart.dto.CartDTO;

public interface CartService {
	List<CartDTO> getCartList(int memberNo);
	
	void addCart(int memberNo,int bookNumber);
	
	void deleteCart(int cartItemNo);
	
	void deleteCart(int[] cartItemNo);
	
}