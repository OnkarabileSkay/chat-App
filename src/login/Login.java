package login;
import java.util.Scanner;
public class Login
{

	//DECLERING STATIC VARIABLES
	static String userName;
	static String password;
	static String cellNumber;
	static String firstName;
	static String lastName;

	 public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		registerUser(scanner);

		boolean loginSuccess;
		loginSuccess= false;

		while (!loginSuccess)
		{
			System.out.println("\n");
			System.out.println("WELCOME TO CHAT APP, PLEASE LOGIN");

			System.out.print("Enter username: ");
			String inputUserName = scanner.nextLine();

			System.out.print("Enter password: ");
			String inputPassword = scanner.nextLine();

			loginSuccess = loginUser(inputUserName, inputPassword);

			System.out.println(returnLoginStatus(loginSuccess));

			if (loginSuccess)
			{
				System.out.println("Welcome back " + firstName + "," + lastName + " it is great to see you again.");
                                // After login, start the messaging class
                                MessagingMethod(scanner);
			}
			else
			{

			}
		}
	}
             // runs the messaging app after login
        public static void MessagingMethod(Scanner scanner)
        {
           System.out.println("");
           System.out.println("Welcome to QuickChat");
           System.out.println(" ");
           // Ask the user how many message they will like to send
           System.out.print("How many messages would you like to send?: ");
           int maxMessages = scanner.nextInt();
           System.out.println(" ");
           System.out.print("You will send: " + maxMessages + " Messages Only. ");
           System.out.println(" ");
           int messageSent = 0;
           boolean loopControl = true;
        
           // This while loop will keep on runing until ke loopControl become false 
           while (loopControl == true)
        {
            System.out.println(" ");
            System.out.println("  QuickChat Menu  ");
            System.out.println("Option 1: Send Messages:");
            System.out.println("Option 2: Show recently sent messages:");
            System.out.println("Option 4: Stored Messages:");
            System.out.println("Option 0: Quit:");
            System.out.print("Choose an option: ");
             
            int Userchoice = scanner.nextInt();
       
            // Using if statements to display what the user choosed
            if (Userchoice == 1)
            {
                if (messageSent < maxMessages)
                {
                    sendMessage(scanner, messageSent + 1);
                    messageSent = messageSent + 1;
                    
                    // Check if all messages are done
                    if (messageSent == maxMessages)
                    {
                        System.out.println("\n");
                        System.out.println("You have completed all " + maxMessages + " messages!");
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println("You have already reached your limit of " + maxMessages + " messages.");
                }
            }
            else if (Userchoice == 2)
            {
                displayLastMessage();
            }
            else if (Userchoice == 0)
            {
                System.out.println("Thank you for using QuickChat. Goodbye!");
                loopControl = false; 
            }
            else if (Userchoice==4)
            {
                storedMessagesOption4(scanner);
            }
            else
            {
                System.out.println("Incorrect option. Please choose between 1, 2, and 3.");
            }
        }
    }
    
         // This sends one message
        public static void sendMessage(Scanner scanner, int messageNumber)
        {
            System.out.println("\n Sending Message Number " + messageNumber + " .");
            scanner.nextLine();
            // Get recipient cell number
            String recipient = "";
            boolean correctRecipientNumber = false;
        
            while (correctRecipientNumber == false)
            {
                System.out.print("Enter recipient's cell number: ");
                recipient  = scanner.nextLine();
            if (Message.checkRecipientCell(recipient).equals("correctFormate"))
             {
                System.out.println("Cell number successfully captured!");
                correctRecipientNumber = true;
             }
            else
            {
                System.out.println("Cell number is incorrectly formated or does not contain an international code. Please correct the number and try again,");
            }
        }
        
        // Get message text
        String messageText = "";
        boolean validMessage = false;
        
        while (validMessage == false)
        {
            System.out.print("Enter your message: ");
            messageText = scanner.nextLine();
            
            if (messageText.length() <= 250)
            {
                System.out.println("Message ready to send!");
                validMessage = true;
            }
            else
            {
                System.out.println("Message exceeds 250 characters by" +messageText.length() + " Please reduce the size");
                
            }
        }
        // Create the message object
        Message newMessage = new Message(messageNumber, recipient, messageText);
        
        // Show message preview
        System.out.println("\n Message Details View ");
        System.out.println(newMessage.getMessageDetails());
        System.out.println("\n");
        
        // Ask what to do with the message
        String result = newMessage.sendMessage();
        System.out.println(result);
    if (result.equals("Message sent successfully!")) 
    {
    System.out.println("\n Message Details View ");
    System.out.println(newMessage.getMessageDetails());
    System.out.println("");
   }
    }
	//REGISTERING THE USER
	public static void registerUser(Scanner scanner)
	{
		//METHOD REGISTERS THE USER
		while (true)
		{
			System.out.print("Enter username: ");
			userName = scanner.nextLine();

			if (checkUsername(userName))
			{
				System.out.println("Username successfully captured.");
				break;
			}
			else
			{
				System.out.println("Username is not correctly formatted; Please ensure that your username contains an underscore(_) and is no more than five Characters in length ");
			}
		}
		while (true)
		{
			System.out.print("Enter password: ");
			password = scanner.nextLine();

			if (checkPasswordComplexity(password)) {
				System.out.println("Password successfully captured.");
				break;
			} else {
				System.out.println("Password is not correctly formatted; Please ensure that the password contains at least eight Characters, a capital letter, a number, and a special character");
			}
		}
		while (true)
		{
			System.out.print("Enter cell phone number: ");
			cellNumber = scanner.nextLine();

			if (checkCellNumber(cellNumber)) {
				System.out.println("Cell number successfully added.");
				break;
			}
			else
			{
				System.out.println("Cell phone number incorently formatted or does not contain international code");
			}
		}
		// ASKS THE USER FOR HIS || HER FIRST AND LAST NAME
		System.out.print("Enter your first name: ");
		firstName = scanner.nextLine();

		System.out.print("Enter your last name: ");
		lastName = scanner.nextLine();
	}
        //METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED USERNAME
	public static boolean checkUsername(String storedUsername)
	{
		return storedUsername.length() <= 5 && storedUsername.contains("_");
	}
	//METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED PASSWORD
	public static boolean checkPasswordComplexity(String userPassword)
	{
		boolean hasUppercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (int i = 0; i < userPassword.length(); i++)
		{
			char c = userPassword.charAt(i);

			if (Character.isUpperCase(c)) hasUppercase = true;
			else if (Character.isDigit(c)) hasDigit = true;
			else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
		}

		return userPassword.length() >= 8 && hasUppercase && hasDigit && hasSpecial;
	}
	//METHOD THAT CHECKS IF THE USER ENTERED THE REQUIRED CELL PHONE NUMBER
	public static boolean checkCellNumber(String userCellNumber)
	{
		if (!userCellNumber.startsWith("+27"))
			return false;

		String onlyNumbers = userCellNumber.substring(3);

		if (onlyNumbers.length() != 9)
			return false;

		for (int i = 0; i < onlyNumbers.length(); i++)
		{
			if (!Character.isDigit(onlyNumbers.charAt(i)))
				return false;
		}

		return true;
	}
    /// @param storedUsername
    /// @param storedPassword
    /// @return  
        //METHOD THAT CHECKS IF THE REGISTRATION DETAILS MATCH THE LOGIN DETAILS
	public static boolean loginUser(String storedUsername, String storedPassword)
	{
		return storedUsername.equals(userName) && storedPassword.equals(password);
	}
	//METHOD THAT DISPLAYS THE FINAL RESULTS OF LOGIN

	public static String returnLoginStatus(boolean logdin)
	{
		if (logdin )
		{
			return "A successful login";
		}
		else
		{
			return "A failed login";
		}


	}
        
        public static void storedMessagesOption4(Scanner scanner)
        {
            boolean option4Choices = true;
    
            while (option4Choices)
        {
        System.out.println(" ");
        System.out.println("Option 4 features:");
        System.out.println("1. Display sender and recipient of all stored messages");
        System.out.println("2. Display the longest stored message");
        System.out.println("3. Search for a message ID");
        System.out.println("4. Search for all messages for a particular recipient");
        System.out.println("5. Delete a message using message hash");
        System.out.println("6. Display full report of all stored messages");
        System.out.println("0. Back to Main Menu");
        System.out.print("Choose an option: ");
        
        int option4Choice = scanner.nextInt();
        scanner.nextLine();
        
        if ( option4Choice == 1 )
         {
            displayAllMessages();
         }
        else 
            if (option4Choice == 2)
              {
                longestMessage();
              }
        else
            if (option4Choice == 3)
              {
                searchMessageID(scanner);
              }
        else 
            if (option4Choice==4)
            {
                searchUsingRecipientsNumber(scanner);
            }
        else
            if (option4Choice==5)
            {
                deleteAMessageUsingHash(scanner);
            }
        else
            if (option4Choice==6)
            {
                displayReportForMessages();
            }
        else 
            if (option4Choice == 0)
              {
                option4Choices = false;
              }
       
          }
       }
        //mETHOD THAT DISPLAYS THE LONGEST MESSAGE
        public static void longestMessage()
        {
            System.out.println(" ");
            String allMessages = Message.printMessages();
    
        if (allMessages.equals("No messages sent yet."))
        {
           System.out.println("No messages was sent.");
           return;
        }
           String[] individualMessages = allMessages.split("\n\n");
           String longestMessage = "";
           int longestLength = 0;
           //LOOP THAT CHECKS FOR THE HIGHEST MESSAGE IN LENGTH  
           for (int i = 0; i < individualMessages.length; i++)
          {
            //IT CHECKS ALL THE MESSAGES'S LENGTH IN THE ARRAY
            if (individualMessages[i].length() > longestLength)
             {
               longestLength = individualMessages[i].length();
               longestMessage = individualMessages[i];
             }
          }
    System.out.println("Longest message:" + longestLength + " characters");
    System.out.println(longestMessage);
    System.out.println("");
       }
    
    public static void displayAllMessages()
    {
    System.out.println("");
    System.out.println("Sender               :  Recipient");
    String allMessages = Message.printMessages();
    
    if (allMessages.equals("No messages sent yet."))
    {
        System.out.println("No messages to display.");
        return;
    }
    String[] individualMessages = allMessages.split("\n\n");
    for (int i = 0; i < individualMessages.length; i++)
    {
        String msg = individualMessages[i];
        String recipient = "";
        String sender = "You" + "( " + cellNumber + " )";
        //IT COMBINES THE MESSAGE INTO ONE ELEMENT IN AN ARRAY 
        String[] lines = msg.split("\n");
        for (int indext = 0; indext< lines.length; indext++)
        {
            if (lines[indext].startsWith("Recipient:"))
            {
                //SUB CUTS OUT THE FIRST 10 INDEX
                recipient = lines[indext].substring(10);
                break;
            }
        }
        System.out.println(sender + "  :  " + recipient);
    }
    System.out.println("Total: " + Message.returnTotalMessages() + " messages");
    System.out.println("");
}
    public static void searchMessageID(Scanner scanner)
    {
       System.out.println("\n");
       System.out.print("Enter Message ID to search for: ");
       String searchedMessageID = scanner.nextLine();
    
       String allMessages = Message.printMessages();
    
    if (allMessages.equals("No messages sent yet."))
    {
        System.out.println("No messages to search.");
        return;
    }
    String[] individualMessages = allMessages.split("\n\n");
    boolean found = false;
    for (int i = 0; i < individualMessages.length; i++)
    {
        String msg = individualMessages[i];
        
        // Look for Message ID in the message
        if (msg.contains("Message ID: " + searchedMessageID) || msg.contains(searchedMessageID))
        {
           //If it is found, it will do the following 
            String recipient = "";
            String messageText = "";
            String[] sentance = msg.split("\n");
            
            for (int loopIndex = 0; loopIndex < sentance.length; loopIndex++)
            {
                if (sentance[loopIndex].startsWith("Recipient:")) 
                {
                    recipient = sentance[i].substring(10);
                } 
                else 
                    if (sentance[loopIndex].startsWith("Message:")) 
                      {
                        messageText = sentance[loopIndex].substring(8);
                      }
            }
            System.out.println("Recipient: " + recipient);
            System.out.println("Message: " + messageText);
            found = true;
            break;
        }
    }
    if (!found)
    {
        System.out.println("Message ID '" + searchedMessageID + "' not found.");
    }
    System.out.println(" ");
   }
    //METHOD THAT SEARCHES THE RECEIVERS CELL NUMBER 
    public static void searchUsingRecipientsNumber(Scanner scanner)
    {
       System.out.println("\n");
       System.out.print("Enter recipient's cell number to search for: ");
       String searchRecipient = scanner.nextLine();
       String allMessages = Message.printMessages();
    if (allMessages.equals("No messages sent yet."))
    {
        System.out.println("No messages to search.");
        return;
    }
    String[] individualMessages = allMessages.split("\n\n");
    boolean found = false;
    int count = 0;
    System.out.println("\n");
    for (int i = 0; i < individualMessages.length; i++)
    {
        String msg = individualMessages[i];
        
        // Look for recipient in the message
        if (msg.contains("Recipient: " + searchRecipient) || msg.contains(searchRecipient))
        {
            // Extract message details
            String messageText = "";
            String messageID = "";
            String[] lines = msg.split("\n");
            
            for (int j = 0; j < lines.length; j++)
            {
                if (lines[j].startsWith("MessageI ID:"))
                {
                    messageID = lines[j].substring(11);
                }
                else if (lines[j].startsWith("Message:"))
                {
                    messageText = lines[j].substring(8);
                }
            }
            
            System.out.println("Message ID: " + messageID);
            System.out.println("Message: " + messageText);
            System.out.println(" ");
            found = true;
            count++;
        }
    }
    if (!found)
    {
        System.out.println("No messages found for recipient: " + searchRecipient);
        System.out.println(" ");
    }
    else
    {
        System.out.println("Total messages found for " + searchRecipient + ": " + count);
        System.out.println(" ");
    }
    System.out.println(" ");
    }
    public static void deleteAMessageUsingHash(Scanner scanner)
    {
       String allMessages = Message.printMessages();
    
    if (allMessages.equals("No messages sent yet."))
  {
        System.out.println("No messages to delete.");
        return;
    }
    String[] individualMessages = allMessages.split("\n\n");
    
    for (int i = 0; i < individualMessages.length; i++)
    {
        String msg = individualMessages[i];
        String[] lines = msg.split("\n");
        for (int j = 0; j < lines.length; j++)
        {
            if (lines[j].startsWith("Message Hash:"))
            {
                String hash = lines[j].substring(13);
                System.out.println((i + 1) + ". " + hash);
                break;
            }
        }
    }
    System.out.println("\n");
    System.out.println("Enter the Message Hash you want to delete: ");
    String deleteMessageID = scanner.nextLine();
    
    boolean found = false;
    int deleteIndexPossition = -1;
    for (int i = 0; i < individualMessages.length; i++)
    {
        String msg = individualMessages[i];
        if (msg.contains("Message Hash: " + deleteMessageID) || msg.contains(deleteMessageID))
        {
            found = true;
            deleteIndexPossition = i;
            break;
        }
    }
    if (!found)
    {
        System.out.println("Message with hash '" + deleteMessageID + "' not found.");
        return;
    }
}
public static void displayReportForMessages()
{
    String allMessages = Message.printMessages();
    
    if (allMessages.equals("No messages sent yet."))
    {
        System.out.println("No messages to display.");
        return;
    }
    String[] individualMessages = allMessages.split("\n\n");
    System.out.println("Total messages stored: " + individualMessages.length);
    System.out.println("\n");
    for (int i = 0; i < individualMessages.length; i++)
    {
        System.out.println(" MESSAGE " + (i + 1) + "  :");
        System.out.println(individualMessages[i]);
        System.out.println();
    }
}
public static void displayLastMessage()
{
    System.out.println("\n");
    
    String recent = Message.getLastMessage();
    
    if (recent.equals("No messages have been created yet."))
    {
        System.out.println(recent);
    }
    else
    {
        System.out.println(recent);
    }
}
}