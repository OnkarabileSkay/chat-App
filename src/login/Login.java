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
			}
			else
			{

			}
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

    

    
}