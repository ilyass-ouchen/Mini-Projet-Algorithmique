
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TabElements {
    private Elements[] tabElements;
    private int taille;


    public TabElements(int taille) {

        this.taille = taille;
        tabElements = new Elements[taille];

    }

    public int getTaille() {

        return taille;

    }

    public Elements[] getTabElements() {

        return tabElements;

    }
    public Elements getElementI(int position){
        return tabElements[position];

    }
    public void setTaille(int t) {

        this.taille = t;

    }
    public void setElementI(Elements element, int position){
        tabElements[position] = element;
    }
    public void setTableau(Elements[] t){
        for (int i=0; i<t.length; i++){
            setElementI(t[i],i);
        }
    }


    public String toString() {
        String result = "";

        for (int i = 0; i < this.getTaille(); i++){
            result = result + "\n[" +this.tabElements[i].getCle() + "] ==>[" + this.tabElements[i].getValeur() +"]";
        }
        return result;
    }


    public void remplirAleatoire(int tailleDuValeur , int tailleTab){

        Random aleatoire = new Random();
        Elements[] tab = new Elements[tailleTab];


        String lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder(tailleDuValeur);

        for (int i = 0; i < tailleTab ; i++ ) {
            tab[i] = new Elements();
            // on travaille dans notre exemple sur un intervalle de borne sup égale à 10000 pour éviter les fuites de mémoire
            tab[i].setCle(aleatoire.nextInt(10000000));

            str  = new StringBuilder("");

            for (int j = 0;j < tailleDuValeur ;j++ ) {
                int indice = (int)(lettres.length() * Math.random());
                str.append(lettres.charAt(indice));
                tab[i].setValeur(str.toString());

            }
        }
        setTableau(tab);

    }

    public void remplirUser(){
        Scanner sc = new Scanner(System.in);


        for (int i = 0; i < getTaille(); i++) {
            System.out.println("donnez la cle et la valeur de l element " + (i + 1));

            setElementI(new Elements(sc.nextInt(), sc.nextLine()),i);
        }
    }

    public void triBulle() {
        int indiceFin = getTaille() -1;
        boolean echange = true;// on crée une variable booléen echange pour voir si on fait l'échange ou non

        Elements tmp;//variable tamporaire qui sert à échanger les éléments de tableau
        while (echange) {
            echange = false;
            for (int i = 1; i <= indiceFin; i++) {
                if (getElementI(i-1).getCle() > getElementI(i).getCle()) { // si l'élément courant est plus petit que son prédécesseur on échange les deux éléments
                    tmp = getElementI(i);
                    setElementI( getElementI(i-1),i);
                    setElementI( tmp,i-1);
                    echange = true;
                }
            }
            indiceFin--; // on continue le tri avec le tableau privé de son dernière élément
        }

    }
    public static void triRapide(Elements T[]) {
        int longueur = T.length;
        triRapide(T, 0, longueur - 1);
    }

    private static int partition(Elements T[], int deb, int fin) {
        // dans la fonction de partition, on commence par choisir le pivot
        int compt = deb;
            Elements pivot = T[deb];
            Elements tmp;
            // la boucle for sert à classer les éléments plus petit que le pivot à gauche et les éléments plus grand que les pivot à droite
        for (int i = deb + 1; i <= fin; i++) {

            if (T[i].getCle() < pivot.getCle()) { // si l'élément d'indice i est plus petit que le pivot, on le met à gauche
                compt++;
                tmp = T[compt];
                T[compt] = T[i];
                T[i] = tmp;
            }
        }
            tmp = T[deb];
            T[deb] = T[compt];
            T[compt] = tmp;
        return (compt);
    }

    private static void triRapide(Elements T[], int deb, int fin) {
        if (deb < fin) {
            int positionPivot = partition(T, deb, fin); // on choisit la positiond du pivot à l'aide de la fonction partition
            triRapide(T, deb, positionPivot - 1);  // on fait un appel récursif de la fonction triRapide sur les éléments qui se trouve à gauche du pivot
            triRapide(T, positionPivot + 1, fin); // on fait le même appel récursif sur les élements qi se situe sur la partie droite
        }
    }

    private int max_tableau() { // la fonction max_tableau sert à calculer et retourner le max d'un tableau d'éléments
        int max = tabElements[0].getCle();
        for (int i = 1; i < tabElements.length; i++) {
            if (tabElements[i].getCle() > max) {
                max = tabElements[i].getCle();
            }
        }
        return max;
    }
    public void triDenombrement() {
        Elements[] tab = new Elements[getTaille()];
        int taillemax = max_tableau();
        int[] tableauCle = new int[taillemax + 1];
        int position;
        //Initialisation du tableau des clés
        for (int i = 0; i < tableauCle.length; i++) {
            tableauCle[i] = 0;
        }
        //Remplissage du tableau des clés à partir de tableau qu'on veut trier
        for (int i = 0; i < getTaille(); i++) {
            tableauCle[getElementI(i).getCle()]++;
        }
        for (int i = 1; i < tableauCle.length; i++) {
            tableauCle[i] += tableauCle[i - 1];
        }

        //recopie de tableau final
        for (int i = 0; i < getTaille(); i++) {
            position = tableauCle[getElementI(i).getCle()];
            tab[position - 1] = getElementI(i);
            tableauCle[getElementI(i).getCle()]--;
        }
        setTableau(tab);
    }
    private static void calculation() throws InterruptedException {

        //Sleep 2 seconds
        TimeUnit.SECONDS.sleep(2);

    }
    public static void main(String[] args) throws InterruptedException {


        Scanner entree = new Scanner(System.in);
        System.out.println("donnez la taille du tableau");
        int taille = entree.nextInt();

        TabElements elts = new TabElements(taille);
        System.out.println("-------------------------");
        System.out.println("le tableau avant le tri\n");
        System.out.println("-------------------------");
        elts.remplirAleatoire(3,taille);

        System.out.println(elts);
        System.out.println("-------------------------\n");
        long tempsDebut = System.currentTimeMillis();
        triRapide(elts.getTabElements());
        long tempsFin = System.currentTimeMillis();
        float seconds = (tempsFin - tempsDebut) / 1000F;


        System.out.println("le tableau apres le tri\n");
        System.out.println("-------------------------");
        System.out.println(elts);

        System.out.println("Opération effectuée en: "+ Float.toString(seconds) + " secondes.");
    }

}
