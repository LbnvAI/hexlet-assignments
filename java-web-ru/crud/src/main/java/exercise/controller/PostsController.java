package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        if (pageNumber == 0) pageNumber = 1;
        if (pageNumber > 7) pageNumber = 7;
        List<Post> pagePosts = PostRepository.findAll(pageNumber, 5);
        PostsPage postsPage = new PostsPage(pagePosts, pageNumber);
        ctx.render("posts/index.jte", model("postsPage", postsPage));
    }

    public static void show(Context ctx) {
        var postId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        if (Objects.isNull(postId)) throw new NotFoundResponse("Page Not Found");
        var post = PostRepository.find(postId);
        if (post.isEmpty()) throw new NotFoundResponse("Page Not Found");
        else {
            PostPage postPage = new PostPage(post.get());
            ctx.render("posts/show.jte", model("postPage", postPage));
        }
    }
    // END
}
