import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class AnalyzeWindow {
	
	public Charts chart = new Charts("");
	DataBase db = new DataBase();
	MaterialInfo mat = new MaterialInfo();
	
	public static String choose_folder;
    public static String t_college_name;
	public static String t_dep_name;
	public static Object t_year;
	public static Object t_term;
	
	
	public static ProgressBar progress_bar = new ProgressBar ();
			

					  
	public void mainWindow(Stage teatcher_stage){
		Button generate_teatcher_reports_button = new Button("Start");
		
		generate_teatcher_reports_button.setFont(new Font("Times New Roman", 22));

		
		ChoiceBox college_choiceBox = new ChoiceBox();
		ChoiceBox dep_choiceBox = new ChoiceBox();
		ChoiceBox year_choiceBox = new ChoiceBox();
		ChoiceBox term_choiceBox = new ChoiceBox();
		
		
		generate_teatcher_reports_button.setTranslateX(120);
		generate_teatcher_reports_button.setTranslateY(300);
		generate_teatcher_reports_button.setPrefSize(350, 40);
        
		college_choiceBox.getItems().addAll("College of Computer science");
		dep_choiceBox.getItems().addAll("CS","IS","NE","CE");
		year_choiceBox.getItems().addAll(2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033 ,2034, 2035, 2036, 2037, 2038, 2039, 2040);
		term_choiceBox.getItems().addAll(1,2);
		
		college_choiceBox.getSelectionModel().selectFirst();
		dep_choiceBox.getSelectionModel().selectFirst();
		year_choiceBox.getSelectionModel().selectFirst();
		term_choiceBox.getSelectionModel().selectFirst();
		
		college_choiceBox.setTranslateX(120);
		college_choiceBox.setTranslateY(50);
       
		dep_choiceBox.setTranslateX(120);
		dep_choiceBox.setTranslateY(100);
		
		year_choiceBox.setTranslateX(120);
		year_choiceBox.setTranslateY(150);

		term_choiceBox.setTranslateX(120);
		term_choiceBox.setTranslateY(200);
		
		Group main_root = new Group();
		 
        main_root.getChildren().add(generate_teatcher_reports_button);
        main_root.getChildren().add(college_choiceBox);
        main_root.getChildren().add(dep_choiceBox);
        main_root.getChildren().add(year_choiceBox);
        main_root.getChildren().add(term_choiceBox);


        Scene main_scene = new Scene(main_root, 600, 400);
 
        teatcher_stage.setTitle("Sample Application");
        teatcher_stage.setScene(main_scene);
        teatcher_stage.show();
		
		   
        generate_teatcher_reports_button.setOnAction(ActionEvent  -> {
        	 if (ActionEvent.getSource()==generate_teatcher_reports_button){
        		 teatcher_stage.close();
        		 AnalyzeWindow menu = new AnalyzeWindow();
   			  
        		t_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
             	t_dep_name = dep_choiceBox.getSelectionModel().getSelectedItem().toString();
             	t_year = year_choiceBox.getSelectionModel().getSelectedItem();
             	t_term = term_choiceBox.getSelectionModel().getSelectedItem();            
	  try {
				  
			      String folder_path;
			      folder_path = menu.getFolderPath();
			      progress_bar.create();
				  File folder = new File(folder_path);
				  
				  ReadFilesFromFolder listFiles = new ReadFilesFromFolder();
				  listFiles.getFilesCount(folder);
				  try{
				  listFiles.listAllFiles(folder); 
				  }catch(Exception q){}
		         
			  } catch (IOException e1) {
					e1.printStackTrace();} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
	    }
        	 });		    
		  
	}
	
	
	
	public String getFolderPath() throws IOException{
		try{
			
		   JFileChooser chooser = new JFileChooser();
		   chooser.addChoosableFileFilter(new FileNameExtensionFilter("*xls", "xls"));
	       chooser.setCurrentDirectory(new java.io.File("."));
	       chooser.setDialogTitle("choose a file");
	       chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	       chooser.setAcceptAllFileFilterUsed(true);

	       if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	   chooser.setVisible(false);
	    	   } else {
	                System.out.println("No Selection ");}
		
	        choose_folder = chooser.getSelectedFile().toString();
	    
	    
		    
		}
		catch(Exception e){
			System.out.println("No Selection");}
		return choose_folder;
		
	}

	/*public void collageSubjectsIssuesInStd_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_std.size();		
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_std.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;		
		mat.all_subjects.clear();	
		mat.dep_report_subject_std.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_std.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;		
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();

		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_std.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();

		chart.createCollageReportForSubjectsBar(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"Standard Deviation",year, term);
		
	}
	public void collageSubjectsIssuesInPvalue_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_pvalue.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_pvalue.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_pvalue.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_pvalue.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsBar(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"p value", year, term);
		
	}
	public void collageSubjectsIssuesInCorrelation_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_correlation.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_correlation.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_correlation.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_correlation.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsBar(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"correlation", year, term);
		
	}
	public void collageSubjectsIssuesInMean_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_mean.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_mean.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_mean.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_mean.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsBar(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE," mean", year, term);
		
	}
	
	public void collageSectionsIssuesInStd_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_std.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_std.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_std.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_std.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_std.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();
		

		chart.createCollageReportForSectionsBar(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Standard Deviation", year, term);
		
	}
	public void collageSectionsIssuesInCorrelation_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_correlation.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_correlation.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_correlation.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_correlation.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_correlation.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();

		chart.createCollageReportForSectionsBar(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Correlation", year, term);
		
	}
	public void collageSectionsIssuesInMean_BarChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_mean.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_mean.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_mean.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_mean.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_mean.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();


		chart.createCollageReportForSectionsBar(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Mean", year, term);
		
	}

	public void collageSubjectsIssuesInStd_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_std.size();		
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_std.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;		
		mat.all_subjects.clear();	
		mat.dep_report_subject_std.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_std.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;		
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();

		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnSTD(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_std.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.all_subjects.clear();
		mat.dep_report_subject_std.clear();

		chart.createCollageReportForSubjectsPie(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"Standard Deviation",year, term);
		
	}
	public void collageSubjectsIssuesInPvalue_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_pvalue.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_pvalue.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_pvalue.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnPvalue(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_pvalue.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_pvalue.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsPie(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"P value", year, term);
	
	}
	public void collageSubjectsIssuesInCorrelation_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_correlation.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_correlation.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_correlation.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnCorrelation(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_correlation.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_correlation.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsPie(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"Correlation", year, term);
		
	}
	public void collageSubjectsIssuesInMean_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double subjects_counter_CS;
		double subjects_counter_IS;
		double subjects_counter_NE;
		double subjects_counter_CE;
		
		db.getAllFromSubject(year, term, "CS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"CS");
		subjects_counter_CS = mat.dep_report_subject_mean.size();
		double subjects_per_CS = (subjects_counter_CS/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "IS");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"IS");
		subjects_counter_IS = mat.dep_report_subject_mean.size();
		double subjects_per_IS = (subjects_counter_IS/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "NE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"NE");
		subjects_counter_NE = mat.dep_report_subject_mean.size();
		double subjects_per_NE = (subjects_counter_NE/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		db.getAllFromSubject(year, term, "CE");
		total_subjects = mat.all_subjects.size();
		db.getDataFromSubjectTableBasedOnMean(range, year, term,"CE");
		subjects_counter_CE = mat.dep_report_subject_mean.size();
		double subjects_per_CE = (subjects_counter_CE/total_subjects)*100;
		mat.dep_report_subject_mean.clear();
		mat.all_subjects.clear();
		
		chart.createCollageReportForSubjectsPie(subjects_per_CS, subjects_per_IS, subjects_per_NE, subjects_per_CE,"Mean", year, term);

	}
	
	
	public void collageSectionsIssuesInStd_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_std.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_std.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_std.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_std.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnSTD(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_std.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_std.clear();

		chart.createCollageReportForSectionsPie(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Standard Deviation", year, term);
		
	}
	public void collageSectionsIssuesInCorrelation_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_correlation.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_correlation.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_correlation.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_correlation.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnCorrelation(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_correlation.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_correlation.clear();

		chart.createCollageReportForSectionsPie(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Correlation", year, term);
	
	}
	public void collageSectionsIssuesInMean_PieChart(double range, int year, int term) throws SQLException{
		double total_subjects;
		double sections_counter_CS;
		double sections_counter_IS;
		double sections_counter_NE;
		double sections_counter_CE;
		
		db.getAllFromSection(year, term, "CS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"CS");
		sections_counter_CS = mat.dep_report_section_mean.size();
		double sections_per_CS = (sections_counter_CS/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();

		
		db.getAllFromSection(year, term, "IS");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"IS");
		sections_counter_IS = mat.dep_report_section_mean.size();
		double sections_per_IS = (sections_counter_IS/total_subjects)*100;
		mat.all_sections.clear();	
		mat.dep_report_section_mean.clear();

		
		db.getAllFromSection(year, term, "NE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"NE");
		sections_counter_NE = mat.dep_report_section_mean.size();
		double sections_per_NE = (sections_counter_NE/total_subjects)*100;	
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();


		db.getAllFromSection(year, term, "CE");
		total_subjects = mat.all_sections.size();
		db.getDataFromSectionTableBasedOnMean(range, year, term,"CE");
		sections_counter_CE = mat.dep_report_section_mean.size();
		double sections_per_CE = (sections_counter_CE/total_subjects)*100;
		mat.all_sections.clear();
		mat.dep_report_section_mean.clear();

		chart.createCollageReportForSectionsPie(sections_per_CS, sections_per_IS, sections_per_NE, sections_per_CE,"Correlation", year, term);
		
	}
*/
}
