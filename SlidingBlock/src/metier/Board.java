package metier;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d6ddf743-3bc3-4758-9dce-cc05a09e7b0b")
public class Board {
    @objid ("fb9894d3-b87d-40eb-8c9e-96f6be3cd129")
    private int nbRows;

    @objid ("052fcb37-a57c-4d2d-8ab6-e506c3ea2dfd")
    private int nbColumns;

    @objid ("f041af64-059a-44b9-8afb-19b0ff68fc05")
    private List<AbstractSquare> squares = new ArrayList<AbstractSquare> ();

    @objid ("0e6abeae-5246-471f-9ed4-dc48b96d0573")
    private List<Block> blocks = new ArrayList<Block> ();

}
