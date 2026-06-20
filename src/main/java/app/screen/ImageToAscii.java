package app.screen;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToAscii {

    private static final char[] ASCII_CHARS = {
            ' ', '.', ',', ':', ';', '+', '*', '?', '%', 'S', '#', '@'
    };

    public static String fromFile(String imagePath, int width) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return convertToAscii(image, width);
        } catch (IOException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    public static void printFromFile(String imagePath, int width) {
        System.out.println(fromFile(imagePath, width));
    }

    public static void printFromFile(String imagePath) {
        printFromFile(imagePath, 80);
    }

    public static String fromFile(String imagePath, int width, double verticalCompression) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return convertToAscii(image, width, verticalCompression);
        } catch (IOException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    public static void printFromFile(String imagePath, int width, double verticalCompression) {
        System.out.println(fromFile(imagePath, width, verticalCompression));
    }

    public static String convertToAscii(BufferedImage image, int width) {
        int height = (int) (image.getHeight() * width / (double) image.getWidth());

        Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();

        StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = resized.getRGB(x, y);
                int brightness = getBrightness(rgb);
                int index = brightness * (ASCII_CHARS.length - 1) / 255;
                result.append(ASCII_CHARS[index]);
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static String convertToAscii(BufferedImage image, int width, double verticalCompression) {
        int height = (int) (image.getHeight() * width / (double) image.getWidth() * verticalCompression);
        if (height < 1) height = 1;

        Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();

        StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = resized.getRGB(x, y);
                int brightness = getBrightness(rgb);
                int index = brightness * (ASCII_CHARS.length - 1) / 255;
                result.append(ASCII_CHARS[index]);
            }
            result.append("\n");
        }

        return result.toString();
    }

    private static int getBrightness(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return (r + g + b) / 3;
    }
}
