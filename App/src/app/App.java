package app;

public class App {
    private class Struct{
        public String pav;
        public int kiekis;
        public double kaina;
        
        public static final String PAV = "Pavadinimas";
        public static final String KIEKIS = "Kiekis";
        public static final String KAINA = "Kaina";

        @Override
        public String toString() {
            return String.format("20%s, 5%d, 10%f", pav, kiekis, kaina);
        }
        
        public String antraste(){
            return String.format("20%s, 5%s, 10%s", PAV, KIEKIS, KAINA);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
}