package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void getFormInfo(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        var token = Security.generateToken();
        ctx.cookie("userCookie", token);
        UserRepository.save(new User(firstName, lastName, email, password, token));
        ctx.redirect("/users/" + UserRepository.getEntities().size());
    }

    public static void show(Context ctx) {
        var currentToken = ctx.cookie("userCookie");
        var currentId = ctx.pathParamAsClass("id", Long.class).get();
        var targetUser = UserRepository.find(currentId);
        if (targetUser.get().getToken().equals(currentToken)) {
            var page = new UserPage(targetUser.get());
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect("/users/build");
        }
    }
    // END
}
