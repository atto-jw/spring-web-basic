package com.codeit.springwebbasic.book.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String title; //도서 제목
    private String author; //저자
    private String isbn; //발행번호
    private String publisher; //출판사
    private LocalDate publishData; //출판일
    private BookStatus status; // 대여 상태 (Enum)


    // 나중에 DB를 사용하면 status 값도 DB에 저장될 것이기 때문에
    // 밑에 2개의 메서드는 지울 것
    public void rentOut() { // 대여를 하면 렌트 상태
        if (this.status != BookStatus.AVAILABLE) {
            throw new IllegalArgumentException("이미 대여중인 도서입니다.");
        }
        this.status = BookStatus.RENTED;
    }

    public void returnBook(){ // 대여 반납
        if (this.status != BookStatus.RENTED) {
            throw new IllegalArgumentException("대여중인 아닌 도서 입니다.");
        }
        this.status = BookStatus.AVAILABLE;
    }
}
