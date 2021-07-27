
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;



import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;



import com.itextpdf.text.DocumentException;


public class MainMenuWindow extends Application {
	MaterialInfo info = new MaterialInfo();
    Charts x = new Charts("");
	AnalyzeWindow az = new AnalyzeWindow();
	DataBase db = new DataBase();
	Report rep = new Report();
	public static String d_college_name;
	public static String d_dep_name;
	public static Object d_year;
	public static Object d_term;
	public static Object d_std;
	public String file_name;
	public int section_report_type;
	public int subject_report_type;
	public int rep_type;
	public int chart_type;
	
	public void createMainWindow(Stage main_stage) {
	
		Label l1 = new Label("This project done by : ");
		Label l2 = new Label("Supervisor : ");
		Label student1 = new Label("   -Nafie Ibrahim AL-Hilaly || 435804465");
		Label student2 = new Label("    -Tarek M.Maziad Barakat   ||  435822579");
		Label student3 = new Label("    -Fawaz Mohanned Nasser || 434805768");
		Label student4 = new Label("   -Abdulaziz Yahya Saeed || 432802575");
		Label supervisor = new Label("-Dr.Talal Qaid");
		
		Button teatcher_reports_button = new Button("Analyze Data and generate teacher report");
		Button dep_reports_button = new Button("Generate dapertment reports");
		Button collage_reports_button = new Button("Generate collage reports");
		
		
		
		Stage stage = new Stage();

		l1.setTranslateX(70);
		l1.setTranslateY(20);
		l1.setScaleX(1.5);
		
		student1.setTranslateX(190);
		student1.setTranslateY(50);
		student1.setScaleX(1.5);
		
		student2.setTranslateX(190);
		student2.setTranslateY(75);
		student2.setScaleX(1.5);

		student3.setTranslateX(190);
		student3.setTranslateY(100);
		student3.setScaleX(1.5);
		
		student4.setTranslateX(190);
		student4.setTranslateY(125);
		student4.setScaleX(1.5);
		
		l2.setTranslateX(70);
		l2.setTranslateY(160);
		l2.setScaleX(1.5);

		
		supervisor.setTranslateX(190);
		supervisor.setTranslateY(185);
		supervisor.setScaleX(1.5);

		
		teatcher_reports_button.setTranslateX(150);
		teatcher_reports_button.setTranslateY(230);
        teatcher_reports_button.setPrefSize(300, 40);
        
        dep_reports_button.setTranslateX(150);
        dep_reports_button.setTranslateY(280);
        dep_reports_button.setPrefSize(300, 40);
        
        collage_reports_button.setTranslateX(150);
        collage_reports_button.setTranslateY(330);
        collage_reports_button.setPrefSize(300, 40);
		
		Group main_root = new Group();

        main_root.getChildren().add(teatcher_reports_button);
        main_root.getChildren().add(dep_reports_button);
        main_root.getChildren().add(collage_reports_button);
        main_root.getChildren().add(student1);
        main_root.getChildren().add(student2);
        main_root.getChildren().add(student3);
        main_root.getChildren().add(student4);

        main_root.getChildren().add(supervisor);
        main_root.getChildren().add(l1);
        main_root.getChildren().add(l2);

 
        Scene main_scene = new Scene(main_root, 600, 400);
 
        main_stage.setTitle("MEU Application");
 
        main_stage.setScene(main_scene);
        main_stage.show();
        
        teatcher_reports_button.setOnAction( ActionEvent  -> {
            az.mainWindow(stage);
        });
        
       dep_reports_button.setOnAction( ActionEvent  -> {
        	createDepWindow(stage);
            
        });}
        
        /*collage_reports_button.setOnAction( ActionEvent  -> {

        	createCollageWindow(stage);           
        });
	*/
       
       public void createDepWindow(Stage dep_stage){
		
		Button generate_dep_reports_button = new Button("Generate Reports");
		
		generate_dep_reports_button.setFont(new Font("Times New Roman", 22));
		

		ChoiceBox college_choiceBox = new ChoiceBox();
		ChoiceBox dep_choiceBox = new ChoiceBox();
		ChoiceBox year_choiceBox = new ChoiceBox();
		ChoiceBox term_choiceBox = new ChoiceBox();
		
		college_choiceBox.getItems().addAll("College of Computer science");
		dep_choiceBox.getItems().addAll("CS","IS","NE","CE");
		year_choiceBox.getItems().addAll(2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033 ,2034, 2035, 2036, 2037, 2038, 2039, 2040);
		term_choiceBox.getItems().addAll(1,2);
		
		
		college_choiceBox.getSelectionModel().selectFirst();
		dep_choiceBox.getSelectionModel().selectFirst();
		year_choiceBox.getSelectionModel().selectFirst();
		term_choiceBox.getSelectionModel().selectFirst();
		
		generate_dep_reports_button.setTranslateX(120);
		generate_dep_reports_button.setTranslateY(330);
		generate_dep_reports_button.setPrefSize(350, 40);
		
		college_choiceBox.setTranslateX(120);
		college_choiceBox.setTranslateY(20);
       
		dep_choiceBox.setTranslateX(120);
		dep_choiceBox.setTranslateY(70);
		
		year_choiceBox.setTranslateX(120);
		year_choiceBox.setTranslateY(120);

		term_choiceBox.setTranslateX(120);
		term_choiceBox.setTranslateY(170);
		
		Group main_root = new Group();

		
		main_root.getChildren().add(generate_dep_reports_button);
        main_root.getChildren().add(college_choiceBox);
        main_root.getChildren().add(dep_choiceBox);
        main_root.getChildren().add(year_choiceBox);
        main_root.getChildren().add(term_choiceBox);
        
        Scene main_scene = new Scene(main_root, 600, 400);
        
        dep_stage.setTitle("MEU applecation");
       
        
        generate_dep_reports_button.setOnAction( ActionEvent  -> {
        	

        	d_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
			d_dep_name = dep_choiceBox.getSelectionModel().getSelectedItem().toString();
			d_year = year_choiceBox.getSelectionModel().getSelectedItem();
			d_term = term_choiceBox.getSelectionModel().getSelectedItem();
    	    

			int year = (int)d_year;
			int term = (int)d_term;

			Stage stage = new Stage();
        	
			try {
				db.getAllFromSection(year, term);
				file_name = d_dep_name + " Department, " + "Semester " + d_term; 
    			rep.generateDepSubjectReportPDF_2(file_name + ".pdf");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			info.dep_report_section_number.clear();
			info.dep_report_subject_name.clear();
			info.dep_report_subject_code.clear();
			info.dep_report_subject_std.clear();
			info.dep_report_subject_pvalue.clear();
			info.dep_report_subject_correlation.clear();
			info.dep_report_subject_mean.clear();
			info.dep_report_subject_sq.clear();

			info.count_A.clear();
			info.count_B.clear();
			info.count_C.clear();
			info.count_D.clear();
			info.count_F.clear();
			info.students_count.clear();
			
			
			try {
				
				db.getAllFromSubject(year, term);
				file_name = d_dep_name +  "  Semester " + d_term; 
				rep.generateDepSubjectReportPDF_3(file_name + ".pdf");
				x.createBarchartBasedOnMean();
				x.createBarchartBasedOnCorrelaation();
				x.createBarchartBasedOnStd();
				x.createLinechartBasedOnSkewness();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        dep_stage.close();
        });
        
        dep_stage.setScene(main_scene);
        dep_stage.show();
        
        
        
	}
	/*public void createDepWindow(Stage dep_stage){
		
		Button generate_dep_reports_button = new Button("Generate Reports");
		
		generate_dep_reports_button.setFont(new Font("Times New Roman", 22));

		Label err = new Label();
		
		ChoiceBox college_choiceBox = new ChoiceBox();
		ChoiceBox dep_choiceBox = new ChoiceBox();
		ChoiceBox year_choiceBox = new ChoiceBox();
		ChoiceBox term_choiceBox = new ChoiceBox();
		
		TextField std_textfield = new TextField();
		
		RadioButton r_std = new RadioButton("Standard Deviation");
        RadioButton r_pvalue = new RadioButton("P value");
        RadioButton r_corr = new RadioButton("Correlation");
        RadioButton r_mean= new RadioButton("Mean");
		RadioButton rep_type1 = new RadioButton("Subject");
		RadioButton rep_type2 = new RadioButton("Section");

		rep_type1.setFont(new Font("Times New Roman", 19));
		rep_type1.setTextFill(javafx.scene.paint.Color.BLUE);

		rep_type2.setFont(new Font("Times New Roman", 19));
		rep_type2.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_std.setFont(new Font("Times New Roman", 19));
		r_std.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_pvalue.setFont(new Font("Times New Roman", 19));
		r_pvalue.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_corr.setFont(new Font("Times New Roman", 19));
		r_corr.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_mean.setFont(new Font("Times New Roman", 19));
		r_mean.setTextFill(javafx.scene.paint.Color.BLUE);

		
		err.setTextFill(javafx.scene.paint.Color.RED);

        r_std.setTranslateX(35);
        r_std.setTranslateY(260);
        
        r_pvalue.setTranslateX(220);
        r_pvalue.setTranslateY(260);
        
        r_corr.setTranslateX(315);
        r_corr.setTranslateY(260);
		
        r_mean.setTranslateX(440);
        r_mean.setTranslateY(260);
        
        rep_type1.setTranslateX(220);
        rep_type1.setTranslateY(205);
        
        rep_type2.setTranslateX(315);
        rep_type2.setTranslateY(205);
        

		generate_dep_reports_button.setTranslateX(120);
		generate_dep_reports_button.setTranslateY(330);
		generate_dep_reports_button.setPrefSize(350, 40);
        
		college_choiceBox.getItems().addAll("College of Computer science");
		dep_choiceBox.getItems().addAll("CS","IS","NE","CE");
		year_choiceBox.getItems().addAll(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033 ,2034, 2035, 2036, 2037, 2038, 2039, 2040);
		term_choiceBox.getItems().addAll(1,2);
		
		
		college_choiceBox.getSelectionModel().selectFirst();
		dep_choiceBox.getSelectionModel().selectFirst();
		year_choiceBox.getSelectionModel().selectFirst();
		term_choiceBox.getSelectionModel().selectFirst();
		
		college_choiceBox.setTranslateX(120);
		college_choiceBox.setTranslateY(20);
       
		dep_choiceBox.setTranslateX(120);
		dep_choiceBox.setTranslateY(70);
		
		year_choiceBox.setTranslateX(120);
		year_choiceBox.setTranslateY(120);

		term_choiceBox.setTranslateX(120);
		term_choiceBox.setTranslateY(170);
		
		
		
		std_textfield.setTranslateX(210);
		std_textfield.setTranslateY(290);
		
		err.setTranslateX(400);
		err.setTranslateY(294);
		
		Group main_root = new Group();
		ToggleGroup t_group = new ToggleGroup();
		ToggleGroup t_group2 = new ToggleGroup();

		r_std.setToggleGroup(t_group);
		r_pvalue.setToggleGroup(t_group);
		r_corr.setToggleGroup(t_group);
		r_mean.setToggleGroup(t_group);
		rep_type1.setToggleGroup(t_group2);
		rep_type2.setToggleGroup(t_group2);
		
        main_root.getChildren().add(generate_dep_reports_button);
        main_root.getChildren().add(college_choiceBox);
        main_root.getChildren().add(dep_choiceBox);
        main_root.getChildren().add(year_choiceBox);
        main_root.getChildren().add(term_choiceBox);
        main_root.getChildren().add(std_textfield);
        main_root.getChildren().add(r_std);
        main_root.getChildren().add(r_pvalue);
        main_root.getChildren().add(r_corr);
        main_root.getChildren().add(r_mean);
        main_root.getChildren().add(err);
        main_root.getChildren().add(rep_type1);
        main_root.getChildren().add(rep_type2);
       
        Scene main_scene = new Scene(main_root, 600, 400);
        
        
        std_textfield.setOnAction( ActionEvent  -> {
        	err.setVisible(false);
        });
        
        rep_type2.setOnAction( ActionEvent  -> {
         r_pvalue.setVisible(false);
        	r_std.setTranslateX(90);
            r_std.setTranslateY(260);
            
            r_corr.setTranslateX(275);
            r_corr.setTranslateY(260);
    		
            r_mean.setTranslateX(400);
            r_mean.setTranslateY(260);
            
        });
        
        
        rep_type1.setOnAction( ActionEvent  -> {
        
        	r_pvalue.setVisible(true);
            r_std.setTranslateX(35);
            r_std.setTranslateY(260);
            
            r_pvalue.setTranslateX(220);
            r_pvalue.setTranslateY(260);
            
            r_corr.setTranslateX(315);
            r_corr.setTranslateY(260);
    		
            r_mean.setTranslateX(440);
            r_mean.setTranslateY(260);
            
        });
        dep_stage.setTitle("MEU applecation");
 
        dep_stage.setScene(main_scene);
        dep_stage.show();
        
        t_group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
            	
               if (t_group2.getSelectedToggle() == rep_type1) {
            	   rep_type =1;
            	   
              }else if(t_group2.getSelectedToggle() == rep_type2){
            	  	rep_type =2;
            	  	
            	  	
              }
            }

          });
        
        t_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
            	
               if (t_group.getSelectedToggle() == r_std) {
            	   	std_textfield.setPromptText("Enter standard deviation");
            	   if(rep_type == 1){
            		   subject_report_type = 1;
            	   }
            	   else{section_report_type = 1;
            	   }
               }
               else if(t_group.getSelectedToggle() == r_pvalue){
            	   std_textfield.setPromptText("Enter P value");
            	  if(rep_type == 1){
                  	subject_report_type = 2;
                  }
               }
               else if(t_group.getSelectedToggle() == r_corr){
            	   std_textfield.setPromptText("Enter Correlation");
            	  if(rep_type == 1){
                    	subject_report_type = 3;
                    }else{section_report_type = 2;
                    }
              }
               else if(t_group.getSelectedToggle() == r_mean){
            	  	std_textfield.setPromptText("Enter Range");
            	  if(rep_type == 1){
                    	subject_report_type = 4;
            	  }else{section_report_type = 3;
                  }
              }
            }

          });

        
        generate_dep_reports_button.setOnAction( ActionEvent  -> {
        	
        	if(rep_type == 1){
        		info.dep_report_section_number.clear();
        		info.dep_report_subject_name.clear();
        		info.dep_report_subject_code.clear();
        		info.dep_report_subject_std.clear();
        		info.dep_report_subject_pvalue.clear();
        		info.dep_report_subject_correlation.clear();
        		info.dep_report_subject_mean.clear();

        		if(std_textfield.getText().isEmpty()){
        			err.setText("this should not be empty");
            	
        		}else{
        			d_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
        			d_dep_name = dep_choiceBox.getSelectionModel().getSelectedItem().toString();
        			d_year = year_choiceBox.getSelectionModel().getSelectedItem();
        			d_term = term_choiceBox.getSelectionModel().getSelectedItem();
        			d_std = std_textfield.getText().toString();
            	    
        			
        			
        			int year = (int)d_year;
        			int term = (int)d_term;
        			double std = Double.parseDouble((String) d_std);
            	
        			Stage stage = new Stage();
            	
        			try {
        				if(subject_report_type == 1){
        					db.getAllFromSubject(std, year, term,d_dep_name);
        					generateDepSubjectReportPDF_2(stage);
        				}else if(subject_report_type == 2){
        					db.getDataFromSubjectTableBasedOnPvalue(std, year, term,d_dep_name);
        					generateDeptSubjectReportInTableViewForPvalue(stage);
        				}else if(subject_report_type == 3){
        					db.getDataFromSubjectTableBasedOnCorrelation(std, year, term,d_dep_name);
        					generateDeptSubjectReportInTableViewForCorrelation(stage);
        				}else if(subject_report_type == 4){
        					db.getDataFromSubjectTableBasedOnMean(std, year, term,d_dep_name);
        					generateDeptSubjectReportInTableViewForMean(stage);
        				}
            		
        				
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			dep_stage.close();
        		}
            
        	}else if(rep_type == 2){
            	info.dep_report_section_number.clear();
            	info.dep_report_subject_name.clear();
         	    info.dep_report_subject_code.clear();
         	    info.dep_report_section_std.clear();
         	    info.dep_report_section_correlation.clear();
         	    info.dep_report_section_mean.clear();

                if(std_textfield.getText().isEmpty()){
                	err.setText("this should not be empty");
                	
                }else{
                	d_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
                	d_dep_name = dep_choiceBox.getSelectionModel().getSelectedItem().toString();
                	d_year = year_choiceBox.getSelectionModel().getSelectedItem();
                	d_term = term_choiceBox.getSelectionModel().getSelectedItem();
                	d_std = std_textfield.getText().toString();
                	
                	int year = (int)d_year;
                	int term = (int)d_term;
                	double std = Double.parseDouble((String) d_std);
                	
                	Stage stage = new Stage();
                	
                	try {
                		 if(section_report_type == 1){
                			db.getDataFromSectionTableBasedOnSTD(std, year, term,d_dep_name);
        					generateDeptSectionReportInTableViewForSTD(stage);
                		}else if(section_report_type == 2){
                			db.getDataFromSectionTableBasedOnCorrelation(std, year, term,d_dep_name);
        					generateDeptSectionReportInTableViewForCorrelation(stage);
                		}else if(section_report_type == 3){
                			db.getDataFromSectionTableBasedOnMean(std, year, term,d_dep_name);
        					generateDeptSectionReportInTableViewForMean(stage);
                		}
                		
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
                	dep_stage.close();
                }
        	}
        });
        
        
        
	}
	*/
	

   /* public void createCollageWindow(Stage dep_stage){
    	int sub_rep_type;
    	Button generate_clg_reports_button = new Button("Generate Reports");
    	TextField std_textfield = new TextField();
    	Label err = new Label();
 		
    	ChoiceBox college_choiceBox = new ChoiceBox();
		ChoiceBox year_choiceBox = new ChoiceBox();
		ChoiceBox term_choiceBox = new ChoiceBox();
		
		RadioButton r_sections = new RadioButton("Sections");
        RadioButton r_depts = new RadioButton("Subjects");
        
        RadioButton r_std = new RadioButton("Standard Deviation");
        RadioButton r_pvalue = new RadioButton("P value");
        RadioButton r_corr = new RadioButton("Correlation");
        RadioButton r_mean= new RadioButton("Mean");
        RadioButton r_pie= new RadioButton("Pie chart");
        RadioButton r_bar= new RadioButton("Bar chart");

        r_sections.setFont(new Font("Times New Roman", 19));
        r_sections.setTextFill(javafx.scene.paint.Color.BLUE);

        r_depts.setFont(new Font("Times New Roman", 19));
        r_depts.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_std.setFont(new Font("Times New Roman", 19));
		r_std.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_pvalue.setFont(new Font("Times New Roman", 19));
		r_pvalue.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_corr.setFont(new Font("Times New Roman", 19));
		r_corr.setTextFill(javafx.scene.paint.Color.BLUE);
		
		r_mean.setFont(new Font("Times New Roman", 19));
		r_mean.setTextFill(javafx.scene.paint.Color.BLUE);

		r_pie.setFont(new Font("Times New Roman", 19));
	    r_pie.setTextFill(javafx.scene.paint.Color.BLUE);
	    
	    r_bar.setFont(new Font("Times New Roman", 19));
	    r_bar.setTextFill(javafx.scene.paint.Color.BLUE);
	    
		college_choiceBox.getItems().addAll("College of Computer science");
		year_choiceBox.getItems().addAll(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033 ,2034, 2035, 2036, 2037, 2038, 2039, 2040);
		term_choiceBox.getItems().addAll(1,2);
		
		generate_clg_reports_button.setTranslateX(120);
		generate_clg_reports_button.setTranslateY(350);
		generate_clg_reports_button.setPrefSize(350, 40);
		
		college_choiceBox.getSelectionModel().selectFirst();
		year_choiceBox.getSelectionModel().selectFirst();
		term_choiceBox.getSelectionModel().selectFirst();
		
		college_choiceBox.getSelectionModel().selectFirst();
		year_choiceBox.getSelectionModel().selectFirst();
		term_choiceBox.getSelectionModel().selectFirst();
		
		college_choiceBox.setTranslateX(120);
		college_choiceBox.setTranslateY(30);
        
		std_textfield.setTranslateX(210);
		std_textfield.setTranslateY(310);
	
		
 		err.setTranslateX(350);
 		err.setTranslateY(294);
       
		year_choiceBox.setTranslateX(120);
		year_choiceBox.setTranslateY(80);

		term_choiceBox.setTranslateX(120);
		term_choiceBox.setTranslateY(130);
		
		r_sections.setTranslateX(210);
		r_sections.setTranslateY(170);
        
		r_depts.setTranslateX(325);
		r_depts.setTranslateY(170);
		
		r_pvalue.setTranslateX(220);
        r_pvalue.setTranslateY(220);
         
		r_std.setTranslateX(35);
        r_std.setTranslateY(220);
        
        r_corr.setTranslateX(315);
        r_corr.setTranslateY(220);
		
        r_mean.setTranslateX(440);
        r_mean.setTranslateY(220);
		
        r_pie.setTranslateX(210);
		r_pie.setTranslateY(270);
        
		r_bar.setTranslateX(325);
		r_bar.setTranslateY(270);
        
		ToggleGroup t_group = new ToggleGroup();
		ToggleGroup t_group2 = new ToggleGroup();
		ToggleGroup t_group3 = new ToggleGroup();
		

		r_sections.setToggleGroup(t_group);
		r_depts.setToggleGroup(t_group);
		
		r_std.setToggleGroup(t_group2);
		r_pvalue.setToggleGroup(t_group2);
		r_corr.setToggleGroup(t_group2);
		r_mean.setToggleGroup(t_group2);
		r_pie.setToggleGroup(t_group3);
		r_bar.setToggleGroup(t_group3);
		
		Group main_root = new Group();
		main_root.getChildren().add(college_choiceBox);
		main_root.getChildren().add(year_choiceBox);
		main_root.getChildren().add(term_choiceBox);
		main_root.getChildren().add(r_sections);
		main_root.getChildren().add(r_depts);
		main_root.getChildren().add(r_std);
		main_root.getChildren().add(r_pvalue);
		main_root.getChildren().add(r_corr);
		main_root.getChildren().add(r_mean);
		main_root.getChildren().add(r_pie);
		main_root.getChildren().add(r_bar);
		main_root.getChildren().add(std_textfield);

		main_root.getChildren().add(generate_clg_reports_button);
		Scene main_scene = new Scene(main_root, 600, 400);
		
         std_textfield.setOnAction( ActionEvent  -> {
         	err.setVisible(false);
         });
		
		r_sections.setOnAction( ActionEvent  -> {
	         r_pvalue.setVisible(false);
	         	r_std.setTranslateX(90);
	         	r_std.setTranslateY(220);
	            
	            r_corr.setTranslateX(275);
	            r_corr.setTranslateY(220);
	    		
	            r_mean.setTranslateX(400);
	            r_mean.setTranslateY(220);
	            
	        });
	        
	        
		r_depts.setOnAction( ActionEvent  -> {
	        
	        	r_pvalue.setVisible(true);
	        	r_pvalue.setTranslateX(220);
	            r_pvalue.setTranslateY(220);
	             
	    		r_std.setTranslateX(35);
	            r_std.setTranslateY(220);
	            
	            r_corr.setTranslateX(315);
	            r_corr.setTranslateY(220);
	    		
	            r_mean.setTranslateX(440);
	            r_mean.setTranslateY(220);;
	            
	        });
		dep_stage.setTitle("MEU applecation");
		 
        dep_stage.setScene(main_scene);
        dep_stage.show();
        
        t_group3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
            	
               if (t_group3.getSelectedToggle() == r_pie) {
            	   chart_type = 2;
               }
               else if(t_group3.getSelectedToggle() == r_bar){
            	   chart_type = 1;
               }
            }

          });
        
        t_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
            	
               if (t_group.getSelectedToggle() == r_sections) {
            	   rep_type = 2;
               }
               else if(t_group.getSelectedToggle() == r_depts){
            	   rep_type = 1;
               }
            }

          });
        
        t_group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
            	
            	  if (t_group2.getSelectedToggle() == r_std) {
              	   	std_textfield.setPromptText("Enter standard deviation");
              	   if(rep_type == 1){
              		   subject_report_type = 1;
              	   }
              	   else{section_report_type = 1;
              	   }
                 }
                 else if(t_group2.getSelectedToggle() == r_pvalue){
              	   std_textfield.setPromptText("Enter P value");
              	  if(rep_type == 1){
                    	subject_report_type = 2;
                    }
                 }
                 else if(t_group2.getSelectedToggle() == r_corr){
              	   std_textfield.setPromptText("Enter Correlation");
              	  if(rep_type == 1){
                      	subject_report_type = 3;
                      }else{section_report_type = 2;
                      }
                }
                 else if(t_group2.getSelectedToggle() == r_mean){
              	  	std_textfield.setPromptText("Enter Range");
              	  if(rep_type == 1){
                      	subject_report_type = 4;
              	  }else{section_report_type = 3;
                    }
                }
            }

          });
        
        generate_clg_reports_button.setOnAction( ActionEvent  -> {

        	if(rep_type == 1){
        	
        		if(std_textfield.getText().isEmpty()){
        			err.setText("this should not be empty");
            	
        		}else{
        			d_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
        			d_year = year_choiceBox.getSelectionModel().getSelectedItem();
        			d_term = term_choiceBox.getSelectionModel().getSelectedItem();
        			d_std = std_textfield.getText().toString();
            	    
        			
        			
        			int year = (int)d_year;
        			int term = (int)d_term;
        			double std = Double.parseDouble((String) d_std);
            	
        			Stage stage = new Stage();
            	
        			try {
        				if(subject_report_type == 1){
        					if(chart_type == 1){
        						az.collageSubjectsIssuesInStd_BarChart(std, year, term);
        					}else if(chart_type == 2){
            					az.collageSubjectsIssuesInStd_PieChart(std, year, term);
	
        					}
        				}else if(subject_report_type == 2){
        					if(chart_type == 1){
            					az.collageSubjectsIssuesInPvalue_BarChart(std, year, term);
        					}else if(chart_type == 2){
            					az.collageSubjectsIssuesInPvalue_PieChart(std, year, term);
	
        					}
        				}else if(subject_report_type == 3){
        					if(chart_type == 1){
            					az.collageSubjectsIssuesInCorrelation_BarChart(std, year, term);
        					}else if(chart_type == 2){
            					az.collageSubjectsIssuesInCorrelation_PieChart(std, year, term);
	
        					}
        				}else if(subject_report_type == 4){
        					if(chart_type == 1){
            					az.collageSubjectsIssuesInMean_BarChart(std, year, term);
        					}else if(chart_type == 2){
            					az.collageSubjectsIssuesInMean_PieChart(std, year, term);
	
        					}

        				}
            		
        				
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			dep_stage.close();
        		}
            
        	}else if(rep_type == 2){
           

                if(std_textfield.getText().isEmpty()){
                	err.setText("this should not be empty");
                	
                }else{
                	d_college_name = college_choiceBox.getSelectionModel().getSelectedItem().toString();
                	d_year = year_choiceBox.getSelectionModel().getSelectedItem();
                	d_term = term_choiceBox.getSelectionModel().getSelectedItem();
                	d_std = std_textfield.getText().toString();
                	
                	int year = (int)d_year;
                	int term = (int)d_term;
                	double std = Double.parseDouble((String) d_std);
                	
                	Stage stage = new Stage();
                	
                	try {
                		 if(section_report_type == 1){
                			 if(chart_type == 1){
              					az.collageSectionsIssuesInStd_BarChart(std, year, term);
         					}else if(chart_type == 2){
             					az.collageSectionsIssuesInStd_PieChart(std, year, term);
 	
         					}
                		 }else if(section_report_type == 2){
                			
                    			 if(chart_type == 1){
                  					az.collageSectionsIssuesInCorrelation_BarChart(std, year, term);
             					}else if(chart_type == 2){
                 					az.collageSectionsIssuesInCorrelation_PieChart(std, year, term);
     	
             					}
                		}else if(section_report_type == 3){
                			
                			 if(chart_type == 1){
               					az.collageSectionsIssuesInMean_BarChart(std, year, term);
          					}else if(chart_type == 2){
              					az.collageSectionsIssuesInMean_PieChart(std, year, term);
  	
          					}
                		}
                		}
                		
    				 catch (Exception e) {
    					e.printStackTrace();
    				}
                	dep_stage.close();
                }
        	}
        });
        
     
        
    }*/
	
	
	@Override
	public void start(Stage stage) throws Exception {
		 Charts x = new Charts("");
		 DataBase db = new DataBase();
		 createDir();
		 
		 try {
			    
			 	db.getConnection();
			
		} catch (Exception e) {
			

		}
		 
			createMainWindow(stage);
		
	}
	
	 

	
	/*public void generateDeptSubjectReportInTableViewForSTD(Stage stage){
		
		
		Button get_pdf_file_button = new Button("Generate PDF report file");
		Button exit_button = new Button("Exit");
		
		get_pdf_file_button.setFont(new Font("Times New Roman", 22));
		exit_button.setFont(new Font("Times New Roman", 24));

		TableView table = new TableView();
		
        TableColumn cln_subject_name = new TableColumn("Subject name");
        TableColumn cln_subject_code = new TableColumn("Subject code");
        TableColumn cln_std = new TableColumn("Standard Deviation");
        TableColumn cln_sq = new TableColumn("Skewness");
 
        table.getColumns().addAll(cln_subject_name, cln_subject_code, cln_std,cln_sq);
 
         ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
       
        for( int i = 0;i <= (info.dep_report_subject_name.size()-1); i++){
        	 data.add(
        			new MaterialInfo(info.dep_report_subject_name.get(i),
        					         info.dep_report_subject_code.get(i),
        					         info.dep_report_subject_std.get(i),
        					         info.dep_report_subject_sq.get(i))
        			
        			);
        	
        }
        table.setItems(data);
        
        cln_subject_name.setCellValueFactory( new PropertyValueFactory<>("subject_name") );
        
        cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );
 
        cln_std.setCellValueFactory( new PropertyValueFactory<>("std"));
        
        cln_sq.setCellValueFactory( new PropertyValueFactory<>("sq"));
        

        exit_button.setTextFill(javafx.scene.paint.Color.RED);
        
        table.setPrefSize(800, 425);
 
        cln_subject_name.setPrefWidth(200);
        cln_subject_code.setPrefWidth(200);
        cln_std.setPrefWidth(200);
        cln_sq.setPrefWidth(200);
 
        table.setTranslateX(10);
        table.setTranslateY(10);
        
        get_pdf_file_button.setTranslateX(60);
        get_pdf_file_button.setTranslateY(445);
        get_pdf_file_button.setPrefSize(350, 40);
 
        
        exit_button.setTranslateX(420);
        exit_button.setTranslateY(445);
        exit_button.setPrefSize(350, 40);
        
        Group root = new Group();
 
        root.getChildren().add(table);
        root.getChildren().add(get_pdf_file_button);
        root.getChildren().add(exit_button);
        
        Scene scene = new Scene(root, 820, 500);
 
        stage.setTitle("Department Report based on standard deviation");
 
        stage.setScene(scene);
 
        stage.show();
        
        exit_button.setOnAction( ActionEvent  -> {
        	
        		stage.close();
        });
        
        get_pdf_file_button.setOnAction( ActionEvent  -> {
        	
        	setPDFNameWindow(stage);

       });
	}
	
	public void generateDeptSubjectReportInTableViewForPvalue(Stage stage){

		
		Button get_pdf_file_button = new Button("Generate PDF report file");
		Button exit_button = new Button("Exit");
		
		get_pdf_file_button.setFont(new Font("Times New Roman", 22));
		exit_button.setFont(new Font("Times New Roman", 24));
		
		TableView table = new TableView();
		
        TableColumn cln_subject_name = new TableColumn("Subject name");
        TableColumn cln_subject_code = new TableColumn("Subject code");
        TableColumn cln_pvalue = new TableColumn("P value");
 
        table.getColumns().addAll(cln_subject_name, cln_subject_code, cln_pvalue);
 
         ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
       
        for( int i = 0;i <= (info.dep_report_subject_name.size()-1); i++){
        	 data.add(
        			new MaterialInfo(info.dep_report_subject_name.get(i),
        					         info.dep_report_subject_code.get(i),
        					         info.dep_report_subject_pvalue.get(i),
        					         true));
        	
        }
        table.setItems(data);
        
        cln_subject_name.setCellValueFactory( new PropertyValueFactory<>("subject_name") );
        
        cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );
 
        cln_pvalue.setCellValueFactory( new PropertyValueFactory<>("pvalue"));
        

        exit_button.setTextFill(javafx.scene.paint.Color.RED);

        table.setPrefSize(800, 425);
 
        cln_subject_name.setPrefWidth(266);
        cln_subject_code.setPrefWidth(266);
        cln_pvalue.setPrefWidth(266);
 
        table.setTranslateX(10);
        table.setTranslateY(10);
        
        get_pdf_file_button.setTranslateX(60);
        get_pdf_file_button.setTranslateY(445);
        get_pdf_file_button.setPrefSize(350, 40);
 
        
        exit_button.setTranslateX(420);
        exit_button.setTranslateY(445);
        exit_button.setPrefSize(350, 40);
        
        Group root = new Group();
 
        root.getChildren().add(table);
        root.getChildren().add(get_pdf_file_button);
        root.getChildren().add(exit_button);
        
        Scene scene = new Scene(root, 820, 500);
 
        stage.setTitle("Department Report based on p value");
 
        stage.setScene(scene);
 
        stage.show();
        
        
        
        exit_button.setOnAction( ActionEvent  -> {
        	   
        		stage.close();
        });
        
        get_pdf_file_button.setOnAction( ActionEvent  -> {
        	
        	setPDFNameWindow(stage);
        });
	}
	
public void generateDeptSubjectReportInTableViewForCorrelation(Stage stage){

		
		Button get_pdf_file_button = new Button("Generate PDF report file");
		Button exit_button = new Button("Exit");
		
		get_pdf_file_button.setFont(new Font("Times New Roman", 22));
		exit_button.setFont(new Font("Times New Roman", 24));
		
		TableView table = new TableView();
		
        TableColumn cln_subject_name = new TableColumn("Subject name");
        TableColumn cln_subject_code = new TableColumn("Subject code");
        TableColumn cln_correlation = new TableColumn("Correlation");
 
        table.getColumns().addAll(cln_subject_name, cln_subject_code, cln_correlation);
 
         ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
       
        for( int i = 0;i <= (info.dep_report_subject_name.size()-1); i++){
        	 data.add(
        			new MaterialInfo(info.dep_report_subject_name.get(i),
        					         info.dep_report_subject_code.get(i),
        					         info.dep_report_subject_correlation.get(i),
        					         false));
        	
        }
        table.setItems(data);
        
        cln_subject_name.setCellValueFactory( new PropertyValueFactory<>("subject_name") );
        
        cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );
 
        cln_correlation.setCellValueFactory( new PropertyValueFactory<>("correlation"));
        

        exit_button.setTextFill(javafx.scene.paint.Color.RED);
        
        
        
        table.setPrefSize(800, 425);
 
        cln_subject_name.setPrefWidth(266);
        cln_subject_code.setPrefWidth(266);
        cln_correlation.setPrefWidth(266);
 
        table.setTranslateX(10);
        table.setTranslateY(10);
        
        get_pdf_file_button.setTranslateX(60);
        get_pdf_file_button.setTranslateY(445);
        get_pdf_file_button.setPrefSize(350, 40);
 
        
        exit_button.setTranslateX(420);
        exit_button.setTranslateY(445);
        exit_button.setPrefSize(350, 40);
        
        Group root = new Group();
 
        root.getChildren().add(table);
        root.getChildren().add(get_pdf_file_button);
        root.getChildren().add(exit_button);
        
        Scene scene = new Scene(root, 820, 500);
 
        stage.setTitle("Department Report based on correlation");
 
        stage.setScene(scene);
 
        stage.show();
        
        exit_button.setOnAction( ActionEvent  -> {
        		stage.close();
        });
        
        get_pdf_file_button.setOnAction( ActionEvent  -> {
        	
        	setPDFNameWindow(stage);
        });
	}

public void generateDeptSubjectReportInTableViewForMean(Stage stage){

	
	Button get_pdf_file_button = new Button("Generate PDF report file");
	Button exit_button = new Button("Exit");
	
	get_pdf_file_button.setFont(new Font("Times New Roman", 22));
	exit_button.setFont(new Font("Times New Roman", 24));
	
	TableView table = new TableView();
	
    TableColumn cln_subject_name = new TableColumn("Subject name");
    TableColumn cln_subject_code = new TableColumn("Subject code");
    TableColumn cln_mean = new TableColumn("Mean");

    table.getColumns().addAll(cln_subject_name, cln_subject_code, cln_mean);

     ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
   
    for( int i = 0;i <= (info.dep_report_subject_name.size()-1); i++){
    	 data.add(
    			new MaterialInfo(info.dep_report_subject_name.get(i),
    					         info.dep_report_subject_code.get(i),
    					         info.dep_report_subject_mean.get(i)));
    	
    }
    table.setItems(data);
    
    cln_subject_name.setCellValueFactory( new PropertyValueFactory<>("subject_name") );
    
    cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );

    cln_mean.setCellValueFactory( new PropertyValueFactory<>("mean"));
    

    exit_button.setTextFill(javafx.scene.paint.Color.RED);
    
    
    table.setPrefSize(800, 425);

    cln_subject_name.setPrefWidth(266);
    cln_subject_code.setPrefWidth(266);
    cln_mean.setPrefWidth(266);

    table.setTranslateX(10);
    table.setTranslateY(10);
    
    get_pdf_file_button.setTranslateX(60);
    get_pdf_file_button.setTranslateY(445);
    get_pdf_file_button.setPrefSize(350, 40);

    
    exit_button.setTranslateX(420);
    exit_button.setTranslateY(445);
    exit_button.setPrefSize(350, 40);
    
    Group root = new Group();

    root.getChildren().add(table);
    root.getChildren().add(get_pdf_file_button);
    root.getChildren().add(exit_button);
    
    Scene scene = new Scene(root, 820, 500);

    stage.setTitle("Department Report based on correlation");

    stage.setScene(scene);

    stage.show();
    
    exit_button.setOnAction( ActionEvent  -> {
    		stage.close();
    });
    
    get_pdf_file_button.setOnAction( ActionEvent  -> {
    	
    	setPDFNameWindow(stage);
    });
}

public void generateDeptSectionReportInTableViewForSTD(Stage stage){
	
	
	Button get_pdf_file_button = new Button("Generate PDF report file");
	Button exit_button = new Button("Exit");
	
	get_pdf_file_button.setFont(new Font("Times New Roman", 22));
	exit_button.setFont(new Font("Times New Roman", 24));
	
	TableView table = new TableView();
	
    TableColumn cln_section_number = new TableColumn("Section number");
    TableColumn cln_subject_code = new TableColumn("Subject code");
    TableColumn cln_std = new TableColumn("Standard Deviation");
    TableColumn cln_sq = new TableColumn("Skewness");

    table.getColumns().addAll(cln_section_number, cln_subject_code, cln_std,cln_sq);

     ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
   
    for( int i = 0;i <= (info.dep_report_section_number.size()-1); i++){
    	 data.add(
    			new MaterialInfo(info.dep_report_section_number.get(i),
    					         info.dep_report_subject_code.get(i),
    					         info.dep_report_section_std.get(i),
    					         info.dep_report_section_sq.get(i))
    			
    			);
    	
    }
    table.setItems(data);
    
    cln_section_number.setCellValueFactory( new PropertyValueFactory<>("section_number") );
    
    cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );

    cln_std.setCellValueFactory( new PropertyValueFactory<>("std"));
    
    cln_sq.setCellValueFactory( new PropertyValueFactory<>("sq"));
    
    
    exit_button.setTextFill(javafx.scene.paint.Color.RED);
    
    
    table.setPrefSize(800, 425);

    cln_section_number.setPrefWidth(200);
    cln_subject_code.setPrefWidth(200);
    cln_std.setPrefWidth(200);
    cln_sq.setPrefWidth(200);

    table.setTranslateX(10);
    table.setTranslateY(10);
    
    get_pdf_file_button.setTranslateX(60);
    get_pdf_file_button.setTranslateY(445);
    get_pdf_file_button.setPrefSize(350, 40);

    
    exit_button.setTranslateX(420);
    exit_button.setTranslateY(445);
    exit_button.setPrefSize(350, 40);
    
    Group root = new Group();

    root.getChildren().add(table);
    root.getChildren().add(get_pdf_file_button);
    root.getChildren().add(exit_button);
    
    Scene scene = new Scene(root, 820, 500);

    stage.setTitle("Department Report based on standard deviation");

    stage.setScene(scene);

    stage.show();
    
    exit_button.setOnAction( ActionEvent  -> {
    	
    		stage.close();
    });
    
    get_pdf_file_button.setOnAction( ActionEvent  -> {
    	
    	setPDFNameWindow(stage);

   });
}

public void generateDeptSectionReportInTableViewForMean(Stage stage){

	
	Button get_pdf_file_button = new Button("Generate PDF report file");
	Button exit_button = new Button("Exit");
	
	get_pdf_file_button.setFont(new Font("Times New Roman", 22));
	exit_button.setFont(new Font("Times New Roman", 24));
	
	TableView table = new TableView();
	
    TableColumn cln_section_number = new TableColumn("Section number");
    TableColumn cln_subject_code = new TableColumn("Subject code");
    TableColumn cln_mean = new TableColumn("Mean");

    table.getColumns().addAll(cln_section_number, cln_subject_code, cln_mean);

     ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
   
    for( int i = 0;i <= (info.dep_report_section_number.size()-1); i++){
    	 data.add(
    			new MaterialInfo(info.dep_report_section_number.get(i),
    					         info.dep_report_subject_code.get(i),
    					         info.dep_report_section_mean.get(i),
    					         true));
    	
    }
    table.setItems(data);
    
    cln_section_number.setCellValueFactory( new PropertyValueFactory<>("section_number") );
    
    cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );

    cln_mean.setCellValueFactory( new PropertyValueFactory<>("mean"));
    


    exit_button.setTextFill(javafx.scene.paint.Color.RED);
    
    
    
    table.setPrefSize(800, 425);

    cln_section_number.setPrefWidth(266);
    cln_subject_code.setPrefWidth(266);
    cln_mean.setPrefWidth(266);

    table.setTranslateX(10);
    table.setTranslateY(10);
    
    get_pdf_file_button.setTranslateX(60);
    get_pdf_file_button.setTranslateY(445);
    get_pdf_file_button.setPrefSize(350, 40);

    
    exit_button.setTranslateX(420);
    exit_button.setTranslateY(445);
    exit_button.setPrefSize(350, 40);
    
    Group root = new Group();

    root.getChildren().add(table);
    root.getChildren().add(get_pdf_file_button);
    root.getChildren().add(exit_button);
    
    Scene scene = new Scene(root, 820, 500);

    stage.setTitle("Department Report based on Mean");

    stage.setScene(scene);

    stage.show();
    
    exit_button.setOnAction( ActionEvent  -> {
    	   
    		stage.close();
    });
    
    get_pdf_file_button.setOnAction( ActionEvent  -> {
    	
    	setPDFNameWindow(stage);
    });
}

public void generateDeptSectionReportInTableViewForCorrelation(Stage stage){

	
	Button get_pdf_file_button = new Button("Generate PDF report file");
	Button exit_button = new Button("Exit");
	
	get_pdf_file_button.setFont(new Font("Times New Roman", 22));
	exit_button.setFont(new Font("Times New Roman", 24));
	
	TableView table = new TableView();
	
    TableColumn cln_section_number = new TableColumn("Section number");
    TableColumn cln_subject_code = new TableColumn("Subject code");
    TableColumn cln_correlation = new TableColumn("Correlation");

    table.getColumns().addAll(cln_section_number, cln_subject_code, cln_correlation);

     ObservableList<MaterialInfo> data = FXCollections.observableArrayList();
   
    for( int i = 0;i <= (info.dep_report_section_number.size()-1); i++){
    	 data.add(
    			new MaterialInfo(info.dep_report_section_number.get(i),
    					         info.dep_report_subject_code.get(i),
    					         info.dep_report_section_correlation.get(i),
    					         false));
    	
    }
    table.setItems(data);
    
    cln_section_number.setCellValueFactory( new PropertyValueFactory<>("section_number") );
    
    cln_subject_code.setCellValueFactory( new PropertyValueFactory<>("subject_code") );

    cln_correlation.setCellValueFactory( new PropertyValueFactory<>("correlation"));
    
    
    
    exit_button.setTextFill(javafx.scene.paint.Color.RED);
    
    
    
    table.setPrefSize(800, 425);

    cln_section_number.setPrefWidth(266);
    cln_subject_code.setPrefWidth(266);
    cln_correlation.setPrefWidth(266);

    table.setTranslateX(10);
    table.setTranslateY(10);
    
    get_pdf_file_button.setTranslateX(60);
    get_pdf_file_button.setTranslateY(445);
    get_pdf_file_button.setPrefSize(350, 40);

    
    exit_button.setTranslateX(420);
    exit_button.setTranslateY(445);
    exit_button.setPrefSize(350, 40);
    
    Group root = new Group();

    root.getChildren().add(table);
    root.getChildren().add(get_pdf_file_button);
    root.getChildren().add(exit_button);
    
    Scene scene = new Scene(root, 820, 500);

    stage.setTitle("Department Report based on correlation");

    stage.setScene(scene);

    stage.show();
    
    exit_button.setOnAction( ActionEvent  -> {
    		stage.close();
    });
    
    get_pdf_file_button.setOnAction( ActionEvent  -> {
    	
    	setPDFNameWindow(stage);
    });
}



	public void setPDFNameWindow(Stage stage){
		
		Button save_button = new Button("save pdf file");
		TextField pdf_file_textfield = new TextField();
		Label err = new Label();
		
	    err.setTextFill(javafx.scene.paint.Color.RED);

		pdf_file_textfield.setPromptText("Enter file name ");
		
		pdf_file_textfield.setTranslateX(20);
		pdf_file_textfield.setTranslateY(20);
		pdf_file_textfield.setPrefSize(200, 30);
		
		save_button.setTranslateX(205);
		save_button.setTranslateY(88);
		save_button.setPrefSize(140, 45);
		err.setTranslateX(225);
		err.setTranslateY(25);
		
        
        Group root = new Group();
        
        root.getChildren().add(save_button);
        root.getChildren().add(pdf_file_textfield);
        root.getChildren().add(err);
        
        Scene scene = new Scene(root, 400, 150);
        
        
        stage.setTitle("File name");

        stage.setScene(scene);
 
        stage.show();
        
        save_button.setOnAction( ActionEvent  -> {
            if(pdf_file_textfield.getText().isEmpty()){
            	err.setText("this should not be empty");
            	
            }else{
            	try {
            		
            		if(rep_type == 1){
            			
            			if(subject_report_type == 1){
            				file_name = pdf_file_textfield.getText().toString();
                			rep.generateDepSubjectReportPDF_BasedOnSTD(file_name + ".pdf");
                			}
            			else if(subject_report_type == 2){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSubjectReportPDF_BasedOnPvalue(file_name + ".pdf");
            				}
            			else if(subject_report_type == 3){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSubjectReportPDF_BasedOnCorr(file_name + ".pdf");
            				}
            			else if(subject_report_type == 4){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSubjectReportPDF_BasedOnMean(file_name + ".pdf");
            				}
            		}
            		
            		else if(rep_type == 2){
            			if(section_report_type == 1){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSectionReportPDF_BasedOnSTD(file_name + ".pdf");
            				}
            			else if(section_report_type == 2){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSectionReportPDF_BasedOnCorrelation(file_name + ".pdf");
            				}
            			else if(section_report_type == 3){
            				file_name = pdf_file_textfield.getText().toString();
            				rep.generateDepSectionReportPDF_BasedOnMean(file_name + ".pdf");
            				}
            		}
            }catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                stage.close();
            }
        });
		
	}
	*/
	public void createDir(){
		  File files1 = new File("C:\\MEU-system\\teacher_report");
		  File files2 = new File("C:\\MEU-system\\department_report");
		  File files3 = new File("C:\\MEU-system\\charts");
	        if (!files1.exists()) {
	            if (files1.mkdirs()) {
	                System.out.println("Multiple directories are created!");
	            } else {
	                System.out.println("Failed to create multiple directories!");
	            }
	        }
	        
	        if (!files2.exists()) {
	            if (files2.mkdirs()) {
	                System.out.println("Multiple directories are created!");
	            } else {
	                System.out.println("Failed to create multiple directories!");
	            }
	        }
	        
	        if (!files3.exists()) {
	            if (files3.mkdirs()) {
	                System.out.println("Multiple directories are created!");
	            } else {
	                System.out.println("Failed to create multiple directories!");
	            }
	        }
	    }	
}