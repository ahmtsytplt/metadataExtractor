package org.ahmetsaitpolat.metadataextractor;

public class MetadataExtractorApplication {
    public static void main(String[] args) {

        System.out.println("Select the file ");
        MetadataExtractor.run(new MetadataExtractor(), 640, 480);

    }
}
