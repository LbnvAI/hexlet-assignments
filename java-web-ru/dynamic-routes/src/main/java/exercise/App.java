package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var companyId = ctx.pathParamAsClass("id", Integer.class).getOrDefault(null);
            Optional<Map<String,String>> company;
            if (Objects.isNull(companyId)) throw new NotFoundResponse("Company not found");
            else {
                company = COMPANIES.stream()
                        .filter(item -> {
                            return (item.get("id").equals(String.valueOf(companyId)));
                        })
                        .findFirst();

            }
            if(company.isEmpty()) throw new NotFoundResponse("Company not found");
            else ctx.json(company.get());
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
