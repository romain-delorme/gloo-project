package metier;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

//@objid ("a9b29eca-7f72-4b01-a41d-f6b4ecf1b68f")
@objid ("a9b29eca-7f72-4b01-a41d-f6b4ecf1b68f")
public class Position {
//@objid ("fde5f6be-b6fb-4402-a60d-067243b894f5")
    @objid ("fde5f6be-b6fb-4402-a60d-067243b894f5")
    private int row;

//@objid ("ae678b6e-573f-4c98-bebb-eb1d2fdce7b2")
    @objid ("ae678b6e-573f-4c98-bebb-eb1d2fdce7b2")
    private int column;

    @objid ("5ab8cafa-2ba5-4e38-b9b1-b2a8ed175bc0")
    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @objid ("d08e3913-cc9a-4bc7-a772-92a253b1a2a3")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return column == other.column && row == other.row;
    }

    @objid ("a175ff6b-2dfe-4866-94d9-83396c48dc31")
    public Position(int row, int col) {
    	this.column = col;
    	this.row = row;
    }

}
