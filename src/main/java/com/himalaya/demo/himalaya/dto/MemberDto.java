package com.himalaya.demo.himalaya.dto;

import com.himalaya.demo.himalaya.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberDto {

    private Long id;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotEmpty
    private String memberId;


    public static MemberDto fromEntity(@NonNull Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .memberId(member.getMemberId())
                .build();
    }
}
