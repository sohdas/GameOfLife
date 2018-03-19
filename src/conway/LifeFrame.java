/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conway;
//import java.util.Timer;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;

/**
 *
 * @author Soham
 */
public class LifeFrame extends javax.swing.JFrame {
    final int width = 600;
    final int height = 400;
    int tilesize = 6; //should be 8
    final int numX = width/tilesize; 
    final int numY = height/tilesize; 
    final long tickLength = 100;
    int xMouse, yMouse;
    long lastTime;
    //
    int lastxM,lastyM ;
    //
    boolean thisMove [][]= new boolean[numY][numX];
    boolean nextMove [][]= new boolean[numY][numX];
    JSlider simSpeed = new JSlider();
    
  
    Timer timer = new Timer((int)tickLength, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                life();
            } catch (InterruptedException ex) {
                System.err.println("Interrupted.");
            }
        }
    });
    
    //TimerTask task;
    Image backImage;
    Graphics backGraph;
    
    InterruptedException ie = new InterruptedException();
    
    public LifeFrame() {
        initComponents();
        seticon();
        backImage = createImage(width, height);
        backGraph = backImage.getGraphics();
        
        reDraw();
        reDraw();
        System.out.println("Grid has been cleaned.");
    }
    private void seticon() {
     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lifeicon.png")));
    }
     
     private void reDraw(){

        backGraph.setColor(Color.WHITE);
        backGraph.fillRect(0,0,width,height);
             
        for(int i = 0; i < numY; i++)
            for(int j = 0; j< numX; j++)
            {
                if(thisMove[i][j])
                {                
             
                backGraph.setColor(Color.red);
                int x = j * tilesize;
                int y = i * tilesize;
                backGraph.fillRect(x+1, y+1, tilesize+1,tilesize+1);
                }
            }
        
        backGraph.setColor(Color.BLACK);
        for(int y = 1; y<height; y+= tilesize)
        {
            backGraph.drawLine(0, y, width, y);
        }
        
        for(int x = 1; x<width; x+= tilesize)
        {
            backGraph.drawLine(x, 0, x, height);
        }
        
        lifePanel.getGraphics().drawImage(backImage, 0, 0, lifePanel);
        
    }
    /*
     
      Rules of Game Of Life: 
 
a live cell with zero or one live neighbours will die.
a live cell with two or three live neighbours will remain alive
a live cell with four or more live neighbours will die. 

a dead cell with exactly three live neighbours becomes alive
in all other cases a dead cell will stay dead.
     
     */
    private void life() throws InterruptedException
    {
      
            onTick();
            if(!Arrays.deepEquals(thisMove, nextMove))
            {
            for(int a = 0; a<numY; a++)
                for(int b = 0; b<numX; b++)
                    thisMove[a][b] = nextMove[a][b];
            }
          
            reDraw();                         
          
    }
    
    private void onTick()
    {
       
                        
            for(int yy = 0; yy< thisMove.length; yy++)
                for(int xx = 0; xx< thisMove[0].length; xx++)
                {
                    if(thisMove[yy][xx] == true)
                    {
                        if(numInRadius(thisMove,yy,xx)<2 || numInRadius(thisMove,yy,xx)>3)
                          nextMove[yy][xx] = false;
                        else
                            nextMove[yy][xx] = true;
                                                                          
                    }
                    if(thisMove[yy][xx] == false)
                    {
                        if(numInRadius(thisMove,yy,xx)==3)
                         nextMove[yy][xx] = true;
                           
                    }
                } 
        

    }
    
    private int numInRadius(boolean[][] thisMove, int y, int x)
    {   
       int count = 0;       
           
           
                if((y > 0 && y < numY -1) && (x > 0 && x < numX -1))
                {
                   
                    if(thisMove[y + 1][x])
                        count++;
                    if(thisMove[y -1][x])
                        count++;
                    if(thisMove[y + 1][x+1])
                        count++;
                    if(thisMove[y + 1][x-1])
                        count++;
                    if(thisMove[y-1][x+1])
                        count++;
                    if(thisMove[y-1][x-1])
                        count++;
                    if(thisMove[y][x-1])
                        count++;
                    if(thisMove[y][x+1])
                        count++;
                }
               
       
        return count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        lifePanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        dragLabel = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conway's Game Of Life");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        lifePanel.setBackground(new java.awt.Color(204, 204, 204));
        lifePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lifePanelMouseDragged(evt);
            }
        });
        lifePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lifePanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lifePanelMousePressed(evt);
            }
        });
        lifePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                lifePanelComponentResized(evt);
            }
        });

        javax.swing.GroupLayout lifePanelLayout = new javax.swing.GroupLayout(lifePanel);
        lifePanel.setLayout(lifePanelLayout);
        lifePanelLayout.setHorizontalGroup(
            lifePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        lifePanelLayout.setVerticalGroup(
            lifePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        startButton.setText("Start");
        startButton.setToolTipText("Starts the simulation.");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        restartButton.setText("Restart");
        restartButton.setToolTipText("Restarts the grid.");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setToolTipText("Stops the simulation.");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        dragLabel.setBackground(new java.awt.Color(61, 202, 207));
        dragLabel.setFont(new java.awt.Font("Consolas", 3, 14)); // NOI18N
        dragLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dragLabel.setText("Game Of Life");
        dragLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        dragLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragLabelMouseDragged(evt);
            }
        });
        dragLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lifePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restartButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(dragLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dragLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lifePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(restartButton)
                    .addComponent(stopButton)
                    .addComponent(stepButton)
                    .addComponent(exitButton)
                    .addComponent(helpButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lifePanel.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lifePanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lifePanelComponentResized
        backImage = createImage(width, height);
        backGraph = backImage.getGraphics();
              
        reDraw();
    }//GEN-LAST:event_lifePanelComponentResized

    private void lifePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifePanelMouseClicked
   
      int xM = (int)lifePanel.getMousePosition().getX();
      int yM = (int)lifePanel.getMousePosition().getY();
      
      xM/=tilesize;
      yM/=tilesize;
     
      thisMove[yM][xM] = !thisMove[yM][xM];
      reDraw();
      
    }//GEN-LAST:event_lifePanelMouseClicked

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        
        timer.start();
        //reDraw();
        try
        {
        life();
        }
        catch (InterruptedException intex)
        {
            System.err.println("Error - Interrupted.");
        }
        
    }//GEN-LAST:event_startButtonActionPerformed
    
    //Tentative
    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        timer.stop();
      
        for(int yy = 0; yy< thisMove.length; yy++)
            for(int xx = 0; xx< thisMove[0].length; xx++)
            {
                thisMove[yy][xx] = false;
                nextMove[yy][xx] = false;
            }
        
        reDraw();
        System.out.println("Grid has been cleaned.");
        
    }//GEN-LAST:event_restartButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        timer.stop();
        // TODO add your handling code here:
    }//GEN-LAST:event_stopButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        timer.start();
        
    
        try
        {
        life();
        }
        catch (InterruptedException intex)
        {
            System.err.println("Error - Interrupted.");
        }
        timer.stop();
    }//GEN-LAST:event_stepButtonActionPerformed

    private void lifePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifePanelMousePressed
       
    }//GEN-LAST:event_lifePanelMousePressed

    private void lifePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifePanelMouseDragged
      int xM = (int)lifePanel.getMousePosition().getX();
      int yM = (int)lifePanel.getMousePosition().getY();
      
      xM/=tilesize;
      yM/=tilesize;
     
      if(!(xM == lastxM && yM == lastyM))
        thisMove[yM][xM] = !thisMove[yM][xM];
      
      reDraw();
      
      lastxM = xM;
      lastyM = yM;
      
    }//GEN-LAST:event_lifePanelMouseDragged

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
       System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed
                     
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
JOptionPane.showMessageDialog(this, "Game of Life is a cellular automata developed by John Conway in 1970.\n"
        + "\nThe rules are simple: \n1. A cell can be either alive or dead.\n2. A live cell "
        + "will only stay alive if it has 2 or 3 other live cells bordering it."
        + "\n3. A dead cell can become alive if it has exactly 3 cells bordering it."
        + "\n\n The game board will update each 'generation' to reflect these rules."
        + "\n\n Play around and see what you can discover!", "What is the Game of Life?", JOptionPane.INFORMATION_MESSAGE);        // TODO add your handling code here:
    }//GEN-LAST:event_helpButtonActionPerformed

    private void dragLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMouseDragged
          int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse,y - yMouse);
    }//GEN-LAST:event_dragLabelMouseDragged

    private void dragLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMousePressed
xMouse = evt.getX();
        yMouse = evt.getY();        // TODO add your handling code here:
    }//GEN-LAST:event_dragLabelMousePressed

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
                if ("Windows".equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LifeFrame().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dragLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel lifePanel;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
