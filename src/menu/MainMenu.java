package menu;

import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {
    private static HotelResource hotelResource = HotelResource.getInstance();

    public MainMenu() {
    }

    public static void getMainMenu() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
//                while (scanner.hasNext()) {
                int selection = Integer.parseInt(scanner.next());

                switch (selection) {
                    case 1:
                        System.out.println("Main Menu - 1: Find and reserve a room\n");
//                        keepRunning = false;
                        findAndReserve();
                        break;
                    case 2:
                        System.out.println("Main Menu - 2: See my reservations\n");
//                        keepRunning = false;
                        break;
                    case 3:
                        System.out.println("Main Menu - 3: Create an account\n");
//                        keepRunning = false;
                        createACustomer();
//                        hotelResource.createACustomer(email, firstName, lastName);
                        break;
                    case 4:
                        System.out.println("Main Menu - 4: Admin\n");
                        AdminMenu.getAdminMenu();
                        break;
                    case 5:
                        System.out.println("Main Menu - 5: Exit\n");
                        keepRunning = false;
                        break;
                }
            }
//            keepRunning = true;
        } catch (Exception ex) {
            System.out.println("Wrong selection. Select between 1 through 5 on the menu.");
            getMainMenu();
        }
    }

    private static void findAndReserve() {
        Scanner dateScanner = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
        System.out.println("Enter check in date (mm/dd/yyyy example 02/01/2020)");
        Date checkInDate = null;
        try {
            checkInDate = formatter.parse(dateScanner.nextLine());
        }catch (ParseException pe){
            System.out.println("Invalid date format");
        }
        System.out.println("Enter check out date (mm/dd/yyyy example 02/01/2020)");
        Date checkOutDate = null;
        try {
            checkOutDate = formatter.parse(dateScanner.nextLine());
        }catch (ParseException pe){
            System.out.println("Invalid date format");
        }
        //1. get available rooms
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        for(IRoom room : availableRooms){
            System.out.println(room);
        }
        //2. check if the user wants to book any availabel room?
        System.out.println("Do you want to book a room?");
        String yesno = dateScanner.nextLine();
        //3. if yes, then ask if the user has an account
        if(yesno.equals("y")){
            System.out.println("Do you have an account?");
            String email = dateScanner.nextLine();
            //4. check if the customer has an account - check using the email
            Customer bookingCustomer = hotelResource.getCustomer(email);
            //5. customer has to select a room number from the available rooms - you get a IRoom
            System.out.println("Select a room from the available rooms");
            String roomNum = dateScanner.nextLine();
            IRoom room = hotelResource.getRoom(roomNum);
            hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        }

    }

    private static void createACustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customers first name : ");
        String firstName = scanner.next();
        System.out.println("Enter customers last name: ");
        String lastName = scanner.next();
        System.out.println("Enter customers email (name@somecompany.com: ");
        String email = scanner.next();
        hotelResource.createACustomer(email, firstName, lastName);
    }
}
