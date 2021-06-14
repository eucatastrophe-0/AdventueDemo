package AdventureDemo;

import java.util.Scanner;

public class AdventureDemoV2 {

    public static void main(String[] args) {
        //DEMOS & DRAGONS
        //v2.0
        //goals
        //      adapt code from v1.x to use objects & methods
        //          status: down to 451 lines, from 930!
        //issues
        //      rewards received after running from opponents
        //      complex commands (special attacks, 'talk', poison) not useable with current combat method
        
        Scanner input = new Scanner(System.in);
    
    //vaviables
        int inQuest;
        
        //build & populate roster of enemies
        enemy[] eneRoster = new enemy[9];
        eneRoster[0] = new enemy("direbat", 0, 5);
        eneRoster[1] = new enemy("orc warrior", 5, 10);
        eneRoster[2] = new enemy("ghost", 0, 20);
        eneRoster[3] = new enemy("tiger", 2, 5);
        eneRoster[4] = new enemy("king cobra", 0, 5);
        eneRoster[5] = new enemy("goblin", 0, 30);
        eneRoster[6] = new enemy("giant mantis", 10, 10);
        eneRoster[7] = new enemy("mummy", 5, 30);
        eneRoster[8] = new enemy("hydra", 3, 20);
        
        hero Hero = new hero();
        Hero.setMaxHP(20);
        Hero.setHP(Hero.getMaxHP());
        
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
"                                                                                                                        ");
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
    
    int randEnc = (int) (Math.random() * 5);
    switch (randEnc) {
        case 0:
        case 1:
        case 2:
            Hero.combat(eneRoster[0]);
            break;
        case 3:
            Hero.combat(eneRoster[1]);
            break;
        case 4:
            Hero.combat(eneRoster[2]);
            System.out.println("\n  You ask the ghost what it wants.");
            System.out.println("    ...it moans and says:");
            System.out.println("    \"Ooohhh! I'm frightened! Where am I?  Can you help me?\"");
            System.out.println("\n  The ghost starts to follow you...");
            ghostFriend = true;
            break;
    }
        
    //Challenge
    //the player faces a non-combat challenge based on where they are questing
    
        System.out.println("\nYou venture on...");
        
        //initialize variables for the challenge
        String challenge1Desc, ghostHint1;
        String challenge1Opt1, challenge1Opt2, challenge1Opt3;
        String challenge1Result1, challenge1Result2, challenge1Result3;
        //boolean chalFight = false;
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
                        Hero.combat(eneRoster[3]);
                        break;
                    case 3:
                        //kingcobra
                        Hero.combat(eneRoster[4]);
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
                        Hero.modHP(-arrowTrap);
                        if (Hero.getHP() <= 0) {
                            System.out.println("    You are slain!");
                            System.out.println("\nYour quest has come to an end.");
                            System.exit(0);
                
                        }
                        else {
                            System.out.println("\n                            Hero HP: " + Hero.getHP());
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
                        Hero.combat(eneRoster[5]);
                        break;
                    case 2:
                        //bypass, 10% fail
                        double sneak = Math.random();
                        if (sneak <= 0.10) {
                            System.out.println("    ... as you slink away, a twig snaps under your feet.\n  The goblins know you're there!");
                            //goblin fight
                            Hero.combat(eneRoster[5]);
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
        
    //Reward 1
        //declare and set variables related to rewards
        boolean necklace = false;
        boolean fireScroll = false;
        boolean hydraInfo = false;
        
        //use switch cases to describe rewards for different inQuest inputs
        if (chalReward == true) {
            switch (inQuest) {
                case 1:
                    Hero.modGold(100);
                    necklace = true;
                    System.out.println("\n    Amongst the debris of the cart, you find a sack...\n    ...with 100 gold pieces and a jeweled necklace!");
                    System.out.println("\n                               Gold: " + Hero.getGold());
                    break;
                case 2:
                    fireScroll = true;
                    System.out.println("\n    In the knapsack, you find a Fireball Scroll!");
                    break;
                case 3:
                    Hero.restore();
                    hydraInfo = true;
                    System.out.println("\n    You are fully healed!");
                    System.out.println("                            Hero HP: " + Hero.getHP());
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
        boolean fight1Reward = false;
        switch (inQuest) {
            case 1:
                System.out.println("\nYou venture deeper into the jungle...");
                System.out.println("A small clearing opens before you, surrounded by brush and branches.");
                System.out.println("It's a giant mantis nest!");
                System.out.println("Trees crash to the ground behind you as the mantis returns to its home,");
                System.out.println("    its scimitar-like arms stretched before its delicate body.");
                enemy3 = 1;
                Hero.combat(eneRoster[6]);
                fight1Reward = true;
                break;
            case 2:
                System.out.println("\nYou walk carefully down the corridor.");
                System.out.println("It opens up to a circular room with a bloodied altar at the center.");
                System.out.println("A figure shuffles from behind the altar...");
                System.out.println("It's a mummy!");
                enemy3 = 2;
                Hero.combat(eneRoster[7]);
                fight1Reward = true;
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
                Hero.combat(eneRoster[8]);
                fight1Reward = true;
                break;
            default:
                System.out.println("something is terribly wrong 4");
        }
        
        
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
                    /*heroWeapon = "dagger";
                    weaponAtk = 5;
                    heroAtk = weaponAtk + heroBaseAtk;*/
                    Hero.modATK(5);
                    System.out.println("                            Hero ATK: " + Hero.getATK());
                    break;
                case 3:
                    if (hydraInfo) {
                        System.out.println("\nYou cut out the hydra's heart, wrap it in cloth and stow it away.");
                    }
                    System.out.println("You investigate the area and find the partly consumed body of an orc warrior.");
                    System.out.println("The hyrda must have been interrupted during dinner...");
                    System.out.println("The orc is wearing an iron breastplate. You take it, and whatever coin he was carrying.");
                    Hero.setMaxHP(30);
                    Hero.modHP(10);
                    Hero.modGold(25);
                    System.out.println("                            Hero HP: " + Hero.getHP() + "/30");
                    System.out.println("                               Gold: " + Hero.getGold());
                    break;
            }
        }
    
    //Trading Post
        System.out.println("\nThe day has been long; best to head to a safe haven.");
    
        //poison subroutine
        if (poison == true) {
            System.out.println("As you head home, you feel a throbbing pain on your arm.\nThe king cobra poisoned you!\nWeakness spreads through your body.");
            Hero.modHP(-((int) (Math.random() * 6) + 2));
            if (Hero.getHP() <= 0) {
                System.out.println("You collapse.");
                System.exit(0);
            }
            else {
                System.out.println("Better get back to an apothecary before it's too late!");
                System.out.println("                            Hero HP: " + Hero.getHP());
            }
        }
        
        System.out.println("\nAs dusk starts to fall, you happen upon a crossroads, with an inn and a trading post.");
        
        //the rest of the adventure will go here! Work in progress!
        
        System.out.println("\nYour quest has come to an end.");
            
    }
    
}
