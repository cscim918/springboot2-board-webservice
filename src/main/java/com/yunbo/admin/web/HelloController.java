package com.yunbo.admin.web;

import com.yunbo.admin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
public class HelloController {
    @GetMapping("/hello") // HTTP 메서드인 Get의 요청을 받을 수 있는 API 생성, /hello로 요청이 오면 문자열 hello를 반환하는 기능
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
