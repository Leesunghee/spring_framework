package com.himalaya.demo.himalaya;


import com.himalaya.demo.himalaya.exception.EntityNotFoundException;
import com.himalaya.demo.himalaya.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SimpleRestController {

    private final MemberService memberService;

    @GetMapping("/simple")
    public MemberDto findMemberOne(@RequestParam("value") final String value) {
        if (value.equals("value")) {
            throw new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE);
        } else if (value.equals("none")) {
            throw new IllegalArgumentException("invalid input value");
        } else {
            return new MemberDto(1L, "aaa", "abc@abc.com", "memberId");
        }
    }

    @PostMapping("/simple")
    public void SaveMember(@RequestBody @Valid final MemberDto member) {

    }

    @GetMapping("/member/{memberId}")
    public MemberDto findByMemberId(@PathVariable("memberId") final String memberId) {
        log.info("GET /member/{memberId HTTP/1.1");

        return memberService.findByMemberId(memberId);
    }
}
