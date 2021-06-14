package AdventureDemo;

public class enemy {
    private String name;
    private int ATK;
    private int HP;
    private int Special;
    
    public enemy() {
        name = "null";
        ATK = 0;
        HP = 1;
    }
    
    public enemy(String n, int a, int h) {
        name = n;
        setATK(a);
        setHP(h);
    }
    
    //setters
    
    public void setName(String n) {
        name = n;
    }
    
    public void setATK(int a) {
        if (a >= 0) {
            ATK = a;
        }
        else {
            System.err.println("Invalid ATK value");
            ATK = 0;
        }
    }
    
    public void setHP(int h) {
        if (h > 0) {
            HP = h;
        }
        else {
            System.err.println("Invalid HP value");
            HP = 1;
        }
    }
    
    //getters
    
    public String getName() {
        return name;
    }
    
    public int getATK() {
        return ATK;
    }
    
    public int getHP() {
        return HP;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + "\nATK: " + ATK + "\nHP: " + HP;
    }
}
