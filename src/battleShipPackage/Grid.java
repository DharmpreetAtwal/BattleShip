package battleShipPackage;

/**
 * <h2> Class Description: </h2>
 * <p1> This class handles the attributes each grid will
 *  have and what operations they will be able to perform.</p1>
 * @author Dharmpreet Atwal
 *
 */
public class Grid {
	char[][] coordinateGrid = new char[12][12];
	char shipSymbol;
	char hitSymbol;
	char missSymbol;
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1>This constructor will handle the creation of a grid object. </p1>
	 * @param shipSymbol
	 * @param hitSymbol
	 * @param missSymbol
	 */
	public Grid(char shipSymbol, char hitSymbol, char missSymbol) {
		
		this.shipSymbol = shipSymbol;
		this.hitSymbol = hitSymbol;
		this.missSymbol = missSymbol;
		
	}

	/**
	
	 * <h2> Method Description: </h2>
	 * <p1>This method will loop through coordinateGrid[][] and 
	 * initialize the starting layout of the grid</p1>
	 **/
	public void intitializeGrid () {
		int countX = 49;
		int countY = 49;
		
		// Uses ASCII to initialize the x and y axis 
		for (int i=0; i<this.coordinateGrid.length - 1; i++) {
              for (int j=0; j<this.coordinateGrid.length - 1; j++) { 
            	  // If coordinate on y axis
            	  if (j == 0 && i != 0) {
            		  this.coordinateGrid[i][0]= (char)(countY);
            		  countY++;
            	  // If coordinate on x axis
            	  } else if (i == 0 && j != 0) {
            		  this.coordinateGrid[0][j] = (char)(countX);
            		  countX++;
            	  // If not on axis
            	  } else {
            		  this.coordinateGrid[i][j] = '~';
            	  } 
              }
		}		
		
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle printing out the grid as a String </p1>
	 */
	public String toString() {
		String gridTxt="";
	
		 for (int i=0; i<this.coordinateGrid.length; i++) {
	            for(int j=0; j<this.coordinateGrid.length; j++) {
	                    gridTxt += (this.coordinateGrid[i][j] + "   ");
	            }
	            gridTxt += " \n";
	            gridTxt += " \n";
		 }
	    
	     return gridTxt;
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle changing the symbol of specific coordinate on the grid </p1>
	 * @param x The x coordinate of the symbol that is to be changed coordinateGrid[ ] [x]
	 * @param y The y coordinate of the symbol that is to be changed coordinateGrid[y] []
	 * @param symbl The symbol that is going to be added to the (x, y) coordinate on the grid
	 * @return True if the symbol is already at the coordinate or False if the symbol isn't at the coordinate 
	 */
	public boolean changeSymbol(int x, int y, char symbl) {
	
		if (this.coordinateGrid[y][x]==symbl) {
			return true;
		} else {
			this.coordinateGrid[y][x] = symbl;
			return false;
		}
		
	}
	
	/**
	 * <h2> Method Description: </h2>
	 * <p1> This method will handle the check for validating a set of coordinates. </p1>
	 * @param x The x coordinate that is being validated
	 * @param y The y coordinate that is being validated
	 * @return True if the coordinates are valid, False if the coordinates are not valid
	 */
	public boolean validCoordinates(int x, int y) {
		
		// Checks if the x and y coordinate is between 1 - 10
		if ((y<11 && y>0) && (x>0 && x<11)) {
			return false;
		} else {
			System.out.println("Invaid coordinate set, please enter a valid coordinate set.");
			return true;
		}
			
	}
	
}
