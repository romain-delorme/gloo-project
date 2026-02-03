import control.*;
import metier.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class UserInterface {
	/*
	Comment créer un plateau:

	- on commence par définir toutes les positions
	- ensuite, on crée les cases qu'on veut (wall, exit ou square)
	- on crée ensuite des blocs élémentaires qu'on met dans des squares
	
	*/

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

	static int[][] map1 = {
		{-2, -2, -2, -2, -2},
		{-2, +0, +0, -1, -3},
		{-2, +2, +3, +4, -2},
		{-2, +1, -1, -1, -2},
		{-2, -2, -2, -2, -2}
	};
	
	//#endregion

	public static void main(String[] args) {
		Board test1 = createBoard(map1);
		System.out.println(test1);
		boolean not_finished = true;
		while (not_finished==true) {
			not_finished = playTour(map1);
			Board updated_board = createBoard(map1);
			System.out.println(updated_board);
		}
	}

	// Euh premier test potentiel :
	public static boolean playTour(int[][] map) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Quelle pièce voulez-vous bouger (indiquer le numéro)?");
		
		// Validation du type des inputs
		if (!scan.hasNextInt()) {
			System.out.println("Ce n'est pas un nombre");
			return true;
		}

		int rep = scan.nextInt();


		
		if (rep == -2 || rep == -3) {
			System.out.println("Qu'est ce que vous essayez de faire là?");
			return true;
		}

		// First we get all the elements from our block, we will use an array list because we don't know with this
		int nbRows = map.length;
		int nbColumns = map[0].length; 
		ArrayList<Position> indices = new ArrayList<>();
		for (int i=0; i<nbRows; i++) {
			for (int j=0; j<nbColumns; j++) {
				if (map[i][j]==rep) {
					indices.add(new Position(i, j));
				}
			}
		}

		if (indices.size()==0) {
			System.out.println("Ce bloc n'existe pas");
			return true;
		}

		System.out.println("Dans quelle direction?");
		String dir = scan.next();

		


		ArrayList<Position> new_position = new ArrayList<>(); // On va profiter du passable dans la boucle pour avoir la liste des nouvelles positions si possible
		// Now we iterate over all the subblocks to know if you can move
		for (int i=0; i<indices.size(); i++) {
			Position pos = indices.get(i);
			int a = pos.getRow();
			int b = pos.getColumn();

			// Placeholder for new position
			int new_r;
			int new_c;

			switch (dir) { // Here we just want to get the position of the projected new
				case "UP":
                    new_r = a-1;
					new_c = b;
				break;
				case "DOWN":
					new_r = a+1;
					new_c = b;
				break;
				case "LEFT":
					new_r = a;
					new_c = b-1;
				break;
				case "RIGHT":
					new_r = a;
					new_c = b+1;
				break;
				default: // La direction n'existe pas
					System.out.println("Le déplacement n'existe pas");
					return true;
			}
	
			if (map[new_r][new_c]!=-1 && map[new_r][new_c]!=rep && map[new_r][new_c]!=-3) { // Si c'est pas la piece 0 on peut pas move dans le S d'où le check
				System.out.println("Le déplacement n'est pas possible");
				return true;
			}
			else if (map[new_r][new_c]==-3 && rep!=0) {
				System.out.println("Le déplacement n'est pas possible");
				return true;
			}
			else { // si on vient ici le move est possible et en plus on connait la position
				new_position.add(new Position(new_r, new_c));
			}
		}

		/*  Si on sort ici ça veut dire que toutes les positions sont possibles
		Pour mettre à jour la map on procède en deux étapes:
		-> D'abord on remplace tout les anciens emplacements (indices) par du vide
		-> Puis on remplace les nouveaux emplacements (new_position) par les blocs
		*/
		
		// Pour regarder la condition de sortie, il suffit de regarder si à la fois tout les blocs peuvent bouger et que parmis les prochains blocs il y a un bloc de sortie
		// ici comme on est sortis il suffit de regarder s'il y a un -3

		for (int i=0; i<new_position.size();i++) {
			Position pos = new_position.get(i);
			int a = pos.getRow();
			int b = pos.getColumn();
			if (map[a][b]==-3) {
				System.out.println("Finito Pipo");
				return false;
			}
		}



		for (int i=0; i<indices.size(); i++) {
			Position pos = indices.get(i);
			int a = pos.getRow();
			int b = pos.getColumn();
			map[a][b] = -1;
		}
		for (int i=0; i<new_position.size(); i++) {
			Position pos = new_position.get(i);
			int a = pos.getRow();
			int b = pos.getColumn();
			map[a][b] = rep;
		}
		return true;
	}
}