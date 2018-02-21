package service.impl;

import dto.Car;
import dto.Vehicle;
import exception.ParkingException;
import service.IParkingService;

import java.util.*;

/**
 * @author chopra
 * 21/02/18
 */
public class ParkingServiceImpl implements IParkingService {

    private Map<Integer, Vehicle> parkingLot = null;
    private int maxNoofVehicles;
    private boolean isParkingLotCreated = false;
    private List<Integer> vacantSlots = null;
    private List<String> parkedCarRegistrationNoList = null;

    public boolean isParkingLotCreated() {
        return isParkingLotCreated;
    }

    public void createParkingLot(int maxNoofVehicles) throws ParkingException {
        try {
            if (maxNoofVehicles>0){
                this.maxNoofVehicles = maxNoofVehicles;
                parkingLot = new TreeMap<Integer, Vehicle>();
                vacantSlots = new ArrayList<Integer>();
                parkedCarRegistrationNoList = new ArrayList<String>();

                for (int i=0;i<maxNoofVehicles;i++){
                    vacantSlots.add(i+1);
                }

                System.out.println("Created a parking lot with " + maxNoofVehicles + " slots.\n");
                isParkingLotCreated = true;
            }
            else {
                System.out.println("Unable to Create Parking Lot.Enter Postive No for Creating Parking Lot.\n");
            }
        }
        catch (Exception e){
            throw new ParkingException("Exception");
        }
    }

    public void destroyParkingLot() {
        parkingLot = null;
        vacantSlots = null;
        isParkingLotCreated = false;
        parkedCarRegistrationNoList = null;
        System.out.println("Parking lot Destroyed.\n");
    }

    public void park(String registrationNo, String color) throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        if (!parkedCarRegistrationNoList.contains(registrationNo)) {
            if (parkingLot.keySet().size() == maxNoofVehicles) {
                System.out.println("Sorry, parking lot is full.\n");
            } else {
                Vehicle vehicle = new Car(registrationNo, color);
                int parkingSlot = nearestParkingSlot();
                parkingLot.put(parkingSlot, vehicle);
                vacantSlots.remove(vacantSlots.indexOf(parkingSlot));
                parkedCarRegistrationNoList.add(registrationNo);
                System.out.println("Allocated slot number: " + parkingSlot + ".\n");
            }
        } else {
            System.out.println("Car with Registration No. " + registrationNo + " already parked.\n");
        }
    }

    public void leave(int parkingSlotNo) throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        if (parkingLot.containsKey(parkingSlotNo)) {
            String parkedCarregistrationNo = parkingLot.get(parkingSlotNo).getRegNo();
            parkingLot.remove(parkingSlotNo);
            parkedCarRegistrationNoList.remove(parkedCarRegistrationNoList.indexOf(parkedCarregistrationNo));
            vacantSlots.add(parkingSlotNo);
            Collections.sort(vacantSlots);
            System.out.println("Slot number " + parkingSlotNo + " is free.\n");
        } else if (parkingSlotNo > maxNoofVehicles) {
            System.out.println("Invalid Input.Input beyond Parking lot capacity.\n");
        } else {
            System.out.println("Parking Slot is already vacant.\n");
        }
    }

    public void parkingStatus() throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        System.out.println("Slot No.	Registration No.	Colour");
        for (Map.Entry mpIterate: parkingLot.entrySet()){
            Integer parkingSlot = (Integer) mpIterate.getKey();
            Vehicle parkedVehicle = (Car)mpIterate.getValue();
            System.out.println(parkingSlot.toString() + "		" + parkedVehicle.getRegNo() + "		" + parkedVehicle.getColor());

        }
        System.out.println("\n");
    }

    public void registrationNosofCarsWithColor(String color) throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry mpIterate: parkingLot.entrySet()){
            Vehicle parkedVehicle = (Car)mpIterate.getValue();
            if (parkedVehicle!=null && parkedVehicle.getColor().equalsIgnoreCase(color)){
                builder.append(parkedVehicle.getRegNo());
                builder.append(", ");
            }
        }
        builder.setLength(builder.length() - 2);
        System.out.println(builder.toString());
    }

    public void slotNosOfCarsWithColor(String color) throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry mpIterate: parkingLot.entrySet()){
            Integer parkingSlot = (Integer) mpIterate.getKey();
            Vehicle parkedVehicle = (Car)mpIterate.getValue();
            if (parkedVehicle!=null && parkedVehicle.getColor().equalsIgnoreCase(color)){
                builder.append(parkingSlot.toString());
                builder.append(", ");
            }
        }
        builder.setLength(builder.length() - 2);
        System.out.println(builder.toString());
    }

    public void slotNoBasedOnRegistrationNo(String registrationNo) throws ParkingException {
        if (!isParkingLotCreated){
            throw new ParkingException("Exception");
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry mpIterate: parkingLot.entrySet()){
            Integer parkingSlot = (Integer) mpIterate.getKey();
            Vehicle parkedVehicle = (Car)mpIterate.getValue();
            if (parkedVehicle!=null && parkedVehicle.getRegNo().equalsIgnoreCase(registrationNo)){
                builder.append(parkingSlot.toString());
                builder.append(", ");
            }
        }
        builder.setLength(builder.length() - 2);
        System.out.println(builder.toString());
    }

    private int nearestParkingSlot() {
        return vacantSlots.get(0);
    }
}
