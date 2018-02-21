package rule.impl;

import exception.ParkingException;
import rule.IParkingRule;
import service.IParkingService;
import service.impl.ParkingServiceImpl;

/**
 * @author chopra
 * 21/02/18
 */
public class SlotNumberListBasedOnColour implements IParkingRule {
    private final IParkingService parkingService = ParkingServiceImpl.getInstance();

    public void apply(String[] input) {
        int arrayCount = input.length;
        if (arrayCount == 2){
            if (parkingService.isParkingLotCreated()) {
                try {
                    parkingService.slotNosOfCarsWithColor(input[1]);
                } catch (ParkingException e) {
                    System.out.println("Exception");
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
