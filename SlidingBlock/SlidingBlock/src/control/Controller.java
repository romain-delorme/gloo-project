package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import metier.Block;
import metier.Board;
import metier.Direction;

@objid ("352b1156-5fea-47f6-8c72-3232ad92a044")
public class Controller {
    @objid ("f7a9764b-ce8c-410d-9c0f-f34286aa9e6a")
    private Block selectedBlock;

    @objid ("0d29d4a6-91bf-4b9c-9bbb-7cd8b1deb43d")
    private Board board;

    @objid ("5155981e-5b4a-4ae2-a8ef-d93181280a14")
    public void selection(final int row, final int column) {
    }

    @objid ("ae7a1763-049d-4014-aecf-9cb5da605fd5")
    public void action(final Direction direction) {
    }

}
