package controller;

import constants.ParkingLotConstants;
import exception.ParkingException;
import service.IParkingService;
import service.impl.ParkingServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chopra
 * 21/02/18
 */
public class ParkingLotController {

    private static IParkingService parkingService = new ParkingServiceImpl();

    public static void main(String[] args) {
        if (args.length == 0){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Command ");
            String parkingCommand = sc.nextLine();
            while (parkingCommand != null){
                processCommand(parkingCommand);
                System.out.println("Enter Command ");
                parkingCommand = sc.nextLine();
            }
        }
        else {
            String fileLocation = args[0];
            processFile(fileLocation);
        }
    }

    private static void processCommand(String parkingCommand){
        String[] parkingCommandArray = parkingCommand.split("\\s+");
        processCommandUtil(parkingCommandArray);

    }

    private static void processCommandUtil(String [] parkingCommandArray){
        String key = parkingCommandArray[0];
        if (key.equalsIgnoreCase(ParkingLotConstants.CREATE_PARKING_LOT)){
            if (!parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 1) {
                    try {
                        parkingService.createParkingLot(Integer.parseInt(parkingCommandArray[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Inavlid input passed.\n");
                    } catch (ParkingException e) {
                        System.out.println("Inavlid input passed.\n");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            }
            else {
                System.out.println("Parking lot already created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.PARK)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 2) {
                    try {
                        parkingService.park(parkingCommandArray[1], parkingCommandArray[2]);
                    } catch (ParkingException e) {
                        System.out.println("Invalid input passed.\n");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.LEAVE)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 1) {
                    final String REGEX = "\\d+";
                    Pattern pattern = Pattern.compile(REGEX);
                    Matcher matcher = pattern.matcher(parkingCommandArray[1]);
                    if (matcher.matches()) {
                        try {
                            parkingService.leave(Integer.parseInt(parkingCommandArray[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("Inavlid input passed.\n");
                        } catch (ParkingException e) {
                            System.out.println("Inavlid input passed.\n");
                        }
                    } else {
                        System.out.println("Invalid input passed.\n");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.REG_NUM_LIST_WITH_COLOUR)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 1) {
                    try {
                        parkingService.registrationNosofCarsWithColor(parkingCommandArray[1]);
                    } catch (ParkingException e) {
                        System.out.println("Exception");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.SLOT_NUM_FOR_REG_NUM)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 1) {
                    try {
                        parkingService.slotNoBasedOnRegistrationNo(parkingCommandArray[1]);
                    } catch (ParkingException e) {
                        System.out.println("Exception");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.SLOT_NUM_LIST_WITH_COLOUR)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 1) {
                    try {
                        parkingService.slotNosOfCarsWithColor(parkingCommandArray[1]);
                    } catch (ParkingException e) {
                        System.out.println("Exception");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }
            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.STATUS)){
            if (parkingService.isParkingLotCreated()) {
                if ((parkingCommandArray.length - 1) == 0) {
                    try {
                        parkingService.parkingStatus();
                    } catch (ParkingException e) {
                        System.out.println("Invalid ");
                    }
                } else {
                    System.out.println("Invalid input passed.\n");
                }

            } else {
                System.out.println("Parking lot is Not Created.\n");
            }
        }
        else {
            System.out.println("Invalid command Entered.\n");
        }
    }


    private static void processFile(String file){
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            List<String> parkingLotCommands = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                parkingLotCommands.add(line);
                line = reader.readLine();
            }

            if (parkingLotCommands.isEmpty()){
                return;
            }

            for (String command : parkingLotCommands){
                processCommand(command);
            }

        } catch (IOException e) {

        }
        finally {
            if (reader!= null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }

    }
}
