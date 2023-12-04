public class Menu {

    public static void displayInital(){
        System.out.println("1)Login\t\t2)Register");
    }

    public static void displaySplashMenu(Account account){
        System.out.println("1) Bank transfer\t\t2) Deposit Check\t\t3) Withdraw\t\t0) Quit\t\t9) Disable/Enable account\tAccount is currently blocked: " + account.isBlocked());
    }
}
