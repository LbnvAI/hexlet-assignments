package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String getPostsPath(){return "/posts";}
    public static String getPostPathById(){return "/posts/{id}";}
    // END
}
