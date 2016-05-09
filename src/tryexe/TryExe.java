/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryexe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author test
 */
public class TryExe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //convertToTiff();
       //CommandParser.runCommand();
       //WorkspaceFrame gf = new WorkspaceFrame();
       testImageIO();

    
        
    }
    static void convertToTiff() {
        String filename = "src/images/mynewtiff";
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/images/jpegs/happyface.jpeg"));
        } catch (IOException e) {
        }
        boolean success = Utility.saveTiff(filename, img);
        System.out.println("success = " + success);
    }
        public static void testImageIO() {
        try {
            File readFile = new File("src/test.tiff");
            BufferedImage img = ImageIO.read(readFile);
            File writeFile = new File("src/test.png");
            ImageIO.write(img, "png", writeFile);
        } catch (IOException ex) {
            System.out.println("IMAGE WRITE FAILED: "+ex);
        }
    }
    



}
