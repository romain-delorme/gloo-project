package metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4ff4c585-2998-4db7-81c6-c8e96e453d44")
public abstract class AbstractSquare {
    @objid ("3dabf6ce-4fe5-4b84-a664-a42c5c7c2aac")
    private Position position;

    @objid ("b817c817-3781-41b8-8d61-a50101862faf")
    protected Board board;

    @objid ("2219bf08-44ca-4c69-89b4-c8cae4e89eec")
    protected BlocElementaire blocElementaire;
    
    public void setPosition(Position position) {
    	this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    public BlocElementaire getBlocElementaire(){
        return this.blocElementaire;
    }
}
