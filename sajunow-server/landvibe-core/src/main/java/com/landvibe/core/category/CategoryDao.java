package com.landvibe.core.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryDao {
	
	int insert(Category category);

	//Category selectByCode(@Param("category_code") long category_code);
	
	List<Category> selectAll();
	
	int delete(@Param("category_code") long category_code);
	
	int update(Category category); 

}
