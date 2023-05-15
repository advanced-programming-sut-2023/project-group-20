import org.example.controller.SignupController;
import org.example.model.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignupControllerTest extends SignupController {
    @Test
    public void test(){
        randomSloganTest();
    }
    @Test
    public void randomSloganTest(){
        Assertions.assertNotNull(DataBase.getSlogans().contains(makeRandomSlogan()));
    }
}
