package com.codeit.springwebbasic.member.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id; //회원 Id
    private String name;
    private String email;
    private String phone;
    private MemberGrade grade; // 회원 등급
    private LocalDateTime joinedAt; // 가입일

    // 등급 업그레이드 (기존의 등급에서 한 단계 업그레이드)
    public void upgradeGrade() {
        this.grade = this.grade.upgrade();
    }

}
