package com.library.bookmanage.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.bookmanage.dto.BookLoanStatusDTO;
import com.library.bookmanage.dto.BookManageDTO;
import com.library.bookmanage.service.BookManageServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookManageController {
	private final BookManageServiceImpl bookManageService;

	//図書登録
	@PostMapping(value = "/bookcreate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public void insertBook(
			@RequestPart("book") BookManageDTO bookDTO,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {
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
		}

		Long bookCount = bookDTO.getBookNumberInfo();

		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));

		Long uniqueBase = Long.parseLong(today);

		for (int i = 0; bookCount > i; i++) {

			//			Long bookUniqueNumber = uniqueBase + i;

			bookDTO.setBookNumberInfo(uniqueBase);

			bookManageService.createBook(bookDTO);
		}

	}

	//図書詳細
	@GetMapping(value = "/bookmanagedetail/{bookNumberInfo}")
	public ResponseEntity<BookManageDTO> getBookManageDetail(@PathVariable Long bookNumberInfo) {

		BookManageDTO bookManageDTO = new BookManageDTO();
		bookManageDTO.setBookNumberInfo(bookNumberInfo);

		BookManageDTO result = bookManageService.bookManageDetail(bookManageDTO);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/bookmanagedetail/{bookNumberInfo}/stock")
	public ResponseEntity<List<BookLoanStatusDTO>> getBookStock(@PathVariable Long bookNumberInfo) {

		List<BookLoanStatusDTO> stockList = bookManageService.bookStockList(bookNumberInfo);

		return ResponseEntity.ok(stockList);
	}

	//図書削除
	@PutMapping("/bookdelete/{bookNumberInfo}")
	public void deleteBook(
			@PathVariable Long bookNumberInfo) {
		bookManageService.deleteBook(bookNumberInfo);
	}

	//図書在庫削除
	@PutMapping("/bookDeleteStock/{bookNumber}")
	public void deleteBookStock(
			@PathVariable int bookNumber) {
		bookManageService.bookDeleteStock(bookNumber);
	}

	//図書修正
	@PutMapping(value = "/bookupdate/{bookId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateBook(
			@PathVariable("bookId") String bookId,
			@RequestPart("book") BookManageDTO bookDTO,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		bookManageService.updateBook(bookId, bookDTO, imageFile);

		return ResponseEntity.ok(bookDTO);
	}

	//図書リスト
	@GetMapping("/bookmanagelist")
	public List<BookManageDTO> bookManageList(@RequestParam(value = "title", required = false) String title) {

		return bookManageService.bookList(title);
	}
}