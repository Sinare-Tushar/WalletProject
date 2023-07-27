package com.example.spring.datajpa.service.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.example.spring.datajpa.model.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class CreatePdf {
	@Autowired
	EmailUtil util;
	 public void generatePDFReport(List<Transaction> transaction,LocalDateTime startDate, LocalDateTime endDate) {
	        // Code to generate the PDF report using iText library
	        // Customize this method based on your requirements
	        Document document = new Document();
	        String reportLocation = "report.pdf";
	        try {
	            PdfWriter.getInstance(document, new FileOutputStream(reportLocation));
	            document.open();
	            document.add(new Paragraph("\t\t\tTransaction Report"));
	            document.add(new Paragraph("Start Date: " + startDate.toString()+ "\t End Date: " + endDate.toString()));
	            document.add(new Paragraph(""+"Transaction Id \t\t\t\t  Transaction Date \t\t Transaction Amount \t\t TransactionWallet"));
	            for (Transaction tran : transaction) {
	           
	            document.add(new Paragraph(""+tran.getTransactionId()+"\t \t"+tran.getDate()+"\t\t"+tran.getTransaction_amount()+"\t\t"+tran.getWallet().getId()));
	            }
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        storeReportLocation(reportLocation);
	        FileSystemResource file=new FileSystemResource("D:\\WalletProject\\spring-boot-data-jpa-mysql-master\\spring-boot-data-jpa-mysql-master\\report.pdf");
			boolean flag=util.send("tusharsinare1995@gmail.com", startDate.toString()+" to "+ endDate.toString() +"Report", "I have mailed from Spring Boot Application but let me know you get it. So I will know my mail Application is developed", file, "Tushar Sinare . 9834052716");
			
	        
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
