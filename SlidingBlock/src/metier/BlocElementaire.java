package metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("812a3bfd-9b2b-4516-bb74-fa322bbac424")
public class BlocElementaire {
    @objid ("14dfbc01-5d50-4bda-b77f-b87461444a81")
    private AbstractSquare square;

    @objid ("92a5a81b-8e90-4d9c-9ae6-5b37eb1af865")
    private Block block;
    
    public String toString() {
    	return this.block.toString();
    }
}
