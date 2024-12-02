import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public LoginPage(String appTitle) {
        super(appTitle);

        // Set the main layout
        setLayout(new BorderLayout());

        // Create and add components to the dashboard
        add(createTopBar(), BorderLayout.NORTH);
        add(createSideBar(), BorderLayout.WEST);
        add(createMainContent(), BorderLayout.CENTER);
    }

    // Create a top bar
    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setBackground(Color.DARK_GRAY);
        topBar.setPreferredSize(new Dimension(800, 50));

        JLabel title = new JLabel("Dashboard with JFreeChart");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        topBar.add(title);

        return topBar;
    }

    // Create a sidebar with buttons
    private JPanel createSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(0, 1));
        sideBar.setBackground(Color.LIGHT_GRAY);
        sideBar.setPreferredSize(new Dimension(150, 400));

        String[] buttons = {"Home", "Chart", "Settings"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(e -> JOptionPane.showMessageDialog(this, "You clicked: " + text));
            sideBar.add(button);
        }

        return sideBar;
    }

    // Create the main content area with the chart
    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());

        // Create a chart panel
        JFreeChart chart = createBarChart();
        ChartPanel chartPanel = new ChartPanel(chart);

        // Add chart panel to main content area
        mainContent.add(chartPanel, BorderLayout.CENTER);

        return mainContent;
    }

    // Create a bar chart using JFreeChart
    private JFreeChart createBarChart() {
        CategoryDataset dataset = createDataset();
        return ChartFactory.createBarChart(
                " G", // Chart title
                "Year",              // X-Axis Label
                "P", // Y-Axis Label
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
    }

    // Create dataset for the chart
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Population data
        dataset.addValue(10, "January", "2015");
        dataset.addValue(15, "February", "2015");
        dataset.addValue(20, "March", "2015");

        dataset.addValue(15, "August", "2016");
        dataset.addValue(20, "September", "2016");
        dataset.addValue(25, "October", "2016");

        dataset.addValue(20, "May", "2017");
        dataset.addValue(25, "June", "2017");
        dataset.addValue(30, "July", "2017");

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage dashboard = new LoginPage("Dashboard with Chart");
            dashboard.setSize(800, 600);
            dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
        });
    }
}
