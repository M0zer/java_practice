package tictactoe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends BaseWindow {

    private int size;
    private Model model;
    private JLabel label;
    private MainWindow mainWindow;
    public JPanel mainPanel = new JPanel();
    
    public Window(int size, MainWindow mainWindow) {
        this.size = size;
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);
        label = new JLabel();
        updateLabelText();
       JButton newGameButton = new JButton();
        newGameButton.setText("Új játék");
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
            
        });
        
        JPanel top = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
          top.add(label);
        top.add(newGameButton);
         
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH,0);
        renderBoard(null);
        
    }

    private void addButton(JPanel panel, final int i,
            final int j,Player[][] table) {
        final JButton button = new JButton();
        if(table!=null){
        if(table[i][j]!=Player.NOBODY){
            button.setText(table[i][j].toString());
        }}else{button.setText("");}        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player newValue = model.step(i, j);
                if(newValue.name()=="NOBODY"){
                    button.setText("");
                }else{
                    button.setText(newValue.name());
                }
                
                updateLabelText();
                renderBoard(model.getTable());
                
                Player winner =model.getWinner();
                
                if (winner != Player.NOBODY) {
                    showGameOverMessage(winner);
                }
                if(model.isBoardFull() ) {
                showGameOverMessageDraw();}
                
            }
        });
        panel.add(button);
    }

    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Játék vége. Nyert: " + winner.name());
        newGame();
    }
    private void showGameOverMessageDraw() {
        JOptionPane.showMessageDialog(this,
                "Játék vége. Döntetlen");
        newGame();
    }
    
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText() {
        label.setText("Aktuális játékos: "
                + model.getActualPlayer().name());
    }
    private void renderBoard(Player[][] table){
        
        if(table==null){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        
         for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j,table);
            }
        }
        getContentPane().add(mainPanel, BorderLayout.CENTER,1);
        }
        else{ 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
         for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j,table);
            }
        } getContentPane().add(mainPanel, BorderLayout.CENTER,1);
    
        }
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    
}
