package chatapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 9 JUnit tests as required by POE
 */
public class LoginFeatureTest {
    
    private LoginFeature auth;
    
    @Before
    public void setUp() {
        auth = new LoginFeature();
    }
    
    // Valid username
    @Test
    public void testValidUsername() {
        boolean result = auth.checkUserName("kyl_1");
        assertTrue(result);
        
        String regResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(regResult.contains("Username successfully captured"));
    }
    
    //: Invalid username
    @Test
    public void testInvalidUsername() {
        boolean result = auth.checkUserName("kyle!!!!!!");
        assertFalse(result);
        
        String regResult = auth.registerUser("kyle!!!!!!", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(regResult.contains("Username is not correctly formatted"));
    }
    
    //  Valid password
    @Test
    public void testValidPassword() {
        boolean result = auth.checkPasswordComplexity("Ch&sec@ke99!");
        assertTrue(result);
        
        String regResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(regResult.contains("Password successfully captured"));
    }
    
    //  Invalid password
    @Test
    public void testInvalidPassword() {
        boolean result = auth.checkPasswordComplexity("password");
        assertFalse(result);
        
        String regResult = auth.registerUser("kyl_1", "password", "Kyle", "Smith", "+27838968976");
        assertTrue(regResult.contains("Password is not correctly formatted"));
    }
    
    //  Valid phone
    @Test
    public void testValidPhone() {
        boolean result = auth.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
        
        String regResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(regResult.contains("Cell phone number successfully added"));
    }
    
    // Invalid phone
    @Test
    public void testInvalidPhone() {
        assertFalse(auth.checkCellPhoneNumber("08966553"));
        assertFalse(auth.checkCellPhoneNumber("+2783896"));
        assertFalse(auth.checkCellPhoneNumber("+278389689761"));
        assertFalse(auth.checkCellPhoneNumber("+267838968976"));
        
        String regResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "08966553");
        assertTrue(regResult.contains("Cell phone number incorrectly formatted"));
    }
    
    // Login success
    @Test
    public void testLoginSuccess() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(auth.loginUser("kyl_1", "Ch&sec@ke99!"));
        
        String msg = auth.returnLoginStatus(true, "Kyle", "Smith");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", msg);
    }
    
    //  Login fail
    @Test
    public void testLoginFail() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertFalse(auth.loginUser("kyl_1", "wrongpass"));
        assertFalse(auth.loginUser("wronguser", "Ch&sec@ke99!"));
        
        String msg = auth.returnLoginStatus(false, "Kyle", "Smith");
        assertEquals("Username or password incorrect, please try again.", msg);
    }
    
    //  Stored data
    @Test
    public void testStoredData() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        
        assertEquals("kyl_1", auth.getStoredUser());
        assertEquals("Ch&sec@ke99!", auth.getStoredPass());
        assertEquals("Kyle", auth.getStoredFirst());
        assertEquals("Smith", auth.getStoredLast());
        assertEquals("+27838968976", auth.getStoredPhone());
    }
}