package com.codeit.springwebbasic.book.controller;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 변수를 초기화 하는 매개값을 전달받는 생성자
public class BookController {

    private final BookService bookService;

    /*
        { 도서 등록
            "title": string,
            "author": string,
            "isbn": string,
            "publisher": string,
            "publishedData": data
        }
        이 모양과 똑같이 클래스 (DTO) 생성해서 보내주자.
     */
    public void createBook(@Valid @RequestBody BookCreateRequestDto requestDto) { // Valid -> 검증이 필요함!
        bookService.createBook(requestDto);

    }

}
