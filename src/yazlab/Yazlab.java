
package yazlab;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JProgressBar;



public class Yazlab extends javax.swing.JFrame {
    List<SubServer> subServers;  
    public Yazlab() {
        initComponents();       
        ServerOrganizer creater = new ServerOrganizer();
        
        List<JProgressBar> myProgress = new ArrayList<>();
        Timer timer = new Timer();           
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {                
                List<Float> informations = creater.control.information();
                sunucuSayisi.setText(String.valueOf(informations.size()));              
                if(myProgress.size()!=informations.size()&&!myProgress.isEmpty()) clearProgress(myProgress);                
                if(myProgress.isEmpty()) addProgress(myProgress,informations.size());
                for (int i = 0; i < informations.size(); i++) {                   
                   if(i==0) myProgress.get(i).setString("%"+informations.get(i));                       
                   else  myProgress.get(i).setString("%"+informations.get(i));                     
                   int value = Math.round(informations.get(i));
                   myProgress.get(i).setValue(value);
                }                                                
            }

            private void clearProgress(List<JProgressBar> myprogress ) { 
                myprogress.forEach((item) -> {
                    remove(item);
                });
                myprogress.clear(); 
                repaint();
            }
            private void addProgress(List<JProgressBar> myprogress, int size) {
                for (int i = 0; i < size; i++) {
                    JProgressBar progressBar = new JProgressBar();                    
                    progressBar.setBounds(25,(i+1)*50,200,50); 
                    progressBar.setStringPainted(true);
                    if(i==0) progressBar.setForeground(Color.BLUE);
                    myprogress.add(progressBar);                   
                    getContentPane().add(progressBar);
                }
                repaint();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0,500);        
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        sunucuSayisi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sunucu Sayısı =>");

        sunucuSayisi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sunucuSayisi.setText("3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sunucuSayisi)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sunucuSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(501, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Yazlab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Yazlab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Yazlab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Yazlab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Yazlab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel sunucuSayisi;
    // End of variables declaration//GEN-END:variables
}
