/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couldlibdemswin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Matthew
 */
public class CouldLibDemsWin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String csv = "election_data.csv";
        BufferedReader reader;
        String line = "";
        String split = ",";
        int lib = 0;
        int con = 0;
        int lab = 0;
        int SNP = 0;
        int ukip = 0;
        try {

            reader = new BufferedReader(new FileReader(csv));
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(split);
                //System.out.println(line);
                int[] var = new int[7];
                
                for (int i = 6; i < 13; i++)
                {
                    var[i-6]  = removePercent(data[i]);
                }
                double cFactor = 0.5;
                
                var[2] = var[2] + var[3]; // Labour fixed
                var[4] = (int) (var[4] + 0.63*cFactor*var[2] + 0.42*cFactor*var[0] + 0.75*cFactor*var[1]); // Liberal democrats
                var[0] = (int) (var[0]*1-0.63*cFactor); // Conservatives
                var[2] = (int) (var[2]* 1-0.42*cFactor); // Labour
                var[1] = (int) (var[1]* 1- 0.75*cFactor); // Greens
                
                if ( (var[4]> var[0])&&(var[4]> var[1])&&(var[4]> var[2])&&(var[4]> var[5])&&(var[4]> var[6])) // Liberal Dems
                {
                    System.out.println(data[1]);
                    lib++;
                    System.out.println("Liberal Democrats " + lib);
                }
                if ( (var[0]> var[4])&&(var[0]> var[1])&&(var[0]> var[2])&&(var[0]> var[5])&&(var[0]> var[6])) //Conservatives
                {
                    System.out.println(data[1]);
                    con++;
                    System.out.println("Conservatives " + con);
                }
                if ( (var[2]> var[0])&&(var[2]> var[1])&&(var[2]> var[4])&&(var[2]> var[5])&&(var[2]> var[6])) // Labour
                {
                    System.out.println(data[1]);
                    lab++;
                    System.out.println("Labour " + lab);
                }
                if ( (var[5]> var[0])&&(var[5]> var[1])&&(var[5]> var[2])&&(var[5]> var[4])&&(var[5]> var[6]))
                {
                    System.out.println(data[1]);
                    SNP++;
                    System.out.println("Scottish Naional Party " + SNP);
                }
                if ( (var[6]> var[0])&&(var[6]> var[1])&&(var[6]> var[2])&&(var[6]> var[4])&&(var[6]> var[5]))
                {
                    System.out.println(data[1]);
                    ukip++;
                    System.out.println("United Kingdom Independence Party " + ukip);
                }
            }
            System.out.println("Lib Dem Total " + lib);
            System.out.println("Con Total " + con );
            System.out.println("Lab Total " + lab );
            System.out.println("SNP Total " + SNP );
            System.out.println("UKIP Total " + ukip );

            
            System.out.println("This calculator does not include the seat "
                    + "of the Speaker or the seats in Northern Ireland");
        } catch (IOException e) {
            System.out.println("Error");
        }
        
    }
public static int parseNumber(String number) {
        String output = number.replace("\"", "");
        output = output.replace(",", "");
        return Integer.parseInt(output);
    }

    public static int removePercent(String perLine) {
       if (perLine.equalsIgnoreCase(""))
       {
           return 0;
       } 
        String[] line;
        line = perLine.split(" ");
        return parseNumber(line[0]);
    }
}
