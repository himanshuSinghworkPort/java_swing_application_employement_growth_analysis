package employmentgrowthanalysis;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmploymentGrowthAnalysis extends JFrame {
    private JTextField xField;
    private JTextField yField;
    private DefaultCategoryDataset dataset;

    public EmploymentGrowthAnalysis() {
        setTitle("Employment Growth Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Enter Year (X-axis): "));
        xField = new JTextField();
        inputPanel.add(xField);

        inputPanel.add(new JLabel("Enter Employment Growth (Y-axis): "));
        yField = new JTextField();
        inputPanel.add(yField);

        JButton addButton = new JButton("Add Data");
        inputPanel.add(addButton);

        // Graph Panel
        dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "Employment Statistical Growth",
                "Year",
                "Employment Growth (%)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Add Button Action Listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String year = xField.getText();
                String growth = yField.getText();

                if (!year.isEmpty() && !growth.isEmpty()) {
                    try {
                        double growthValue = Double.parseDouble(growth);
                        dataset.addValue(growthValue, "Employment Growth", year);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number for employment growth.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both Year and Employment Growth.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmploymentGrowthAnalysis app = new EmploymentGrowthAnalysis();
            app.setVisible(true);
        });
    }
}
