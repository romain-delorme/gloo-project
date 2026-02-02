package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d6ddf743-3bc3-4758-9dce-cc05a09e7b0b")
public class Board {
    @objid ("fb9894d3-b87d-40eb-8c9e-96f6be3cd129")
    private int nbRows;

    @objid ("052fcb37-a57c-4d2d-8ab6-e506c3ea2dfd")
    private int nbColumns;

    @objid ("f041af64-059a-44b9-8afb-19b0ff68fc05")
    private Map<Position, AbstractSquare> squares = new HashMap<Position, AbstractSquare> ();

    @objid ("0e6abeae-5246-471f-9ed4-dc48b96d0573")
    private Map<Integer, Block> blocks = new HashMap<Integer, Block> ();

    @objid ("1e6e413d-51c2-4ac6-be58-d57091e10fef")
    public void getBloc(final int row, final int column) {
    }

    @objid ("4e5f7261-7d95-4f56-b6cc-c80d8459f39d")
    public void getCase(final Position p11) {
    }
    
    public String toString() {
    	String result = "";
    	
    	for(int i=0; i<this.nbRows; i++) {
    		result += "| ";
    		for(int j=0; j<this.nbColumns; j++) {
    			result += this.squares.get(new Position(i, j)).toString() + " | ";
    		}
    		result += "\n";
    	}
    	return result;
    }

}
