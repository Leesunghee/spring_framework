package com.himalaya.demo.himalaya;

import com.himalaya.demo.himalaya.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimpleRestController.class)
class SimpleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    protected MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @MockBean
    private MemberService memberService;

    @Test
    public void testFindByMemberId() throws Exception {
        //given
        given(memberService.findByMemberId(anyString()))
                .willReturn(MemberDto.builder()
                        .memberId("memberId")
                        .name("tester")
                        .email("test@test.com")
                        .build());

        //when
        mockMvc.perform(get("/member/memberId").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.memberId", is("memberId"))
                )
                .andExpect(
                        jsonPath("$.name", is("tester"))
                )
                .andExpect(
                        jsonPath("$.email", is("test@test.com"))
                );

        //then
    }

}