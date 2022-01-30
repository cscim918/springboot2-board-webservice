package com.yunbo.admin.web.dto;

import com.yunbo.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        //Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성
        //-> Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이므로 자주 변경되면 안된다.(Req/Res Dto는 View를 위한 클래스라 자주 변경 필요)
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
