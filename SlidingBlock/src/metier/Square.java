package metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("08490f4e-9565-42b3-ae53-262d6e86ef88")
public class Square extends AbstractSquare {
    @objid ("e335ab87-563d-4f0e-90c3-6dc0d638e3d3")
    public void getBloc(final int row, final int column) {
    }

    public String toString() {
        // if the block associated with a square is null, this means that said square is empty
        if (this.blocElementaire) return this.blocElementaire.toString();
    	return "V";
    }

    public Square(Position position, Board board, BlocElementaire blocElementaire){
        super.position = position;
        super.board = board;
        super.blocElementaire = blocElementaire;
    }
    public Square(Position position, Board board){
        super.position = position;
        super.board = board;
        super.blocElementaire = null;
    }

    public void setBlocElementaire(BlocElementaire blocElementaire){
        super.blocElementaire = blocElementaire;
    }
}
