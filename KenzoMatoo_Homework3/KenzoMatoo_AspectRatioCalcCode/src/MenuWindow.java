import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MenuWindow extends JFrame {
    // UI components
    private JPanel panel;
    private JLabel imageLabel, widthLabel, heightLabel, resultLabel;
    private JTextField inputField1, inputField2;
    private JButton calculateButton, fileButton;

    public MenuWindow() {
        this.setSize(700, 600);
        this.setLocationRelativeTo(null); // Center the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Aspect Ratio Calculator");

        components(); // Initialize UI components
    }

    private void components() {
        panel();
        inputFields();
        buttons();
        resultLabel();
    }

    private void panel() {
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    private void inputFields() {
        // Hidden image label (for shrimp Easter egg)
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 700, 600);
        imageLabel.setVisible(false);
        panel.add(imageLabel);

        // Width input
        widthLabel = new JLabel("Width:");
        widthLabel.setBounds(10, 10, 100, 25);
        panel.add(widthLabel);

        inputField1 = new JTextField();
        inputField1.setBounds(120, 10, 100, 25);
        panel.add(inputField1);

        // Height input
        heightLabel = new JLabel("Height:");
        heightLabel.setBounds(10, 50, 100, 25);
        panel.add(heightLabel);

        inputField2 = new JTextField();
        inputField2.setBounds(120, 50, 100, 25);
        panel.add(inputField2);
    }

    private void buttons() {
        // Button for manual aspect ratio calculation
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 90, 120, 30);
        panel.add(calculateButton);
        calculateButton.addActionListener(e -> calculateAspectRatio());

        // Button for file selection and automatic aspect ratio calculation
        fileButton = new JButton("Choose File");
        fileButton.setBounds(150, 90, 120, 30);
        panel.add(fileButton);
        fileButton.addActionListener(e -> selectFileAndCalculate());

        // Easter egg button (shrimp)
        JButton shrimp = new JButton("Shrimp \uD83E\uDD90");
        shrimp.setBounds(550, 500, 100, 50);
        panel.add(shrimp);
        shrimp.addActionListener(e -> showShrimp());
    }

    private void resultLabel() {
        resultLabel = new JLabel("Aspect Ratio: ");
        resultLabel.setBounds(10, 140, 300, 25);
        panel.add(resultLabel);
    }

    // Gets width & height input, calculates aspect ratio using AspectRatioCalculator
    private void calculateAspectRatio() {
        try {
            int width = Integer.parseInt(inputField1.getText());
            int height = Integer.parseInt(inputField2.getText());
            String aspectRatio = AspectRatioCalculator.calculateAspectRatio(width, height);
            resultLabel.setText("Aspect Ratio: " + aspectRatio);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid integers!");
        }
    }

    // Calls FileAspectRatioCalculator to select an image file and calculate aspect ratio
    private void selectFileAndCalculate() {
        String result = FileAspectRatioCalculator.selectFileAndCalculate();
        resultLabel.setText("Aspect Ratio: " + result);
    }

    // Displays shrimp image and plays a sound effect (Easter egg)
    private void showShrimp() {
        imageLabel.setIcon(new ImageIcon("src\\Images\\CoolShrimp.png"));
        imageLabel.setVisible(true);
        playSound("src\\Sounds\\vine-boom.wav");

        // Hide shrimp after 1 second
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                imageLabel.setVisible(false);
            }
        }, 1000);
    }

    // Plays an audio file
    private void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
