package com.landvibe.core.post;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao {
	int insert(Post post);

	Post select(@Param("postNo") long postNo);

	int update(Post post);

	int delete(@Param("postNo") long postNo);
}