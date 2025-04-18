import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.LineUnavailableException;

public class MorseCodeTranslatorGUI extends JFrame {
    private JButton playSoundButton;
    private JTextArea morseCodeArea;

    public MorseCodeTranslatorGUI() {
        // Initialize GUI components
        setTitle("Morse Code Translator");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a layout manager instead of setLayout(null)
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Morse Code Translator", SwingConstants.CENTER);

        morseCodeArea = new JTextArea();
        JScrollPane textInputScroll = new JScrollPane(morseCodeArea);
        textInputScroll.setPreferredSize(new Dimension(400, 100));

        playSoundButton = new JButton("Play Sound");

        // Panel for better layout management
        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(textInputScroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(playSoundButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add ActionListener to button
        playSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSoundButton.setEnabled(false);
                  
                Thread playMorseCodeThread = new Thread(new Runnable() {
                    @Override
                    
                    public void run() {
                    	
                    	
                        try {
                            String[] morseCodeMessage = morseCodeArea.getText().trim().split("\\s+");
                            MorseCodeController.playSound(morseCodeMessage);
                        } catch (LineUnavailableException | InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            System.err.println("Error playing Morse code: " + ex.getMessage());
                            ex.printStackTrace();
                        } finally {
                            SwingUtilities.invokeLater(() -> playSoundButton.setEnabled(true));
                        }
                    }
                });
                playMorseCodeThread.start();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MorseCodeTranslatorGUI().setVisible(true));
    }
}
