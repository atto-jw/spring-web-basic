package com.codeit.springwebbasic.book.dto.response;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.entity.BookStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

//응답 DTO
@Getter
@Builder
public class BookResponseDto {

    // 지금은 실습이라, Book과 응답 DTO의 필드가 차이가 없지만,
    // 실제로는 화면(client)단에 맞는 데이터만 정제해서 보내는 것이 일반적입니다.

    private Long id;
    private String title; //도서 제목
    private String author; //저자
    private String isbn; //발행번호
    private String publisher; //출판사
    private LocalDate publishDate; //출판일
    private BookStatus status; // 대여 상태 (Enum)

    // Book 객체를 응답용 DTO로 변환 해주는 유틸 메서드
    public static BookResponseDto from(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .publishDate(book.getPublishDate())
                .status(book.getStatus())
                .build();
    }


}
