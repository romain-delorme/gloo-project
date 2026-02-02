package metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0208252f-c65a-46ed-91c2-f9d92a437def")
public class Exit extends AbstractSquare {
	public String toString() {
		return "S";
	}

	public Exit(Position position, Board board){
        super.setPosition(position);
        super.board = board;
        super.blocElementaire = null;
    }
}
