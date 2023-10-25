package com.spring.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;

import jakarta.validation.Valid;

@Controller
public class CodeblogController {
	
	@Autowired
	CodeblogService codeblogService;
	
	@GetMapping("/posts")
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Post> posts = codeblogService.findAll();
		mv.addObject("posts", posts);
		return mv;
	}
	
	@GetMapping("/posts/{id}")
	public ModelAndView getPostDetails(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("postDetails");
		Post post = codeblogService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	
	@GetMapping("/posts/edit/{id}")
	public ModelAndView editPostForm(@PathVariable long id) {
	    ModelAndView mv = new ModelAndView("editPost");
	    Post post = codeblogService.findById(id);
	    mv.addObject("post", post);
	    return mv;
	}
	
	@GetMapping("/newpost")
	public String getPostForm() {
		return "postForm";
	}
	
	@PostMapping("/newpost")
	public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos");
			return "redirect:/newpost";
		}
		post.setData(LocalDate.now());
		codeblogService.save(post);
		return "redirect:/posts";
	}
	
	@PutMapping("/posts/edit/{id}")
	public String editPost(@PathVariable Long id, @Valid Post post, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.println("o result --> " + result);
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos");
            return "redirect:/posts/edit/" + id;
        }
        Post existingPost = codeblogService.findById(id);
        if (existingPost != null) {
            existingPost.setTitulo(post.getTitulo());
            existingPost.setAutor(post.getAutor());
            existingPost.setTexto(post.getTexto());
            codeblogService.save(existingPost);
        }
        return "redirect:/posts";
	}
	
	@DeleteMapping("/posts/{id}")
	@ResponseBody
	public String deletePost(@PathVariable long id) {
		Post post = codeblogService.findById(id);
		if (post != null) {
			codeblogService.delete(id);
		}
		return "redirect:/posts";
	}
	
}
