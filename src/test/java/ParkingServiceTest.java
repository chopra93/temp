import exception.ParkingException;
import mockit.NonStrictExpectations;
import mockit.Tested;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.impl.ParkingServiceImpl;

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

}
