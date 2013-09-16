package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//1. atsitiktini kieki
//2. tokia, kokia startuoja

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
    
    private abstract class SuperRunnable implements Runnable{
    	private int nr;
    	SuperRunnable(int nr){
    		this.nr = nr;
    	}
    	
    	public int getNr(){
    		return nr;
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
    
    public ArrayList<ArrayList<Struct>> skaitymas(String failas){
    	ArrayList<ArrayList<Struct>> ret = new ArrayList<>();
        String[] duomenai = readLines(failas);
        ArrayList<Struct> tmp = new ArrayList<>();
        for(int i = 0; i < duomenai.length; i++){
            if("".equals(duomenai[i])){
            	ret.add(tmp);
            	tmp = new ArrayList<>();
            }else{
            	tmp.add(new Struct(duomenai[i]));
            }
            
        }
        return ret;
    }
    
    public void spausdinti(ArrayList<Struct> duomenai, String prefix){
        System.out.println();
        for(int i = 0; i < duomenai.size(); i++)
            System.out.println(prefix + i + " " + duomenai.get(i).toString());
    }
    
    public void otherMain(){
    	final ArrayList<ArrayList<Struct>> duomenys = skaitymas("duomenys.txt");
    	final int procesuSkaicius = duomenys.size();

        List<Thread> procesai = new ArrayList<>(procesuSkaicius);
        for(int i = 0; i < procesuSkaicius; i++)
        	spausdinti(duomenys.get(i), "Procesas " + i + " ");
        System.out.print("\n\n\n");
        for(int i = 0; i < procesuSkaicius; i++){
            procesai.add(new Thread(new SuperRunnable(i) {

                @Override
                public void run() {
                    spausdinti(duomenys.get(getNr()), "Procesas " + getNr() + " ");
                }
            }));
        }
        for(Thread t : procesai){
            t.start();
        }
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.otherMain();
    }
}