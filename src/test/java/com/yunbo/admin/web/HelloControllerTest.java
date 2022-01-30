package com.yunbo.admin.web;

import com.yunbo.admin.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Security;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행(스프링 부트 테스트와 JUnit 사이에 연결자 역할)
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
//여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
//선언할 경우 @Controller, @ControllerAdvice 등을 사용 가능
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입받는다
    private MockMvc mvc;
    //웹API를 테스트할 때 사용
    //스프링 MVC 테스트의 시작점
    //이 클래스를 통해 HTTP 메서드 등에 대한 API 테스트

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) //HTTP Header의 Status를 검증(200인지 아닌지 검증)
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증(hello를 리턴하는지 검증)
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name) //API 테스트할 때 사용될 요청 파라미터 설정, 단 값은 String만 허용
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        //jsonPath - JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
    }
}
