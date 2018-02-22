import exception.ParkingException;
import mockit.NonStrictExpectations;
import mockit.Tested;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.impl.ParkingServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by Chopra on 22/02/18.
 */
public class ParkingServiceTest {

    @Tested
    private ParkingServiceImpl parkingService;

    @Test
    public void isParkingLotCreatedNegativeTest(){
        Assert.assertEquals(parkingService.isParkingLotCreated(),false);
    }

    @Test
    public void isParkingLotCreatedPositiveTest() throws ParkingException {
        parkingService.createParkingLot(1);
        Assert.assertEquals(parkingService.isParkingLotCreated(),true);
    }

    @Test
    public void createParkingLotPositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\n");
    }

    @Test
    public void createParkingLotNegativeTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(-1);

        Assert.assertEquals(outContent.toString(),"Unable to Create Parking Lot.Enter Postive No for Creating Parking Lot.\n");
    }

    @Test
    public void destroyParkingLotTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.destroyParkingLot();

        Assert.assertEquals(outContent.toString(),"Parking lot Destroyed.\n");
        Assert.assertEquals(parkingService.isParkingLotCreated(),false);
    }

    @Test(expectedExceptions = ParkingException.class)
    public void parkExceptionTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.park("KA-01-CC-1234","white");
        Assert.assertEquals(outContent.toString(),"Allocated slot number: 1.\n");
    }

    @Test
    public void parkPositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\n");
    }

    @Test
    public void parkAlreadtParkedTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.park("KA-01-CC-1234","silver");
        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nCar with Registration No. KA-01-CC-1234 already parked.\n");
    }

    @Test(expectedExceptions = ParkingException.class)
    public void leaveExceptionTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.leave(1);
        Assert.assertEquals(outContent.toString(),"Allocated slot number: 1.\n");
    }

    @Test
    public void leavePositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.leave(1);
        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nSlot number 1 is free.\n");
    }

    @Test
    public void leaveBeyondCapacityTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.leave(3);
        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nInvalid Input.Input beyond Parking lot capacity.\n");
    }

    @Test
    public void leaveVacantSlotTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.leave(2);
        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nParking Slot is already vacant.\n");
    }

    @Test(expectedExceptions = ParkingException.class)
    public void registrationNosofCarsWithColorExceptionTest() throws ParkingException {
        parkingService.registrationNosofCarsWithColor("white");
    }

    @Test
    public void registrationNosofCarsWithColorPositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.park("KA-01-AA-5454","white");
        parkingService.registrationNosofCarsWithColor("white");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nAllocated slot number: 2.\nKA-01-CC-1234, KA-01-AA-5454\n");
    }

    @Test
    public void registrationNosofCarsWithColorNotfoundTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.park("KA-01-AA-5454","white");
        parkingService.registrationNosofCarsWithColor("black");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nAllocated slot number: 2.\nNot found\n");
    }


    @Test(expectedExceptions = ParkingException.class)
    public void slotNosOfCarsWithColorExceptionTest() throws ParkingException {
        parkingService.slotNosOfCarsWithColor("white");
    }

    @Test
    public void slotNosOfCarsWithColorPositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.park("KA-01-AA-5454","white");
        parkingService.slotNosOfCarsWithColor("white");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nAllocated slot number: 2.\n1, 2\n");
    }

    @Test
    public void slotNosOfCarsWithColorNotfoundTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.park("KA-01-AA-5454","white");
        parkingService.slotNosOfCarsWithColor("black");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nAllocated slot number: 2.\nNot found\n");

    }

    @Test(expectedExceptions = ParkingException.class)
    public void slotNoBasedOnRegistrationNoExceptionTest() throws ParkingException {
        parkingService.slotNoBasedOnRegistrationNo("HA-01-AA-1234");
    }

    @Test
    public void slotNoBasedOnRegistrationNoPositiveTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.slotNoBasedOnRegistrationNo("KA-01-CC-1234");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\n1\n");
    }

    @Test
    public void slotNoBasedOnRegistrationNoNotfoundTest() throws ParkingException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        parkingService.createParkingLot(2);
        parkingService.park("KA-01-CC-1234","white");
        parkingService.slotNoBasedOnRegistrationNo("KA-01-CC-5555");

        Assert.assertEquals(outContent.toString(),"Created a parking lot with 2 slots.\nAllocated slot number: 1.\nNot found\n");

    }


}
