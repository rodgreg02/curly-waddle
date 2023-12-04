public class Account {
    String username;
    String password;
    int money;
    boolean blocked;
    public Account(String username, String password, int money, boolean blocked) {
        this.username = username;
        this.money = money;
        this.password = password;
        this.blocked = blocked;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return this.username + "/" + this.password + "/" + this.money + "/" + this.blocked;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public int getMoney() {
        return money;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
