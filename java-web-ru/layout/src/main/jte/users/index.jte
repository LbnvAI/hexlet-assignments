@import exercise.dto.users.UsersPage
@import exercise.model.User

@param UsersPage page

@template.layout.page(content = @`
    <ul>
        @for(User user: page.getUsers())
            <li><a href="/users/${user.getId()}">${user.getFirstName()+" "+user.getLastName()}</a></li>
        @endfor
    </ul>
`)