package control;

import metier.*;

/**
 * Controleur bouchon pour tester l'IHM du jeu SlidingBloc.
 *
 * Le plateau affiché est celui du TD de conception du modèle métier du projet.
 * Si on clique sur le bloc bleu, il est sélectionné, sinon c'est le bloc jaune qui l'est.
 * Les flèches ne sont prises en compte que si le bloc jaune est sélectionné.
 * La flèche GAUCHE est ignorée.
 * 
 * @author Dominique Marcadet
 * @version 1.0
 */
public class ControleurBouchon implements IControleur {
	
	private boolean fin = false;
    @Override
    public boolean jeuTermine() {
        return fin;
    }

    @Override
    public int getNbLignes() {
        return 5;
    }

    @Override
    public int getNbColonnes() {
        return 4;
    }

    @Override
    public ContenuPlateau getContenu(int l, int c) {
        switch(l) {
        case 0:
        case 4:
        	return ContenuPlateau.MUR;
        case 1:
        case 2:
        case 3:
        	switch (c) {
        	case 0:
            	return ContenuPlateau.MUR;
        	case 1:
        	case 2:
            	return ContenuPlateau.CASE;
        	case 3:
            	return l == 3 ? ContenuPlateau.MUR : ContenuPlateau.SORTIE;
        	}
        }
        return null;
    }

    @Override
    public int getNbBlocs() {
        return 2;
    }

    private final int[][][] positionsPossiblesBloc0 = {{{1, 2}, {2, 2}}, {{2, 2}, {3, 2}}};
    private int positionBloc0 = 1;
    private final int[][] positionBloc1 = {{1, 1}};

    @Override
    public int[][] getPositionsBloc(int numero) {
    	return numero == 0 ? positionsPossiblesBloc0[positionBloc0] : positionBloc1;
    }

    private int blocSelectionne = 0;

    @Override
    public int getNumeroBlocSelectionne() {
        return blocSelectionne;
    }

    @Override
    public void selection(int ligne, int colonne) {
    	if ((ligne == 1) && (colonne == 1)) blocSelectionne = 1;
    	else                                blocSelectionne = 0;
    }

    @Override
    public void action(Direction direction) {
    	if (blocSelectionne == 1) return;
    	switch (direction) {
    	case UP:
    		if (positionBloc0 == 1) positionBloc0 = 0;
    		break;
    	case DOWN:
    		if (positionBloc0 == 0) positionBloc0 = 1;
    		break;
    	case RIGHT:
    		if (positionBloc0 == 0) fin = true;
    		break;
		case LEFT:
			break;
    	}
    }

}
