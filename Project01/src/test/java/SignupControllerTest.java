import org.example.controller.SignupController;
import org.example.model.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignupControllerTest extends SignupController {
    @Test
    public void test(){
        randomPasswordTest();
        randomSloganTest();
    }
    @Test
    public void randomPasswordTest(){
        Assertions.assertEquals(checkPassword(SignupController.makeRandomPassword()),"accepted");
    }
    @Test
    public void randomSloganTest(){
        Assertions.assertNotNull(DataBase.getSlogans().contains(makeRandomSlogan()));
    }
}
