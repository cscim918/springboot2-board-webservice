// DB Layer 접근자(보통 ibatis나 MyBatis 등에서 Dao라 불리는)
// Entity 클래스와 함께 위치해야 한다
package com.yunbo.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
