package com.landvibe.core.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBo {
	
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * 카테고리 추가 
	 * @param category
	 * @return
	 */
	public int insert(Category category){
		return categoryDao.insert(category);
	}
	
//	public Category getByCode(long category_code){
//		return categoryDao.selectByCode(category_code);
//	}
	
	/**
	 * 카테고리 리스트 
	 * @return
	 */
	public List<Category> getAll(){
		return categoryDao.selectAll();
	}
	
	/**
	 * 카테고리 삭제 
	 * @param category_code
	 * @return
	 */
	public int delete(long category_code){
		return categoryDao.delete(category_code);
	}
	
	/**
	 * 카테고리 수정 
	 * @param category
	 * @return
	 */
	public int update(Category category){
		return categoryDao.update(category);
	}

}
