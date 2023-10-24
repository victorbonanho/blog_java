package com.spring.codeblog.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;

//import jakarta.annotation.PostConstruct;

@Component
public class DummyData {
	
	@Autowired
	CodeblogRepository codeblogRepository;
	
	//@PostConstruct
	public void savePosts() {
		
		List<Post> postList = new ArrayList<>();
		Post post1 = new Post();
		post1.setAutor("Victor Bonanho");
		post1.setData(LocalDate.now());
		post1.setTitulo("Docker");
		post1.setTexto("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ut ante felis. Suspendisse ornare porttitor est, quis malesuada lectus suscipit quis. Integer dignissim massa a efficitur pretium. Donec rutrum arcu tortor, bibendum accumsan odio dapibus a. Vestibulum rhoncus placerat est ac scelerisque. Aenean placerat at magna ac condimentum. Cras et iaculis tortor.");
	
		Post post2 = new Post();
		post2.setAutor("Victor Bonanho");
		post2.setData(LocalDate.now());
		post2.setTitulo("API REST");
		post2.setTexto("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ut ante felis. Suspendisse ornare porttitor est, quis malesuada lectus suscipit quis. Integer dignissim massa a efficitur pretium. Donec rutrum arcu tortor, bibendum accumsan odio dapibus a. Vestibulum rhoncus placerat est ac scelerisque. Aenean placerat at magna ac condimentum. Cras et iaculis tortor");
	
		postList.add(post1);
		postList.add(post2);
		
		for(Post post: postList) {
			Post postSaved = codeblogRepository.save(post);
			System.out.println(postSaved.getId());
		}
		
		
	}
	

}
