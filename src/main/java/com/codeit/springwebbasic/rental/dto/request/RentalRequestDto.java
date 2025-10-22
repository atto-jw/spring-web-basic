package com.codeit.springwebbasic.rental.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequestDto {

    // client -> 대여할 때 필요한 정보만 가져와야한다.
    // 즉, 멤버나 책 모든 정보(객체)를 넘겨줄 필요가 없다. 그래서 식별할 수 있는 고유의 ID만 있는게 좋다

    // 문자열이 아닌 정수 타입에는 NotNull만 작성할 수 있습니다. (공백이나 빈 문자열이 전달될 수 없음)
    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId; // 멤버를 식별할 수 있는 Id

    @NotNull(message = "도서 ID는 필수입니다.")
    private Long bookId; // 책을 식별할 수 있는 Id


}
