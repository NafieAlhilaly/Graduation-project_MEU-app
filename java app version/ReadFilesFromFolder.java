import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.commons.io.FilenameUtils;

import com.itextpdf.text.DocumentException;


public class ReadFilesFromFolder {
	public static boolean flag = false;
	ArrayList<Double> stds_finalExam = new ArrayList<Double>();
	ArrayList<Double> stds_midtermExam = new ArrayList<Double>();
	ArrayList<Double> stds_TotalMarks = new ArrayList<Double>();
	DecimalFormat dc = new DecimalFormat("#.##");
	DecimalFormat dc_pval = new DecimalFormat("#.######");
	DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
	PearsonsCorrelation cor = new PearsonsCorrelation();
	Report report = new Report();
	OneWayAnova anova = new OneWayAnova();
	AnalyzeWindow menu = new AnalyzeWindow();
	final Charts chart = new Charts("Normal Distribution");
	ProgressBar PB= new ProgressBar ();
	DataBase db = new DataBase();
	String folder_name;
	String file_name;
	File file_to_delete = new File("C://Users/tarek/Desktop/stuff/project/Project 1/charts/chart.JPEG");
	static double total_files = 0;
	static int degree_of_freedom = 0;
	static int stds_total = 0;
	static int stds_count = 0;
    public static double pb_value;
    ProgressBar progress_bar = new ProgressBar();
    static int progress = 0;
    String subject_name;
    int dept_number;
    
	   public void getFilesCount(File folder) throws FileNotFoundException, IOException, DocumentException, InterruptedException{
		   folder_name = folder.getName();
		   File[] fileNames = folder.listFiles();
	         for(File file : fileNames){
	             if(file.isDirectory()){
	            	 getFilesCount(file);
	            	 
	             }else{
	            	 total_files = total_files +1;
	             }
	            
	         }
	            
	            
	   }
	
		public void listAllFiles(File folder) throws Exception{
            
			folder_name = folder.getName();
			File[] fileNames = folder.listFiles();
	         
	         for(File file : fileNames){
	             if(file.isDirectory()){
		                listAllFiles(file);
	             }else{
	                 try {
	                	 pb_value = pb_value +1;
	                	 
	                	 readContent(file);
	          		   	
	                 } catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }
	            
	         }
	         
	      	 MaterialInfo mat = new MaterialInfo();
	      	 
	      	 double pvalue_TM_arr[] = null;
	      	 double TM_arr[] = null;
	      	 double IM_arr[] = null;
	      	 double FM_arr[] = null;
	      	 
	      	 
	         for(File file : fileNames){
	            mat.getStds_TotalMarks(file);
	            mat.getStds_internalMarks(file);
	            mat.getStds_finalMarks(file);
	            subject_name = mat.getSubjectName(file);
	            mat.getStdsCounts(file);
	            for(int i = 0; i <= (mat.stds_count_arr.size()-1); i++){
	            	stds_total = stds_total + mat.stds_count_arr.get(i);
	            }
	           
	            TM_arr = new double[mat.stds_TotalMarks.size()];
	            IM_arr = new double[mat.stds_internalMarks.size()];
	            FM_arr = new double[mat.stds_finalMarks.size()];
	            
	            for(int i = 0; i <= (mat.stds_TotalMarks.size()-1); i++){
	         		TM_arr[i] = mat.stds_TotalMarks.get(i);
	         	}
	            
	            
	            for(int i = 0; i <= (mat.stds_internalMarks.size()-1); i++){
	         		IM_arr[i] = mat.stds_internalMarks.get(i);
	         	}
	            
	            
	            for(int i = 0; i <= (mat.stds_finalMarks.size()-1); i++){
	         		FM_arr[i] = mat.stds_finalMarks.get(i);
	         	}
	            
	         }
	         if(menu.t_dep_name == "CS"){
            	 dept_number = 1;
             }else if(menu.t_dep_name == "IS"){
            	 dept_number = 2;
             }else if(menu.t_dep_name == "NE"){
            	 dept_number = 3;
             }else if(menu.t_dep_name == "CE"){
            	 dept_number = 4;
             }
	       
	         String composite_key = dept_number + folder_name + menu.t_year.toString() + menu.t_term.toString();
	         
	         int id = Integer.parseInt(composite_key);
	         int subject_code = Integer.parseInt(folder_name);
	         int term = (int)menu.t_term;
	         int year = (int)menu.t_year;
	         double mean = mat.calcMean(TM_arr);
	         double max = mat.calcMaximum(TM_arr);
	         double min = mat.calcMinimum(TM_arr);
	         double std = mat.calcSD(TM_arr);
	         double sq = mat.calcSQ(TM_arr);
	         double corr = mat.calcCor(FM_arr, IM_arr);
	         mat.getStds_grades();
	         
	         
	         double short_mean = Double.parseDouble(dc.format(mean));
	         double short_std = Double.parseDouble(dc.format(std));
	         double short_sq = Double.parseDouble(dc.format(sq));
	         double short_corr;
	         
	         try{
	         short_corr = Double.parseDouble(dc.format(corr));
	         }catch(NumberFormatException e){
	        	 short_corr = 0;
	         }
	         
	         mat.stds_TotalMarks.clear();
	         
	         for(File file : fileNames){
		            mat.getStds_TotalMarks(file);
		            
		            
		            pvalue_TM_arr = new double[mat.stds_TotalMarks.size()];
		            for(int i = 0; i <= (mat.stds_TotalMarks.size()-1); i++){
		         		pvalue_TM_arr[i] = mat.stds_TotalMarks.get(i);
		         	}
		            
		            mat.mat_data_collection.add(pvalue_TM_arr);
		            mat.stds_TotalMarks.clear();
		         	

		         }	
	         double p_value = anova.anovaPValue(mat.mat_data_collection);
             try{
            	 
	             db.addValuesToSubjectTable(id,subject_name, subject_code,menu.t_dep_name,term, year, short_mean, p_value,max, min 
	                    ,short_corr, short_sq, short_std);
	         
	         }catch(Exception e){}
             
             chart.createCharts(mat.stds_grade_dist_arr,"Subject : " + folder_name);
             
           
             
             
             String report_file_name = menu.t_dep_name +"_"+folder_name + "_" +"_"+ menu.t_year.toString()+"_" + menu.t_term.toString() + ".pdf";
             String toStringMean = dc.format(mean);
             String toStringStd = dc.format(std);
             String toStringSq = dc.format(sq);
             String toStringCorr = dc.format(corr);
             String toStringMin = dc.format(min);
             String toStringMax = dc.format(max);
             String toString_pvalue = dc_pval.format(p_value);
             
             report.generatePdfReportF2(folder_name, menu.t_dep_name, menu.t_college_name, report_file_name, toStringMean, 
            		            		toStringStd, toStringSq, toStringCorr, toStringMin, toStringMax, 
            		            		toString_pvalue,Integer.toString(degree_of_freedom));
	   
             degree_of_freedom = 0;
             
           
             }	      

 
 public void readContent(File file) throws Exception{
 	 MaterialInfo mat = new MaterialInfo();
 	 String report_file_name;
     file_name = FilenameUtils.removeExtension(file.getName());
     mat.getStds_internalMarks(file);
     mat.getStds_finalMarks(file);
     mat.getStds_TotalMarks(file);
     mat.getStds_grades();
     
     if(menu.t_dep_name == "CS"){
    	 dept_number = 1;
     }else if(menu.t_dep_name == "IS"){
    	 dept_number = 2;
     }else if(menu.t_dep_name == "NE"){
    	 dept_number = 3;
     }else if(menu.t_dep_name == "CE"){
    	 dept_number = 4;
     }
     
     mat.getStdsCounts(file);
     
     
     String composite_key = dept_number + file_name + menu.t_year.toString() + menu.t_term.toString() ;
     
     long id = Long.parseLong(composite_key);
     int subject_code = Integer.parseInt(folder_name);
     int section_number =Integer.parseInt(file_name);
     int term = (int) menu.t_term;
     int year = (int)menu.t_year;
     double mean = mat.calcMean(mat.stds_TotalMarks);
     double max = mat.calcMaximum(mat.stds_TotalMarks);
     double min = mat.calcMinimum(mat.stds_TotalMarks);
     double std = mat.calcSD(mat.stds_TotalMarks);
     double sq = mat.calcSQ(mat.stds_TotalMarks);
     double corr = mat.calcCor(mat.stds_finalMarks, mat.stds_internalMarks);
     double short_mean = Double.parseDouble(dc.format(mean));
     double short_std = Double.parseDouble(dc.format(std));
     double short_sq = Double.parseDouble(dc.format(sq));
     double short_corr;
     
     
     for(int i = 0; i <= (mat.stds_count_arr.size() - 1);i++){
    	 stds_count =  stds_count + mat.stds_count_arr.get(i);
    	 
     }
     degree_of_freedom = degree_of_freedom + (stds_count - 1);
     stds_count = 0;
     try{
     short_corr = Double.parseDouble(dc.format(corr));
     }catch(NumberFormatException e){
    	 short_corr = 0;
     }
     double grade_A = mat.stds_grades_numbers[0];
     double grade_B = mat.stds_grades_numbers[1];
     double grade_C = mat.stds_grades_numbers[2];
     double grade_D = mat.stds_grades_numbers[3];
     double grade_F = mat.stds_grades_numbers[4];

     
     try{
     db.addValuesToSectionTable(id, subject_code,section_number,menu.t_dep_name , term , year, short_mean, max, min,grade_A,grade_B,grade_C, 
             grade_D, grade_F, short_corr, short_sq, short_std);
     }catch(Exception e){}
     
     try(BufferedReader br  = new BufferedReader(new FileReader(file))){
   
     }
     
     try{
         db.addValuesToMarksTable( id, subject_code, section_number);
         }catch(Exception e){}
         
         try(BufferedReader br  = new BufferedReader(new FileReader(file))){
       
         }
     
     chart.createCharts(mat.stds_grade_dist_arr,"Subject : "+ folder_name +" ,"+ "Section : "+file_name);
     
     
     report_file_name = menu.t_dep_name +"_"+folder_name + "_(" + file_name +")_"+ menu.t_year.toString()+"_" + menu.t_term.toString() + ".pdf";
     String toStringMean = dc.format(mean);
     String toStringStd = dc.format(std);
     String toStringSq = dc.format(sq);
     String toStringCorr = dc.format(corr);
     String toStringMin = dc.format(min);
     String toStringMax = dc.format(max);
     report.generatePdfReportF1(file_name, folder_name, menu.t_dep_name, menu.t_college_name,report_file_name,
    		                  toStringMean,toStringStd,
    		                  toStringSq,toStringCorr,toStringMin,toStringMax);
     file_to_delete.delete();
     
     
     mat.stds_count_arr.clear();
     
     progress = (int) ((pb_value/total_files)*100);
     progress_bar.fill(progress);
     
     if(pb_value == total_files){
    	 progress_bar.button.setVisible(true);
    	 progress_bar.done_label.setVisible(true); 
     }
     
 }

}
