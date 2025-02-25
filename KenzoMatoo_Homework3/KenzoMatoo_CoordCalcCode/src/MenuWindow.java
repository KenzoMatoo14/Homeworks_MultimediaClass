import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MenuWindow extends JFrame {
    // UI components declaration
    private JPanel panel; // Panel to hold components
    private JLabel imageLabel, resultLabel; // Image and result labels
    private JTextField xField, yField, rField, thetaField; // Input fields for Cartesian and Polar coordinates
    private JButton calculateButton; // Calculate button
    private JComboBox<String> conversionType; // Dropdown for conversion type

    public MenuWindow() {
        this.setSize(700, 600); // Set window size
        this.setLocationRelativeTo(null); // Center window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Set close operation
        this.setTitle("Coordinate Converter"); // Set window title

        components(); // Initialize UI components
    }

    private void components() {
        panel(); // Setup panel
        inputFields(); // Setup input fields
        buttons(); // Setup buttons
        resultLabel(); // Setup result label
    }

    private void panel() {
        panel = new JPanel(); // Create panel
        this.getContentPane().add(panel); // Add panel to content pane
        panel.setLayout(null); // Set layout to null for custom positioning
    }

    private void inputFields() {
        // Hidden image label for shrimp
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 700, 600); // Set size and position
        imageLabel.setVisible(false); // Initially hidden
        panel.add(imageLabel);

        // Cartesian coordinates input
        JLabel xLabel = new JLabel("X:"); // Label for X
        xLabel.setBounds(10, 10, 100, 25); // Set position
        panel.add(xLabel);

        xField = new JTextField(); // Text field for X
        xField.setBounds(120, 10, 100, 25); // Set position
        panel.add(xField);

        JLabel yLabel = new JLabel("Y:"); // Label for Y
        yLabel.setBounds(10, 50, 100, 25); // Set position
        panel.add(yLabel);

        yField = new JTextField(); // Text field for Y
        yField.setBounds(120, 50, 100, 25); // Set position
        panel.add(yField);

        // Polar coordinates input
        JLabel rLabel = new JLabel("R:"); // Label for R
        rLabel.setBounds(10, 90, 100, 25); // Set position
        panel.add(rLabel);

        rField = new JTextField(); // Text field for R
        rField.setBounds(120, 90, 100, 25); // Set position
        panel.add(rField);

        JLabel thetaLabel = new JLabel("Theta (°):"); // Label for Theta
        thetaLabel.setBounds(10, 130, 100, 25); // Set position
        panel.add(thetaLabel);

        thetaField = new JTextField(); // Text field for Theta
        thetaField.setBounds(120, 130, 100, 25); // Set position
        panel.add(thetaField);

        // Conversion type selection dropdown
        JLabel conversionLabel = new JLabel("Conversion:"); // Label for conversion
        conversionLabel.setBounds(10, 170, 100, 25); // Set position
        panel.add(conversionLabel);

        conversionType = new JComboBox<>(new String[]{"Cartesian → Polar", "Polar → Cartesian"}); // Dropdown options
        conversionType.setBounds(120, 170, 160, 25); // Set position
        panel.add(conversionType);
    }

    private void buttons() {
        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 200, 120, 30); // Set position
        panel.add(calculateButton);
        calculateButton.addActionListener(e -> {
            // Handle calculate button click
            String selection = (String) conversionType.getSelectedItem();
            String result = CoordinateCalculator.calculate(selection, xField.getText(), yField.getText(), rField.getText(), thetaField.getText());
            resultLabel.setText(result); // Show result
        });

        // Easter egg button (shrimp)
        JButton shrimp = new JButton("Shrimp \uD83E\uDD90");
        shrimp.setBounds(550, 500, 100, 50);
        panel.add(shrimp);
        shrimp.addActionListener(e -> showShrimp());
    }

    private void resultLabel() {
        // Label to show calculation result
        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(10, 250, 300, 25); // Set position
        panel.add(resultLabel);
    }

    // Displays shrimp image and plays sound
    private void showShrimp() {
        imageLabel.setIcon(new ImageIcon("src/Images/CoolShrimp.png"));
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

    private void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName); // Load sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile); // Create audio stream
            Clip clip = AudioSystem.getClip(); // Get audio clip
            clip.open(audioStream); // Open audio clip
            clip.start(); // Play audio clip
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace(); // Print error if occurs
        }
    }
}
