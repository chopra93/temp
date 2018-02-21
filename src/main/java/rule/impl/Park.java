package rule.impl;

import exception.ParkingException;
import rule.IParkingRule;
import service.IParkingService;
import service.impl.ParkingServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chopra
 * 21/02/18
 */
public class Park implements IParkingRule {
    private final IParkingService parkingService = ParkingServiceImpl.getInstance();

    public void apply(String[] input) {
        int arrayCount = input.length;
        if (arrayCount == 3){
            if (parkingService.isParkingLotCreated()) {
                try {
                  parkingService.park(input[1], input[2]);
                } catch (ParkingException e) {
                    System.out.println("Invalid input passed.");
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
