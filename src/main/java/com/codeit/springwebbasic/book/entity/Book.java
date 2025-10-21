package com.codeit.springwebbasic.book.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Long id;
    private String title; //도서 제목
    private String author; //저자
    private String isbn; //발행번호
    private String publisher; //출판사
    private LocalDate publishDate; //출판일
    private BookStatus status; // 대여 상태 (Enum)

//    public Book(Builder builder) { // Builder를 받는 생성자
//        this.id = builder.id;
//        //...
//    }
//
//    //Builder 패턴 - 객체를 생성하고 초기화 할 때 생성자나 setter 대체하기 위해 사용. (내부클래스 이용)
//    public static class Builder {
//        // 원본 클래스와 완벽하게 동일한 필드를 구성
//        private Long id;
//        private String title;
//        private String author;
//        private String isbn;
//        private String publisher;
//        private LocalDate publishDate;
//        private BookStatus status;
//
//        public Builder() {} //기본 생성자
//
//        // 필드를 초기화하는 setter를 자기 필드명과 동일하게 생성
//        public Builder id(Long id){
//            // 자기 자신 객체를 리턴
//            this.id = id;
//            return this;
//        }
//        // .... 필드에 있는 동일하게 초기화
//        // 최종 연산에서는 원본 객체 리턴
//        public Book build() {
//            return new Book(this);
//        }
//
//    public static Book.Builder builder() {
//        return new Book.builder();
//    }


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
