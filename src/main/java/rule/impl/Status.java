package rule.impl;

import exception.ParkingException;
import rule.IParkingRule;
import service.IParkingService;
import service.impl.ParkingServiceImpl;

/**
 * @author chopra
 * 21/02/18
 */
public class Status implements IParkingRule {
    private final IParkingService parkingService = ParkingServiceImpl.getInstance();

    public void apply(String[] input) {
        int arrayCount = input.length;
        if (arrayCount == 1){
            if (parkingService.isParkingLotCreated()) {
                try {
                    parkingService.parkingStatus();
                } catch (ParkingException e) {
                    System.out.println("Invalid");
                }
            }
            else {
                System.out.println("Parking lot is Not Created.");
            }
        }
        else {
            System.out.println("Invalid input passed.");
        }
    }
}
