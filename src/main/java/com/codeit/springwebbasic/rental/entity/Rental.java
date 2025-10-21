package com.codeit.springwebbasic.rental.entity;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.member.entity.Member;

import java.time.LocalDateTime;

public class Rental {

    private Long id;
    private Member member;
    private Book book;
    private LocalDateTime rentedAt; // 대여 일시
    private LocalDateTime returnedAt; // 반납 일시
    private LocalDateTime dueDate; //반납 기한

    //반납 기능
    public void returnBook() {
        if (this.returnedAt != null) {
            throw new IllegalStateException("이미 반납된 도서입니다.");
        }
        this.book.returnBook(); // book의 status 변경
        this.returnedAt = LocalDateTime.now(); // 현재 날짜, 시간 정보
    }

    // 반납 기한 체크
    public boolean isOverdue(){
        // 반납이 되었다면 반납 날짜로 체크, 반납이 안되었다면 현재 날짜로 체크
        LocalDateTime checkDate = rentedAt != null ? rentedAt : LocalDateTime.now();
        return checkDate.isAfter(this.dueDate); // checkDate 이후면 true, A.isAfter(B), A가 B보다 이후면 true.
    }


}
