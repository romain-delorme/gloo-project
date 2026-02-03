package control;

import java.util.Map;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import metier.*;

@objid ("352b1156-5fea-47f6-8c72-3232ad92a044")
public class Controller implements IControleur{
    private boolean fin = false;

    @objid ("f7a9764b-ce8c-410d-9c0f-f34286aa9e6a")
    private Block selectedBlock;

    @objid ("0d29d4a6-91bf-4b9c-9bbb-7cd8b1deb43d")
    private Board board;

    @objid ("5155981e-5b4a-4ae2-a8ef-d93181280a14")
    public void selection(final int ligne, final int colonne) {
        this.selectedBlock = this.board.getBloc(ligne, colonne);
    }

    @objid ("ae7a1763-049d-4014-aecf-9cb5da605fd5")
    public void action(final Direction direction) {
        boolean canMove = true;
        boolean isWinningMove = false;
        int rowModifier = 0;
        int colModifier = 0;

        switch(direction){
            case UP:
                rowModifier = -1;
            break;
            
            case DOWN:
                rowModifier = 1;
            break;
            
            case LEFT:
                colModifier = -1;
            break;

            case RIGHT:
                colModifier = 1;
            break;
        }

        int[][] positions = this.getPositionsBloc(selectedBlock.getNumber());
        int nbElements = positions.length;
        Map<Position, AbstractSquare> squares = this.board.getSquares();

        // first, we iterate over every element from block to check the target square
        for(int i=0; i<nbElements; i++){
            int currentRow = positions[i][0];
            int currentCol = positions[i][1];

            int newRow = currentRow + rowModifier;
            int newCol = currentCol + colModifier;

            AbstractSquare target = squares.get(new Position(newRow, newCol));

            if (target instanceof Wall) canMove = false;
            else if (target instanceof Exit) isWinningMove = true;
            else if ((target.getBlocElementaire() != null) && (target.getBlocElementaire().getBlock().getNumber() != selectedBlock.getNumber())) canMove = false;
        }

        // then, if the move is allowed, we update the board
        if(canMove){
            int i=0;
            // when going right or down, we need to iterate backwards to make sure that swaps are made in the right order
            if (rowModifier > 0 || colModifier > 0) i=nbElements-1

            for(; i>=0 && i<nbElements; i -= (rowModifier + colModifier)){
                int currentRow = positions[i][0];
                int currentCol = positions[i][1];

                int newRow = currentRow + rowModifier;
                int newCol = currentCol + colModifier;

                Position currentPos = new Position(currentRow, currentCol);
                Position newPos = new Position(newRow, newCol);

                

                AbstractSquare source = squares.get(currentPos);
                AbstractSquare target = squares.get(newPos);

                target.setPosition(currentPos);
                // source.setPosition(newPos);
                squares.put(newPos, source);
                // squares.put(currentPos, target);
            }
            this.fin = isWinningMove;
        }
    }

    public int getNumeroBlocSelectionne(){
        if (this.selectedBlock == null) return -1;
        return this.selectedBlock.getNumber();
    }

    public boolean jeuTermine(){
        return fin;
    }

    public int getNbLignes(){
        return this.board.getNbRows();
    }

    public int getNbColonnes(){
        return this.board.getNbColumns();
    }

    public ContenuPlateau getContenu(int ligne, int colonne){
        AbstractSquare square = this.board.getSquares().get(new Position(ligne, colonne));
        if (square instanceof Square) return ContenuPlateau.CASE;
        if (square instanceof Wall) return ContenuPlateau.MUR;
        if (square instanceof Exit) return ContenuPlateau.SORTIE;
        return null;
    }

    public int getNbBlocs(){
        return this.board.getBlocks().keySet().size();
    }

    public int[][] getPositionsBloc(int numero){
        Block currentBlock = this.board.getBlocks().get(numero);
        int blockSize = currentBlock.getElements().size();
        int[][] result = new int[blockSize][2];

        Object[] elementsArray = currentBlock.getElements().toArray();
        // can't cast directly to BlocElementaire[] for some reason
        for(int i=0; i<blockSize; i++){
        	BlocElementaire elt = (BlocElementaire) elementsArray[i];
            result[i][0] = elt.getSquare().getPosition().getRow();
            result[i][1] = elt.getSquare().getPosition().getColumn();
        }
        return result;
    }

    public Controller(Board b){
        this.board = b;
    }
}
