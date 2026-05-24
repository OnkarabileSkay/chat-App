package login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest 
{
    // TEST 1: checkMessageID() - Tests if message ID is not more than 10 characters
    @Test
    public void testCheckMessageID() 
    {
        Message msg = new Message(1, "+27731234567", "Hello world");
        assertTrue(msg.checkMessageID());
    }
    
    // TEST 2: checkRecipientCell() - Tests if cell number starts with +
    @Test
    public void testCheckRecipientCell() 
    {
        assertEquals("correctFormate", Message.checkRecipientCell("+27731234567"));
    }
    
    // TEST 3: createMessageHash() - Tests if hash is created
    @Test
    public void testCreateMessageHash() 
    {
        Message msg = new Message(1, "+27731234567", "Hello world");
        String hash = msg.createMessageHash();
        assertNotNull(hash);
    }
    
    // TEST 4: returnTotalMessages() - Tests if total messages returned
    @Test
    public void testReturnTotalMessages() 
    {
        int total = Message.returnTotalMessages();
        assertTrue(total >= 0);
    }
    
    // TEST 5: printMessages() - Tests if messages can be printed
    @Test
    public void testPrintMessages() 
    {
        String result = Message.printMessages();
        assertNotNull(result);
    }
    
    // TEST 6: getMessageDetails() - Tests if message details are returned
    @Test
    public void testGetMessageDetails() 
    {
        Message msg = new Message(1, "+27731234567", "Hello friend");
        String details = msg.getMessageDetails();
        assertNotNull(details);
    }
}