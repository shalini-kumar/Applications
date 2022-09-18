import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooker {

    static int availableLowerBerths = 1;
    static int availableMiddleBerths = 1;
    static int availableUpperBerths = 1;
    static int availableRAC = 1;
    static int availableWaitingList = 1;

    static Queue<Integer> racList = new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    static List<Integer> bookedList = new ArrayList<>();

    static List<Integer> lowerBerthPositions = new ArrayList<>(
            Arrays.asList(1));
    static List<Integer> middleBerthPositions = new ArrayList<>(
            Arrays.asList(1));
    static List<Integer> upperBerthPositions = new ArrayList<>(
            Arrays.asList(1));
    static List<Integer> RACPositions = new ArrayList<>(
            Arrays.asList(1));
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

    static Map<Integer, Passenger> passengers = new HashMap<>();

    public void bookTicket(Passenger p, int berthnum, String allotedberth) {
        p.number = berthnum;
        p.alloted = allotedberth;
        passengers.put(p.passengerId, p);
        bookedList.add(p.passengerId);
        System.out.println("ticket booked successfully");
        System.out.println("-------------------------------------------");

    }

    public void cancelTicket(int passengerId) {
        Passenger p = passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));
        bookedList.remove(Integer.valueOf(passengerId));
        int positionBooked = p.number;
        System.out.println("TICKET CANCELLED SUCCESSFULLy!!");
        System.out.println("---------------------------------------------");

        if (p.alloted.equals("L")) {
            availableLowerBerths++;
            lowerBerthPositions.add(positionBooked);
        }
        if (p.alloted.equals("M")) {
            availableMiddleBerths++;
            middleBerthPositions.add(positionBooked);
        }
        if (p.alloted.equals("U")) {
            availableUpperBerths++;
            upperBerthPositions.add(positionBooked);
        }

        if (racList.size() > 0) {
            Passenger racPassenger = passengers.get(racList.poll());
            int RACposition = racPassenger.number;
            RACPositions.add(RACposition);
            racList.remove(Integer.valueOf(racPassenger.passengerId));
            availableRAC++;

            if (waitingList.size() > 0) {
                Passenger waitingListPassenger = passengers.get(waitingList.poll());
                int WaitingListPosition = waitingListPassenger.number;
                waitingListPositions.add(WaitingListPosition);
                waitingList.remove(Integer.valueOf(waitingListPassenger.passengerId));

                waitingListPassenger.number = RACPositions.get(0);
                waitingListPassenger.alloted = "RAC";
                RACPositions.remove(0);
                racList.add(waitingListPassenger.passengerId);

                availableRAC--;
                availableWaitingList++;

            }

            Main.bookTicket(racPassenger);

        }

    }

    public void bookRAC(Passenger p, int berthnum, String allotedberth) {
        p.number = berthnum;
        p.alloted = allotedberth;
        passengers.put(p.passengerId, p);
        racList.add(p.passengerId);
        RACPositions.remove(0);
        availableRAC--;

    }

    public void bookWaitingList(Passenger p, int berthnum, String allotedberth) {
        p.number = berthnum;
        p.alloted = allotedberth;
        passengers.put(p.passengerId, p);
        waitingList.add(p.passengerId);
        waitingListPositions.remove(0);
        availableWaitingList--;
    }

    public void printPassengers() {
        if (passengers.size() == 0) {
            System.out.println("NO PASSENGER LIST AVAILABLE");
            return;
        } else {
            for (Passenger p : passengers.values()) {
                System.out.println("PASSENGER_ID:  " + p.passengerId);
                System.out.println("PASSENGER_NAME:  " + p.passengerName);
                System.out.println("PASSENGER_AGE:  " + p.age);
                System.out.println("PASSENGER_STATUS:  " + p.number + p.alloted);
                System.out.println("------------------------------------");
            }
        }

    }

    public void printAvailablePasssengers() {
        System.out.println("AVAILABLE LOWER BERTHS:  " + availableLowerBerths);
        System.out.println("AVAILABLE MIDDLE BERTHS:  " + availableMiddleBerths);
        System.out.println("AVAILABLE UPPER BERTHS:  " + availableUpperBerths);
        System.out.println("AVAILABLE RAC:  " + availableRAC);
        System.out.println("AVAILABLE WAITING LIST:  " + availableWaitingList);
        System.out.println("----------------------------------------");
    }

}
