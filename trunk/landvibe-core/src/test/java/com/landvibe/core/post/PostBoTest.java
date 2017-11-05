package com.landvibe.core.post;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.landvibe.LandvibeSpringContextLoaderForTest;

public class PostBoTest extends LandvibeSpringContextLoaderForTest {
	@Autowired
	private PostBo postBo;

	@Test
	public void testCreate() {
	}
}