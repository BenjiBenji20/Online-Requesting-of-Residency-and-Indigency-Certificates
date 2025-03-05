package com.azathoth.OLRResidency_Indigency.service;

import com.azathoth.OLRResidency_Indigency.model.Resident;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PdfGeneratorService {
    // for generating indigency certificate
    public byte[] generateIndigencyPDF(Resident resident) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Load a font (Arial in this case)
                PDType0Font font = PDType0Font.load(document, new File("C:/Windows/Fonts/Arial.ttf"));
                contentStream.setFont(font, 12);

                // Set initial position
                float margin = 50;
                float yPosition = 750;

                // Add the header
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("REPUBLIC OF THE PHILIPPINES");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Province of Metro Manila");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Municipality of Malabon");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Barangay SANTULAN");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Office of the Punong Barangay");
                yPosition -= 30;
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("CERTIFICATE OF INDIGENCY");
                contentStream.endText();

                // Add the body
                contentStream.beginText();
                yPosition -= 30;
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("TO WHOM IT MAY CONCERN:");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("This is to CERTIFY that Mr./Ms. " + resident.getFirstName() + " " + resident.getLastName() + ", ");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("of legal age, single or married, " );
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Filipino Citizen and a resident of " + resident.getCompleteAddress() + ",");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("belongs to the Indigent Families of this barangay having an annual income");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("not exceeding the Regional Poverty Threshold (RPT) of Php 169,824.00 per annum");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("as determined by the National Economic Development Authority (NEDA).");
                yPosition -= 30;
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("This CERTIFICATION is issued upon the request of the above-mentioned individual");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("for whatever legal purpose/s it may best serve him or her.");
                yPosition -= 30;
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("ISSUED this " + LocalDate.now().getDayOfMonth() +
                        " day of " + LocalDate.now().getMonth() + " " + LocalDate.now().getYear() +
                        " at Address " + resident.getCompleteAddress() + ".");
                contentStream.endText();

                // Add the signature and name of Punong Barangay
                contentStream.beginText();
                yPosition -= 50;
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Signature");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("PRINTED NAME OF PUNONG BARANGAY");
                contentStream.endText();

                // Add the special note on the dry seal
                contentStream.beginText();
                yPosition -= 50;
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("SPECIAL NOTE ON THE DRY SEAL:");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("\"Place the Dry Seal IF AVAILABLE;");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("If the Barangay has NO dry seal, then leave the lower part of the Certificate EMPTY.\"");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("If the Certificate contains \"NOT VALID WITHOUT SEAL\", then the seal MUST BE PLACED.");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("If the \"NOT VALID WITHOUT SEAL\" is present but no dry seal has been placed,");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("then the Certificate is NOT VALID AND");
                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }


    // for generating residency certificate
    public byte[] generateResidencyPDF(Resident resident) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Load a font (Arial in this case)
                PDType0Font font = PDType0Font.load(document, new File("C:/Windows/Fonts/Arial.ttf"));
                contentStream.setFont(font, 12);

                // Set initial position
                float margin = 50;
                float yPosition = 750;

                // Add the header
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Republic of the Philippines");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Province of Metro Manila");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Municipality of Malabon");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Barangay Santulan");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("OFFICE OF THE PUNONG BARANGAY");
                yPosition -= 30;
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("CERTIFICATE OF RESIDENCY");
                contentStream.endText();

                // Add the body
                contentStream.beginText();
                yPosition -= 30;
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("TO WHOM IT MAY CONCERN:");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("THIS IS TO CERTIFY that as per records available in this office, Mr./Ms. " +
                        resident.getFirstName() + " " + resident.getLastName() + ",");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText(resident.getGender().toLowerCase() + ", single or married" + ", of legal age, Filipino");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("citizen is a bonafide resident of Barangay Santulan, Malabon City.");
                yPosition -= 30;
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("CERTIFYING FURTHER, that above-named person, is a person of good");
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("moral character and a law-abiding citizen of Santulan, Malabon City.");
                contentStream.endText();

                // Add the signature and name of Punong Barangay
                contentStream.beginText();
                yPosition -= 50;
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("JONATHAN A. TUNAC"); // change this name with the punong barangay
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Punong Barangay");
                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
