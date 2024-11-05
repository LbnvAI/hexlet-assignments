package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage(null,null);
        ctx.render("build.jte",model("page",page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var pass = Security.encrypt(ctx.formParamAsClass("password", String.class).get());
        var targetUser = UsersRepository.findByName(name);
        if (targetUser.isEmpty()) {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        } else {
            if(targetUser.get().getPassword().equals(pass)){
                ctx.sessionAttribute("userCookie",name);
                ctx.redirect(NamedRoutes.rootPath());
            }
            else{
                var page = new LoginPage(name, "Wrong username or password");
                ctx.render("build.jte", model("page", page));
            }
        }
    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("userCookie",null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void root(Context ctx) {
        var currName = ctx.sessionAttribute("userCookie");
        var page = new MainPage(currName);
        ctx.render("index.jte", model("page", page));
    }
    // END
}
