package com.yunbo.admin.service.posts;

import com.yunbo.admin.domain.posts.Posts;
import com.yunbo.admin.domain.posts.PostsRepository;
import com.yunbo.admin.web.dto.PostsListResponseDto;
import com.yunbo.admin.web.dto.PostsResponseDto;
import com.yunbo.admin.web.dto.PostsSaveRequestDto;
import com.yunbo.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
// 생성자를 직접 안쓰고 롬복 어노테이션 사용한 이유는
// 해당 클래스의 의존성 관계가 변경될 때 마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //readOnly = true를 주면 트랜잭션 범위 유지하되 조회 기능만 남겨 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //.map(posts -> new PostsListResponseDto(posts))와 똑같다.
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
