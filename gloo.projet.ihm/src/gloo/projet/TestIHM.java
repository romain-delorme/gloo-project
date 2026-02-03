package gloo.projet;

import javax.swing.SwingUtilities;
import gloo.projet.controle.ControleurBouchon;
import gloo.projet.ihm.FenetreBloc;

/**
 * Application de test de l'IHM du jeu SlidingBloc en utilisant le controleur bouchon.
 * 
 * @author Dominique Marcadet
 * @version 1.0
 */
public class TestIHM implements Runnable {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater( new TestIHM() );
    }

    @Override
    public void run() {
        new FenetreBloc( new ControleurBouchon() );
    }
}
