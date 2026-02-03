package metier;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6881afc6-b21b-44b0-928b-508edf3ca60a")
public class Block {
    @objid ("295dd7bb-4110-406a-b435-008d0014a9c0")
    private int number;

    @objid ("3fad99e1-31a9-4fa9-aefc-4256ef5ec9f0")
    private List<BlocElementaire> elements = new ArrayList<BlocElementaire> ();

    public String toString() {
    	return "" + this.number;
    }

    public Block(int number, List<BlocElementaire> elements){
        this.number = number;
        this.elements = elements;
    }

    public List<BlocElementaire> getElements(){
        return this.elements;
    }

    public int getNumber(){
        return this.number;
    }
}
