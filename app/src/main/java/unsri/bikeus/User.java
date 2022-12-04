package unsri.bikeus;

public class User {
    public String id;
    public String email;
    public String password;
    private static String emailStatic = "";
    private static boolean rentStatus = false;

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

    public static boolean getRentStatus(){
        return rentStatus;
    }

    public static void setRentStatus(boolean status){
        rentStatus = status;
    }

}