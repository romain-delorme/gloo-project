package control;

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
        boolean canMove = false;
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

        for(int i=0; i<nbElements; i++){
            int currentRow = positions[i][0];
            int currentCol = positions[i][1];

            int newRow = currentRow + rowModifier;
            int newCol = currentCol + colModifier;
        }

        // TODO       
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

        BlocElementaire[] elementsArray = (BlocElementaire[]) currentBlock.getElements().toArray();
        for(int i=0; i<blockSize; i++){
            result[i][0] = elementsArray[i].getSquare().getPosition().getRow();
            result[i][1] = elementsArray[i].getSquare().getPosition().getColumn();
        }
        return result;
    }

    public Controller(Board b){
        this.board = b;
    }
}
