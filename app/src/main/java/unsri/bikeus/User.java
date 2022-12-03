package unsri.bikeus;

public class User {
    public String id;
    public String email;
    public String password;
    public static String emailStatic = "";

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static String getEmail(){
        return emailStatic;
    }

    public static void setEmail(String email){
        emailStatic = email;
    }
}