package javabackend.dao;

import java.util.List;

import javabackend.model.Blog;

public interface BlogDao {

	public void createBlog(Blog blog);

	public Blog getBlogById(int blogid);

	public List<Blog> getAllBlogs();

	public Blog updateBlog(int blogid, Blog blog);

	public void deleteBlog(int blogid);

	public List<Blog> getBlogByStatus(String status);

}
