import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MenuWindow extends JFrame {
    //Attributes
    public JPanel panel;
    private JLabel imageLabel, menuText, inputLabel1, inputLabel2, resultLabel;
    public JTextField inputField1, inputField2;

    public MenuWindow() {
        this.setSize(700, 500);
        this.setLocationRelativeTo(null); //center the Window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        components();
    }

    private void components() {
        panel();
        Labels();
        InputField();
        Buttons();
        Dropdown();
    }

    private void panel() {
        panel = new JPanel(); // Initialize the panel
        this.getContentPane().add(panel); // Add the panel to the main container of the JFrame
        panel.setLayout(null); // Set null layout for the panel
    }

    //sets text for the GUI so the user can read
    private void Labels(){
        menuText = new JLabel();
        menuText.setText("Pick a figure to calculate its area and perimeter");
        menuText.setBounds(220,0,400,100);
        panel.add(menuText);

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 700, 500); // Position for the image
        imageLabel.setVisible(false); // Initially hidden
        panel.add(imageLabel);

        resultLabel = new JLabel();
        resultLabel.setBounds(220, 300, 400, 100); // Position the result
        panel.add(resultLabel);

        inputLabel1 = new JLabel("Enter side length:");
        inputLabel1.setBounds(10, 160, 150, 25);
        panel.add(inputLabel1);

        inputLabel2 = new JLabel("Enter second parameter:");
        inputLabel2.setBounds(10, 200, 200, 25);
        inputLabel2.setVisible(false);
        panel.add(inputLabel2);
    }

    private void Buttons(){
        //Instance the buttons and its text
        JButton Shrimp = new JButton("Shrimp \uD83E\uDD90");
        Shrimp.setBounds(480, 380, 200, 80);
        panel.add(Shrimp);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(280, 160, 100, 25);
        panel.add(calculateButton);

        // Ignore this part if you want, is for the shrimp button
        Shrimp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the image
                ImageIcon shrimpIcon = new ImageIcon("src\\Images\\CoolShrimp.png");
                imageLabel.setIcon(shrimpIcon);
                imageLabel.setVisible(true);

                // Play the sound effect
                playSound("src\\Sounds\\vine-boom.wav");

                // Hide the image after 1 seconds
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        imageLabel.setVisible(false);
                    }
                }, 1000); // 1000ms = 1 seconds
            }
        });

        // Add ActionListener for calculation
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAreaAndPerimeter();
            }
        });
    }

    //ignore this part too if you want is for the shrimp button
    private void playSound(String soundFileName) {
        try {
            // Load the sound file
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Play the sound
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void Dropdown() {
        // Create a dropdown box with the requested elements
        String[] shapes = {"Square", "Rectangle", "Triangle", "Circle", "Regular Pentagon", "Pentagram", "Semi-circle"};

        JComboBox<String> shapeDropdown = new JComboBox<>(shapes);
        shapeDropdown.setBounds(10, 100, 200, 50); // Position the dropdown
        panel.add(shapeDropdown);

        // Add ActionListener to handle selection
        shapeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the selected shape in the messageLabel
                String selectedShape = (String) shapeDropdown.getSelectedItem();
                menuText.setText("Selected Shape: " + selectedShape);
                inputLabel2.setVisible(false);
                inputField2.setVisible(false);

                switch (selectedShape) { //changes the text and its visibility depending on the figure
                    case "Square", "Triangle", "Regular Pentagon", "Pentagram":
                        inputLabel1.setText("Enter side length:");
                        break;
                    case "Rectangle":
                        inputLabel1.setText("Enter base length:");
                        inputLabel2.setText("Enter Height length:");
                        inputLabel2.setVisible(true);
                        inputField2.setVisible(true);
                        break;
                    case "Circle", "Semi-circle":
                        inputLabel1.setText("Enter radius:");
                        break;
                }

            }
        });
    }

    private void InputField() {
        inputField1 = new JTextField();
        inputField1.setBounds(160, 160, 100, 25);
        panel.add(inputField1);

        inputField2 = new JTextField();
        inputField2.setBounds(160, 200, 100, 25);
        inputField2.setVisible(false); //initially invisible because is only needed for rectangle
        panel.add(inputField2);
    }

    private void calculateAreaAndPerimeter() {
        String selectedShape = menuText.getText().replace("Selected Shape: ", ""); //i found easier to grab the selected shape from the menu text
        String input = inputField1.getText();
        String input2 = inputField2.getText();

        try {
            double value1 = Double.parseDouble(input); // Parse the first input
            // Validate the first input
            if (value1 <= 0) {
                throw new IllegalArgumentException("The value must be greater than zero.");
            }

            // Check if second input is required for the shape
            double value2 = 0; // Default value for shapes that don't require a second input
            if (selectedShape.equals("Rectangle")) {
                if (input2.isEmpty()) {
                    throw new IllegalArgumentException("The second input cannot be empty for a rectangle.");
                }
                value2 = Double.parseDouble(input2);
                if (value2 <= 0) {
                    throw new IllegalArgumentException("The second value must be greater than zero.");
                }
            }

            switch (selectedShape) {
                case "Square":
                    Square square = new Square();
                    square.setSideLength(value1);
                    //ChatGPT helped me with this syntax, i liked it and kept using it
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", square.getArea(), square.getPerimeter()));
                    break;
                case "Rectangle":
                    Rectangle rectangle = new Rectangle();
                    rectangle.setBase(value1);
                    rectangle.setHeight(value2);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", rectangle.getArea(), rectangle.getPerimeter()));
                    break;
                case "Triangle":
                    Triangle triangle = new Triangle();
                    triangle.setSide(value1);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", triangle.getArea(), triangle.getPerimeter()));
                    break;
                case "Circle":
                    Circle circle = new Circle();
                    circle.setRadius(value1);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", circle.getArea(), circle.getPerimeter()));
                    break;
                case "Semi-circle":
                    SemiCircle semiCircle = new SemiCircle();
                    semiCircle.setRadius(value1);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", semiCircle.getArea(), semiCircle.getPerimeter()));
                    break;
                case "Regular Pentagon":
                    Pentagon pentagon = new Pentagon();
                    pentagon.setSide(value1);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", pentagon.getArea(), pentagon.getPerimeter()));
                    break;
                case "Pentagram":
                    Pentagram pentagram = new Pentagram();
                    pentagram.setSide(value1);
                    resultLabel.setText(String.format("Area: %.2f, Perimeter: %.2f", pentagram.getArea(), pentagram.getPerimeter()));
                    break;
                default:
                    resultLabel.setText("Shape not supported for calculations.");
                    return;
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid side length. Please enter a number.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
    }
    }
}