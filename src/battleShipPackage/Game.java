package battleShipPackage;

import java.util.Random;
import java.util.Scanner;

/**
 * <h2>Class Description:</h2>
 * This class implements the game and handles interactions
 * between the Human and Computer objects and prompting the 
 * user for information
 * @version 1.0
 * @author Dharmpreet Atwal
**/
public class Game {
	Player Human;
	Player Computer;
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1>This method handles the execution of the other methods</p1>
	 * in the class once the program begins
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[]args) throws InterruptedException {
		
		// STARTING GAME INTIALIZATION
		Game battleShip = new Game();
		battleShip.intializePlayers();
		battleShip.displayGrids();
		battleShip.intializePlayersShips();
		battleShip.startGame();
		
	}

	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method handles the initialization of the player objects</p1>
	 */
	public void intializePlayers() {
		Human = new Player('H', 5);
		Computer = new Player('C', 5);
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method handles the prompting of the user with
	 * updated grids whenever there is change to their grids</p1>
	 * @throws InterruptedException
	 */
	public void displayGrids() throws InterruptedException {

		
		System.out.println("-------------------------");
		System.out.println("This is your grid, the computer's attacks will be shown on this grid.");
		System.out.println(Human.myGrid.toString());
		System.out.println("-------------------------");
		
		System.out.println("This is the computer's grid with the ships hidden, your attacks will be shown on this grid.");
		System.out.println(Human.opponentsGrid.toString());
		System.out.println("-------------------------");
		Human.pauseForPlayer();
		
	}
	
	/**
	 * <h2> Method Description:</h2>
	 * <p1> This method calls the intializeShips method in each of the Objects from the player class and initializes their ships. </p1>
	 * @throws InterruptedException
	 */
	public void intializePlayersShips() throws InterruptedException {
		Human.initializeShips();		
		Computer.initializeShips();
		displayGrids();
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method handles the looping of the game until one 
	 * of the players have lost all their ships</p1>
	 * @throws InterruptedException
	 */
	public void startGame() throws InterruptedException {
		boolean playersTurn = false;

		do {

			switchTurn(playersTurn);
			displayGrids();
			
			if (playersTurn == true) {
				playersTurn = false;
			} else {
				playersTurn = true;
			}
			
		// Loops until User or Computer runs out of ships
		} while(Human.numOfShips != 0 && Computer.numOfShips !=0);
		
		// Checks to see who won
		if (Human.numOfShips == 0) {
			System.out.println("Computer Wins!");
		} else {
			System.out.println("Player Wins!");
		}
		
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method handles the switching of turns after each player finished theirs</p1>
	 * @param playersTurn Keeps track of whether it's the computer or player's turn.
	 * @throws InterruptedException
	 */
	public void switchTurn(boolean playersTurn) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		
		int x = 0; 
		int y = 0;
		
		// If its the user's turn
		if (playersTurn == true) {
			System.out.println("It's the User's Turn.");
			Thread.sleep(2500);
			
			do {
				System.out.print("Please enter a Valid x coordinate for your attack");
				x = input.nextInt();
			
				System.out.print("Please enter a Valid y coordinate for your attack");
				y = input.nextInt();
				
			// Loops until valid coordinates are entered
			} while (Human.myGrid.validCoordinates(x, y));
			
			Human.attack(x, y, Computer);
			
		// If it's the computer's turn
		} else {
			System.out.println("It's the Computer's Turn.");
			Human.pauseForPlayer();

			Random rand = new Random();
			x = rand.nextInt(10) + 1;
			y = rand.nextInt(10) + 1;	
			
			System.out.println("The Computer attacks the Coordinates of (" + x + ", " + y + ")");
			Thread.sleep(1500);
			Computer.attack(x, y, Human);

		}
		
	}

}

