import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javafx.stage.Stage;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Report {
	
	 MaterialInfo info = new MaterialInfo();
	 
	 DecimalFormat dc = new DecimalFormat("#.##");
	 DecimalFormat dc_pval = new DecimalFormat("#.######");
	 String folder = "C:/MEU-system/teacher_report/";
	 
		    static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
		            Font.BOLD);
		    static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 5,
		            Font.NORMAL, BaseColor.RED);
		    static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 7,
		            Font.BOLD);
		    static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
		            Font.BOLD); 
	 
	 public void generatePdfReportF1(String section_number, String course_name, 
			                       String dep_name, String colleg_name,
			                       String file_name,
			                       String mean,
			                       String std,
			                       String sq,
			                       String corr,
			                       String min,
			                       String max) throws DocumentException, MalformedURLException, IOException{
		    URL chart_img_url = new URL("file:///C:/MEU-system/charts/chart.JPEG");
		    URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
            Image chart_img = Image.getInstance(chart_img_url);
            Image header_img = Image.getInstance(header_img_url);
		    Document document = new Document();
		    String file_path = folder + file_name;
            PdfWriter.getInstance(document, new FileOutputStream(file_path));
            
            
            document.open();
            
	        Anchor anchor = new Anchor();
	      
	        Chapter catPart = new Chapter(new Paragraph(anchor),1);
            Paragraph subPara = new Paragraph();
	        Section subCatPart = catPart.addSection(subPara,0);
	        subPara.add(header_img);
            PdfPTable table1 = new PdfPTable(6);
            
            
            subPara.add(new Paragraph("                            "+"Sample report of the results analysis of a course for a faculty member",
	                smallBold));
            
            subPara.add(new Paragraph("                                               ",
            		catFont));
            PdfPCell table1_cells = new PdfPCell(new Phrase("College"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	    
	        table1_cells = new PdfPCell(new Phrase("Department"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Course"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Section"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Test Type"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Test Score"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase(colleg_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);

	        table1_cells = new PdfPCell(new Phrase(dep_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase(course_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase(section_number));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Internal       final"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("100"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        subCatPart.add(table1);
	        
	        Paragraph subPara2 = new Paragraph();
	        Section subCatPart2 = catPart.addSection(subPara2,0);
	        
	        subPara2.add(new Paragraph("                                                    "+"Statistical Parameters for students' scores",
	                smallBold));
	        
	        
	        subPara2.add(new Paragraph("                                               ",
	        		catFont));
	        PdfPTable table2 = new PdfPTable(2);
	        

	        PdfPCell table2_cells = new PdfPCell(new Phrase("Parameter"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Value"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Mean"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
   
	        table2_cells = new PdfPCell(new Phrase(mean));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	       
	        table2_cells = new PdfPCell(new Phrase("Std. Deviation"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	       
	        table2_cells = new PdfPCell(new Phrase(std));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Skewness"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(sq));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Correlation(final & periodical)"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(corr));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);

	        table2_cells = new PdfPCell(new Phrase("Minimum"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(min));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Maximum"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(max));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	       
	        subCatPart2.add(table2);
	        
	        
	        Paragraph subPara3 = new Paragraph();
	        Section subCatPart3 = catPart.addSection(subPara3,0);
	        
	        PdfPTable table3 = new PdfPTable(1);
	        
	        PdfPCell table3_cells = new PdfPCell(new Phrase("Histogram with normal curve"));
	        table3_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table3.addCell(table3_cells);
	        
	        table3_cells = new PdfPCell(new PdfPCell(chart_img));
	        table3_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3_cells.setPaddingBottom(1);
	        table3_cells.setPaddingTop(1);
	        table3_cells.setPaddingLeft(1);
	        table3_cells.setPaddingRight(1);
	        table3.addCell(table3_cells);
	        
	        subCatPart3.add(table3);
   
	        Paragraph subPara4 = new Paragraph();
	      
	  
	        subPara4.add(new Paragraph("                                   "+"Explanation of statistical indicators and the recommendations",
	                smallBold));
	       
	        subPara4.add(new Paragraph("                                               ",
	        		catFont));
	        
	        Section subCatPart4 = catPart.addSection(subPara4,0);
	        
	        PdfPTable table4 = new PdfPTable(1);
	        
	        PdfPCell table4_cells = new PdfPCell(new Phrase(recommendation(mean, std, sq, corr, false, null)));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table4_cells.setPaddingBottom(10);
	        table4_cells.setPaddingTop(3);
	        table4.addCell(table4_cells);

	        table4_cells = new PdfPCell(new Phrase("Reasons: "));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4_cells.setPaddingBottom(10);
	        table4_cells.setPaddingTop(5);
	        table4.addCell(table4_cells);
	        
	        subCatPart4.add(table4); 
	        
	        document.add(catPart);
            document.close();
            
         
	 }
	 public void generatePdfReportF2(String course_name, 
             String dep_name, String colleg_name,
             String file_name,
             String mean,
             String std,
             String sq,
             String corr,
             String min,
             String max,
             String p_value,
             String df) throws DocumentException, IOException{
		 

		 
		 URL chart_img_url = new URL("file:///C:/MEU-system/charts/chart.JPEG");
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image chart_img = Image.getInstance(chart_img_url);
         Image header_img = Image.getInstance(header_img_url);
         
		 Document document = new Document();
		 String file_path =  folder + file_name ;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),2);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);
         PdfPTable table1 = new PdfPTable(6);
         
         
         subPara.add(new Paragraph("                            "+"Sample report of the results analysis of a course for a faculty member",
	                smallBold));
         
         subPara.add(new Paragraph("                                               ",
        		 redFont));
         PdfPCell table1_cells = new PdfPCell(new Phrase("College"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	    
	        table1_cells = new PdfPCell(new Phrase("Department"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Course"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Section"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Test Type"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Test Score"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase(colleg_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);

	        table1_cells = new PdfPCell(new Phrase(dep_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase(course_name));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("All"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("Internal       final"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("100"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        subCatPart.add(table1);
	        
	        Paragraph subPara2 = new Paragraph();
	        Section subCatPart2 = catPart.addSection(subPara2,0);
	        
	        subPara2.add(new Paragraph("                                                    "+"Statistical Parameters for students' scores",
	                smallBold));
	        
	        
	        subPara2.add(new Paragraph("                                               ",
	        		redFont));
	        PdfPTable table2 = new PdfPTable(2);
	        

	        PdfPCell table2_cells = new PdfPCell(new Phrase("Parameter"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Value"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Mean"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);

	        table2_cells = new PdfPCell(new Phrase(mean));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	       
	        table2_cells = new PdfPCell(new Phrase("Std. Deviation"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	       
	        table2_cells = new PdfPCell(new Phrase(std));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Skewness"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(sq));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Correlation(final & periodical)"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(corr));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);

	        table2_cells = new PdfPCell(new Phrase("Minimum"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(min));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase("Maximum"));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	        table2_cells = new PdfPCell(new Phrase(max));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table2.addCell(table2_cells);
	        
	       
	        subCatPart2.add(table2);
	        
	        
	        Paragraph subPara3 = new Paragraph();
	        Section subCatPart3 = catPart.addSection(subPara3,0);
	        
	        PdfPTable table3 = new PdfPTable(1);
	        
	        
	        PdfPCell table3_cells = new PdfPCell(new Phrase("Histogram with normal curve"));
	        table3_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table3.addCell(table3_cells);
	        
	        table3_cells = new PdfPCell(new PdfPCell(chart_img));
	        table3_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3_cells.setPaddingBottom(1);
	        table3_cells.setPaddingTop(1);
	        table3_cells.setPaddingLeft(1);
	        table3_cells.setPaddingRight(1);
	        table3.addCell(table3_cells);
	        
	        subCatPart3.add(table3);

	        
	        
	        Paragraph subPara4 = new Paragraph();
	        
	        subPara4.add(new Paragraph("                                  "+"Statistical Parameters for compare of Sections students' scores",
	                smallBold));
	        subPara4.add(new Paragraph("                                               ",
	        		redFont));
	        Section subCatPart4 = catPart.addSection(subPara4,0);

            PdfPTable table4 = new PdfPTable(3);
            PdfPCell table4_cells = new PdfPCell(new Phrase("Parameter"));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);
	        
	        table4_cells = new PdfPCell(new Phrase("Degree of freedom"));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);
	        
	        table4_cells = new PdfPCell(new Phrase("Value"));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);

	        table4_cells = new PdfPCell(new Phrase("P-value"));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);
	       
	        table4_cells = new PdfPCell(new Phrase(df));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);
	        
	        table4_cells = new PdfPCell(new Phrase(p_value));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table4.addCell(table4_cells);

	        subCatPart4.add(table4);
	        
	        Paragraph subPara5 = new Paragraph();
	        subPara5.add(new Paragraph("                                   "+"Explanation of statistical indicators and the recommendations",
	                smallBold));
	       
	        subPara5.add(new Paragraph("                                               ",
	        		subFont));
	        
	        Section subCatPart5 = catPart.addSection(subPara5,0);
	        
	        PdfPTable table5 = new PdfPTable(1);
	        
	        PdfPCell table5_cells = new PdfPCell(new Phrase(recommendation(mean, std, sq, corr, true, p_value)));
	        table5_cells.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table5_cells.setPaddingBottom(10);
	        table5_cells.setPaddingTop(3);
	        table5.addCell(table5_cells);
	        
	        table5_cells = new PdfPCell(new Phrase("Reasons: "));
	        table5_cells.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table5_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table5_cells.setPaddingBottom(10);
	        table5_cells.setPaddingTop(5);
	        table5.addCell(table5_cells);
	        
	        
	        
	        subCatPart5.add(table5); 
	        
	        document.add(catPart);
	        document.close();
	        
	 }
	 
	 
	 public String recommendation(String mean, String std, String sq, String corr, boolean isPValue, String pvalue){
		 
		 double mean_ = Double.parseDouble(mean);
		 double diffrence = mean_ - 68.0;
		 String rec5 = "";
		 String stly_above_avg = " that means the average is slightly above the normal by "+dc.format(diffrence)+".";
		 String above_avg = " that means the average is above the normal by "+dc.format(diffrence)+".";
		 String normal_avg = " that means the average is normal.";
		 String under_avg = " that means the average is under the normal by "+dc.format(diffrence)+".";
		 String stly_under_avg = " that means the average is slightly under the normal by "+dc.format(diffrence)+".";
		 
		 String avg_para1 = "The average of students' marks is "+mean +",";;
		 String avg_para2 = null;
		 
		 if(mean_ == 68.0){
			 avg_para2 = normal_avg;}
		 else if(mean_ > 68.0 && mean_ <= 73){
			 avg_para2 = stly_above_avg;}
		 else if(mean_ < 68.0 && mean_ >= 63){
			 avg_para2 = stly_under_avg;}
		 else if(mean_ > 68.0){
			 avg_para2 = above_avg;}
		 else if(mean_ < 68.0){
			 avg_para2 = under_avg;}
		 
		 String rec1 = avg_para1+avg_para2;
		 
		 
		 double std_ = Double.parseDouble(std);
		 
		 String normal_std = " And the deviation of grades from the average is " +std_+",";
		 String under_std =  " shows that the deviation is  under  the average.";
		 String above_std =  " shows that the deviation is above the average.";
		 
		 String std_para = null;
		 
		 if(std_ == 20.0){
			 std_para = normal_std;}
		 else if(std_ > 20.0){
			 std_para = normal_std + above_std;}
		 else if(std_ < 20.0){
			 std_para = normal_std + under_std;}
		 
		 String rec2 = std_para;
		 
		 double sq_ = Double.parseDouble(sq);
		 
		 String normal_sq = " The Skewness is ";
		 String under_sq =  "under the average.";
		 String above_sq =  "above the average.";
		 
		 String sq_para = null;
		 
		 if(sq_ == 0.0){
			 sq_para = normal_sq;}
		 else if(sq_ > 0.0){
			sq_para = normal_sq+ under_sq;}
		 else if(sq_ < 0.0){
			 sq_para = normal_sq + above_sq;}
		 
		 String rec3 = sq_para;
		 double corr_;
		 try{
		 corr_ = Double.parseDouble(corr);
		 }catch(NumberFormatException f){
			 corr_ = 0.0;
		 }
		 String normal_corr = " But the correlation "+corr_;
		 String above_corr =  " says that there is strong correlation between the internal and external marks.";
		 String under_corr =  " says that there is weak correlation between the internal and external marks.";
		 
		 String corr_para = null;
		 
		 if(corr_ == 0.5){
			 corr_para = normal_corr;}
		 else if(corr_ > 0.5){
			corr_para = normal_corr + above_corr;}
		 else if(corr_ < 0.5){
			 corr_para = normal_corr + under_corr;}
		 
		 String rec4 = corr_para;
		 
		 
		 if(isPValue == true){
			 double pval_ = Double.parseDouble(pvalue);
			 
			 String normal_pval = " The value of significance "+pval_;
			 String under_pval =  " means there is difference between sections scores.";
			 
			 String pval_para = null;
			 
			
			 if(pval_< 0.05){
				 pval_para = normal_pval + under_pval;}
			 
			 rec5 = pval_para;
		 }
		 
		 
		 return rec1 + rec2 + rec3 + rec4 + rec5;
	 }
	 
	 public void generateDepSubjectReportPDF_2(String string) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + string;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	     
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
	   
	     Paragraph subPara2 = new Paragraph();
	        Section subCatPart2 = catPart.addSection(subPara2,0);
	        
		    subPara2.add(header_img);

		    subPara2.add(new Paragraph("                                               ",
		    		catFont));
	        subPara2.add(new Paragraph("                                                                       "+"Result Evaluation",
	                smallBold));	        
	        subPara2.add(new Paragraph("                                               ",
	        		catFont));
	       PdfPTable table1 = new PdfPTable(10);
	       
	       PdfPCell table1_cells = new PdfPCell(new Phrase("subject code"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	    
	        table1_cells = new PdfPCell(new Phrase("section number"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("course name"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("total students"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("A"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("B"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("C"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("D"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("F"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        table1_cells = new PdfPCell(new Phrase("PASS"));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table1.addCell(table1_cells);
	        
	        
	        int i = 0;
	        while(i <= (info.dep_report_subject_code.size()-1)){
	        	table1_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.dep_report_section_number.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i)));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.students_count.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.count_A.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.count_B.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.count_C.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.count_D.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        
		        table1_cells = new PdfPCell(new Phrase(info.count_F.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new Phrase(info.std_pass.get(i).toString()));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table1_cells.setPaddingBottom(5);
		        table1_cells.setPaddingTop(5);
		        table1_cells.setPaddingLeft(5);
		        table1_cells.setPaddingRight(5);
		        table1.addCell(table1_cells);
		        

		        
		        i++;
	        }
	        
	        subCatPart2.add(table1);
	        document.add(catPart);
		     document.close();
	 }
	        public void generateDepSubjectReportPDF_3(String string) throws DocumentException, IOException{
	   		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
	            Image header_img = Image.getInstance(header_img_url);
		       
	            
	            String files_path = "C:/MEU-system/department_report/";
	   		 
	   		 Document document = new Document();
	   		 String file_path =  files_path + string;
	            PdfWriter.getInstance(document, new FileOutputStream(file_path));
	            document.open();
	            
	            
	   	     Anchor anchor = new Anchor();
		 

         Paragraph subPara = new Paragraph();
         Chapter catPart1 = new Chapter(new Paragraph(anchor),0);
	        Section subCatPart1 = catPart1.addSection(subPara,0);
		    subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                             "+"All Subjects in the CS department",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	    
	   
	     PdfPTable table = new PdfPTable(5);
	     
	     
	   
	        
	     PdfPCell table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Mean"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Standard Deviation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Skewness"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Correlation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        
	        int i1 = 0;
	        while(i1 <= (info.dep_report_subject_code.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i1).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_mean.get(i1).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_std.get(i1).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_sq.get(i1).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_correlation.get(i1).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i1++;

	        }
	        subCatPart1.add(table);
		     document.add(catPart1);
		     
	   
	     document.close();
	 }
	 /*     Paragraph subPara2 = new Paragraph();
	         Chapter catPart2 = new Chapter(new Paragraph(anchor),0);
		        Section subCatPart2 = catPart2.addSection(subPara2,0);
			    subPara.add(header_img);

		     subPara.add(new Paragraph("                                               ",
		        		catFont));
		     subPara.add(new Paragraph("                                        			     "+"Charts",
		    		 catFont));
		     subPara.add(new Paragraph("                                               ",
		        		catFont));
		    
		   
		     PdfPTable table2 = new PdfPTable(1);
		        
		        PdfPCell table1_cells = new PdfPCell(new Phrase("Mean"));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table2.addCell(table1_cells);
		        
		        table1_cells = new PdfPCell(new PdfPCell(chart_img1));
		        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table1_cells.setPaddingBottom(1);
		        table1_cells.setPaddingTop(1);
		        table1_cells.setPaddingLeft(1);
		        table1_cells.setPaddingRight(1);
		        table2.addCell(table_cells);
		        
		     subCatPart2.add(table2);
		     document.add(catPart2);
	     
	      TimeUnit.SECONDS.sleep(1);

	            URL chart_img_url1 = new URL("file:///C:/MEU-system/dept_charts/chart1.PNG");
	            Image chart_img1 = Image.getInstance(chart_img_url1);
	            
	            URL chart_img_url2 = new URL("file:///C:/MEU-system/dept_charts/chart2.PNG");
	            Image chart_img2 = Image.getInstance(chart_img_url2);
	   		 
	            URL chart_img_url3 = new URL("file:///C:/MEU-system/dept_charts/chart3.PNG");
	            Image chart_img3 = Image.getInstance(chart_img_url3);
	            
	            URL chart_img_url4 = new URL("file:///C:/MEU-system/dept_charts/chart4.PNG");
	            Image chart_img4 = Image.getInstance(chart_img_url4);
	   PdfPCell table1_cells = new PdfPCell(new PdfPCell(chart_img1));
	        table1_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table1_cells.setPaddingBottom(1);
	        table1_cells.setPaddingTop(1);
	        table1_cells.setPaddingLeft(1);
	        table1_cells.setPaddingRight(1);
	        table.addCell(table1_cells);
	        
	        PdfPCell table2_cells = new PdfPCell(new PdfPCell(chart_img2));
	        table2_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table2_cells.setPaddingBottom(1);
	        table2_cells.setPaddingTop(1);
	        table2_cells.setPaddingLeft(1);
	        table2_cells.setPaddingRight(1);
	        table.addCell(table2_cells);
	        
	        PdfPCell table3_cells = new PdfPCell(new PdfPCell(chart_img3));
	        table3_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3_cells.setPaddingBottom(1);
	        table3_cells.setPaddingTop(1);
	        table3_cells.setPaddingLeft(1);
	        table3_cells.setPaddingRight(1);
	        table.addCell(table3_cells);
	        
	        PdfPCell table4_cells = new PdfPCell(new PdfPCell(chart_img4));
	        table4_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table4_cells.setPaddingBottom(1);
	        table4_cells.setPaddingTop(1);
	        table4_cells.setPaddingLeft(1);
	        table4_cells.setPaddingRight(1);
	        table.addCell(table4_cells);
	  public void generateDepSubjectReportPDF_BasedOnSTD(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
         
	    
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);
	     
	     
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                      "+"The Subjects with defects in Standard Deviation",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	    
	   
	     PdfPTable table = new PdfPTable(4);
	     
	     
	     PdfPCell table_cells = new PdfPCell(new Phrase("Subject name"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Standard Deviation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Skewness"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        
	        int i = 0;
	        while(i <= (info.dep_report_subject_name.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_subject_name.get(i)));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_std.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_sq.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }
	 
	 public void generateDepSubjectReportPDF_BasedOnPvalue(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                         "+"The Subjects with defects in P value",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	    
	     PdfPTable table = new PdfPTable(3);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Subject name"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("P value"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        int i = 0;
	        while(i <= (info.dep_report_subject_name.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_subject_name.get(i)));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_pvalue.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		       
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }
	
	 public void generateDepSubjectReportPDF_BasedOnCorr(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                      "+"The Subjects with defects in Correlation",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	    
	   
	     PdfPTable table = new PdfPTable(3);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Subject name"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Correlation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	       
	        int i = 0;
	        while(i <= (info.dep_report_subject_name.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_subject_name.get(i)));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_correlation.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		    
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }
	 
	 public void generateDepSubjectReportPDF_BasedOnMean(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                          "+"The Subjects with defects in Mean",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	   
	   
	     PdfPTable table = new PdfPTable(3);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Subject name"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Mean"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        
	        int i = 0;
	        while(i <= (info.dep_report_subject_name.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_subject_name.get(i)));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_mean.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }

	 
	 public void generateDepSectionReportPDF_BasedOnSTD(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                     "+"The Sections with defects in Standard Deviation",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));

	     PdfPTable table = new PdfPTable(4);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Section number"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Standard Deviation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Skewness"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        
	        int i = 0;
	        while(i <= (info.dep_report_section_number.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_section_number.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_section_std.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_section_sq.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();

	 }
	 
	 public void generateDepSectionReportPDF_BasedOnMean(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";

		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                           "+"The Sections with defects in Mean",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));

	     PdfPTable table = new PdfPTable(3);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Section number"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Mean"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        int i = 0;
	        while(i <= (info.dep_report_section_number.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_section_number.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_section_mean.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }
	 
	 public void generateDepSectionReportPDF_BasedOnCorrelation(String file_name) throws DocumentException, IOException{
		 URL header_img_url = new URL("file:///C:/MEU-system/MEU_report_header.PNG");
         Image header_img = Image.getInstance(header_img_url);
         
		 String files_path = "C:/MEU-system/department_report/";
		 
		 Document document = new Document();
		 String file_path =  files_path + file_name;
         PdfWriter.getInstance(document, new FileOutputStream(file_path));
         document.open();
         
         
	     Anchor anchor = new Anchor();
	        
	     Chapter catPart = new Chapter(new Paragraph(anchor),0);
         Paragraph subPara = new Paragraph();
	     Section subCatPart = catPart.addSection(subPara,0);
	     subPara.add(header_img);

	     subPara.add(new Paragraph("                                               ",
	        		catFont));
	     subPara.add(new Paragraph("                                       "+"The Sections with defects in Correlation",
	    		 catFont));
	     subPara.add(new Paragraph("                                               ",
	        		catFont));

	     PdfPTable table = new PdfPTable(3);
         
	     PdfPCell table_cells = new PdfPCell(new Phrase("Section number"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Subject code"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        table_cells = new PdfPCell(new Phrase("Correlation"));
	        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table_cells.setPaddingBottom(5);
	        table_cells.setPaddingTop(5);
	        table_cells.setPaddingLeft(5);
	        table_cells.setPaddingRight(5);
	        table.addCell(table_cells);
	        
	        int i = 0;
	        while(i <= (info.dep_report_section_number.size()-1)){
	        	table_cells = new PdfPCell(new Phrase(info.dep_report_section_number.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);

		        table_cells = new PdfPCell(new Phrase(info.dep_report_subject_code.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        table_cells = new PdfPCell(new Phrase(info.dep_report_section_correlation.get(i).toString()));
		        table_cells.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table_cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table_cells.setPaddingBottom(5);
		        table_cells.setPaddingTop(5);
		        table_cells.setPaddingLeft(5);
		        table_cells.setPaddingRight(5);
		        table.addCell(table_cells);
		        
		        i++;

	        }
	     
	     subCatPart.add(table);
	     document.add(catPart);
	     document.close();
	 }
*/
	 
}
