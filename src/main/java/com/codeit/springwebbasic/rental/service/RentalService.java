package com.codeit.springwebbasic.rental.service;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.repository.BookRepository;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import com.codeit.springwebbasic.notification.NotificationService;
import com.codeit.springwebbasic.rental.entity.Rental;
import com.codeit.springwebbasic.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    //빈 컨테이너에서 싱글톤 관리함
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;
    private final NotificationService notificationService; //알림 발송 의존 주입, @Primary로 기본 Console 주입

    public Rental rentBook(Long memberId, Long bookId) { //
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다.")); //멤버 ID 찾기

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾을 수 없습니다.")); //도서 ID 찾기

        book.rentOut(); // 대여 처리

        Rental rental = Rental.builder() //Rental 객체 만들기
                .member(member)
                .book(book)
                .rentedAt(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7)) //지금 날짜 이후부터 7일
                .build();

        Rental save = rentalRepository.save(rental);

        // 대여를 완료했으면 알림 발송 로직
        // 알림 발송
        String message = String.format(
                "%s님, '%s' 도서를 대여하셨습니다. 반납기한: %s",
                member.getName(), book.getTitle(), rental.getDueDate().toLocalDate()
        );

        // 알림 전송 서비스 전달
        notificationService.sendNotification(member.getName(), message);

        return save;


    }
}
