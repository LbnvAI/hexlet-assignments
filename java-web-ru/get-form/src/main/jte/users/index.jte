@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page
@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Искать">
    </form>
    @if(!page.getUsers().isEmpty())
        <ul>
            @for(User user: page.getUsers())
                <li>${user.toString()}</li>
            @endfor
        </ul>
    @endif
`)
