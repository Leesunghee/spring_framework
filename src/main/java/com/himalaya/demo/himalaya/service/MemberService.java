package com.himalaya.demo.himalaya.service;

import com.himalaya.demo.himalaya.ErrorCode;
import com.himalaya.demo.himalaya.MemberDto;
import com.himalaya.demo.himalaya.entity.Member;
import com.himalaya.demo.himalaya.entity.MemberRepository;
import com.himalaya.demo.himalaya.exception.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<MemberDto> findAll() {
        Optional<List<Member>> findMemberAll = memberRepository.findAll();
        if (findMemberAll.isPresent()) {
            return findMemberAll.get()
                    .stream()
                    .map(fm -> MemberDto.fromEntity(fm))
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException(ErrorCode.SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public MemberDto findByMemberId(String memberId) {
        return MemberDto.fromEntity(
                        getMemberByMemberId(memberId)
                );
    }

    private Member getMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE));
    }

    public MemberDto saveMember(MemberDto request) {
        validateSaveMemberRequest(request);
        return request.fromEntity(
                memberRepository.save(createMemberFromRequest(request))
        );
    }

    private void validateSaveMemberRequest(@NonNull MemberDto memberDto) {

        memberRepository.findByMemberId(memberDto.getMemberId())
                .ifPresent(member -> {
                    throw new EntityNotFoundException(ErrorCode.DUPLICATED_MEMBER_ID);
                });
    }

    public Member createMemberFromRequest(MemberDto request) {
        return Member.builder()
                .memberId(request.getMemberId())
                .name(request.getName())
                .email(request.getEmail())
                .build();



    }
}
