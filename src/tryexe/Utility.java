/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryexe;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author test
 */
public class Utility {
    
//    static boolean saveImageFolder()
    
    protected static boolean saveTiff(String filename, BufferedImage image) {

        File tiffFile = new File(filename);
        ImageOutputStream ios = null;
        ImageWriter writer = null;

        try {

            // find an appropriate writer
            Iterator it = ImageIO.getImageWritersByFormatName("TIF");
            if (it.hasNext()) {
                writer = (ImageWriter) it.next();
            } else {
                return false;
            }

            // setup writer
            ios = ImageIO.createImageOutputStream(tiffFile);
            writer.setOutput(ios);
            TIFFImageWriteParam writeParam = new TIFFImageWriteParam(Locale.ENGLISH);
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //  see writeParam.getCompressionTypes() for available compression type strings
            writeParam.setCompressionType("PackBits");

            // convert to an IIOImage
            IIOImage iioImage = new IIOImage(image, null, null);
            // write it!
            writer.write(null, iioImage, writeParam);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }    
}
