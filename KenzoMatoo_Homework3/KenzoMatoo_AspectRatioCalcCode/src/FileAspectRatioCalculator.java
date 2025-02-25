import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileAspectRatioCalculator {

    // Opens a file chooser, reads image dimensions, and returns aspect ratio
    public static String selectFileAndCalculate() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                if (image != null) {
                    int width = image.getWidth();
                    int height = image.getHeight();
                    return AspectRatioCalculator.calculateAspectRatio(width, height); // Reuse AspectRatioCalculator
                } else {
                    return "Invalid image file!";
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return "Error reading the file!";
            }
        }
        return "No file selected!";
    }
}