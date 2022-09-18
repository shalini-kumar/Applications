import java.util.Scanner;

public class Main {
    public static void bookTicket(Passenger p) {

        if (TicketBooker.availableWaitingList == 0) {
            System.out.println("NO TICKETS AVAILABLE");
            return;
        }

        TicketBooker booker = new TicketBooker();
        if (p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0 ||
                p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0 ||
                p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0) {
            System.out.println("preferred berth is available!!");
            if (p.berthPreference.equals("L")) {
                System.out.println("Lower Berth guiven");
                booker.bookTicket(p, (TicketBooker.lowerBerthPositions.get(0)), "L");
                TicketBooker.lowerBerthPositions.remove(0);
                TicketBooker.availableLowerBerths--;

            } else if (p.berthPreference.equals("M")) {
                System.out.println("Middle Berth guiven");
                booker.bookTicket(p, (TicketBooker.middleBerthPositions.get(0)), "M");
                TicketBooker.middleBerthPositions.remove(0);
                TicketBooker.availableMiddleBerths--;

            } else if (p.berthPreference.equals("U")) {
                System.out.println("Upper Berth guiven");
                booker.bookTicket(p, (TicketBooker.upperBerthPositions.get(0)), "U");
                TicketBooker.upperBerthPositions.remove(0);
                TicketBooker.availableUpperBerths--;

            }
        } else if (TicketBooker.availableLowerBerths > 0) {
            System.out.println("Lower Berth guiven");
            booker.bookTicket(p, (TicketBooker.lowerBerthPositions.get(0)), "L");
            TicketBooker.lowerBerthPositions.remove(0);
            TicketBooker.availableLowerBerths--;

        } else if (TicketBooker.availableMiddleBerths > 0) {
            System.out.println("Middle Berth guiven");
            booker.bookTicket(p, (TicketBooker.middleBerthPositions.get(0)), "M");
            TicketBooker.middleBerthPositions.remove(0);
            TicketBooker.availableMiddleBerths--;
        } else if (TicketBooker.availableUpperBerths > 0) {
            System.out.println("Upper Berth guiven");
            booker.bookTicket(p, (TicketBooker.upperBerthPositions.get(0)), "U");
            TicketBooker.upperBerthPositions.remove(0);
            TicketBooker.availableUpperBerths--;
        } else if (TicketBooker.availableRAC > 0) {
            System.out.println("RAC guiven");
            booker.bookRAC(p, (TicketBooker.RACPositions.get(0)), "RAC");
            // TicketBooker.upperBerthPositions.remove(0);
            // TicketBooker.availableUpperBerths--;

        } else if (TicketBooker.availableWaitingList > 0) {
            System.out.println("waiting list guiven");
            booker.bookWaitingList(p, (TicketBooker.waitingListPositions.get(0)), "WL");
        }
    }

    private static void cancelTicket(int id) {
        TicketBooker booker = new TicketBooker();

        if (!TicketBooker.passengers.containsKey(id)) {
            System.out.println("NO PASSENGER DETAILS AVAILABLE");
        } else {
            booker.cancelTicket(id);
        }
    }

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);
        Boolean loop = true;
        while (loop) {

            System.out.println("Enter you choice");
            System.out.println(
                    " 1. Book Ticket \n 2. Cancel Ticket \n 3. Booked Tickets \n 4. Available Tickets \n 5. Exit");
            int choice = s.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter passenger_name, age and berthpreference:");
                    String passengerName = s.next();
                    int age = s.nextInt();
                    String berthPreference = s.next();
                    Passenger p = new Passenger(passengerName, age, berthPreference);
                    bookTicket(p);

                }
                    break;
                case 2: {
                    System.out.println("ENTER THE PASSENGER ID:");
                    int id = s.nextInt();
                    cancelTicket(id);

                }
                    break;
                case 3: {
                    TicketBooker booker = new TicketBooker();
                    booker.printPassengers();

                }
                    break;
                case 4: {
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailablePasssengers();

                }
                    break;
                case 5: {
                    loop = false;

                }
                    break;

                default:
                    break;

            }
        }

    }

}
