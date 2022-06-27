package com.himalaya.demo.himalaya.service;

import com.himalaya.demo.himalaya.dto.MemberDto;
import com.himalaya.demo.himalaya.entity.Member;
import com.himalaya.demo.himalaya.entity.MemberRepository;
import com.himalaya.demo.himalaya.exception.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member defaultMember = Member.builder()
            .memberId("memberId")
            .email("test@test.com")
            .name("tester")
            .build();

    private MemberDto defaultCreateMemberRequest = MemberDto.builder()
            .memberId("memberId")
            .email("test@test.com")
            .name("tester")
            .build();

    @Test
    public void testSomething() throws Exception {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultMember));

        //when
        MemberDto memberDto = memberService.findByMemberId("memberId");

        //then
        then(memberDto.getMemberId()).isEqualTo("memberId");
        then(memberDto.getEmail()).isEqualTo("test@test.com");
        then(memberDto.getName()).isEqualTo("tester");

    }

    @Test
    @DisplayName("멤버등록_테스트")
    public void 멤버등록_Test_success() throws Exception {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        given(memberRepository.save(any()))
                .willReturn(defaultMember);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        //when
        MemberDto memberDto = memberService.saveMember(defaultCreateMemberRequest);

        //then
        verify(memberRepository, times(1))
                .save(captor.capture());

        Member savedMember = captor.getValue();
        then(savedMember.getMemberId()).isEqualTo("memberId");
        then(savedMember.getName()).isEqualTo("tester");
        then(savedMember.getEmail()).isEqualTo("test@test.com");

    }

    @Test
    @DisplayName("멤버등록실패_테스트")
    public void 멤버등록실패_Test_failed_with_duplicated() throws Exception {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultMember));

        //when
        //then
        thenThrownBy(() -> {
            MemberDto memberDto = memberService.saveMember(defaultCreateMemberRequest);
        }).isInstanceOf(EntityNotFoundException.class);
    }
}