public class Elements {
    private int cle;
    private String valeur;


    //constructeurs sans argument
    public Elements(){
        this.cle=0;
        this.valeur="";
        /* constructeurs */
    }
    public Elements(int cle, String valeur){
        this.cle=cle;
        this.valeur=valeur;

    }

    //accesseurs
    public int getCle(){
        return cle;
    }
    public String getValeur(){
        return valeur;
    }

    //modificateurs
    public void setCle( int c){
        if(this.cle >= 0)
            this.cle = c;

    }
    public void setValeur( String v){
        this.valeur = v;

    }
    public String toString(){
        String resu;
        resu = "["+getValeur()+"] ==>"+getCle();
        return resu;

    }
}
