package AdventureDemo;

import java.util.Scanner;

public class AdventureDemoWorking {

    public static void main(String[] args) {
        //DEMOS & DRAGONS
        //v1.1.2
        //working version
        //changes from v1.0
        //      made random encounter algo more general
        //      edited & expanded ghost encounter
        //      added gold & weapons
        //      event tree expanded thru boss fight reward
        //goals
        //      IMPLEMENT objects & classes!!
        //      expand ghost sidequest
        //      implement gold & items
        //      expand events past Crossroads Inn
        
        Scanner input = new Scanner(System.in);
    
    //vaviables
        //call variables related to combat & control flow
        boolean enemyAlive, fightChoiceMade, fightOver;
        double heroStrike, enemyStrike;
        int inQuest;
        
        //call & initialize weapon stats
        String heroWeapon = "sword";
        int weaponAtk = 0;
        
        //call & initialize hero base stats
        int heroHPmax = 20;
        int heroHP = heroHPmax;
        int heroBaseAtk = 0;
        int heroAtk = weaponAtk + heroBaseAtk;
        int heroGold = 0;
        boolean heroAlive = true;
        boolean ghostFriend = false;
        //call & initialize hero status conditions
        boolean poison = false;
        boolean absorb = false;
        
        System.out.println(" ______   _______  _______  _______  _______      __      ______   _______  _______  _______  _______  _        _______ \n" +
"(  __  \\ (  ____ \\(       )(  ___  )(  ____ \\    /__\\    (  __  \\ (  ____ )(  ___  )(  ____ \\(  ___  )( (    /|(  ____ \\\n" +
"| (  \\  )| (    \\/| () () || (   ) || (    \\/   ( \\/ )   | (  \\  )| (    )|| (   ) || (    \\/| (   ) ||  \\  ( || (    \\/\n" +
"| |   ) || (__    | || || || |   | || (_____     \\  /    | |   ) || (____)|| (___) || |      | |   | ||   \\ | || (_____ \n" +
"| |   | ||  __)   | |(_)| || |   | |(_____  )    /  \\/\\  | |   | ||     __)|  ___  || | ____ | |   | || (\\ \\) |(_____  )\n" +
"| |   ) || (      | |   | || |   | |      ) |   / /\\  /  | |   ) || (\\ (   | (   ) || | \\_  )| |   | || | \\   |      ) |\n" +
"| (__/  )| (____/\\| )   ( || (___) |/\\____) |  (  \\/  \\  | (__/  )| ) \\ \\__| )   ( || (___) || (___) || )  \\  |/\\____) |\n" +
"(______/ (_______/|/     \\|(_______)\\_______)   \\___/\\/  (______/ |/   \\__/|/     \\|(_______)(_______)|/    )_)\\_______)\n" +
"                                                                                                                        \t\tv1.1.2");
    //choose your adventure
        System.out.println("Where shall we quest today?");
        System.out.println("    (1) the Forbidden Jungle");
        System.out.println("    (2) the Sunken Crypt");
        System.out.println("    (3) the Fetid Bog");
        
        //while loop prevents choosing invalid integer
        //valid choice prints appropriate flavor text
        
        boolean advChoice = false;
        
        do {
            System.out.print("-->  ");
            inQuest = input.nextInt();
            switch (inQuest) {
                case 1:
                    System.out.println("\nThe jungle is dense and steamy.");
                    advChoice = true;
                    break;
                case 2:
                    System.out.println("\nThe halls of the crypt are musty and dark.");
                    advChoice = true;
                    break;
                case 3:        
                    System.out.println("\nThe stench of stagnant water and rot hangs in the air.");
                    advChoice = true;
                    break;
                default:
                    System.out.println("\nChoose again...");
                    break;
            }
        }
        while (advChoice == false);
        
    //Random Encounter
        //first, initialize some variables related to enemy, combat & encounter flow
        //  >note use of random to yield different enemies!
        
        int inEnc1;
        int enemy1 = (int) (Math.random() * 5) + 1;
        String enemy1Name = " null";
        int enemy1Atk = 0;
        int enemy1HP = 0;
        
        enemyAlive = true;
        fightChoiceMade = false;
        fightOver = false;
        
        //second, use cases to create different monsters
        //  >probably could be replaced with arrays down the line?
        //  >note that case distribution determines probability of encounter
        
        switch (enemy1) {
            case 1:
            case 2:
            case 3:        
                enemy1Name = " direbat";  //fuck you direbat
                enemy1Atk = 0;
                enemy1HP = 5;
                break;
            case 4:        
                enemy1Name = "n orc warrior";
                enemy1Atk = 5;
                enemy1HP = 10;
                break;
            case 5:        
                enemy1Name = " ghost";
                enemy1Atk = 0;
                enemy1HP = 20;
                break;
            default:
                break;
        }

        //announce encounter
        
        System.out.println("You encounter a" + enemy1Name +"!");

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
            System.out.println("\nWhat do you do?             Hero HP: " + heroHP);
            System.out.println("    (1) Attack");
            System.out.println("    (2) Run");
            System.out.println("    (3) Talk");
            
            //Action Loop
            do {
                System.out.print("-->  ");
                inEnc1 = input.nextInt();
                
                //Strike values are recalculated every loop
                //  >used to determine hit/miss chance, Run success
                
                heroStrike = Math.random();
                enemyStrike = Math.random();
                
                //switch cases used for encounter choices, checks for invalid
                
                switch (inEnc1) {
                    case 1:
                        System.out.println("\n  You swing your " + heroWeapon + "!");
                        //hero has 75% chance to hit
                        if (heroStrike > 0.25)
                        {
                            System.out.println("    You hit!");
                            enemy1HP -= ((int) ((Math.random() * 6) + 1) + heroAtk);
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
                        //you can only talk to the ghost
                        if (enemy1 != 5)
                        {
                            System.out.println("\n  Enemy doesn't understand!");
                        }
                        else
                        {
                            System.out.println("\n  You ask the ghost what it wants.");
                            System.out.println("    ...it moans and says:");
                            System.out.println("    \"Ooohhh! I'm frightened! Where am I?  Can you help me?\"");
                            System.out.println("\n  The ghost starts to follow you...");
                            ghostFriend = true;
                            fightOver = true;
                        }
                        fightChoiceMade = true;
                        break;
                    default:
                        System.out.println("\nChoose again...");
                        break;
                }

            } while (fightChoiceMade == false);
            
            //Enemy Alive?
            if (enemy1HP <= 0)
            {
            enemyAlive = false;
            }
            
            //Enemy Response
            //      >only case where enemyAlive && fightOver is true
            //       is when you talk to ghost!
            if (enemyAlive == true && fightOver == false)
            {
                fightChoiceMade = false;
                System.out.println("\n  Enemy attacks...");
                //enemy has 60% chance to hit
                if (enemyStrike > 0.40)
                {
                    System.out.println("    and hits!");
                    heroHP -= ((int) ((Math.random() * 6) + 1) + enemy1Atk);
                }
                else
                {
                    System.out.println("    but misses!");
                }
            }
            else if (enemyAlive == false && fightOver == false)
            {
                System.out.println("    Enemy is slain!");
                if (enemy1 == 4) {
                    System.out.println("    You take the orc's spear.");
                    heroWeapon = "spear";
                    weaponAtk = 5;
                    heroAtk = weaponAtk + heroBaseAtk;
                    System.out.println("                            Hero ATK: " + heroAtk);
                }
                fightOver = true;
            }
            
            //Hero Alive?
            if (heroHP <= 0)
            {
                heroAlive = false;
            }
            if (heroAlive == false)
            {
                System.out.println("    You are slain!");
                System.out.println("\nYour quest has come to an end.");
                System.exit(0);
            }
        } while (fightOver == false);
        
    //Challenge
    //the player faces a non-combat challenge based on where they are questing
    
        System.out.println("\nYou venture on...");
        
        //initialize variables for the challenge
        String challenge1Desc, ghostHint1;
        String challenge1Opt1, challenge1Opt2, challenge1Opt3;
        String challenge1Result1, challenge1Result2, challenge1Result3;
        boolean chalFight = false;
        boolean chalReward = false;
        int inChallenge1;
        int enemy2 = 0;
        
        //declare challenge flavor text & choices cases, based on adventure choice
        switch (inQuest) {
            case 1:
                challenge1Desc = "You come upon a wrecked cart, long abandoned.\nIts tattered canvas top obscures any contents.";
                ghostHint1 = "  ...the ghost whispers, \"Danger abounds...\"";
                challenge1Opt1 = "Loot";
                challenge1Opt2 = "Ignore";
                challenge1Opt3 = "Examine";
                challenge1Result1 = "Finders, keepers.\nAs you approach, your feet start to sink into the earth...\nIt's quicksand!";
                challenge1Result2 = "Best to leave it be.\nAs you turn to leave, you hear rustling from the cart.\nA tiger leaps toward you!";
                challenge1Result3 = "You carefully approach...\nA king cobra slithers out from the cart!";
                break;
            case 2:
                challenge1Desc = "You happen upon a desicated corpse, arrows in its back.\nA knapsack sits beside it.";
                ghostHint1 = "  ...the ghost whispers, \"A sorcerer. He died alone...\"";
                challenge1Opt1 = "Loot";
                challenge1Opt2 = "Ignore";
                challenge1Opt3 = "Examine";
                challenge1Result1 = "As you approach the knapsack, you feel a panel shift under your feet.\nArrows fly through the air and strike you!";
                challenge1Result2 = "No sense disturbing the dead.";
                challenge1Result3 = "As you kneel beside the body, you notice a tripwire.\nIt's an arrow trap! You carefully disarm the device.";
                break;
            case 3:
                challenge1Desc = "You come upon a goblin patrol, setting up camp.\nThey haven't noticed you... yet.";
                ghostHint1 = "  ...the ghost whispers, \"Fixing supper. Looks good...\"";
                challenge1Opt1 = "Attack";
                challenge1Opt2 = "Leave";
                challenge1Opt3 = "Talk";
                challenge1Result1 = "You rush out of the brush and lop off a goblin's head.\nThe other three take up arms!";
                challenge1Result2 = "It's safer to avoid danger...";
                challenge1Result3 = "You carefully hail the goblins.\nThey recognize you mean no harm, and share some food with you.\nA hydra is stalking this bog, they say.\nBig reward from the local sheriff if you bring him its heart.\n\"Just remember - hit the body, not the heads!\"";
                break;
            default:
                challenge1Desc = "something is terribly wrong 1";
                ghostHint1 = "";
                challenge1Opt1 = "";
                challenge1Opt2 = "";
                challenge1Opt3 = "";
                challenge1Result1 = "";
                challenge1Result2 = "";
                challenge1Result3 = "";
                break;
        }
        
        //print challenege text & hint
        System.out.println(challenge1Desc + "\n");
        if (ghostFriend == true) {
            System.out.println(ghostHint1 +"\n");
        }
        System.out.println("What do you do?");
        System.out.println("    (1) " + challenge1Opt1);
        System.out.println("    (2) " + challenge1Opt2);
        System.out.println("    (3) " + challenge1Opt3);
        
        advChoice = false;
        do {
            System.out.print("-->  ");
            inChallenge1 = input.nextInt();
            switch (inChallenge1) {
                case 1:
                    System.out.println("\n" + challenge1Result1);
                    advChoice = true;
                    break;
                case 2:
                    System.out.println("\n" + challenge1Result2);
                    advChoice = true;
                    break;
                case 3:        
                    System.out.println("\n" + challenge1Result3);
                    advChoice = true;
                    break;
                default:
                    System.out.println("\nChoose again...");
                    break;
            }
        }
        while (advChoice == false);
        
        //nested switch cases determine consequences
        //either a fight (1-2, 1-3, 3-1), a bypass (2-2, 3-2), or an event (1-1, 2-1, 2-3, 3-3) occurs
        
        switch (inQuest) {
            //first case: Jungle
            case 1:
                switch (inChallenge1) {
                    case 1:
                        //Quicksand (63% fail rate, 3 times => 25% net fail)
                        int quicksand;
                        double trapChance;
                        boolean quickStuck = true;
                        System.out.println("    (1) Escape!");
                        int quickTrap = 0;
                        while (quickStuck == true) {
                            System.out.print("--> ");
                            quicksand = input.nextInt();
                            trapChance = Math.random();
                            if (quicksand == 1) {
                                if (trapChance >= 0.63) {
                                    System.out.println("    You free yourself from the mire!");
                                    quickStuck = false;
                                }
                                else {
                                    System.out.println("    You struggle against the muck, but cannot escape...");
                                    quickTrap += 1;
                                }
                            if (quickTrap == 3) {
                                System.out.println("    Your head slips below the surface.\nYour quest has come to an end.");
                                System.exit(0);
                                }
                            }
                            else {
                                System.out.println("    Don't give up!");
                            }
                        }
                        chalReward = true;
                        break;
                    case 2:
                        //tiger
                        enemy2 = 1;
                        chalFight = true;
                        break;
                    case 3:
                        //kingcobra
                        enemy2 = 2;
                        chalFight = true;
                        break;
                    default:
                        break;
                }
                break;
            //second case: Crypt
            case 2:
                switch (inChallenge1) {
                    case 1:
                        //arrow trap, reward if survive
                        int arrowTrap = (int) (Math.random() * 6) + 5;
                        heroHP -= arrowTrap;
                        if (heroHP <= 0) {
                            System.out.println("    You are slain!");
                            System.out.println("\nYour quest has come to an end.");
                            System.exit(0);
                
                        }
                        else {
                            System.out.println("\n                            Hero HP: " + heroHP);
                        }
                        chalReward = true;
                        break;
                    case 2:
                        //bypass
                        break;
                    case 3:
                        //reward!
                        chalReward = true;
                        break;
                    default:
                        break;
                }
                break;
            //third case: Bog
            case 3:
                switch (inChallenge1) {
                    case 1:
                        //goblin fight
                        enemy2 = 3;
                        chalFight = true;
                        break;
                    case 2:
                        //bypass, 10% fail
                        double sneak = Math.random();
                        if (sneak <= 0.10) {
                            System.out.println("    ... as you slink away, a twig snaps under your feet.\n  The goblins know you're there!");
                            enemy2 = 3;
                            chalFight = true;
                        }
                        break;
                    case 3:
                        //reward!
                        chalReward = true;
                        break;
                    default:
                        break;
                }
                break;
            default:
                System.out.println("something is terribly wrong 2");
                break;
        }
        
        //Challenge Fight
        //only triggered by certain challenge paths
        
        if (chalFight == true) {
            //first, initialize some variables related to enemy, combat & encounter flow
            int inEnc2;
            String enemy2Name = " null";
            int enemy2Atk = 0;
            int enemy2HP = 0;

            enemyAlive = true;
            fightChoiceMade = false;
            fightOver = false;
            
            //second, use cases to create different monsters

            switch (enemy2) {
                case 1:       
                    enemy2Name = "tiger";
                    enemy2Atk = 2;
                    enemy2HP = 5;
                    break;
                case 2:        
                    enemy2Name = "king cobra";
                    enemy2Atk = 0;
                    enemy2HP = 5;
                    break;
                case 3:        
                    enemy2Name = "goblin band";
                    enemy2Atk = 0;
                    enemy2HP = 30;
                    break;
                default:
                    break;
            }

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
                System.out.println("    (3) Talk");

                //Action Loop
                do {
                    System.out.print("-->  ");
                    inEnc2 = input.nextInt();

                    //Strike values are recalculated every loop

                    heroStrike = Math.random();
                    enemyStrike = Math.random();

                    //switch cases used for encounter choices, default checks for invalid

                    switch (inEnc2) {
                        case 1:
                            System.out.println("\n  You swing your " + heroWeapon + "!");
                            if (heroStrike > 0.25)
                            {
                                System.out.println("    You hit!");
                                enemy2HP -= ((int) ((Math.random() * 6) + 1) + heroAtk);
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
                            if (enemy2 == 3) {
                                System.out.println("\n  The goblins do not want to talk!");
                            }
                            else {
                                System.out.println("\n  Enemy doesn't understand!");
                            }
                            fightChoiceMade = true;
                            break;
                        default:
                            System.out.println("\nChoose again...");
                            break;
                    }

                } while (fightChoiceMade == false);

                //Enemy Alive?
                if (enemy2HP <= 0)
                {
                enemyAlive = false;
                }

                if (enemyAlive == true && fightOver == false)
                {
                    fightChoiceMade = false;
                    System.out.println("\n  Enemy attacks...");
                    if (enemyStrike > 0.40)
                    {
                        System.out.println("    and hits!");
                        heroHP -= ((int) ((Math.random() * 6) + 1) + enemy2Atk);
                        if (enemy2 == 2) {
                            poison = true;
                        }
                    }
                    else
                    {
                        System.out.println("    but misses!");
                    }
                }
                else if (enemyAlive == false && fightOver == false)
                {
                    System.out.println("    The " + enemy2Name + " is slain!");
                    if (enemy2 == 1 || enemy2 == 2) {
                        chalReward = true;
                    }
                    if (enemy2 == 3) {
                        heroWeapon = "spear";
                        weaponAtk = 5;
                        heroAtk = weaponAtk + heroBaseAtk;
                        System.out.println("    You take one of the goblins' spears.");
                        System.out.println("                            Hero ATK: " + heroAtk);
                        heroGold += 20;
                        System.out.println("\n    You search the camp... not much here, but you find 20 gold coins.");
                        System.out.println("\n                               Gold: " + heroGold);
                    }
                    fightOver = true;
                }

                //Hero Alive?
                if (heroHP <= 0)
                {
                    heroAlive = false;
                }
                if (heroAlive == false)
                {
                    System.out.println("    You are slain!");
                    System.out.println("\nYour quest has come to an end.");
                    System.exit(0);
                }
            } while (fightOver == false);
            
        }
        
    //Reward 1
        //declare and set variables related to rewards
        boolean necklace = false;
        boolean fireScroll = false;
        boolean hydraInfo = false;
        
        //use switch cases to describe rewards for different inQuest inputs
        if (chalReward == true) {
            switch (inQuest) {
                case 1:
                    heroGold += 100;
                    necklace = true;
                    System.out.println("\n    Amongst the debris of the cart, you find a sack...\n    ...with 100 gold pieces and a jeweled necklace!");
                    System.out.println("\n                               Gold: " + heroGold);
                    break;
                case 2:
                    fireScroll = true;
                    System.out.println("\n    In the knapsack, you find a Fireball Scroll!");
                    break;
                case 3:
                    heroHP = heroHPmax;
                    hydraInfo = true;
                    System.out.println("\n    You are fully healed!");
                    System.out.println("                            Hero HP: " + heroHP);
                    break;
                default:
                    System.out.println("something is terribly wrong 3");
                    break;
            }

        }
        
    //Fight
    //player now enters a boss fight, dependent on quest location
    
        //print intro flavor text
        int enemy3 = 0;
        switch (inQuest) {
            case 1:
                System.out.println("\nYou venture deeper into the jungle...");
                System.out.println("A small clearing opens before you, surrounded by brush and branches.");
                System.out.println("It's a giant mantis nest!");
                System.out.println("Trees crash to the ground behind you as the mantis returns to its home,");
                System.out.println("    its scimitar-like arms stretched before its delicate body.");
                enemy3 =1;
                break;
            case 2:
                System.out.println("\nYou walk carefully down the corridor.");
                System.out.println("It opens up to a circular room with a bloodied altar at the center.");
                System.out.println("A figure shuffles from behind the altar...");
                System.out.println("It's a mummy!");
                enemy3 = 2;
                break;
            case 3:
                System.out.println("\nYou venture further into the mire...");
                System.out.println("It is foggy. Very easy to get lost.");
                System.out.println("You hear something shambling in the distance.");
                System.out.println("The figure of a great beast looms ahead of you.");
                System.out.println("It raises its head toward you... then it raises its other heads!");
                // if/else used to change between definite & indefinite article
                // dependent on whether the player has learned about the hydra
                System.out.print("It's ");
                if (hydraInfo == true) {
                    System.out.print("the");
                }
                else {
                    System.out.print("a");
                }
                System.out.println(" hydra!");
                enemy3 = 3;
                break;
            default:
                System.out.println("something is terribly wrong 4");
        }
        
        //fight sequence!
        //first, initialize some variables related to enemy, combat & encounter flow
            boolean fight1Reward = false;
            boolean hydraHit = false;
            int inEnc3;
            String enemy3Name = " null";
            int enemy3Atk = 0;
            int enemy3HP = 0;

            enemyAlive = true;
            fightChoiceMade = false;
            fightOver = false;
            
            //second, use cases to create different monsters

            switch (enemy3) {
                case 1:       
                    enemy3Name = "giant mantis";
                    enemy3Atk = 10;
                    enemy3HP = 10;
                    break;
                case 2:        
                    enemy3Name = "mummy";
                    enemy3Atk = 5;
                    enemy3HP = 30;
                    break;
                case 3:        
                    enemy3Name = "hydra";
                    enemy3Atk = 3;
                    enemy3HP = 20;
                    break;
                default:
                    break;
            }

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
                System.out.println("    (3) Talk");
                if (fireScroll == true) {
                    System.out.println("    (4) Use fireball scroll");
                }

                //Action Loop
                do {
                    System.out.print("-->  ");
                    inEnc3 = input.nextInt();

                    //Strike values are recalculated every loop

                    heroStrike = Math.random();
                    enemyStrike = Math.random();

                    //switch cases used for encounter choices, default checks for invalid

                    switch (inEnc3) {
                        case 1:
                            System.out.println("\n  You swing your " + heroWeapon + "!");
                            if (heroStrike > 0.25)
                            {
                                System.out.println("    You hit!");
                                enemy3HP -= ((int) ((Math.random() * 6) + 1) + heroAtk);
                                if (enemy3 == 3) {
                                    hydraHit = true;
                                }
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
                            System.out.println("\n  Enemy doesn't understand!");
                            fightChoiceMade = true;
                            break;
                        case 4:
                            if (fireScroll == true) {
                                System.out.println("\n You unroll the scroll and recite the incantation!\n The scroll bursts into flames and hurtles toward the mummy.\n With a hollow scream, it bursts into flame!");
                                enemy3HP -= 20;
                                fireScroll = false;
                                fightChoiceMade = true;
                            }
                            else {
                                System.out.println("\nChoose again...");
                            }
                            break;
                        default:
                            System.out.println("\nChoose again...");
                            break;
                    }

                } while (fightChoiceMade == false);

                //Enemy Alive?
                if (enemy3HP <= 0)
                {
                enemyAlive = false;
                }

                if (enemyAlive == true && fightOver == false)
                {
                    fightChoiceMade = false;
                    if (enemy3 == 3 && hydraInfo == false && hydraHit == true) {
                        System.out.println("    ...but the hydra's wounds start to heal!");
                        enemy3HP += ((int) (Math.random() * 6) + 6);
                    }
                    System.out.println("\n  Enemy attacks...");
                    if (enemyStrike > 0.40)
                    {
                        System.out.println("    and hits!");
                        heroHP -= ((int) ((Math.random() * 6) + 1) + enemy3Atk);
                    }
                    else
                    {
                        System.out.println("    but misses!");
                    }
                }
                else if (enemyAlive == false && fightOver == false)
                {
                    System.out.println("    The " + enemy3Name + " is slain!");
                    fight1Reward = true;
                    fightOver = true;
                }

                //Hero Alive?
                if (heroHP <= 0)
                {
                    heroAlive = false;
                }
                if (heroAlive == false)
                {
                    System.out.println("    You are slain!");
                    System.out.println("\nYour quest has come to an end.");
                    System.exit(0);
                }
            } while (fightOver == false);
            
    //Reward 2
    
        if (fight1Reward) {
            switch (enemy3) {
                case 1:
                    System.out.println("\nYou rummage through the mantis nest.\nAmongst the bones and scraps of cloth, you find a parchment scroll...\nIt's a Fireball Scroll!");
                    fireScroll = true;
                    break;
                case 2:
                    System.out.println("\nYou investigate the altar.\nYou find a golden idol in the shape of a winged demon.\nYou also find a jeweled sacrificial dagger.\nYou feel warmth sweep through your body as you hold it.");
                    absorb = true;
                    heroWeapon = "dagger";
                    weaponAtk = 5;
                    heroAtk = weaponAtk + heroBaseAtk;
                    System.out.println("                            Hero ATK: " + heroAtk);
                    break;
                case 3:
                    if (hydraInfo) {
                        System.out.println("\nYou cut out the hydra's heart, wrap it in cloth and stow it away.");
                    }
                    System.out.println("You investigate the area and find the partly consumed body of an orc warrior.");
                    System.out.println("The hyrda must have been interrupted during dinner...");
                    System.out.println("The orc is wearing an iron breastplate. You take it, and whatever coin he was carrying.");
                    heroHPmax = 30;
                    heroHP += 10;
                    heroGold += 25;
                    System.out.println("                            Hero HP: " + heroHP + "/" + heroHPmax);
                    System.out.println("                               Gold: " + heroGold);
                    break;
            }
        }
    
    //Trading Post
        System.out.println("\nThe day has been long; best to head to a safe haven.");
    
        //poison subroutine
        if (poison == true) {
            System.out.println("As you head home, you feel a throbbing pain on your arm.\nThe king cobra poisoned you!\nWeakness spreads through your body.");
            heroHP -= (Math.random() * 6) + 2;
            if (heroHP <= 0) {
                System.out.println("You collapse.");
                System.exit(0);
            }
            else {
                System.out.println("Better get back to an apothecary before it's too late!");
                System.out.println("                            Hero HP: " + heroHP);
            }
        }
        
        System.out.println("\nAs dusk starts to fall, you happen upon a crossroads, with an inn and a trading post.");
        
        //the rest of the adventure will go here! Work in progress!
        
        System.out.println("\nYour quest has come to an end.");
            
    }
    
}
