import control.*;
import metier.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import ihm.FenetreBloc;

public class GraphicalInterface implements Runnable {
	//#region create test boards

	public static Board createBoard(int[][] map){
		/*
		The "map" argument is a matrix where each element corresponds to a square (symbolized by the AbstractSqaure class).
		We can match map[i][j] with a Board object according to the following logic (note that map[i][j] contains the element at Position(i, j)):
			- 0 for the main block
			- 1+ for the other blocks (grouped by number)
			- -1 for empty squares
			- -2 for walls
			- -3 for exits
		The map is assumed to be non-empty, otherwise it will throw a null pointer dereference
		*/

		// get board size from matrix size
		int nbRows = map.length;
		int nbColumns = map[0].length; 

		// initialize Board attributes
		Board b = new Board(nbRows, nbColumns);
		Map<Position, AbstractSquare> squares = new HashMap<Position, AbstractSquare> ();
		Map<Integer, Block> blocks = new HashMap<Integer, Block> ();
		
		for(int i=0; i<nbRows; i++){
			for(int j=0; j<nbColumns; j++){
				Position currentPos = new Position(i, j);
				int val = map[i][j];
				switch(val){
					case -3: //exit
						squares.put(currentPos, new Exit(currentPos, b));
					break;

					case -2: //wall
						squares.put(currentPos, new Wall(currentPos, b));
					break;

					case -1: //empty square
						squares.put(currentPos, new Square(currentPos, b));
					break;

					default: //bloc élémentaire
						Block currentBlock = blocks.get(val);

						Square square = new Square(currentPos, b);
						BlocElementaire element = new BlocElementaire(square);

						if(currentBlock == null){
							List<BlocElementaire> elts = new ArrayList<BlocElementaire>();
							elts.add(element);
							currentBlock = new Block(val, elts);
							element.setBlock(currentBlock);
							blocks.put(val, currentBlock);
						}
						else{
							List<BlocElementaire> elts = currentBlock.getElements();
							elts.add(element);
							element.setBlock(currentBlock);
						}

						square.setBlocElementaire(element);
						squares.put(currentPos, square);
					break;
				}
			}
		}
		
		b.setSquares(squares);
		b.setBlocks(blocks);

		return b;
	}

	// first test board: 5x5 board with only single-square blocks
	static int[][][] testMaps = {
		{
			{-2, -2, -2, -2, -2},
			{-2, +0, +0, -1, -3},
			{-2, +2, +3, +4, -2},
			{-2, +1, -1, -1, -2},
			{-2, -2, -2, -2, -2}
		},
		{
			{-2, -2, -2, -2, -2},
			{-2, +0, +0, -1, -3},
			{-2, +0, -1, -1, -3},
			{-2, -1, -1, -1, -2},
			{-2, -2, -2, -2, -2}
		},
		{
			{-2, -2, -2, -2, -2},
			{-2, -1, +0, -1, -3},
			{-2, +0, +0, -1, -3},
			{-2, +0, +1, -1, -2},
			{-2, -1, -1, -1, -2},
			{-2, -1, -1, -1, -2},
			{-2, -1, -1, -1, -2},
			{-2, -2, -2, -2, -2}
		}
	};

	static int[][][] easyMaps = {

	};

	static int[][][] mediumMaps = {

	};

	static int[][][] hardMaps = {

	};
	
	//#endregion

	static int[][] selectedMap = testMaps[0]

	public static void main(String[] args) {
		int mapSet = -1;
		int mapId = -1;
		int[][][][] allMaps = {testMaps, easyMaps, mediumMaps, hardMaps};
		Scanner input = new Scanner(system.in);
		while(mapSet<0 || mapSet>3){
			System.out.println("Choisissez votre jeu de niveaux (0: test, 1: facile, 2: moyen, 3: difficile)");
			mapSet = input.nextInt();
			if (mapSet<0 || mapSet>3) 
				System.out.println("Veuillez choisir un nombre entre 1 et 3");
		}
		while(mapId<0 || mapId > allMaps[mapSet].length){
			System.out.println("Choisissez votre niveau (0-" + allMaps[mapSet].length + ")");
			mapId = input.nextInt();
			if (mapId<0 || mapId > allMaps[mapSet].length)
				System.out.println("Veuillez choisir un nombre entre 1 et 3");
		}

		selectedMap = allMaps[mapSet][mapId];

        SwingUtilities.invokeLater(new GraphicalInterface());

	}

	@Override
    public void run() {
        new FenetreBloc( new Controller(createBoard(selectedMap)) );
    }
}