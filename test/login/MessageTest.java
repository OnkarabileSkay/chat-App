package login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest 
{
    
    // TEST 1: checkMessageID() - Tests if message ID is not more than 10 characters
    @Test
    public void testCheckMessageID_valid() 
    {
        // Create a message
        Message msg = new Message(1, "+27731234567", "Hello world");
        assertTrue(msg.checkMessageID());
    }
    
    @Test
    public void testCheckMessageID_withValidID() 
    {
        // Message ID is generated as a number, should always be 9-10 digits
        Message msg = new Message(1, "+27731234567", "Test message");
        assertTrue(msg.checkMessageID());
    }
    
    // TEST 2: checkRecipientCell() - Tests if cell number starts with + and is <= 13 characters
    @Test
    public void testCheckRecipientCell_valid() 
    {
        // Valid cell numbers
        assertEquals("correctFormate", Message.checkRecipientCell("+27731234567"));
        assertEquals("correctFormate", Message.checkRecipientCell("+123456789"));
        assertEquals("correctFormate", Message.checkRecipientCell("+27123456789"));
    }
    
    @Test
    public void testCheckRecipientCell_invalid() 
    {
        // Invalid cell numbers (no + sign)
        assertEquals("incorrectFormate", Message.checkRecipientCell("27731234567"));
        assertEquals("incorrectFormate", Message.checkRecipientCell("123456789"));
        
        // Invalid cell numbers (empty or too short)
        assertEquals("incorrectFormate", Message.checkRecipientCell(""));
        assertEquals("incorrectFormate", Message.checkRecipientCell("+"));
    }
    
    // TEST 3: createMessageHash() - Tests if hash is created correctly
    @Test
    public void testCreateMessageHash_format() 
    {
        Message msg = new Message(1, "+27731234567", "Hello world");
        String hash = msg.createMessageHash();
        
        // Check that hash is in uppercase
        assertEquals(hash, hash.toUpperCase());
        
        // Check that hash contains colon separators
        assertTrue(hash.contains(":"));
    }
    
    @Test
    public void testCreateMessageHash_withMultipleWords() 
    {
        Message msg = new Message(2, "+27731234567", "Hi Tommorow");
        String hash = msg.createMessageHash();
        
        // Hash should contain first two digits of ID, message count, first and last word
        assertTrue(hash.contains(":2:"));
        assertTrue(hash.contains("HITOMOROW") || hash.contains("HITOMMOROW"));
    }
    
    // TEST 4: returnTotalMessages() - Tests if total messages sent is tracked correctly
    @Test
    public void testReturnTotalMessages_initial() 
    {
        // Check that method returns a number (0 or more)
        int total = Message.returnTotalMessages();
        assertTrue(total >= 0);
    }
    
    // TEST 5: printMessages() - Tests if messages are stored and returned
    @Test
    public void testPrintMessages_withNoMessages() 
    {
        // Create new message but don't send it
        String result = Message.printMessages();
        assertNotNull(result);
    }
    
    // TEST 6: Message object creation - Tests if message is created properly
    @Test
    public void testMessageCreation() 
    {
        Message msg = new Message(1, "+27731234567", "Test message content");
        
        assertNotNull(msg);
        assertNotNull(msg.getMessageDetails());
        assertTrue(msg.getMessageDetails().contains("Test message content"));
    }
    
    // TEST 7: getMessageDetails() - Tests if all details are included
    @Test
    public void testGetMessageDetails_containsAllFields() 
    {
        Message msg = new Message(1, "+27731234567", "Hello friend");
        String details = msg.getMessageDetails();
        
        // Check that all required fields are in the output
        assertTrue(details.contains("Message ID:"));
        assertTrue(details.contains("Message Hash:"));
        assertTrue(details.contains("Recipient:"));
        assertTrue(details.contains("Message:"));
        assertTrue(details.contains("Hello friend"));
    }
    
    // TEST 8: checkRecipientCell with edge cases
    @Test
    public void testCheckRecipientCell_edgeCases() 
    {
        // Exactly 13 characters (maximum allowed)
        assertEquals("correctFormate", Message.checkRecipientCell("+123456789012"));
        
        // 14 characters (should fail)
        assertEquals("incorrectFormate", Message.checkRecipientCell("+1234567890123"));
        
        // Starts with + but very short (should pass if length <= 13)
        assertEquals("correctFormate", Message.checkRecipientCell("+1"));
    }
    
    // TEST 9: Message ID generation - Tests that IDs are unique and random
    @Test
    public void testMessageID_isGenerated() 
    {
        Message msg1 = new Message(1, "+27731234567", "First");
        Message msg2 = new Message(2, "+27731234567", "Second");
        
        // Both messages should have IDs
        assertNotNull(msg1.messageID);
        assertNotNull(msg2.messageID);
        
        // IDs should be different (random generation)
        assertNotEquals(msg1.messageID, msg2.messageID);
    }
    
    // TEST 10: Message count tracking - Tests if message count increments properly
    @Test
    public void testMessageCount_increments() 
    {
        Message msg1 = new Message(1, "+27731234567", "First");
        Message msg2 = new Message(2, "+27731234567", "Second");
        
        assertEquals(1, msg1.messageCounter);
        assertEquals(2, msg2.messageCounter);
    }
    
    // TEST 11: testCheckMessageID with different message IDs
    @Test
    public void testCheckMessageID_multipleMessages() 
    {
        Message msg1 = new Message(1, "+27731234567", "First");
        Message msg2 = new Message(2, "+27731234567", "Second");
        
        assertTrue(msg1.checkMessageID());
        assertTrue(msg2.checkMessageID());
    }
    
    // TEST 12: test createMessageHash with single word
    @Test
    public void testCreateMessageHash_singleWord() 
    {
        Message msg = new Message(3, "+27731234567", "Hello");
        String hash = msg.createMessageHash();
        
        // For single word, first and last word are the same
        assertTrue(hash.contains("HELLOHELLO") || hash.contains("HELLO"));
        assertTrue(hash.contains(":3:"));
    }
}