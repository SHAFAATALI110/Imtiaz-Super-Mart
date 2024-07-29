package org.ImtiazSuperMarket.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PdfGeneratorUi {
    public static void generateInvoice(String fileName, int customerId,
                                       int userId, float subTotal, String paymentType)
            throws DocumentException, IOException, FileNotFoundException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();
        addMetaData(document);
        addTitlePage(document, customerId, userId, subTotal, paymentType);
        addInvoiceTable(document,
       customerId,userId,subTotal,paymentType);

        document.close();
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File(fileName));
        } else {
            JOptionPane.showMessageDialog(null, "Desktop is not supported. Cannot open the PDF automatically.");
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Invoice");
        document.addSubject("Generated Invoice");
        document.addKeywords("Invoice, PDF, Java");
        document.addAuthor("Your Company");
        document.addCreator("Your Company");
    }

    private static void addTitlePage(Document document, int customerId, int userId, float subTotal, String paymentType)
            throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font infoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("", titleFont));
        preface.add(new Paragraph("Description: " + "asdasdasdasd", infoFont));
        preface.add(new Paragraph("Quantity: " + "12" , infoFont));
        preface.add(new Paragraph("Date: " + new Date(), infoFont));
        addEmptyLine(preface, 1);

        document.add(preface);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addInvoiceTable(Document document,
                                        int customerId,
                                        int userId, float subTotal,
                                        String paymentType) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
            table.addCell("Customer Id: " + customerId);
            table.addCell("User Id: " + userId);
            table.addCell("Sub Total: " + subTotal);
            table.addCell("Payment: " + paymentType);


//        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//        Font bodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

//        PdfPCell cell1 = new PdfPCell(new Phrase("Invoice ID:", headerFont));
//        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell1);
//
//        PdfPCell cell2 = new PdfPCell(new Phrase("Customer ID:", headerFont));
//        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell2);
//
//        PdfPCell cell3 = new PdfPCell(new Phrase("User ID :", headerFont));
//        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell3);
//
//        PdfPCell cell4 = new PdfPCell(new Phrase("Sub Total :", headerFont));
//        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell4);
//
//        PdfPCell cell5 = new PdfPCell(new Phrase("Payment Type :", headerFont));
//        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell5);

        // Adding sample rows (replace with actual data)
//        for (int i = 0; i < 5; i++) {
//            table.addCell(new PdfPCell(new Phrase("Item " + (i + 1), bodyFont)));
//            table.addCell(new PdfPCell(new Phrase("Description " + (i + 1), bodyFont)));
//            table.addCell(new PdfPCell(new Phrase(String.valueOf(i + 1), bodyFont)));
//            table.addCell(new PdfPCell(new Phrase("$" + (i + 1) * 10, bodyFont)));
//        }

        document.add(table);
    }
}
