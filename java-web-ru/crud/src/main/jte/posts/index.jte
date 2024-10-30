@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage postsPage

@template.layout.page(content = @`
    <ul>
        @for(Post post: postsPage.getPosts())
            <li><a href="${NamedRoutes.getPostsPath()}/${post.getId()}">${post.getName()}</a></li>
        @endfor
    </ul>
    <a href="${NamedRoutes.getPostsPath()}/?page=${postsPage.getPageNumber()-1}">назад</a>
    <a href="${NamedRoutes.getPostsPath()}/?page=${postsPage.getPageNumber()+1}">вперед</a>
`)