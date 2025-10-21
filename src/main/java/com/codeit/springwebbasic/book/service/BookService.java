package com.codeit.springwebbasic.book.service;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.entity.BookStatus;
import com.codeit.springwebbasic.book.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookCreateRequestDto requestDto) {
        //ISBN 중복 체크
        Optional<Book> byIsbn = bookRepository.findByIsbn(requestDto.getIsbn());
        if (byIsbn.isPresent()){ //Book 객체가 존재한지?
            throw new IllegalArgumentException("이미 등록된 ISBN입니다. : " + requestDto.getIsbn());
        }
        //Builder 패턴(직관적으로 객체 생성 가능)
        //이 패턴을 활용하면 초기화 순서를 내 마음대로 지정가능, 원하는 필드만 골라서 초기화 하는것이 가능.
        Book book = Book.builder()
                .title(requestDto.getTitle())
                .isbn(requestDto.getIsbn())
                .author(requestDto.getAuthor())
                .publisher(requestDto.getPublisher())
                .publishDate(requestDto.getPublicationDate())
                .status(BookStatus.AVAILABLE)
                .build();

        return bookRepository.save(book);

    }
}
