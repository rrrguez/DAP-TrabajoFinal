package es.ull.patrones.factory.products.barchart;

import es.ull.patrones.model.Brand;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

public class ItemsBarChart extends BarChart {
    public ItemsBarChart(List<Brand> brands) {
        super("Items Bar Chart");

        // Crear un conjunto de datos para el gráfico de barras
        CategoryDataset dataset = createDataset(brands);

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Number of items per brand",  // Título del gráfico
                "Brand",                          // Etiqueta del eje X
                "Number of items",                // Etiqueta del eje Y
                dataset,                          // Conjunto de datos
                PlotOrientation.VERTICAL,         // Orientación del gráfico
                true,                             // Incluir leyenda
                true,                             // Usar tooltips
                false                             // Usar URLs
        );

        // This will make the bars have different colors
        customizeRenderer(chart, brands);

        // Crear un panel de gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<Brand> brands) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar datos al conjunto de datos
        for (Brand brand : brands) {
            dataset.addValue(brand.getItemCount(), "Number of items", brand.getTitle());
        }

        return dataset;
    }

    private void customizeRenderer(JFreeChart chart, List<Brand> brands) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Configurar colores distintos para cada barra
        for (int i = 0; i < brands.size(); i++) {
            renderer.setSeriesPaint(i, getRandomColor());
        }
    }

    private Color getRandomColor() {
        // Generar un color aleatorio
        return new Color(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)
        );
    }
}