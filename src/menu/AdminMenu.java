package menu;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getInstance();

    public AdminMenu() {
    }

    public static void getAdminMenu() {
        boolean keepRunning = true;
        System.out.println("Hotel Admin Menu");
//        Scanner scanner = null;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                System.out.println("1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. See all Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Back to Main Menu");
//                while (scanner.hasNext()) {
                int selection = Integer.parseInt(scanner.next());

                switch (selection) {
                    case 1:
                        System.out.println("Admin Menu - 1: See all Customers\n");
                        Collection<Customer> customers = adminResource.getAllCustomers();
                        for(Customer customer : customers){
                            System.out.println(customer);
                        }
                        System.out.println("----------------------------------------------------------------");
                        break;
                    case 2:
                        System.out.println("Admin Menu - 2: See all Rooms\n");
                        Collection<IRoom> allRoomsAvailable = adminResource.getAllRooms();
                        for (IRoom room : allRoomsAvailable) {
                            System.out.println( "Room # : " + room.getRoomNumber() + ", Price : " + room.getRoomPrice() + ", Type : " + room.getRoomType());
                        }
                        break;
                    case 3:
                        System.out.println("Admin Menu - 3: See all Reservations\n");
                        adminResource.displayAllReservations();
                        break;
                    case 4:
                        System.out.println("Admin Menu - 4: Add a Room\n");
//                        keepRunning = false;
                        addARoom();
                        break;
                    case 5:
                        System.out.println("Admin Menu - 5: Back to Main Menu\n");
                        MainMenu.getMainMenu();
                }
            }
        } catch (Exception ex) {
            System.out.println("Wrong selection");
            ex.printStackTrace();
        }
    }

    private static void addARoom() {
        boolean keepRunningAddRoom = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter a room number ");
            String roomNum = scanner.next();
            System.out.println("Enter price per night");
            Double price = scanner.nextDouble();
            System.out.println("Enter room type: 1 for a single bed, 2 for double bed");
            int type = scanner.nextInt();
            RoomType roomType = null;
            if (type == 1) {
                roomType = RoomType.SINGLE;
            } else if (type == 2) {
                roomType = RoomType.DOUBLE;
            } else if (type != 1 || type != 2) {
                throw new IllegalArgumentException("Room type is invalid. Select either 1 or 2");
            }
            adminResource.addRoom(new Room(roomNum, price, roomType));
            System.out.println("Number of rooms = " + adminResource.getAllRooms().size());
            System.out.println("Would like to add another room y/n");

            if (scanner.nextLine().equals("y")) {
                keepRunningAddRoom = true;
            }
//            if (scanner.nextLine().equals("n")) {
//                keepRunningAddRoom = false;
//            }
//
//            while(!scanner.nextLine().equals("y") && !scanner.nextLine().equals("n")) {
//                System.out.println("Please select either y/n and not " + scanner.nextLine());
//            }

        } while (keepRunningAddRoom && !scanner.nextLine().equals("n"));
        System.out.println("finished adding a room");
        scanner.close();
    }
}
