import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class MaterialInfo {
	public String subject_name;
	public int section_number;
	public int subject_code;
	public double std;
	public double sq;
	public double pvalue;
	public double correlation;
	public double mean;
	


	ArrayList<Double> stds_finalMarks = new ArrayList<Double>();
	ArrayList<Double> stds_internalMarks = new ArrayList<Double>();
	ArrayList<Double> stds_TotalMarks = new ArrayList<Double>();
	ArrayList<Integer> stds_count_arr = new ArrayList<Integer>();
	
	public static ArrayList<String> dep_report_subject_name = new ArrayList<String>();
	public static ArrayList<Integer> dep_report_subject_code = new ArrayList<Integer>();
	public static ArrayList<Double> dep_report_subject_std = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_subject_sq = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_subject_pvalue = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_subject_correlation = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_subject_mean = new ArrayList<Double>();
	
	public static ArrayList<Double> dep_report_section_std = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_section_sq = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_section_correlation = new ArrayList<Double>();
	public static ArrayList<Double> dep_report_section_mean = new ArrayList<Double>();
	public static ArrayList<Integer> dep_report_section_number = new ArrayList<Integer>();
	
	public static ArrayList<Integer> count_A = new ArrayList<Integer>();
	public static ArrayList<Integer> count_B = new ArrayList<Integer>();
	public static ArrayList<Integer> count_C = new ArrayList<Integer>();
	public static ArrayList<Integer> count_D = new ArrayList<Integer>();
	public static ArrayList<Integer> count_F = new ArrayList<Integer>();
	
	public static ArrayList<Double> std_pass = new ArrayList<Double>();

	
	public static ArrayList<Integer> students_count = new ArrayList<Integer>();

	
	public static ArrayList<Integer> all_subjects = new ArrayList<Integer>();
	public static ArrayList<Integer> all_sections = new ArrayList<Integer>();

	
	public static int stds_count;
	
	
	double[] stds_grades_numbers = new double[5];
	double[] grades_ND_arr = {2,14,68,14,2};
	double[] stds_grade_dist_arr = new double[5];
	double total_grades = 0;
	
	Collection<double[]> mat_data_collection = new ArrayList<double[]>();
	DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
	PearsonsCorrelation cor = new PearsonsCorrelation();
	OneWayAnova anova = new OneWayAnova();
	
	
	public MaterialInfo(){
		
	
	}

	public MaterialInfo(String subject_name, int subject_code, double std, double sq){
		setSubject_name(subject_name);
		setSubject_code(subject_code);
		setStd(std);
		setSq(sq);
	}
	
	public MaterialInfo(String subject_name, int subject_code, double pvalue_or_corr,boolean flag){
		setSubject_name(subject_name);
		setSubject_code(subject_code);
		if(flag == true){
			setPvalue(pvalue_or_corr);
			}else{
				setCorrelation(pvalue_or_corr);
			}
	}
	public MaterialInfo(String subject_name, int subject_code, double mean){
		setSubject_name(subject_name);
		setSubject_code(subject_code);
		setMean(mean);
	}
	
	
	
	
	public MaterialInfo(int section_number, int subject_code, double std, double sq){
		setSection_number(section_number);
		setSubject_code(subject_code);
		setStd(std);
		setSq(sq);
	}
	public MaterialInfo(int section_number, int subject_code, double mean_or_corr,boolean flag){
		setSection_number(section_number);
		setSubject_code(subject_code);
		if(flag == true){
			setMean(mean_or_corr);
			}else{
				setCorrelation(mean_or_corr);
			}
	}
	
	
    public void getStds_TotalMarks(File file) throws FileNotFoundException, IOException{
    	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsoluteFile()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		for(int i =7 ; i <= sheet.getLastRowNum();){
			HSSFRow corrent_row = sheet.getRow(i);
			HSSFCell corrent_cell = corrent_row.getCell(4);	
			try{
				
			double corrent_value = Integer.parseInt(corrent_cell.getStringCellValue());
			stds_TotalMarks.add(corrent_value);
			i++;
			}catch(NumberFormatException c){
			i++;
			}
		}

		workbook.close();
    }
    public void getStds_internalMarks(File file) throws FileNotFoundException, IOException{
    	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsoluteFile()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		for(int i=7; i <= sheet.getLastRowNum();){
			HSSFRow corrent_row = sheet.getRow(i);
			HSSFCell corrent_cell = corrent_row.getCell(2);	
			try{
			double corrent_value = Double.parseDouble(corrent_cell.getStringCellValue());
			
			stds_internalMarks.add(corrent_value);
			i++;
			
			}catch(NumberFormatException c){
				i++;
			}
		}
		workbook.close();
    }
    public void getStds_finalMarks(File file) throws FileNotFoundException, IOException{
    	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsoluteFile()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		for(int i = 7; i <= sheet.getLastRowNum();){
			HSSFRow corrent_row = sheet.getRow(i);
			HSSFCell corrent_cell = corrent_row.getCell(3);
			try{
				
				double corrent_value = Integer.parseInt(corrent_cell.getStringCellValue());
			
				stds_finalMarks.add(corrent_value);
				
				i++;
				 
				}catch(NumberFormatException c){	
					
					i++;
				}
			
		}
		workbook.close();
    }
    
    public void getStdsCounts(File file) throws FileNotFoundException, IOException{
    	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsoluteFile()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(int i = 7; i <= sheet.getLastRowNum();){
			HSSFRow corrent_row = sheet.getRow(i);
			HSSFCell corrent_cell = corrent_row.getCell(0);
			
			
			
			try{
				int corrent_value = Integer.parseInt(corrent_cell.getStringCellValue());
				if(corrent_value != 0){	
					stds_count++;
					i++;
				}
				
				}catch(NumberFormatException c){
					
				}
			
		}
		workbook.close();
		stds_count_arr.add(stds_count);
		stds_count = 0;
		
    }
    
    public void getStds_grades(){
    	for(int i = 0; i <= (stds_TotalMarks.size()-1);i++){
    		if(stds_TotalMarks.get(i) >= 90.0){
    			stds_grades_numbers[0]++;
    		}else if(stds_TotalMarks.get(i) >= 80.0 && stds_TotalMarks.get(i) < 90.0){
    			stds_grades_numbers[1]++;
    		}else if(stds_TotalMarks.get(i) >= 70.0 && stds_TotalMarks.get(i) < 80.0){
    			stds_grades_numbers[2]++;
    		}else if(stds_TotalMarks.get(i) >= 60.0 && stds_TotalMarks.get(i) < 70.0){
    			stds_grades_numbers[3]++;
    		}else{
    			stds_grades_numbers[4]++;
    			
    		}
    	}
    	
    	
    	
    		for(int i = 0; i < stds_grades_numbers.length; i++){
    			total_grades = total_grades + stds_grades_numbers[i];
    		}
    		
    		for(int i = 0; i < stds_grades_numbers.length; i++){
    			stds_grade_dist_arr[i] = Math.round((stds_grades_numbers[i]/total_grades)*100);
    			
    		}
	}
    
   
    public double calcMean(ArrayList<Double> TotalMarks){
    	
    	for (int i = 0; i <=(TotalMarks.size() -1); i++) {
    	    descriptiveStatistics.addValue(TotalMarks.get(i));}
    	
    	double mean = descriptiveStatistics.getMean();
    	return mean;
    	
    }
    
    
    public double calcMean(double []TotalMarks){
    	
    	for (int i = 0; i <=(TotalMarks.length -1); i++) {
    	    descriptiveStatistics.addValue(TotalMarks[i]);
    	    }
    	double mean = descriptiveStatistics.getMean();
    	return mean;
    	
    }
    
    public double calcMaximum(ArrayList<Double> TotalMarks){
    	
    	for (int i = 0; i <=(TotalMarks.size() -1); i++) {
    	    descriptiveStatistics.addValue(TotalMarks.get(i));
    	    }
    	double max = descriptiveStatistics.getMax();
    	return max;
    }
    
    public double calcMinimum(ArrayList<Double> TotalMarks){
    	
    	for (int i = 0; i <=(TotalMarks.size() -1); i++) {
    	    descriptiveStatistics.addValue(TotalMarks.get(i));}
    	
    	double min= descriptiveStatistics.getMin();
    	return min;
    	
    }


    public double calcSD(ArrayList<Double> TotalMarks){
	
    	for (int i = 0; i <=(TotalMarks.size() -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks.get(i));}
	
    	double std = descriptiveStatistics.getStandardDeviation();
    	return std;
    }
    public double calcSQ (ArrayList<Double> TotalMarks){
    	
    	for (int i = 0; i <=(TotalMarks.size() -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks.get(i));}
	
    	double sq= descriptiveStatistics.getSkewness();
    	return sq;
	
    }


    public double calcCor(ArrayList<Double> final_marks, ArrayList<Double> mid_marks){
	
    	double fm_arr[] = new double[final_marks.size()];
    	double mm_arr[] = new double[mid_marks.size()];
    
    	for(int i = 0; i <= (final_marks.size()-1); i++){
    		fm_arr[i] = final_marks.get(i);
    	}
	
    	for(int i = 0; i <= (mid_marks.size()-1); i++){
    		mm_arr[i] = mid_marks.get(i);
    	}
    	double correlation = cor.correlation(fm_arr, mm_arr);
    	return correlation;

		}

    public double calcCor(double[] final_marks, double[] internal_marks){
	
    	double correlation = cor.correlation(internal_marks,final_marks);
    	return correlation;
	
		}

    public double calcMaximum(double[] TotalMarks){
	
    	for (int i = 0; i <=(TotalMarks.length -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks[i]);}
	
    	double max = descriptiveStatistics.getMax();
    	return max;
    }

    public double calcMinimum(double[] TotalMarks){
	
    	for (int i = 0; i <=(TotalMarks.length -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks[i]);}
	
    	double min= descriptiveStatistics.getMin();
    	return min;
	
    }


    public double calcSD(double[] TotalMarks){

    	for (int i = 0; i <=(TotalMarks.length -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks[i]);}

    	double std = descriptiveStatistics.getStandardDeviation();
    	return std;
    }
    public double calcSQ (double[] TotalMarks){

    	for (int i = 0; i <=(TotalMarks.length -1); i++) {
    		descriptiveStatistics.addValue(TotalMarks[i]);}

    	double sq = descriptiveStatistics.getSkewness();
    	return sq;

    }
    
    public String getSubjectName(File file) throws FileNotFoundException, IOException{
    	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsoluteFile()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		HSSFRow corrent_row = sheet.getRow(2);
			HSSFCell corrent_cell = corrent_row.getCell(1);
			
			
				String corrent_value = corrent_cell.getStringCellValue();
			
		
		workbook.close();
		return corrent_value;
    }
    
    
    
	public double getSq() {
		return sq;
	}

	public void setSq(double sq) {
		this.sq = sq;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public int getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(int subject_code) {
		this.subject_code = subject_code;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}
	public double getPvalue() {
		return pvalue;
	}

	public void setPvalue(double pvalue) {
		this.pvalue = pvalue;
	}

	public double getCorrelation() {
		return correlation;
	}

	public void setCorrelation(double correlation) {
		this.correlation = correlation;
	}


	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}
	
	public int getSection_number() {
		return section_number;
	}

	public void setSection_number(int section_number) {
		this.section_number = section_number;
	}
}
