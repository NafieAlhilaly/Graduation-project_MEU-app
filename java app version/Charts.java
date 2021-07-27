
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;



public class Charts extends ApplicationFrame{

    MaterialInfo m = new MaterialInfo();
  
    
	
	public Charts(final String title) {
	super(title);
	
	    
    
	}
	
	public void createCharts(double[] grades, String chart_title) throws IOException, InterruptedException {
        XYSeriesCollection series_coll = new XYSeriesCollection();
        XYSeries normal_dst_series = new XYSeries("normal distribution");
        XYSeries grade_dtr_series = new XYSeries("grade distribution");
        XYSplineRenderer render = new XYSplineRenderer();
        XYPlot plot;
        
        normal_dst_series.add(1, 2);
        normal_dst_series.add(2, 14);
        normal_dst_series.add(3, 68);
        normal_dst_series.add(4, 14);
        normal_dst_series.add(5, 2);
        
        grade_dtr_series.add(1, grades[4]);
        grade_dtr_series.add(2, grades[3]);
        grade_dtr_series.add(3, grades[2]);
        grade_dtr_series.add(4, grades[1]);
        grade_dtr_series.add(5, grades[0]);
        series_coll.addSeries(normal_dst_series);
        series_coll.addSeries(grade_dtr_series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(null,null, null, series_coll,
                PlotOrientation.VERTICAL, true, true, false);
         
        
        		ChartFrame frame = new ChartFrame("Chart", chart);
                frame.pack();
                plot = (XYPlot) chart.getPlot();
                plot.setRenderer(new XYSplineRenderer());
                ChartUtilities.saveChartAsJPEG(new File("C:/MEU-system/charts/chart.JPEG"), chart,320, 200);
                TimeUnit.MILLISECONDS.sleep(5);

    }

	public void createBarchartBasedOnMean() throws InterruptedException{
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Mean");
		xAxis.setLabel("Subject Code");       
		yAxis.setLabel("");

		XYChart.Series series1;
		for(int i = 0; i <= (m.dep_report_subject_code.size()-1); i++ ){
			series1 = new XYChart.Series();
			series1.setName(m.dep_report_subject_code.get(i).toString());
			series1.getData().add(new XYChart.Data(m.dep_report_subject_code.get(i).toString(), m.dep_report_subject_mean.get(i)));
		
		
		bc.getData().add(series1);
		}
		Scene scene  = new Scene(bc,1200,600);
		stage.setScene(scene);

		xAxis.setAnimated(false); 
		yAxis.setAnimated(false);
		
		//stage.show();
	     File file = new File("C:/MEU-system/dept_charts/chart1.PNG");
	     try {
	    	 WritableImage image = scene.snapshot(null);
	         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
	         TimeUnit.MILLISECONDS.sleep(5);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     
	}

	public void createBarchartBasedOnCorrelaation() throws InterruptedException{
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Correlation");
		xAxis.setLabel("Subject Code");       
		yAxis.setLabel("");

		XYChart.Series series1;
		for(int i = 0; i <= (m.dep_report_subject_code.size()-1); i++ ){
			series1 = new XYChart.Series();
			series1.setName(m.dep_report_subject_code.get(i).toString());
			series1.getData().add(new XYChart.Data(m.dep_report_subject_code.get(i).toString(), m.dep_report_subject_correlation.get(i)));
		
		bc.getData().add(series1);
		}
		Scene scene  = new Scene(bc,1200,600);
		stage.setScene(scene);

		xAxis.setAnimated(false); 
		yAxis.setAnimated(false);
		
		//stage.show();
	     File file = new File("C:/MEU-system/dept_charts/chart2.PNG");
	     try {
	    	 WritableImage image = scene.snapshot(null);
	         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
	         TimeUnit.MILLISECONDS.sleep(5);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     
	}

	public void createBarchartBasedOnStd() throws InterruptedException{
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Standard Deviation");
		xAxis.setLabel("Subject Code");       
		yAxis.setLabel("");

		XYChart.Series series1;
		for(int i = 0; i <= (m.dep_report_subject_code.size()-1); i++ ){
			series1 = new XYChart.Series();
			series1.setName(m.dep_report_subject_code.get(i).toString());
			series1.getData().add(new XYChart.Data(m.dep_report_subject_code.get(i).toString(), m.dep_report_subject_std.get(i)));
		
		
		bc.getData().add(series1);
		}
		Scene scene  = new Scene(bc,1200,600);
		stage.setScene(scene);
		xAxis.setAnimated(false); 
		yAxis.setAnimated(false);
		
		//stage.show();
	     File file = new File("C:/MEU-system/dept_charts/chart3.PNG");
	     try {
	    	 WritableImage image = scene.snapshot(null);
	         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
	         TimeUnit.MILLISECONDS.sleep(5);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     
	}

	public void createLinechartBasedOnSkewness() throws InterruptedException{
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<String,Number> bc = 	new LineChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Skewness");
		xAxis.setLabel("Subject Code");       
		yAxis.setLabel("");

		XYChart.Series series1;
		for(int i = 0; i <= (m.dep_report_subject_code.size()-1); i++ ){
			series1 = new XYChart.Series();
			series1.setName(m.dep_report_subject_code.get(i).toString());
			series1.getData().add(new XYChart.Data(m.dep_report_subject_code.get(i).toString(), m.dep_report_subject_sq.get(i)));
		
		
		bc.getData().add(series1);
		}
		Scene scene  = new Scene(bc,1200,600);
		stage.setScene(scene);

		xAxis.setAnimated(false); 
		yAxis.setAnimated(false);
		
		//stage.show();
	     File file = new File("C:/MEU-system/dept_charts/chart4.PNG");
	     try {
	    	 WritableImage image = scene.snapshot(null);
	         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
	         TimeUnit.MILLISECONDS.sleep(5);
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     
	}
	
	
	/*public void createLinechartBasedOnSkewness() throws IOException, InterruptedException {
        XYSeriesCollection series_coll = new XYSeriesCollection();
        XYSeries normal_dst_series = new XYSeries("Skewness");
       
        XYPlot plot;
        for(int i = 0; i<= (m.dep_report_subject_code.size()-1); i++){
        	
        	normal_dst_series.add(m.dep_report_subject_code.get(i), m.dep_report_subject_sq.get(i));
        
        }
    	series_coll.addSeries(normal_dst_series);
    	
        JFreeChart chart = ChartFactory.createXYLineChart(null,null, null, series_coll,
                PlotOrientation.VERTICAL, true, true, false);
         
        
        		ChartFrame frame = new ChartFrame("Chart", chart);
                frame.pack();
                plot = (XYPlot) chart.getPlot();
                ChartUtilities.saveChartAsJPEG(new File("C:/MEU-system/dept_charts/linechart.JPEG"), chart,800, 600);
                TimeUnit.MILLISECONDS.sleep(5);

    }
	*/
/*public void createDepReportForSubjectsMean(double CS_counter, double IS_counter, double NE_counter, double CE_counter, String name ,int year ,int term){
		
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Mean");
		xAxis.setLabel("Subject Code");       
		yAxis.setLabel("Average");
	
		XYChart.Series series = new XYChart.Series();
		series.setName("CS");       
		series.getData().add(new XYChart.Data("CS", CS_counter));
	
		
		bc.getData().addAll(series);
		
		Scene scene  = new Scene(bc,800,600);
		stage.setScene(scene);
		stage.show();
	    
	}
*/


	/*public void createCollageReportForSubjectsBar(double CS_counter, double IS_counter, double NE_counter, double CE_counter, String name ,int year ,int term){
		
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Subjects with defects in "+ name+", year " + year +", semester " + term);
		xAxis.setLabel("Depts");       
		yAxis.setLabel("Percentage of subjects that are defective");
	
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("CS");       
		series1.getData().add(new XYChart.Data("CS", CS_counter));
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("IS");
		series2.getData().add(new XYChart.Data("IS", IS_counter));
	
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("NE");
		series3.getData().add(new XYChart.Data("NE", NE_counter));
	
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("CE");
		series4.getData().add(new XYChart.Data("CE", CE_counter));
	
		
		bc.getData().addAll(series1,series2,series3,series4);
		
		Scene scene  = new Scene(bc,800,600);
		stage.setScene(scene);
		stage.show();
	    
	}
	public void createCollageReportForSectionsBar(double CS_counter, double IS_counter, double NE_counter, double CE_counter, String name ,int year ,int term){
		Stage stage = new Stage();
		
		stage.setTitle("Bar Chart Sample");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 	new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Sections with defects in "+ name+", year " + year +", semester " + term);
		xAxis.setLabel("Depts");       
		yAxis.setLabel("Percentage of sections that are defective");
	
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("CS");       
		series1.getData().add(new XYChart.Data("CS", CS_counter));
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("IS");
		series2.getData().add(new XYChart.Data("IS", IS_counter));
	
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("NE");
		series3.getData().add(new XYChart.Data("NE", NE_counter));
	
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("CE");
		series4.getData().add(new XYChart.Data("CE", CE_counter));
	
		
		bc.getData().addAll(series1,series2,series3,series4);
		
		Scene scene  = new Scene(bc,800,600);
		stage.setScene(scene);
		stage.show();
	    
	}
	
	public void createCollageReportForSubjectsPie(double CS_counter, double IS_counter, double NE_counter, double CE_counter, String name ,int year ,int term){
		 Scene scene = new Scene(new Group());
	        Stage stage = new Stage();
	        stage.setTitle("");
	        stage.setWidth(550);
	        stage.setHeight(500);
	 
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("CS", CS_counter),
	                new PieChart.Data("IS", IS_counter),
	                new PieChart.Data("NE", NE_counter),
	                new PieChart.Data("CE", CE_counter));
	        
	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle("Subjects with defects in "+ name+", year " + year +", semester " + term);

	        ((Group) scene.getRoot()).getChildren().add(chart);
	        stage.setScene(scene);
	        stage.show();
	}
	
	public void createCollageReportForSectionsPie(double CS_counter, double IS_counter, double NE_counter, double CE_counter, String name ,int year ,int term){
		 Scene scene = new Scene(new Group());
	        Stage stage = new Stage();
	        stage.setTitle("");
	        stage.setWidth(550);
	        stage.setHeight(500);
	 
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("CS", CS_counter),
	                new PieChart.Data("IS", IS_counter),
	                new PieChart.Data("NE", NE_counter),
	                new PieChart.Data("CE", CE_counter));
	        
	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle("Sections with defects in "+ name+", year " + year +", semester " + term);

	        ((Group) scene.getRoot()).getChildren().add(chart);
	        stage.setScene(scene);
	        stage.show();
	}
	*/
	
}