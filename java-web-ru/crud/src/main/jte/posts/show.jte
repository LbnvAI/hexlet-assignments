@import exercise.dto.posts.PostPage
@param PostPage postPage
@template.layout.page(content = @`
    <p>${postPage.getPost().getName()}</p>
    <p>${postPage.getPost().getBody()}</p>
`)
