import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBase {
	
	private static Connection con;
	private static boolean hasData = false;
	MaterialInfo mat = new MaterialInfo();


	public void getConnection() throws Exception {
		
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:MEU_DB.db");
		
		
		initialise();
		
	}

	private void initialise() throws Exception {
		if(hasData == true){
			
			
		}else{

			    hasData = true;
				
				
				java.sql.Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE IF NOT EXISTS subject(id integer,"
						                               + "Subject_name varchar2(30),"
						                               + "Subject_Code integer,"
						                               + "department varchar2(30),"
						                               + "term integer,"
						                               + "year integer,"
						                               + "mean integer,"
						                               + "pValue integer,"
						                               + "maxValue integer,"
						                               + "minValue integer,"
						                               + "correlation integer,"
						                               + "skewness integer,"
						                               + "std integer,"
						                               + "primary key(id));");
				
				java.sql.Statement state3 = con.createStatement();
				state3.execute("CREATE TABLE IF NOT EXISTS section(id integer,"
                                                   + "Subject_code integer,"
                                                   + "Section_number integer,"
					                               + "department varchar2(30),"
                                                   + "term integer,"
                                                   + "year integer,"
                                                   + "mean integer,"
                                                   + "maxValue integer,"
                                                   + "minValue integer,"
                                                   + "CountA integer,"
					                               + "CountB integer,"
					                               + "CountC integer,"
					                               + "CountD integer,"
					                               + "CountF integer,"
                                                   + "correlation integer,"
                                                   + "skewness integer,"
                                                   + "std integer,"
                                                   + "primary key(id));");
				
				java.sql.Statement state4 = con.createStatement();
				state3.execute("CREATE TABLE IF NOT EXISTS marks(id integer,"
                                                   + "Subject_code integer,"
                                                   + "section_number intege );");
				}
		
	}
	
	public void addValuesToSectionTable(double id, double subject_code,double section_number,String department,int term, int year, double mean, double max, double min,
										double A, double B, double C, double D, double F,
			                            double corr, double sq, double std) throws SQLException{
		PreparedStatement prep = con.prepareStatement("INSERT INTO section(id, subject_code,section_number,department, term, year, mean, "
				                                                        + "maxValue, minValue,CountA,CountB,CountC,CountD,CountF,"
				                                                        + " correlation, skewness,"
				                                                        + "std) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		
		prep.setDouble(1, id);
		prep.setDouble(2, subject_code);
		prep.setDouble(3, section_number);
        prep.setString(4, department);
		prep.setDouble(5, term);
		prep.setDouble(6, year);
		prep.setDouble(7, mean);
		prep.setDouble(8, max);
		prep.setDouble(9, min);
		prep.setDouble(10, A);
        prep.setDouble(11, B);
        prep.setDouble(12, C);
        prep.setDouble(13, D);
        prep.setDouble(14, F);
		prep.setDouble(15, corr);
		prep.setDouble(16, sq);
		prep.setDouble(17, std);
		prep.executeUpdate();
		
	}
	
	public void addValuesToSubjectTable(double id,String Subject_name, double Subject_code, String department,int term, int year, double mean,double p_value, double max, double min,
			double corr, double sq, double std) throws SQLException{
        PreparedStatement prep = con.prepareStatement("INSERT INTO subject(id,subject_name, subject_code, department,"
        	                              	+ "term, year, mean, pValue,"
                                            + "maxValue, minValue,"
                                            + " correlation, skewness,"
                                            + "std) values(?,?,?,?,?,?,?,?,?,?,?,?,?);");

        prep.setDouble(1, id);
        prep.setString(2, Subject_name);
        prep.setDouble(3, Subject_code);
        prep.setString(4, department);
        prep.setDouble(5, term);
        prep.setDouble(6, year);
        prep.setDouble(7, mean);
        prep.setDouble(8, p_value);
        prep.setDouble(9, max);
        prep.setDouble(10, min);
        prep.setDouble(11, corr);
        prep.setDouble(12, sq);
        prep.setDouble(13 ,std);
        prep.executeUpdate();
   }
	
	public void addValuesToMarksTable(double id, double subject_code, double section_number) throws SQLException{
PreparedStatement prep = con.prepareStatement("INSERT INTO marks(id, subject_code,section_number) values("
												+ "?,?,?);");
		prep.setDouble(1, id);
		prep.setDouble(2, subject_code);
		prep.setDouble(3, section_number);

		prep.executeUpdate();

	}
	
	/*public void getDataFromSubjectTableBasedOnSTD(double std, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT subject_name, subject_code, std, skewness FROM subject "
				                                                   + "WHERE std > ? AND year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, std);
		get_data.setDouble(2, year);
		get_data.setDouble(3, term);
		get_data.setString(4, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_subject_name.add(result.getString("subject_name"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_subject_std.add(result.getDouble("std"));
            mat.dep_report_subject_sq.add(result.getDouble("skewness"));
            
            
        }
		
	}
	
	public void getDataFromSubjectTableBasedOnPvalue(double pvalue, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT subject_name, subject_code, pValue FROM subject "
				                                                   + "WHERE pValue <= ? AND year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, pvalue);
		get_data.setDouble(2, year);
		get_data.setDouble(3, term);
		get_data.setString(4, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_subject_name.add(result.getString("subject_name"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_subject_pvalue.add(result.getDouble("pValue"));
            
        }
		
	}
	
	public void getDataFromSubjectTableBasedOnCorrelation(double corr, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT subject_name, subject_code, correlation FROM subject "
				                                                   + "WHERE correlation <= ? AND year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, corr);
		get_data.setDouble(2, year);
		get_data.setDouble(3, term);
		get_data.setString(4, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_subject_name.add(result.getString("subject_name"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_subject_correlation.add(result.getDouble("correlation"));
            
        }
		
	}
	
	
	public void getDataFromSubjectTableBasedOnMean(double range, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT subject_name, subject_code, mean FROM subject "
				                                                   + "WHERE (mean >= 70 + ? OR mean <= 70 - ?) AND  year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, range);
		get_data.setDouble(2, range);
		get_data.setDouble(3, year);
		get_data.setDouble(4, term);
		get_data.setString(5, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_subject_name.add(result.getString("subject_name"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_subject_mean.add(result.getDouble("mean"));
            
            
        }
		//System.out.println(mat.dep_report_subject_mean);
	}
	
	
	public void getDataFromSectionTableBasedOnSTD(double std, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT section_number, subject_code, std, skewness FROM section "
				                                                   + "WHERE std > ? AND year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, std);
		get_data.setDouble(2, year);
		get_data.setDouble(3, term);
		get_data.setString(4, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_section_number.add(result.getInt("section_number"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_section_std.add(result.getDouble("std"));
            mat.dep_report_section_sq.add(result.getDouble("skewness"));
            
            
        }
		
	}


	public void getDataFromSectionTableBasedOnCorrelation(double corr, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT section_number, subject_code, correlation FROM section "
				                                                   + "WHERE correlation <= ? AND year = ? AND term = ? AND department = ?");
		get_data.setDouble(1, corr);
		get_data.setDouble(2, year);
		get_data.setDouble(3, term);
		get_data.setString(4, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_section_number.add(result.getInt("section_number"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_section_correlation.add(result.getDouble("correlation"));
            
            
        }
		
	}
	public void getDataFromSectionTableBasedOnMean(double range, int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT section_number, subject_code, mean FROM section "
                											+ "WHERE (mean >= 70 + ? OR mean <= 70 - ?) AND  year = ? AND term = ? AND department = ?");
		

		get_data.setDouble(1, range);
		get_data.setDouble(2, range);
		get_data.setDouble(3, year);
		get_data.setDouble(4, term);
		get_data.setString(5, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_section_number.add(result.getInt("section_number"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_section_mean.add(result.getDouble("mean"));
            
        }

	}
	*/
	public void getAllFromSubject(int year, int term) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT subject_code, mean, std, skewness, correlation FROM subject "
				                                                   + "WHERE year = ? AND term = ? ");
		
		
		
		get_data.setDouble(1, year);
		get_data.setDouble(2, term);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.dep_report_subject_mean.add(result.getDouble("mean"));
            mat.dep_report_subject_std.add(result.getDouble("Std"));
            mat.dep_report_subject_sq.add(result.getDouble("skewness"));
            mat.dep_report_subject_correlation.add(result.getDouble("correlation"));
           
        }
		

	}
	public void getAllFromSection(int year, int term) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT section_number,subject_code, CountA, CountB, CountC, CountD, "
				                                            + "CountF  FROM section "
                											+ "WHERE year = ? AND term = ?");
		
		PreparedStatement get_data1 = con.prepareStatement("SELECT subject_name FROM subject "
				+ "WHERE year = ? AND term = ?");

		
		get_data.setDouble(1, year);
		get_data.setDouble(2, term);
		
		get_data1.setDouble(1, year);
		get_data1.setDouble(2, term);

		ResultSet result = get_data.executeQuery();
		ResultSet result1 = get_data1.executeQuery();

		while (result.next()) {
            mat.dep_report_section_number.add(result.getInt("section_number"));
            mat.dep_report_subject_code.add(result.getInt("subject_code"));
            mat.count_A.add(result.getInt("CountA"));
            mat.count_B.add(result.getInt("CountB"));
            mat.count_C.add(result.getInt("CountC"));
            mat.count_D.add(result.getInt("CountD"));
            mat.count_F.add(result.getInt("CountF"));

        }
		for(int i = 0; i <= (mat.count_A.size()-1) ;i++){
			int a,b,c,d,f;
			int res = 0;
			a = mat.count_A.get(i);
			b = mat.count_B.get(i);
			c = mat.count_C.get(i);
			d = mat.count_D.get(i);
			f = mat.count_F.get(i);
			
			res = a+b+c+d+f;
			
			mat.students_count.add(res);
			
		}
		
		for(int i=0; i<= (mat.students_count.size()-1);i++){
			
			double pass=0;
			int std_count = mat.students_count.get(i);
			int std_f= mat.count_F.get(i);
			pass=(std_count - std_f );
			int pass1 =(int) ((pass/std_count)*100);
			mat.std_pass.add((double) pass1);
		}
		
		
		while (result1.next()) {
            mat.dep_report_subject_name.add(result1.getString("subject_name"));
            
        }
		

	}
	public void getAllFromSection(int year, int term, String dep) throws SQLException{
		PreparedStatement get_data = con.prepareStatement("SELECT section_number FROM section "
                											+ "WHERE year = ? AND term = ? AND department = ?");
		

		
		get_data.setDouble(1, year);
		get_data.setDouble(2, term);
		get_data.setString(3, dep);
		ResultSet result = get_data.executeQuery();
		
		while (result.next()) {
            mat.all_sections.add(result.getInt("section_number"));
            
        }
		

	}
	

}
