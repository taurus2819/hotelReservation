package service;

import model.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {
    private static ReservationService instance = null;
    private Collection<Reservation> reservations = new HashSet<>(); //contains a collection of reservations
    private Collection<IRoom> rooms = new HashSet<>();  //collection of rooms

    private ReservationService(){}

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public static ReservationService getInstance(){
        if(instance == null){
            instance = new ReservationService();
        }
        return instance;
    }

    public void addARoom(IRoom room){

        //Add the room details during the call FROM the front end menu "Add a room" - set the room details using the setters in the Room model
//        IRoom newRoom = new Room("1A", 100.0, RoomType.SINGLE);
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        IRoom room = null;
        for(Reservation reservation : reservations){
            if(reservation.getIroom().getRoomNumber().equals(roomId)){
                room = reservation.getIroom();
            }
        }
        return room;
    }

    public void printAllReservation(){
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    //return a coolection of a particular customer's current reservations
    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersCurrentReservations = new HashSet<>();
        for(Reservation customerCurrentReservation : reservations){
            if(customer.getEmail().equals(customerCurrentReservation.getCustomer().getEmail())){
                IRoom roomCurrentlyBookedByCustomer = customerCurrentReservation.getIroom();
                customersCurrentReservations.add(customerCurrentReservation);
            }
        }
        return customersCurrentReservations;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> vacantRooms = new HashSet<>();
        if(reservations != null) {                   //if there are any existing reservations
            for (Reservation reservation : reservations) {
                if (reservation.getIroom().getRoomType() == RoomType.SINGLE) {
                    if (checkInDate.compareTo(reservation.getCheckOutDate()) > 0) {
                        vacantRooms.add(reservation.getIroom());
                    }
                    //below is the condition to check if there are any rooms availalbe before any exiting reservation
//                    if(checkInDate.compareTo(reservation.getCheckInDate()) < 0 && checkOutDate.compareTo(reservation.getCheckInDate()) < 0){
//                        vacantRooms.add(reservation.getIroom());
//                    }
                } else if (reservation.getIroom().getRoomType() == RoomType.DOUBLE) {
                    if (checkInDate.compareTo(reservation.getCheckOutDate()) > 0) {
                        vacantRooms.add(reservation.getIroom());
                    }
                }
            }
        } else{
            vacantRooms = rooms;
        }
        return vacantRooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = null;
        Collection<IRoom> freeRooms = findRooms(checkInDate, checkOutDate);
        for (IRoom emptyRoom : freeRooms) {
            IRoom roomNum = getARoom(room.getRoomNumber());
            if(emptyRoom.getRoomNumber().equals(roomNum)){
                reservation = new Reservation(customer, room, checkInDate, checkOutDate);
                reservations.add(reservation);
            }
        }
        return reservation;
    }



}
