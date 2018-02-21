package service;

import exception.ParkingException;

/**
 * @author chopra
 * 21/02/18
 */
public interface IParkingService {

    boolean isParkingLotCreated();
    void createParkingLot(int maxNoofVehicles) throws ParkingException;
    void destroyParkingLot();
    void park(String registrationNo, String color) throws ParkingException;
    void leave(int parkingSlotNo) throws ParkingException;
    void parkingStatus() throws ParkingException;
    void registrationNosofCarsWithColor(String color) throws ParkingException;
    void slotNosOfCarsWithColor(String color) throws ParkingException;
    void slotNoBasedOnRegistrationNo(String registrationNo) throws ParkingException;

}
