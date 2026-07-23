package com.library.cart.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.cart.dto.CartDTO;
import com.library.cart.mapper.CartMapper;
import com.library.cart.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

	private final CartMapper cartMapper;
	
	@Override
	public List<CartDTO> getCartList(int memberNo){
		return cartMapper.selectCartByMember(memberNo);
	}

	 @Override
	 public void addCart(int memberNo, int bookNumber) {
	    cartMapper.insertCart(memberNo, bookNumber);
	 }

	 @Override
	 public void deleteCart(int cartItemNo) {
	    cartMapper.deleteCart(cartItemNo);
	 }

	 @Override
	 public void deleteCart(int[] cartItemNos) {
		 cartMapper.deleteCarts(cartItemNos);		
	 }

}
