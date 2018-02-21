package controller;

import constants.ParkingLotConstants;
import exception.ParkingException;
import rule.IParkingRule;
import rule.impl.*;
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

    private static IParkingRule parkingRule = null;

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
            parkingRule = new CreateParkingLot();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.PARK)){
            parkingRule = new Park();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.LEAVE)){
            parkingRule = new Leave();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.REG_NUM_LIST_WITH_COLOUR)){
            parkingRule = new RegistrationListBasedOnColour();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.SLOT_NUM_FOR_REG_NUM)){
            parkingRule = new SlotBasedOnRegistrationNumber();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.SLOT_NUM_LIST_WITH_COLOUR)){
            parkingRule = new SlotNumberListBasedOnColour();
            parkingRule.apply(parkingCommandArray);
        }
        else
        if (key.equalsIgnoreCase(ParkingLotConstants.STATUS)){
            parkingRule = new Status();
            parkingRule.apply(parkingCommandArray);
        }
        else {
            System.out.println("Invalid command Entered.");
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
