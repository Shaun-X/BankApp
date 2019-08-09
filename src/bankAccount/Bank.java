package bankAccount;

import java.util.Scanner;

public class Bank {

	public static void main(String[] args) {
//		String action = "withdraw";
//		double amount = 20.0;
//		int accNum = 123;
//		String sql2 = "INSERT INTO Transaction (action, amount, AccountId) VALUES ("+ "\""  + action+"\", "+ amount+" , " + accNum + ");";
//		System.out.println(sql2);
		System.out.println();
		System.out.println("  /$$$$$$  /$$                                    /$$              /$$$$$$$                      /$$       /$$                            /$$$$$$ ");                    
		System.out.println(" /$$__  $$| $$                                   | $/             | $$__  $$                    | $$      |__/                           /$$__  $$                    ");
		System.out.println("| $$  \\__/| $$$$$$$   /$$$$$$  /$$   /$$ /$$$$$$$|_//$$$$$$$      | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$ /$$ /$$$$$$$   /$$$$$$       | $$  \\ $$  /$$$$$$   /$$$$$$ ");
		System.out.println("|  $$$$$$ | $$__  $$ |____  $$| $$  | $$| $$__  $$ /$$_____/      | $$$$$$$  |____  $$| $$__  $$| $$  /$$/| $$| $$__  $$ /$$__  $$      | $$$$$$$$ /$$__  $$ /$$__  $$");
		System.out.println(" \\____  $$| $$  \\ $$  /$$$$$$$| $$  | $$| $$  \\ $$|  $$$$$$       | $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ | $$| $$  \\ $$| $$  \\ $$      | $$__  $$| $$  \\ $$| $$  \\ $$");
		System.out.println(" /$$  \\ $$| $$  | $$ /$$__  $$| $$  | $$| $$  | $$ \\____  $$      | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ | $$| $$  | $$| $$  | $$      | $$  | $$| $$  | $$| $$  | $$");
		System.out.println("|  $$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$/| $$  | $$ /$$$$$$$/      | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$| $$| $$  | $$|  $$$$$$$      | $$  | $$| $$$$$$$/| $$$$$$$/");
		System.out.println(" \\______/ |__/  |__/ \\_______/ \\______/ |__/  |__/|_______/       |_______/  \\_______/|__/  |__/|__/  \\__/|__/|__/  |__/ \\____  $$      |__/  |__/| $$____/ | $$____/ ");
		System.out.println("                                                                                                                         /$$  \\ $$                | $$      | $$      ");
		System.out.println("                                                                                                                        |  $$$$$$/                | $$      | $$      ");
		System.out.println("                                                                                                                         \\______/                 |__/      |__/      ");
		System.out.println();

		Scanner scnr = new Scanner(System.in);
		int accountId = 0;
		String firstName = "";
		String lastName = "";
		int age = 0;
		double balance = 0.0;
		System.out.println("Welcome to Shaun's Awesome Banking app");
		System.out.println("Are you a new user? Y/N");
		char n = scnr.nextLine().charAt(0);
		if(n == 'Y' || n =='y') {
			System.out.println("What is your first name?");
			firstName = scnr.next();
			System.out.println("What is your last name?");
			lastName = scnr.next();
			System.out.println("How old are you?");
			age = scnr.nextInt();
			
			accountId = DockerContainer.createUser(firstName,lastName,age, 50);
			System.out.println("Your account Id is: " + accountId);
			System.out.println("Please remember your Id.");
		}
		else if (n == 'N' || n == 'n') {
			System.out.println("What is your account Id?");
			accountId = scnr.nextInt();			
		}
		else{System.out.println("Please enter a Y or N.");}
		BankAccount customer = DockerContainer.lookUpUser(accountId);
		int choice = 0;
		if(customer.getAcc_num() != 0) {
		System.out.println();
		System.out.println("Hello " + customer.getFirstname()+" "+customer.getLastname()
				+", how may I assist you today?");
		DO:
		do{
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("1.Account Balance | 2.Deposit Money | 3.Withdraw Money | 4. Delete Account | 5. Exit ");
		try{
		choice = scnr.nextInt();	
		switch(choice){
			case 1:
				System.out.println("Your account balance is: " + customer.getBalance());
				break;
			case 2:
				System.out.println("How much would you like to deposit?");
				double amount = scnr.nextDouble();
				customer.deposit(amount);				
				break;
			case 3:
				System.out.println("How much would you like to withdraw?");
				double amount2 = scnr.nextDouble();
				customer.withdraw(amount2);
				DockerContainer.updateUserBalance(customer, "withdraw",amount2);
				break;
			case 4:
				System.out.println("Are you sure you want to delete your account? Y to confirm");
				char i = scnr.next().charAt(0);
				if(i == 'Y' || i == 'y') {
					DockerContainer.deleteUser(customer.getAcc_num());
					System.out.println("So sad to see you go.");
					break DO;
				}else {
					System.out.println("Welcome back.");
				}
				break;
				
			case 5:
				System.out.println("See you later.");
				break;
			default:
				System.out.println("Unrecognized choice.");
		}
		}catch (Exception e){
			System.out.println("Please enter a vaild opition.");
		}
		}while(choice != 5);
		}	
		else {
			System.out.println("The Account Id does not exist.");
			System.out.println("Please try again later.");
	}
	}

}