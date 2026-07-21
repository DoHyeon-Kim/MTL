package com.library.book.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.book.dto.BookDTO;
import com.library.book.dto.BookLoanStatusDTO;
import com.library.book.mapper.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final BookMapper bookMapper;

	//図書登録
	@Override
	public void createBook(BookDTO bookDTO) {
		bookMapper.createBook(bookDTO);

	}

	//図書詳細
	@Override
	public BookDTO bookDetail(BookDTO bookDTO) {
		return bookMapper.bookDetail(bookDTO);
	}

	//図書在庫状態
	@Override
	public List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo) {

		return bookMapper.bookStockList(bookNumberInfo);

	}

	//図書削除
	@Override
	public void deleteBook(Long bookNumberInfo) {

		bookMapper.deleteBook(bookNumberInfo);
	}

	//図書修正
	@Override
	public void updateBook(String bookId, BookDTO bookDTO, MultipartFile imageFile) {

		String uploadDir = "C:/uploads/images/";

		if (imageFile != null && !imageFile.isEmpty()) {
			String originalFileName = imageFile.getOriginalFilename();
			String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

			File saveFile = new File(uploadDir + storedFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			try {
				imageFile.transferTo(saveFile);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("予期せぬエラーが発生しました.", e);
			}

			String imageUrl = "http://localhost:8099/uploads/" + storedFileName;

			bookDTO.setBookImg(imageUrl);

			bookMapper.updateBook(bookDTO);

		}
	}

	//図書リスト
	@Override
	public List<BookDTO> bookList(String title) {
		return bookMapper.bookList(title);
	}

	//図書在庫削除
	@Override
	public void bookDeleteStock(int bookNumber) {
		
		bookMapper.bookDeleteStock(bookNumber);
	}

}
