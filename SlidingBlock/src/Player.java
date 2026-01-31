import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b6eca3c9-88e9-426e-88cf-d96ea7721afc")
public class Player {
    @objid ("afcef17d-c138-4791-b97b-df8542707940")
    private Board board;

    @objid ("cfbd748e-8699-4c6a-bfe0-128eca3c5d14")
    private Block selectedBlock;

    @objid ("9479bdf0-f724-4134-bcfe-d19159702d95")
    public void select(final int row, final int column) {
    }

    @objid ("8b3a997f-d0e3-49d9-852e-0be9c5429645")
    public void action(final Direction direction) {
    }

}
