/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryexe;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author test
 */
public class WorkspaceFrame extends javax.swing.JFrame {

    public static List<String> artists = Arrays.asList("Cezanne", "Gaugin", "Seurat", "Van Gogh");
    BufferedImage currImg;
    boolean loading = false;

    /**
     * Creates new form GalleryFrame
     */
    public WorkspaceFrame() {
        initComponents();
        setSize(500, 500);
        setVisible(true);
        initialize();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classifyButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        addFolderMI = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        classifyButton.setText("Classify New Image");
        classifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classifyButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        addFolderMI.setText("Add Folder");
        addFolderMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFolderMIActionPerformed(evt);
            }
        });
        jMenu1.add(addFolderMI);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(classifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(classifyButton)
                .addGap(0, 254, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addFolderMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFolderMIActionPerformed
        addArtist();
    }//GEN-LAST:event_addFolderMIActionPerformed

    private void classifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classifyButtonActionPerformed
        classifyNewImage();
    }//GEN-LAST:event_classifyButtonActionPerformed

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        if (loading) {
            System.out.println("entered paint method");
            g.drawImage(loadingIMG, 100, 100, this);
            System.out.println("Paint: paintedLoadingImg");
        } else if (currImg != null) {
            g.drawImage(currImg, 100, 100, this);
            if (results != null) {
                DecimalFormat df = new DecimalFormat("#0.00");
                Arrays.sort(results);
                for (int p = 0; p < results.length; p++) {
                    String catg = results[p].getCategory();
                    g.drawString(catg, 250, 100 + 30 * p);
                    String per = ""+df.format(results[p].getPercentage())+"%";
                    g.drawString(per, 320, 100 + 30 * p);
                }

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WorkspaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkspaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkspaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkspaceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkspaceFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addFolderMI;
    private javax.swing.JButton classifyButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    private void addArtist() {

        File artistDir = promptUserForArtistDir();
        if (artistDir != null) {
            lastPathUsed = artistDir.getPath();
            System.out.println("Artist Choice: " + artistDir.getName());
            String style = promptUserForStyle(artistDir.getName());
            System.out.println("The style is: " + style);
            saveArtistToSystem(artistDir);
        } else {
            System.out.println("No Folder Chosen");
        }

    }
    String lastPathUsed = "src";

    private File promptUserForArtistDir() {
        JFileChooser chooser = new JFileChooser(lastPathUsed);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }

    }

    private String promptUserForStyle(String artist_name) {

        // prompt the user to enter their name
        String[] styles = {"Post-Impressionism", "Baroque", "Renaissance", "Surrealism"};

        String selectedStyle = (String) JOptionPane.showInputDialog(this,
                "Choose Style for " + artist_name,
                "Style",
                JOptionPane.QUESTION_MESSAGE,
                null,
                styles,
                styles[0]);

        
        return selectedStyle;
    }

    private void saveArtistToSystem(File artistDir) {
        String savePath = "src/images/" + artistDir.getName();

        File dir = new File(savePath);

        // attempt to create the directory here
        if (!dir.exists()) {
            boolean successful = dir.mkdir();
            System.out.println("Created a new directory");
        } else {
            System.out.println("Directory already exists");
        }
        for (final File f : artistDir.listFiles()) {
            BufferedImage img = null;
            String imageName = f.getName();
            try {
                img = ImageIO.read(f);

                if (imageName.contains(".")) {
                    imageName = imageName.substring(0, f.getName().lastIndexOf('.'));

                }
                boolean success = Utility.saveTiff(savePath + "/" + imageName + ".tiff", img);
            } catch (IOException e) {
                System.out.println("Unable to read " + imageName + " :" + e);
            }

            //System.out.println("name: "+imageName);
        }
    }

    StringDouble[] results = null;

    private void classifyNewImage() {
        

        File imageFile = null;
        JFileChooser chooser = new JFileChooser(lastPathUsed);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TIFF", "tiff");
        chooser.setFileFilter(filter);

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            loading = true;
            repaint();
            System.out.println("painted loading screen");
            imageFile = chooser.getSelectedFile();
            lastPathUsed = imageFile.getPath();
            try {
                currImg = ImageIO.read(imageFile);

            } catch (IOException e) {
                System.out.println("Wasn't able to read file");
            }
            ClassifyDialog cd = new ClassifyDialog(this, false);
            cd.setLabel("Classification in progress");
            results = CommandParser.classify(imageFile.getPath());
            cd.setLabel("Classification done");
            loading = false;
            //repaint();
            System.out.println("painted image");

        } else {
            System.out.println("No image chosen");
        }

    }
    BufferedImage loadingIMG = null;

    private void initialize() {
        File imageFile = new File("src/loading.jpeg");
        try {
            ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(WorkspaceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}