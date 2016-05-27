/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hangmanServer;

/**
 *
 * @author Ahad
 */

import java.io.*;
import java.util.*;

public class Word {

    public static String wordSelect(){

        String s = null;
        try
        {
            String fp = new java.io.File( "Dictionary.txt" ).getCanonicalPath();
            File f = new File(fp);
            FileReader fr = new FileReader(f);
            BufferedReader br =new BufferedReader(fr);

            Random rand = new Random();
            int num = rand.nextInt(25143);

            while((s = br.readLine()) != null)
            {
                num--;
                if(num == 0) break;
            }
         }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        return s;
    }



}

