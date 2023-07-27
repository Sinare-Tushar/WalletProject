
package com.example.spring.datajpa.service.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format. DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.example.spring.datajpa.model.Transaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class CreatePdfTable {
@Autowired
EmailUtil util;

private static String imagePath = "D:\\WalletProject\\spring-boot-data-jpa-mysql-master\\spring-boot-data-jpa-mysql-master\\src\\main\\resources\\image\\HDFClogo.png";
	public void generatePDFReport(List<Transaction> transactions, LocalDateTime startDate, LocalDateTime endDate) {
        // Generate the PDF report
		
		
		
       
        String reportLocation = "report.pdf";
        try {
        	Rectangle rect =new Rectangle(PageSize.A4);
        	rect.setBackgroundColor(BaseColor.CYAN);
        	 Document document = new Document(rect);
        	PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(reportLocation));

        	document.open();
            Image watermarkImage = Image.getInstance(imagePath);
            
            float x = (PageSize.A4.getWidth() - watermarkImage.getScaledWidth()) / 2;
            float y = (PageSize.A4.getHeight() - watermarkImage.getScaledHeight()) / 2;
            watermarkImage.setAbsolutePosition(x, y);

            // Apply blur effect to the image
            watermarkImage.setTransparency(new int[]{0, 128, 128, 0, 0, 128, 128, 0, 0});
            PdfContentByte content = writer.getDirectContent();
            content.addImage(watermarkImage);
   
            
            Image image = Image.getInstance(imagePath);
            image.setAlignment(Element.ALIGN_RIGHT);
            image.scaleAbsolute(200, 200);
           
            document.add(image);
            // Add report title
            Paragraph title = new Paragraph("Bank Account Statement");
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);
			
            // Add date range
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDate1 = startDate.toLocalDate().format(formatter);
            String endDate1 = endDate.toLocalDate().format(formatter);
            Paragraph dateRange = new Paragraph("Date Range: " + startDate1 + " to " +  endDate1);
            dateRange.setSpacingAfter(10f);
            document.add(dateRange);

            // Add transaction details table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 3, 2,2});

            addTableHeader(table);
            addTableData(table, transactions);

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        storeReportLocation(reportLocation);
        FileSystemResource file=new FileSystemResource("D:\\WalletProject\\spring-boot-data-jpa-mysql-master\\spring-boot-data-jpa-mysql-master\\report.pdf");
		boolean flag=util.send("tusharsinare1995@gmail.com", startDate.toString()+" to "+ endDate.toString() +"Report", "I have mailed from Spring Boot Application but let me know you get it. So I will know my mail Application is developed", file, "Tushar Sinare . 9834052716");
		
        
    }
	private void addTableHeader(PdfPTable table) {
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headerFont.setColor(BaseColor.BLACK);

        headerCell.setPhrase(new Phrase("Transaction ID", headerFont));
        table.addCell(headerCell);

        headerCell.setPhrase(new Phrase("Date", headerFont));
        table.addCell(headerCell);

        headerCell.setPhrase(new Phrase("Amount", headerFont));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Wallet Id", headerFont));
        table.addCell(headerCell);
    }

    private void addTableData(PdfPTable table, List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            table.addCell(String.valueOf(transaction.getTransactionId()));
            table.addCell(String.valueOf(transaction.getTransactionDate()));
            table.addCell(String.valueOf(transaction.getAmount()));
            table.addCell(String.valueOf(transaction.getWallet().getId()));
        }
    }
    private void storeReportLocation(String reportLocation) {
		  try (FileWriter fileWriter = new FileWriter(reportLocation, true)) {
	          fileWriter.write(reportLocation + "\n");
	      } catch (IOException e) {
	          e.printStackTrace();
	          // Handle the exception
	      }
		  
	  }
}
