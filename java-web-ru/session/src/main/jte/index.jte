@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page
@template.layout.page(content = @`
    @if(page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <p>${page.getName().toString()}</p>
        <a href="${NamedRoutes.logoutPath()}">Выйти</a>
    @endif
`)