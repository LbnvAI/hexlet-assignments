package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // BEGIN
        app.get("/users",ctx->{
            Integer page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            Integer per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            var userList = Data.getUsers();
            var pageCount = userList.size()/per;
            var responseList = new ArrayList<Map<String, String>>();
            for(int i = (page-1)*per;i<page*per;i++){
                responseList.add(userList.get(i));
            }
            ctx.json(responseList);
        });
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
