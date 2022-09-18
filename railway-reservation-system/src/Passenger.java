public class Passenger {
    static int id = 1;
    String passengerName;
    int age;
    String berthPreference;
    int passengerId;
    int number;
    String alloted;

    public Passenger(String passengerName, int age, String berthPreference) {
        this.passengerName = passengerName;
        this.age = age;
        this.berthPreference = berthPreference;
        passengerId = id++;
        number = -1;
        alloted = "";

    }

}
