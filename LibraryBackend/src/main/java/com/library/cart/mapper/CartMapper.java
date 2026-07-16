package com.library.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.cart.dto.CartDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {

	List<CartDTO> selectCartByMember(
	        @Param("memberNo") int memberNo
	    );

	    int insertCart(
	        @Param("memberNo") int memberNo,
	        @Param("bookNumber") int bookNumber
	    );

	    void deleteCart(
	        @Param("cartItemNo") int cartItemNo
	    );
}
