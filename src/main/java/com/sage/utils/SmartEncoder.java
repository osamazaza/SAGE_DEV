package com.sage.utils;





import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class SmartEncoder {

    public static String convertPngToJpg(String pngFilePath, float compressionLevel) {
        try {
            // Read PNG image
            BufferedImage image = ImageIO.read(new File(pngFilePath));

            // Create JPG file path
            String jpgFilePath = pngFilePath.replaceFirst("\\.png$", ".jpg");

            // Ensure the image is in RGB color space for JPEG compatibility
            BufferedImage rgbImage = ensureRgbImage(image);

            // Write JPG image
            writeJpgImage(rgbImage, jpgFilePath, compressionLevel);

            System.out.println("Conversion completed: " + jpgFilePath);
            return jpgFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage ensureRgbImage(BufferedImage image) {
        if (image.getType() == BufferedImage.TYPE_INT_RGB) {
            return image;
        } else {
            // Convert to RGB format
            BufferedImage rgbImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
            );
            rgbImage.getGraphics().drawImage(image, 0, 0, null);
            return rgbImage;
        }
    }

    private static void writeJpgImage(BufferedImage image, String jpgFilePath, float compressionLevel) throws IOException {
        // Get JPEG ImageWriter
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        if (!writers.hasNext()) {
            throw new IllegalStateException("No JPEG writers found");
        }
        ImageWriter writer = writers.next();

        // Create ImageOutputStream
        ImageOutputStream ios = ImageIO.createImageOutputStream(new File(jpgFilePath));
        writer.setOutput(ios);

        // Set compression parameters
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(compressionLevel);
        }

        // Write the image
        writer.write(null, new javax.imageio.IIOImage(image, null, null), param);

        // Cleanup
        ios.close();
        writer.dispose();
    }

    public static String encodeURL(String partOfURL) {
        try {
            return URLEncoder.encode(partOfURL, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeURL(String encodedString) {
        try {
            return URLDecoder.decode(encodedString, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertFileToBase64(String filePath) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveBase64ToFile(String base64String, String savedDir, String fileName, String format) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);

            File directory = new File(savedDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = savedDir + File.separator + fileName + "." + format;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(decodedBytes);
            outputStream.close();

            System.out.println("File saved successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
