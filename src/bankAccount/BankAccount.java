package bankAccount;

public class BankAccount {
	private int acc_num = 0;
	private double balance = 0;
	private String firstname = "";
	private String lastname = "";
	private String email = "";
	private int age = 0;
	private String phoneNum = "";
	private final double dailyLimit = 10.0;
	private final double depositLimit = 50.0;
		
	public BankAccount(){
//		this.acc_num = 123456789;
//		this.balance = 50.0;
//		this.email = "hanx9607@gmail.com";
//		this.phoneNum = "6086224132";
	}
	public BankAccount(int acc_num, String firstname, String lastanme, double balance, int age){
		this.acc_num = acc_num;
		this.firstname = firstname;
		this.lastname = lastname;
		this.balance = balance;
		this.age = age;
	}

	public void deposit(double amount){
		if(amount <= depositLimit) {
			this.balance += amount;
		}
		else 
			System.out.println("Deposit amount exceeds the deposit limit. Only $50 allowed each transaction.");
	}
	public void withdraw(double amount){
		if(this.balance >= amount && amount <= dailyLimit){
			this.balance -= amount;
		}
		else if(this.balance < amount){
			System.out.println("Withdraw amount exceeds your balance.");
		}
		else if(amount > dailyLimit){
			System.out.println("Withdraw amount exceeds the daily limit.");
		}
	}
	public String toString(){
		return "Customer's name is: " + this.firstname + "\n" + 
				"Account number is: " + this.acc_num + "\n"+
				"Balance is: " + this.balance +"\n" + 
				"Email is: " + this.email +"\n"+ "Phone number is: " + 
				this.phoneNum;
	}
	public int getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getDailyLimit() {
		return dailyLimit;
	}
	public double getDepositLimit() {
		return depositLimit;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public double getBalance() {
		return this.balance;
	}
	
}
