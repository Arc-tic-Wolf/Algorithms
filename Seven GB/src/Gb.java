
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 18101617
 */
public class Gb {
    public static void main(String[] args){
        String fileName = "seven.txt";
        long sum=0;
        try { 
            BufferedWriter out = new BufferedWriter( 
                          new FileWriter(fileName,true)); 
            Random rand = new Random();
            
            for(;;){
            int s=rand.nextInt(255);
            
            
            out.write((char)s); 
            if(sum==200292998){
                
                
                out.close(); 
                break;
            }
            sum++;
            }
        } 
        catch (Exception e) { 
            System.out.println("Out"); 
        } 
    }
    
}
