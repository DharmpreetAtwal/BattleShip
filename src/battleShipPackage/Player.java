package battleShipPackage;

import java.util.Random;
import java.util.Scanner;

/**
 * <h2> Class Description: </h2>
 * <p1> This class will handle the attributes each player 
 * will have and what operations they will be able to perform. </p1>
 * @author 590630
 *
 */
public class Player {
	char playerType;
	Grid myGrid;
	Grid opponentsGrid;
	int numOfShips=5;
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle the construction of a Player Object and the initialization of it's grids. </p1>
	 * @param playerType The player type of the object being created.
	 * @param numOfShips The number of ships the player will have.
	 */
	public Player(char playerType, int numOfShips) {
		this.playerType = playerType;
		this.numOfShips = numOfShips;
		
		if (this.playerType == 'H') {
			this.myGrid = new Grid('H', '?', 'Y');
			this.opponentsGrid = new Grid('C', '!', 'X');
		} else {
			this.myGrid = new Grid('C', '!', 'X');
			this.opponentsGrid = new Grid('H', '?', 'Y');
		}
		
		this.myGrid.intitializeGrid();
		this.opponentsGrid.intitializeGrid();
		
	}	
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle the initialization of of the object's ships. </p1>
	 */
	public void initializeShips() {
		
		// If Human is calling this method
		if (this.playerType== 'H') {
			Scanner input = new Scanner(System.in);
		
			// Loops until all 5 ships placed
			for (int i=1; i<=this.numOfShips; i++) {
				int x;
				int y;
					
				// Loops until user enters valid coordinates
				do {
					
					System.out.print("Please enter a valid x coordinates for ship " + i + ".");
					x = input.nextInt();

					System.out.print("Please enter a valid y coordinates for ship " + i + ".");
					y = input.nextInt();
					
				} while(this.myGrid.validCoordinates(x, y));
				
				
				// Checks if the coordinates entered already has a ship placed at the coordinates
				if (this.myGrid.coordinateGrid[y][x] == this.myGrid.shipSymbol) {
					System.out.println("A ship already exists at that coordinate, Please enter in another set of coordinates");
					// Decrements the loop so that the ship can be initialized again
					i--;
				} else {
					this.myGrid.changeSymbol(x, y, this.myGrid.shipSymbol);
				}
		
			}
			
		// If computer is calling this method
		} else {
			Random rand = new Random();
			
			for (int i=1; i<=this.numOfShips; i++) {
				int x = rand.nextInt(10) + 1;
				int y = rand.nextInt(10) + 1;				
				
				// Checks if there is a ship already placed at the coordiante
				if (this.myGrid.coordinateGrid[y][x] == this.myGrid.shipSymbol) {
					i--;
				} else {
					this.myGrid.changeSymbol(x, y, this.myGrid.shipSymbol);
				}
				
			}
			
		}
		
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle the attacking of the Player. </p1>
	 * @param x The x coordinates the Player would like to attack
	 * @param y The y coordinates the Player would like to attack
	 * @param defPlayer The player type of the Defending Player
	 * @return True if attack is a hit, False if a miss
	 * @throws InterruptedException For pausing the program
	 */
	public boolean attack(int x, int y, Player defPlayer) throws InterruptedException {
		
		// If there is a ship at the coordinate
		if (defPlayer.myGrid.coordinateGrid[y][x] == defPlayer.myGrid.shipSymbol) {
			
			this.opponentsGrid.changeSymbol(x, y, this.opponentsGrid.hitSymbol);
			System.out.println(this.playerType + " hit one of " + defPlayer.playerType + " ships.");
			
			defPlayer.myGrid.changeSymbol(x, y, defPlayer.myGrid.hitSymbol);
			defPlayer.numOfShips--;
			
			Thread.sleep(2500);
			
			return true;
		// If there is nothing at the coordinate
		} else if (defPlayer.myGrid.coordinateGrid[y][x] == '~') {
			
			this.opponentsGrid.changeSymbol(x, y, this.opponentsGrid.missSymbol);
			System.out.println(this.playerType + " missed and didn't hit any of " + defPlayer.playerType + " ships");
			
			defPlayer.myGrid.changeSymbol(x, y, defPlayer.myGrid.missSymbol);
			Thread.sleep(2500);
			
			return false;
		// If  the player has already shot at the coordinate 
		} else if (defPlayer.myGrid.coordinateGrid[y][x] == defPlayer.myGrid.hitSymbol || defPlayer.myGrid.coordinateGrid[y][x] == defPlayer.myGrid.missSymbol){
			if (this.playerType == 'H') {
				System.out.println(this.playerType + " shot the same coordinate again..... Dummy");
				Thread.sleep(2500);
			}
			return false;
		}
		
		return false;

	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle pausing the program until the user enters a character. </p1>
	 */
	public void pauseForPlayer() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter any key to continue");
		input.next();
		
	}


}
