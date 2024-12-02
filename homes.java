import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
  
public class homes extends JFrame {  
  
  private static final long serialVersionUID = 1L;  
  
  public homes(String appTitle) {  
    super(appTitle);  
  
    // Create Dataset  
    CategoryDataset dataset = createDataset();  
      
    //Create chart  
    JFreeChart chart=ChartFactory.createBarChart(  
        "Bar Chart Example", //Chart Title  
        "Year", // Category axis  
        "Population in Million", // Value axis  
        dataset,  
        PlotOrientation.VERTICAL,  
        true,true,false  
       );  
  
    ChartPanel panel=new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private CategoryDataset createDataset() {  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
  
    // Population in 2005  
    dataset.addValue(10, "January", "2015");  
    dataset.addValue(15, "February", "2015");  
    dataset.addValue(20, "March", "2015");  
  
    // Population in 2010  
    dataset.addValue(15, "August", "2016");  
    dataset.addValue(20, "September", "2016");  
    dataset.addValue(25, "October", "2016");  
  
    // Population in 2015  
    dataset.addValue(20, "May", "2017");  
    dataset.addValue(25, "June", "2017");  
    dataset.addValue(30, "July", "2017");  
  
    return dataset;  
  }  
  
  public static void main(String[] args) throws Exception {  
    SwingUtilities.invokeAndWait(()->{  
      homes example=new homes("Bar Chart Window");  
      example.setSize(800, 400);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  }  
}  