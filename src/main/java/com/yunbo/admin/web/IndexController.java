package com.yunbo.admin.web;

import com.yunbo.admin.config.auth.LoginUser;
import com.yunbo.admin.config.auth.dto.SessionUser;
import com.yunbo.admin.service.posts.PostsService;
import com.yunbo.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts",postsService.findAllDesc());
        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달

        if(user != null){
            model.addAttribute("user",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save") //이동할 페이지의 주소
    public String postsSave() {
        return "posts-save"; //posts-save.mustache를 호출
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
