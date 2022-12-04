package unsri.bikeus;

public class Bike {
    public static String[] bikes = {"US2211", "US2212", "US2213", "US2214", "US2215"};

    public static boolean[] status = {false, false, false, false, false};

    public static boolean authenticate(String number){
        for(int i = 0; i < bikes.length; i++){
            if(number.equals(bikes[i]) && status[i] == false){
                status[i] = true;
                return true;
            }
        }
        return false;
    }

    public static void returnBike(String number){
        for(int i = 0; i < bikes.length; i++){
            if(number == bikes[i] && status[i] == true){
                status[i] = false;
            }
        }
    }
}
