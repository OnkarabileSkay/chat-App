package login;

public class Message {
    // Static variables for tracking data across all instances
    private static int messageCount = 0;
    // Using a fixed array of 100 instead of an ArrayList
    private static String[] storedMessages = new String[100]; 

    // Instance variables
    private String messageId;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor to initialize the object
    public Message(String recipient, String messageText) {
        this.messageId = generateMessageId();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Check if ID starts with the correct prefix
    public boolean checkMessageId() {
        if (messageId.startsWith("MSG")) {
            return true;
        } else {
            return false;
        }
    }

    // Check if the cell number is valid for South Africa (+27)
    public boolean checkRecipientCell() {
        // Must start with +27
        if (!recipient.startsWith("+27")) {
            return false;
        }

        // Get the numbers after the +27
        String numbersOnly = recipient.substring(3);

        // Check length (must be 9 digits after +27)
        if (numbersOnly.length() != 9) {
            return false;
        }

        // Check if every character is actually a number (Beginner way)
        for (int i = 0; i < numbersOnly.length(); i++) {
            char c = numbersOnly.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Create a unique hash for the message
    public String createMessageHash() {
        String[] words = messageText.split(" ");
        String firstWord = "";
        String lastWord = "";

        // Simple if-else to find the first and last words
        if (words.length > 0) {
            firstWord = words[0];
        }
        
        if (words.length > 1) {
            lastWord = words[words.length - 1];
        }

        // Combine parts into a single string
        return messageId + ":" + messageCount + ":" +
                firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    // Method to "send" the message and save it
    public String sendMessage() {
        if (checkRecipientCell() == false) {
            return "Invalid recipient number.";
        }

        // Add to our static array and increment the count
        if (messageCount < 100) {
            storedMessages[messageCount] = printMessage();
            messageCount++;
        }

        return printMessage();
    }

    // Return a formatted string of the message details
    public String printMessage() {
        String output = "ID: " + messageId +
                      "\nTo: " + recipient +
                      "\nMessage: " + messageText +
                      "\nHash: " + messageHash;
        return output;
    }

    // Static method to get the total count
    public static int returnTotalMessages() {
        return messageCount;
    }

    // Static method to get the history of messages
    public static String[] getStoredMessages() {
        return storedMessages;
    }

    // Private helper to create a random ID
    private String generateMessageId() {
        // Generating a random number between 0 and 9999
        int randomNumber = (int)(Math.random() * 10000);
        return "MSG" + randomNumber;
    }
}