package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN

@RestController
@RequestMapping("/api")
public class PostsController {
    List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> GetPostsById(@PathVariable String id) {
        return Data
                .posts
                .stream()
                .filter(item -> item.getUserId() == Integer.parseInt(id))
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPostByUserId(@PathVariable String id,
                                   @RequestBody Post post) {
        Post createdPost = new Post();
        createdPost.setUserId(Integer.parseInt(id));
        createdPost.setSlug(post.getSlug());
        createdPost.setTitle(post.getTitle());
        createdPost.setBody(post.getBody());
        Data.posts.add(createdPost);
        return createdPost;
    }
}
// END
