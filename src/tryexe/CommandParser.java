/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryexe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author test
 */
public class CommandParser {

    public static String[] classify(String imagePath) {
        Runtime rt = Runtime.getRuntime();
        // String commands0 = {''

        String commands = "wndchrm classify src/train.fit " + imagePath;
        Process proc;
        try {
            proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String s = null;
            String fullText = "";
            while ((s = stdInput.readLine()) != null) {
                fullText += s + "\n";
            }
            int numOfClasses = GalleryFrame.artists.size();
            //System.out.println(""+fullText);
            String[] ss = fullText.split("----------");
            String desired = ss[5];
            //System.out.println(""+desired);
            desired = desired.split(imagePath)[1];
            // System.out.println("2: "+desired);
            String[] desiredSplit = desired.split("\t");
            double[] clasfDecimals = new double[numOfClasses];
            DecimalFormat df = new DecimalFormat("#0.00");
            String[] percents = new String[numOfClasses];
            for (int i = 0; i < numOfClasses; i++) {
                double dec = Double.parseDouble(desiredSplit[i + 2]);
                double per = dec * 100;
                String result = GalleryFrame.artists.get(i)+":\t\t"+df.format(per)+"%";
                percents[i] = result;
                
            }
            

            return percents;

// read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
        } catch (IOException ex) {
            System.out.println("Couldn't run the commands!");
            Logger.getLogger(CommandParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
