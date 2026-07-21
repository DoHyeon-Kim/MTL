package com.library.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.cart.dto.CartDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {

	List<CartDTO> selectCartByMember(int memberNo);

	    int insertCart(
	        @Param("memberNo") int memberNo,
	        @Param("bookNumber") int bookNumber
	    );

	    void deleteCart(int cartItemNo);
	    
	    void deleteCarts(@Param("cartItemNos") int[] cartItemNos);	  
	    
	    int countInCart(CartDTO cartDTO);
}
