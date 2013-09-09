package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    private class Struct{
        public String pav;
        public int kiekis;
        public double kaina;
        
        public static final String PAV = "Pavadinimas";
        public static final String KIEKIS = "Kiekis";
        public static final String KAINA = "Kaina";
        public static final String DELIMS = "[ ]+";
        
        Struct(String input){
            String[] gabalai = input.split(DELIMS);
            pav = gabalai[0];
            kiekis = Integer.valueOf(gabalai[1]);
            kaina = Double.valueOf(gabalai[2]);
        }

        @Override
        public String toString() {
            return String.format("%20s, %6d, %10f", pav, kiekis, kaina);
        }
    }
    
    public static String[] readLines(String filename){
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            return lines.toArray(new String[lines.size()]);
        }catch(Exception e){
            return new String[0];
        }
    }
    
    public List<List<Struct>> skaitymas(String failas){
        List<List<Struct>> ret = new ArrayList<>();
        String[] duomenai = readLines("duomenys.txt");
        List<Struct> tmp = new ArrayList<>();
        for(int i = 0; i < duomenai.length; i++){
            
        }
        return ret;
    }
    
    public void spausdinti(Struct[] duomenai, String prefix){
        System.out.print(antraste());
        for(int i = 0; i < duomenai.length; i++)
            System.out.println(prefix + i + " " + duomenai[i].toString());
    }
    
    public void otherMain(){
        int procesuSkaicius = 3;
        List<Struct[]> sagsag = new ArrayList<>();
        final Struct[] duomenys = skaitymas("duomenys.txt");
        String[] procesaiTmp = new String[procesuSkaicius];
        for(int i = 0; i < procesuSkaicius; i++){
            procesaiTmp[i] = "Procesas" + i + " ";
        }
        final String[] procesuPav = procesaiTmp;
        List<Thread> procesai = new ArrayList(procesuSkaicius);
        antraste();
        spausdinti(duomenys, "");
        System.out.print("\n\n\n");
        for(int i = 0; i < procesuSkaicius; i++){
            final int nr = i;
            procesai.add(new Thread(new Runnable() {

                @Override
                public void run() {
                    spausdinti(duomenys, procesuPav[nr]);
                }
            }));
        }
        for(Thread t : procesai){
            t.start();
        }
    }
    
    public static String antraste(){
            return String.format("%22s, %5s, %10s\n", Struct.PAV, Struct.KIEKIS, Struct.KAINA);
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.otherMain();
    }
}