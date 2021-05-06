package com.example;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DAO<T>  {
	
	
	
	public abstract T create(@PathVariable(value="id") String id, @RequestBody T d, HttpServletResponse response);
	public abstract T read(@PathVariable(value="id") String id, HttpServletResponse response);
	public abstract T update(@PathVariable(value="id") String id, @RequestBody T d, HttpServletResponse response);
	public abstract void delete(@PathVariable(value="id") String id, HttpServletResponse response);

}