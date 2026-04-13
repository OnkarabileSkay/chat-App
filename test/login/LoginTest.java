package login;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest 
{

    // USERNAME TEST
    @Test
    public void testCheckUsername_valid() 
    {
        assertTrue(Login.checkUsername("user_"));
    }

    

    // PASSWORD TEST
    @Test
    public void testCheckPasswordComplexity_valid() 
    {
        assertTrue(Login.checkPasswordComplexity("Pass123@"));
    }

    

    // CELL PHONE NUMBER TEST
    @Test
    public void testCheckCellNumber_valid() 
    {
        assertTrue(Login.checkCellNumber("+27821234567"));
    }

    

    // LOGIN TEST
    @Test
    public void testLoginUser_success() 
    {
        // Set stored values first
        Login.userName = "user_";
        Login.password = "Pass123@";

        assertTrue(Login.loginUser("user_", "Pass123@"));
    }

 

    //LOGIN STATUS TEST
    @Test
    public void testReturnLoginStatus_success() 
    {
        String result = Login.returnLoginStatus(true);
        assertEquals("A successful login", result);
    }

    
}
