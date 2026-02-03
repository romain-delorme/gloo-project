package metier;


/**
 * Énumération des contenus des cases pour l'IHM du jeu SlidingBloc.
 * 
 * Cette énumération figure dans le paquetage metier pour pouvoir
 * etre utilisée par les classes de ce paquetage sans introduire
 * une dépendance vers les paquetages controle ou ihm.
 * 
 * @author Dominique Marcadet
 * @version 1.0
 */
public enum ContenuPlateau {
    CASE,
    MUR,
    SORTIE;
}
