@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page
@template.layout.page(content = @`
    @if(page.getFlash()!=null)
        <p>${page.getFlash()}</p>
    @endif
    <ul>
        @for(Post post: page.getPosts())
        <li><a>${post.getName()}</a></li>
        @endfor
    </ul>
`)