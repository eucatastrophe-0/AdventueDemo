package AdventureDemo;

import java.util.Scanner;

//4-1-21: added variables, setters, & getters for MaxHP, MaxMP

public class hero {
    private String name;
    private int ATK;
    private int HP;
    private int MP;
    private int MaxHP;
    private int MaxMP;
    private int gold;
    private int Special;
    
    public hero() {
        name = "null";
        ATK = 0;
        HP = 1;
    }
    
    public hero(String n, int a, int h) {
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
        if (h > 0 && h <= MaxHP) {
            HP = h;
        }
        else {
            System.err.println("Invalid HP value");
        }
    }

    public void setMP(int m) {
        if (m > 0 && m <= MaxMP) {
            MP = m;
        }
        else {
            System.err.println("Invalid MP value");
        }
    }

    public void setMaxHP(int MaxHP) {
        this.MaxHP = MaxHP;
    }

    public void setMaxMP(int MaxMP) {
        this.MaxMP = MaxMP;
    }
    
    public void setGold(int g) {
        if (g > 0) {
            gold = g;
        }
        else {
            System.err.println("Invalid gold value");
            gold = 0;
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

    public int getMP() {
        return MP;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public int getMaxMP() {
        return MaxMP;
    }
    
    public int getGold() {
        return gold;
    }
    
    //othe methods
    
    @Override
    public String toString() {
        return "Name: " + name + "\nATK: " + ATK + "\nHP: " + HP;
    }
    
    public void modHP(int i) {
        HP += i;
    }
    
    public void modMP(int i) {
        MP += i;
    }
    
    public void modATK(int i) {
        ATK += i;
    }
    
    public void modGold(int i) {
        gold += i;
    }
    
    public void restore() {
        restoreHP();
        restoreMP();
    }
    public void restoreHP() {
        HP = MaxHP;
    }
    public void restoreMP() {
        MP = MaxMP;
    }
    
    public void combat(enemy e) {
        //call variables related to hero & enemy stats, combat & encounter flow
        Scanner input = new Scanner(System.in);
        
        double heroStrike, enemyStrike;
        int fightChoice;
        
        int heroHP = getHP();
        int heroAtk = getATK();
        int eAtk, eHP;
        String eName;
        
        //initialize some variables related to combat & encounter flow
        
        boolean enemyAlive = true;
        boolean fightChoiceMade = false;
        boolean fightOver = false;
        
        eName = e.getName();
        eAtk = e.getATK();
        eHP = e.getHP();
        
        //---------------------------------------------------------------------
        //Encounter Loop starts here!!!
        //---------------------------------------------------------------------
        
        System.out.println("You encounter a " + eName +"!");

        //nested do/while loops
        //first layer is Encounter Loop --> fightOver
        //second layer is Action Loop --> fightChoiceMade
        //encounter sequence includes steps:
        //      Action Loop
        //      Enemy Alive?
        //      Enemy Response
        //      Hero Alive?
        
        //Encounter Loop
        do {
            //add action cases as needed!
            
            System.out.println("\nWhat do you do?             Hero HP: " + heroHP);
            System.out.println("    (1) Attack");
            System.out.println("    (2) Run");
            
            //Action Loop
            do {
                System.out.print("-->  ");
                fightChoice = input.nextInt();
                
                //Strike values are recalculated every loop
                //range: 0 to 0.999...
                
                heroStrike = Math.random();
                enemyStrike = Math.random();
                
                //switch cases used for encounter choices, default checks for invalid
                
                switch (fightChoice) {
                    case 1:
                        //note: this used to use heroWeapon, changed to make more general
                        System.out.println("\n  You attack!");
                        if (heroStrike > 0.25)
                        {
                            System.out.println("    You hit!");
                            eHP -= ((int) ((Math.random() * 6) + 1) + heroAtk);
                        }
                        else
                        {
                            System.out.println("    But you miss...");
                        }
                        fightChoiceMade = true;
                        break;
                    case 2:
                        System.out.println("\n  You attempt to flee!");
                        if (heroStrike > enemyStrike)
                        {
                            System.out.println("    You escape.");
                            fightOver = true;
                        }
                        else
                        {
                            System.out.println("    ...but you cannot.");
                        }
                        fightChoiceMade = true;
                        break;
                    case 3:
                        
                        //add extra cases here!
                        
                    default:
                        System.out.println("\nChoose again...");
                        break;
                }

            } while (!fightChoiceMade);
            
            //Enemy Alive?
            if (eHP <= 0)
            {
            enemyAlive = false;
            }
            
            if (enemyAlive && !fightOver)
            {
                fightChoiceMade = false;
                System.out.println("\n  Enemy attacks...");
                if (enemyStrike > 0.40)
                {
                    System.out.println("    and hits!");
                    heroHP -= ((int) ((Math.random() * 6) + 1) + eAtk);
                }
                else
                {
                    System.out.println("    but misses!");
                }
            }
            else if (!enemyAlive && !fightOver)
            {
                System.out.println("    Enemy is slain!");
                setHP(heroHP);   //without this line, hero returns to full health after battle!
                fightOver = true;
            }
            
            //Hero Alive?
            if (heroHP <= 0)
            {
                System.out.println("    You are slain!");
                System.out.println("\nYour quest has come to an end.");
                System.exit(0);
            }
        } while (!fightOver);
    }
}
