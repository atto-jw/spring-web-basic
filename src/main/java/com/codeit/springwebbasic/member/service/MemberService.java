package com.codeit.springwebbasic.member.service;

import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.entity.MemberGrade;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //이메일 중복 체크 - 조회(Repository)
    public Member createMember(MemberCreateRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이메일이 이미 등록되었습니다. " + requestDto.getEmail());
        }
        Member member = Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .phone(requestDto.getPhone())
                .grade(MemberGrade.BRONZE)
                .joinedAt(LocalDateTime.now())
                .build();

        return memberRepository.save(member);

    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. " + id));
    }

    public List<Member> searchMembers(String name) {
        return memberRepository.findByNameContaining(name);
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
