public class Processing {
    public static boolean transferMoney(Account account1, Account account2, int value){
        if(account1.getMoney() >= value && account1.getMoney() > 0 && value > 0) {
            account1.setMoney(account1.getMoney() - value);
            account2.setMoney(account2.getMoney() + value);
            return true;
        }else{
            return false;
        }
    }
    public static void depositMoney(Account account, int value){
        account.setMoney(account.getMoney() + value);
    }
    public static void withdrawMoney(Account account, int value){
        if(account.getMoney() >= value && account.getMoney() > 0 && value > 0) {
            account.setMoney(account.getMoney() - value);
        }else {
            System.out.println("Insufficient balance");
        }
    }
}
