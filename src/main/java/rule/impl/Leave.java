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
public class Leave implements IParkingRule {
    private final IParkingService parkingService = ParkingServiceImpl.getInstance();

    public void apply(String[] input) {
        int arrayCount = input.length;
        if (arrayCount == 2){
            if (parkingService.isParkingLotCreated()) {
                final String REGEX = "\\d+";
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(input[1]);
                if (matcher.matches()) {
                    try {
                        parkingService.leave(Integer.parseInt(input[1]));
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid input passed.");
                    }
                    catch (ParkingException e) {
                        System.out.println("Invalid input passed.");
                    }
                }
                else {
                    System.out.println("Invalid input passed.");
                }
            }
            else {
                System.out.println("Parking lot is Not Created.");
            }
        }
        else {
            System.out.println("Invalid input passed");
        }
    }
}
