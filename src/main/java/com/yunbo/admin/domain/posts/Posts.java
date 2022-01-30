// Entity 클래스(실제 DB 테입블과 매칭될 클래스)
// ! 절대 Setter 메소드를 만들지 않는다 !
// -> 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수 없어서
package com.yunbo.admin.domain.posts;

import com.yunbo.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성. 롬복 어노테이션
@NoArgsConstructor // 기본 생성자 자동 추가. public Posts(){}와 같은 효과. 롬복 어노테이션
@Entity // 테이블과 링크될 클래스. JPA 어노테이션
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// PK의 생성 규칙. IDENTITY 옵션을 추가해야 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)// 해당 클래스의 필드는 모두 컬럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
