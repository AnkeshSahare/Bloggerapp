package com.myblog7.controller;


import com.myblog7.payload.PostDto;
import com.myblog7.payload.PostResponse;
import com.myblog7.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private  PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }



    //http://localhost:8080/api/5
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer id){
        postService.deletePost(id);
        return new ResponseEntity<>("Ankesh Cool Post is deleted", HttpStatus.OK);//200
    }

    //http://localhost:8080/api/5
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Integer id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK); //200
    }

    //http://localhost:8080/api/5
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id){
        PostDto dto = postService.getPostById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);//200
    }

    //http://localhost:8080/api?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)  int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)  int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)  String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)  String sortDir
    ){
        PostResponse postResponse = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
    }

}
