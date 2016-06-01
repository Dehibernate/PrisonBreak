/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.LevelBuilder.GameBuilder;
import game.LevelBuilder.LBKeyHandler;
import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Aleks Ivanov
 */
public class MenuFrame extends javax.swing.JFrame {

    private JFrame frame;

    /**
     * Creates new form MenuFrame
     */
    public MenuFrame() {
        initComponents();

        // quit the application when the game window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // don't let the game window be resized
        this.setResizable(false);
        // make the window visible
        this.setVisible(true);

        //GET THE SCREEN RESOLUTION OF THE MONITOR  
        int x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2- this.size().width/2;
        int y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-this.size().height/2;
            
        //MOVE THE POSITION OF THE NEW WINDOW TO THE CENTRE OF THE FIRST WINDOW
        this.setLocation(x, y);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Start Game");
        jButton1.setPreferredSize(new java.awt.Dimension(173, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Custom Level");
        jButton2.setPreferredSize(new java.awt.Dimension(173, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Level Builder");
        jButton3.setPreferredSize(new java.awt.Dimension(173, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        startGame(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        startGame(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        startLevelBuilder();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables

    public void initializeGameFrame(String name) {
        frame = new JFrame(name);
        //Initialize the game frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.setSize(525, 625);
        // make the window visible
        
        //GET THE SCREEN RESOLUTION OF THE MONITOR  
        int x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2- frame.size().width/2;
        int y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-frame.size().height/2;
            
        //MOVE THE POSITION OF THE NEW WINDOW TO THE CENTRE OF THE FIRST WINDOW
        frame.setLocation(x, y);
    }

    public void startGame(boolean customLevel) {
        initializeGameFrame("Prison Break");
        Game game;
        String fileName = null;


        if (!customLevel) {
            game = new Game(frame,null);
        } else {
            //FileChooser
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("PB level files (*.pbl)", "pbl"));
            fc.showOpenDialog(null);

            if (fc.getSelectedFile() == null) {
                System.out.println("No file selected");
                return;
            } else {
                fileName = fc.getSelectedFile().toString();
                System.out.println(fc.getSelectedFile());
                game = new Game(frame, fileName);
            }
        }

        if (game != null) {
            game.start();
            // add some keyboard handling
            frame.addKeyListener(new GameKeyHandler(game));

            this.setVisible(false);

            frame.add(game.getView(), BorderLayout.CENTER);
            frame.setVisible(true);
        }
    }

    public void startLevelBuilder() {
        initializeGameFrame("Level Builder");
        GameBuilder gameb = new GameBuilder(frame);
        frame.add(gameb.getView());
        // add some keyboard handling
        frame.addKeyListener(new LBKeyHandler(gameb));
        frame.setVisible(true);
        this.setVisible(false);
    }
//    public Game getGame() {
//        return game;
//    }
}
