/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.util.Random;

import java.util.Random;

public class Message {
    
    // Decleration of Variables
    public String messageID;
    public int messageCounter;
    public String recipient;
    public String textMessage;
    public String signHash;
    
    static int sentMessage = 0;
    static String[] savedMessages = new String[50];
    static int numberOfMessages = 0;
    
    static String lastMessage = "";
    
    // Method that creats the message id
    public Message(int count, String receiver, String text) {
        // Generate random messageID which is 10 digits
        Random randomNumberCreation = new Random();
        int randomNum = randomNumberCreation.nextInt(999999999);
        messageID = "" + randomNum;
        
        messageCounter = count;
        recipient = receiver;
        textMessage = text;
        
        // Creating the message signHash
        String firstTwo = messageID.substring(0, 2);
        String[] words = textMessage.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        signHash = firstTwo + ":" + messageCounter + ":" + firstWord + lastWord;
        signHash = signHash.toUpperCase();
        lastMessage = getMessageDetails();
    }
    
    // This is method it is used to check the message id's length
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
    
    // This method checks if the users recipient cell number meets the requirment
    public static String checkRecipientCell( String cellNumber ) {
        if (cellNumber .startsWith("+") && cellNumber .length() <= 13) {
            return "correctFormate";
        } else {
            return "incorrectFormate";
        }
    }
    
    // // Method used to hold the hash sign 
    public String createMessageHash() {
        return signHash;
    }
    
    // Method that displays the users choice to choose from
    public String sendMessage() {
        java.util.Scanner input = new java.util.Scanner(System.in);
        
        System.out.println("\nWhat do you want to do with this message?");
        System.out.println("Enter 1 to SEND it");
        System.out.println("Enter 2 to STORE it");
        System.out.println("Enter 0 to DELETE it");
        System.out.print("Your choice: ");
        
        int choice = input.nextInt();
        
        if (choice == 1) {
            sentMessage = sentMessage + 1;
            savedMessages[numberOfMessages] = getMessageDetails();
            numberOfMessages = numberOfMessages + 1;
            return "Message sent successfully!";
        } else if (choice == 2) {
            storeMessage();
            return "Message stored successfully!";
        } else if (choice == 0) {
            return "Message disregarded and deleted!";
        } else {
            return "Not available option, please choose between (1 , 2, and 0)";
        }
    }
    
    // This method is used to svae all messages
    public static String printMessages() {
        if (numberOfMessages == 0) {
            return "No messages sent yet.";
        }
        
        String allMessages = "\n";
        for (int i = 0; i < numberOfMessages; i++) {
            allMessages = allMessages + savedMessages[i] + "\n";
            allMessages = allMessages + "\n";
        }
        return allMessages;
    }
    
    // Method that counts all number of messages
    public static int returnTotalMessages() {
        return sentMessage;
    }
    
    // Mathod that stores all the messages
    public void storeMessage() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
            writer.write("{\n");
            writer.write("  \"Message ID\": \"" + messageID + "\",\n");
            writer.write("  \"Message Hash\": \"" + signHash + "\",\n");
            writer.write("  \"Recipient\": \"" + recipient + "\",\n");
            writer.write("  \"Message\": \"" + textMessage + "\"\n");
            writer.write("},\n");
            writer.close();
            System.out.println("Message stored in JSON file!");
        } catch (Exception error) {
            System.out.println("Could not save message.");
        }
    }
    
    // helps the method messageID(), reciever cellphone number, and the hash sign
    public String getMessageDetails() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + signHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + textMessage;
    }
    
    // 
    public static boolean cellNumber2check(String cellNumber) 
    {
        if (cellNumber.startsWith("+") && cellNumber.length() <= 10) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }
      // total of message calling
    public int getMessageCount() 
    {
        return messageCounter;
    }

     // message id calling
    public String getMessageID() 
    {
       return messageID;
    }
    public static String getLastMessage()
{
    if (lastMessage.equals(""))
    {
        return "No messages have been created yet.";
    }
    return lastMessage;
}
    // For testing - sends message without asking user input
public String sendMessageForTest() {
    sentMessage = sentMessage + 1;
    savedMessages[numberOfMessages] = getMessageDetails();
    numberOfMessages = numberOfMessages + 1;
    lastMessage = getMessageDetails();
    return "Message sent successfully!";
}

// For testing - stores message without asking the user to input
    // For testing - stores message without asking user input (JSON format)
    public void storeMessageForTest() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
            writer.write("{\n");
            writer.write("  \"Message ID\": \"" + messageID + "\",\n");
            writer.write("  \"Message Hash\": \"" + signHash + "\",\n");
            writer.write("  \"Recipient\": \"" + recipient + "\",\n");
            writer.write("  \"Message\": \"" + textMessage + "\"\n");
            writer.write("},\n");
            writer.close();
        } catch (Exception error) {
            // Silent fail for testing
        }
    }
}
