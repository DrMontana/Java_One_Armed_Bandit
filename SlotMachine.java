package Project1;

import java.util.Random;
import java.util.Scanner;

/**
 * This simple program runs a slot machine game with three reels
 * and can run the game a million times if prompted
 *
 * @author Wesley Smith
 */
public class SlotMachine {

    //Initial Values
    /**
     * The base values to initialize in our methods.
     * start - how much money we begin our game with (set to 100)
     * current - matches the "start" value initially, and will be the cash amount that is edited
     * int[] slotReelNumbers - making an empty array of size 3 for the random numbers
     * bet - used to catch the user input "bet" in .betInput()
     * win - how much money is won each round
     */
    private int start = 100;
    private int[] slotReelNumbers = new int[3];
    private int current = start;
    private int bet;
    private static int win;
    Scanner scr = new Scanner(System.in);

    //Constructor

    /**
     * The constructor for our SlotMachine class
     */
    public SlotMachine(){}

    //Methods

    /**
     * The beginning setup of the Slot Machine game
     * Gives the user three options
     */
    public void startGame(){
        System.out.println("Welcome to The One Armed Bandit!!");
        System.out.println("You have $"+start+ " to start");
        System.out.println("Enter -1 to cash out.");
        System.out.println("Enter -2 to make me play a million games. \n");
    }

    /**
     * @return this.bet gets the user input for how much they want to bet
     * returns the bet amount to be referenced throughout SlotMachine()
     */
    public int betInput(){
        System.out.println("Enter your bet: ");
        int inputBet = scr.nextInt();
        this.bet = inputBet;
        return this.bet;
    }

    /**
     * Generates a random set of three variable numbers between 1 and 7
     * Assigns these three variables to each index of our initialized array slotReelNumbers
     */
    private void getRandomNumbers(){
        Random rand = new Random();
        int randomNum1 = rand.nextInt(7) + 1;
        int randomNum2 = rand.nextInt(7) + 1;
        int randomNum3 = rand.nextInt(7) + 1;

        //Assigning these random numbers to our array
        this.slotReelNumbers[0] = randomNum1;
        this.slotReelNumbers[1] = randomNum2;
        this.slotReelNumbers[2] = randomNum3;
    }

    /**
     * Prints out our slot machine wheel with the randomly generated numbers from .getRandomNumbers()
     * Uses the .calculateWin() method to calculate if the game was a win or not
     * It also prints out our current info
     * -- If we won any money from the current spin
     * -- Shows the current balance
     */
    private void printOutReel(){
        this.calculateWin();
        System.out.println("+---+---+---+");

        // Represents where rolls will go with slotReelNumbers at each index value
        System.out.println("| " + slotReelNumbers[0] + " | " + slotReelNumbers[1] + " | " + slotReelNumbers[2] + " |");
        System.out.println("+---+---+---+");
        System.out.println("Win: $"+this.win);
        System.out.println("Balance: $"+ (current +=(-bet+this.win))+"\n");
        // reset the value of win to 0 for each game
        this.win=0;
    }

    /**
     *
     * @return returns our win amount based on three conditions
     * -- If the first two reel numbers match
     * -- If all three reel numbers match
     * -- If none of the reel numbers match
     */
    private int calculateWin(){
        if(slotReelNumbers[0]==slotReelNumbers[1]){
            if(slotReelNumbers[1]==slotReelNumbers[2]){
                this.win = slotReelNumbers[1] * this.bet * 5;
                return this.win;
            }
            else{
                this.win = this.bet * slotReelNumbers[1];
                return this.win;
            }
        }
        else{
            this.win = 0;
            return this.win;
        }
    }

    /**
     * This method runs the entire game in a while loop until prompted
     * If the user enters a -1 the while loop is broken
     *
     */
    public void letsGetItStarted(){
        this.startGame();
        // While loop to check for -1 or -2 inputs
        while(!(this.bet == -1 || this.bet == -2)){
            this.betInput();
            // if the bet = -1 it will quit the game and give the user their final balance
            if(this.bet == -1) {
                System.out.println("Your Final Winnings: $"+current);
                break;
            }
            // if the bet = -2 it will run the .millionGames() method
            else if(this.bet == -2){
                this.millionGames();
                break;
            }
            // While the bet != -1 or -2 the game runs normally
            else {
                this.getRandomNumbers();
                this.calculateWin();
                this.printOutReel();
            }
        }
    }

    /**
     * This method is used to run the game a million times in a for loop
     * It assumes a bet of $1 each play
     */
    private void millionGames(){
        //When run, it resets the current balance to the start
        this.current = start;
        win = 0;

        //Sets the bet to $1
        this.bet = 1;
        // Using symbol to fix where the negative sign is if the current goes negative
        String symbol = "";
        for (int i = 0; i < 1000001; i++) {
            this.getRandomNumbers();
            this.calculateWin();
            this.current += (win - bet);
        }
        // Change the current amount to positive and place the "-" symbol before the dollar sign
        if (this.current<0){
            symbol = "-";
            this.current = this.current * -1;
        }
        System.out.println("After 1,000,000 games, your final balance: "+symbol+"$"+current);
    }
    public static void main(String[] args) {
        // Creates a new instance of the SlotMachine class
        SlotMachine game = new SlotMachine();
        //Runs the game using the SlotMachine.letsGetItStarted() method
        game.letsGetItStarted();
    }
}
