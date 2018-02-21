package rule.impl;

import exception.ParkingException;
import rule.IParkingRule;
import service.IParkingService;
import service.impl.ParkingServiceImpl;

/**
 * @author chopra
 * 21/02/18
 */
public class CreateParkingLot implements IParkingRule {

    private final IParkingService parkingService = ParkingServiceImpl.getInstance();


    public void apply(String[] input) {
        int arrayCount = input.length;
        if (arrayCount == 2){
            if (!parkingService.isParkingLotCreated()){
                try {
                    parkingService.createParkingLot(Integer.parseInt(input[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input passed.");
                } catch (ParkingException e) {
                    System.out.println("Invalid input passed.");
                }
            }
            else {
                System.out.println("Parking lot already created.");
            }
        }
        else {
            System.out.println("Invalid input passed");
        }
    }
}
