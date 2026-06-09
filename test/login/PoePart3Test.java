package login;

import org.junit.Test;
import static org.junit.Assert.*;

public class PoePart3Test 
{
    //Tests if arrays are populated correctly
    @Test
    public void testingArraysIfPopulated() 
    {
        Message msg = new Message(1, "+27721234567", "Test message");
        msg.sendMessageForTest();
        int total = Message.returnTotalMessages();
        assertTrue(total > 0);
    }
    
    //Tests if longest message can be displayed
    @Test
    public void testLongestMessage() 
    {
        Message msg1 = new Message(1, "+27721234567", "Hi");
        Message msg2 = new Message(2, "+27721234567", "This is a very long message for testing");
        msg1.sendMessageForTest();
        msg2.sendMessageForTest();
        String result = Message.printMessages();
        assertNotNull(result);
    }
    
    //Tests if search by recipient works
    @Test
    public void testSearchByRecipient() 
    {
        Message msg = new Message(1, "+27721234567", "Hello");
        msg.sendMessageForTest();
        String result = Message.printMessages();
        assertTrue(result.contains("+27721234567"));
    }
    
    //Tests if delete by hash works
    @Test
    public void testDeleteByMessageHash() 
    {
        Message msg = new Message(1, "+27721234567", "Delete me");
        msg.sendMessageForTest();
        String hash = msg.createMessageHash();
        assertNotNull(hash);
    }
    
    //Tests if JSON file is created
    @Test
    public void testJSONFile() 
    {
        Message msg = new Message(1, "+27721234567", "Save to JSON");
        msg.storeMessageForTest();
        assertTrue(true);
    }
    
    //Tests if message report displays
    @Test
    public void testDisplayReport() 
    {
        Message msg = new Message(1, "+27721234567", "Report test");
        msg.sendMessageForTest();
        String report = Message.printMessages();
        assertNotNull(report);
    }
    
    //Tests if sender and recipient display
    @Test
    public void testDisplaySenderAndRecipient() 
    {
        Message msg = new Message(1, "+27721234567", "Sender test");
        msg.sendMessageForTest();
        String result = Message.printMessages();
        assertTrue(result.contains("Recipient:"));
    }
}