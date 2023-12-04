import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account oldAccount = new Account("default", "default", 0, false);
        boolean quit = false;
        boolean authentication = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        if (FileManager.createDatabase()) {

        } else {
            accounts = FileManager.readDatabase();
        }
        while (!quit) {
            while (!authentication) {
                Menu.displayInital();
                switch (scanner.next()) {
                    case "1":
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Insert your username:");
                        String usernameCheck = scanner2.next();
                        for (int i = 0; i < accounts.size(); i++) {
                            String[] login = accounts.get(i).toString().trim().split("/");
                            if (usernameCheck.equals(login[0].trim())) {
                                System.out.println("Enter your password:");
                                String passwordCheck = scanner2.next();
                                boolean auth = (passwordCheck.equals(login[1]));
                                if (auth) {
                                    oldAccount = new Account(login[0], login[1], Integer.parseInt(login[2]), (login[3] == "true") ? true : false);
                                    FileManager.writeEntryLog(oldAccount.getUsername());
                                }
                                System.out.println((auth) ? "Success, logged in. Welcome back, " + login[0] :
                                        "Wrong password.");
                                authentication = auth;

                            }
                        }
                        break;
                    case "2":
                        Scanner scanner3 = new Scanner(System.in);
                        System.out.println("Insert your new username and after Enter, your password:");
                        String username = scanner.next();
                        for (int i = 0; i < accounts.size(); i++) {
                            String[] login = accounts.get(i).toString().split("/");
                            if (username.equals(login[0])) {
                                System.out.println("Username is taken. Try a more unique one.");
                            } else {
                                System.out.println("Password:");
                                String password = scanner3.next();
                                Account newAccount = new Account(username, password);
                                accounts.add(newAccount);
                                FileManager.writeDatabase(accounts.toString().trim());
                                System.out.println("Success, please log in");
                                break;
                            }
                        }
                    default:
                        break;

                }
            }
            System.out.println("Hello, " + oldAccount.getUsername() + ". You have " + oldAccount.getMoney() + "$");
            Menu.displaySplashMenu(oldAccount);
            scanner.reset();
            switch (scanner.next()) {
                case "1":
                    Scanner scannerTransfer = new Scanner(System.in);
                    System.out.println("What's the username for the account you want to transfer funds to?");
                    String accountName = scannerTransfer.next();
                    for (int i = 0; i < accounts.size(); i++) {
                        if(accounts.get(i).getUsername().equals(accountName)){
                        System.out.println("What amount do you wish to transfer?");
                        int value = scannerTransfer.nextInt();
                        if (!oldAccount.isBlocked()) {
                            System.out.println((Processing.transferMoney(oldAccount, accounts.get(i), value)) ? "Success" : "Insufficient balance.");
                            FileManager.writeLog(oldAccount.getUsername(), value, "transferred", accountName);
                        } else {
                            ErrorManager.writeError(oldAccount.getUsername(), "Tried transfer of", value);
                        }
                            break;
                        }}break;

                case "2":
                    System.out.println("What amount to deposit?");
                    Scanner scannerDeposit = new Scanner(System.in);
                    int value = scannerDeposit.nextInt();
                    if (!oldAccount.isBlocked()) {
                        Processing.depositMoney(oldAccount, value);
                        FileManager.writeLog(oldAccount.getUsername(), value, "deposited", oldAccount.getUsername());
                    } else {
                        ErrorManager.writeError(oldAccount.getUsername(), "Tried a deposit of", value);
                    }
                    break;
                case "3":
                    System.out.println("What amount to withdraw?");
                    Scanner scannerWithdraw = new Scanner(System.in);
                    int value1 = scannerWithdraw.nextInt();
                    if (oldAccount.isBlocked()) {
                        Processing.withdrawMoney(oldAccount, value1);
                        FileManager.writeLog(oldAccount.getUsername(), value1, "withdrew", oldAccount.getUsername());
                    } else {
                        ErrorManager.writeError(oldAccount.getUsername(), "Tried a withdraw of", value1);
                    }
                    break;
                case "9":
                    oldAccount.setBlocked(!oldAccount.isBlocked());
                    break;
                case "0":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            for (int i = 0; i < accounts.size(); i++) {
                String[] login = accounts.get(i).toString().split("/");
                if (oldAccount.getUsername().equals(login[0])) {
                    accounts.set(i, oldAccount);
                }
            }
            FileManager.writeDatabase(accounts.toString());
        }
    }
}


