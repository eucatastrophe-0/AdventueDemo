package AdventureDemo;
//@author samuel.schock

//This code implements the combat routine from the AdventureDemo using methods
//  instead of objects.  Obselete, but interesting!
//      -SAS 4/15/20
import java.util.Scanner;

public class EncounterMethod {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
    //these vaviables should already be called in program
        //call & initialize hero base stats
        //heroStats = {heroHPMax, heroHP, heroATK}
        int[] heroStats = {20, 20, 0};
        int weaponAtk = 0;
        int heroBaseAtk = 0;
        heroStats[2] = weaponAtk + heroBaseAtk;
        
        //prompt for enemy selection
        System.out.print("Enter enemy ID (0-4): ");
        int enemyID = input.nextInt();
        
        //call the method!
        heroStats[1] = enemyEnc(enemyID, heroStats);
        
        System.out.println("\npost combat, hero HP is: " + heroStats[1]);
        }
    
    public static int enemyEnc(int enemyID, int[] heroStats) {
        Scanner input = new Scanner(System.in);
        
        //call variables related to hero & enemy stats, combat & encounter flow
        double heroStrike, enemyStrike;
        int fightChoice;
        
        int heroHP = heroStats[1];
        int heroAtk = heroStats[2];
        int eAtk, eHP;
        String eName;
        
        //initialize some variables related to combat & encounter flow
        
        boolean enemyAlive = true;
        boolean fightChoiceMade = false;
        boolean fightOver = false;
        
    //*****create arrays for the enemy stats    
        //create sentinel values
        int enemyATK = 0;
        int enemyHP = 0;
        String enemyName = "null";
        
        //rosterSize must be equal to number of cases, i.e. the number of distinct enemies
        int rosterSize = 5;
        //numArray is the number of arrays to be made, i.e. the number of enemy traits
        int numArray = 3;
        
        
        String[] eneName = new String[rosterSize];
        int[] eneATK = new int[rosterSize];
        int[] eneHP = new int[rosterSize];
        
        for(int z = 0; z < numArray; z++){
            //for loop cycles through switch cases...
            for(int i = 0; i < rosterSize; i++){
                switch(i){
                    case 0:
                        enemyName = "Alpha";
                        enemyATK = 5;
                        enemyHP = 5;
                        break;
                    case 1:
                        enemyName = "Beta";
                        enemyATK = 20;
                        enemyHP = 2;
                        break;
                    case 2:        
                        enemyName = "Gamma";
                        enemyATK = 1;
                        enemyHP = 20;
                        break;
                    case 3:        
                        enemyName = "Delta";
                        enemyATK = 10;
                        enemyHP = 10;
                        break;
                    case 4:        
                        enemyName = "Epsilon";
                        enemyATK = 0;
                        enemyHP = 30;
                        break;
                }
                //...and assigns enemyATK values to an array
                switch(z){
                    case 0:
                        eneName[i] = enemyName;
                        break;
                    case 1:
                        eneATK[i] = enemyATK;
                        break;
                    case 2:
                        eneHP[i] = enemyHP;
                        break;
                }
            }
        }
        
        //Now, with enemy stats compiled, we select the enemy for the encounter
        eName = eneName[enemyID];
        eAtk = eneATK[enemyID];
        eHP = eneHP[enemyID];
        
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
        
        //*******************************
        return heroHP;
    }
    
}
