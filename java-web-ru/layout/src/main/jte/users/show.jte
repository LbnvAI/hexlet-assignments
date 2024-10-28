@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`<p>${page.getUser().toString()}</p>`)