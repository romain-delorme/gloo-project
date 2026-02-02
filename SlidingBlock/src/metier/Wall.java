package metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("92387254-df1a-470f-a6b0-388d77e80129")
public class Wall extends AbstractSquare {
    public String toString(){
        return "M";
    }

    public Wall(Position position, Board board){
        super.position = position;
        super.board = board;
        super.blocElementaire = null;
    }
}
