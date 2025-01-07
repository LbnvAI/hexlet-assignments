package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") Integer pageNumber,
                               @RequestParam(defaultValue = "3") Integer limit) {
        List<Post> result = new ArrayList<>(limit);
        int startPost = (pageNumber - 1) * limit;
        int endPost = startPost + limit - 1;
        for (int i = startPost; i <= endPost; i++) {
            result.add(posts.get(i));
        }
        return result;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        return posts.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> maybePost = posts.stream().filter(item -> item.getId().equals(id)).findFirst();
        if (maybePost.isPresent()) {
            Post exsistPost = maybePost.get();
            exsistPost.setTitle(post.getTitle());
            exsistPost.setBody(post.getBody());
            exsistPost.setId(post.getId());
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.removeIf(item -> item.getId().equals(id));
    }
    // END
}
