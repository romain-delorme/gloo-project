import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d6ddf743-3bc3-4758-9dce-cc05a09e7b0b")
public class Board {
    @objid ("c4e01ee6-012f-4deb-84a0-bdc02d4184a6")
    public List<Square> square = new ArrayList<Square> ();

    @objid ("025d45aa-025c-40c5-bdf5-3f3e0a16ffb4")
    public Player player;

}
