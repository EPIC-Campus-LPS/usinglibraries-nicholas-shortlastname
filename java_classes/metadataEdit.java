package java_classes;

import org.apache.commons.imaging.*;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import java.awt.image.BufferedImage;
import java.io.*;


public class metadataEdit {
    public static void changeMetadata(String fileName, double longitude, double latitude) throws IOException, ImageReadException, ImageWriteException {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\images\\new_" + fileName + ".jpg");
             OutputStream os = new BufferedOutputStream(fos)) {

            TiffOutputSet outputSet = null;

            final File file = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\" + fileName + ".jpg");
            final ImageMetadata metadata = Imaging.getMetadata(file);

            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

            if (null != jpegMetadata) {
                outputSet = jpegMetadata.getExif().getOutputSet();
            }
            if (null == outputSet) {
                outputSet = new TiffOutputSet();
            }

            final TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();


            exifDirectory.removeField(ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            exifDirectory.add(ExifTagConstants.EXIF_TAG_APERTURE_VALUE, new RationalNumber(3, 10));

            {
                //final double longitude = 47 + 9 / 60 + 57.6 / 3600;
                //final double latitude = 9 + 33.322 / 60;

                outputSet.setGPSInDegrees(longitude, latitude);
            }
            {

            }

            new ExifRewriter().updateExifMetadataLossless(file, os, outputSet);
        }
    }
    public static void readMetadata(String path) throws IOException, ImageReadException {
        final File file = new File(path);
        final ImageMetadata metadata = Imaging.getMetadata(file);
        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
            if (null != exifMetadata) {
                final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                if (null != gpsInfo) {
                    final String gpsDescription = gpsInfo.toString();
                    final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                    final double latitude = gpsInfo.getLatitudeAsDegreesNorth();
                    System.out.println("    " + "GPS Description: " + gpsDescription);
                    System.out.println("    " + "GPS Longitude (Degrees East): " + longitude);
                    System.out.println("    " + "GPS Latitude (Degrees North): " + latitude);
                }
            }
        }
    }
    public static void metadataFile(String fileName){
        File image = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\images\\" + fileName + ".jpg");
        try {
            File textdoc = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\images\\" + fileName + "_metadata.txt");
            if (textdoc.createNewFile()) {
                System.out.println("File created: " + textdoc.getName());
            } else {
                System.out.println("File already exists, replacing");
                textdoc.delete();
                textdoc.createNewFile();
            }

            final ImageMetadata metadata = Imaging.getMetadata(image);

            FileWriter fileWriter = new FileWriter(textdoc.getAbsoluteFile());
            fileWriter.write(metadata.toString());

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ImageReadException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, ImageReadException, ImageWriteException {
        changeMetadata("image",39.61329238261948, -104.98496840057305);
        readMetadata("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\images\\new_image.jpg");
        metadataFile("new_image");
    }
}
