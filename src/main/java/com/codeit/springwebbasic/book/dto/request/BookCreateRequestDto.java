package com.codeit.springwebbasic.book.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * // DTO (Data Transfer Object)
 * // 비즈니스 로직 없이 순수하게 전달하고 싶은 데이터를 담는 용도로 사용하는 객체
 * // 요청 DTO, 응답 DTO로 나누어 사용. 꼭 필요한 데이터를 정제해서 전달하는 용도로 사용
 * // Entity를 직접 사용하지 않는 이유
 * // 1. 필요없는 정보까지 노출될 위험, 2. 도메인이 바뀌면 다른 스펙도 전부 변경 3. 검증 로직 배치의 애매함
 */

/*
        {
            "title": string,
            "author": string,
            "isbn": string,
            "publisher": string,
            "publishedData": data
        }
        이 모양과 똑같이 클래스 (DTO) 생성해서 보내주자.
     */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequestDto {
    /*
     @NotNull = Null만 허용하지 않습니다. ""(빈 문자열), " "(공백)은 허용
     @NotEmpty = Null과 ""(빈 문자열) 둘 다 허용하지 않습니다. 공백은 허용
     @NotBlank = Null, "", " " 전부 허용하지 않습니다.
     */

    @NotBlank(message = "제목은 필수입니다.") //만약 title이 비어있다면 메시지 출력
//    @Size(min = 2, max = 10, message = "title은 2글자 이상 10글자 이하여야 합니다.");
    private String title;

    @NotBlank(message = "저자는 필수입니다.")
    private String author;

    @NotBlank(message = "ISBN은 필수입니다.")
    // 정규표현식 (Regualar Expression) : 문자열의 일정한 패턴을 표현 형식 언어
    @Pattern(regexp = "\\d{13}", message = "ISBN은 13자리 숫자여야 합니다.") //정규 표현식, 원하는 패턴 검증
    private String isbn;

    @NotBlank(message = "출판사는 필수입니다.")
    private String publisher;

    @PastOrPresent (message = "출판일은 과거 또는 현재여야 합니다.") // 현재 시간 기준으로, 현재 또는 과거만 가능, 미래 X
    private LocalDate publicationDate;


}
