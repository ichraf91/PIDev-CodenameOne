/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.views;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ReservationService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class StatForm extends Form {

    Form current;
    ArrayList<Reservation> reservationArrayList = new ArrayList<>();
    ReservationService reservationService = new ReservationService();
    int pendingNumber = 0;
    int confirmedNumber = 0;

    public StatForm(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Stats Planning");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        reservationArrayList = reservationService.showAll();
        for (Reservation reservation : reservationArrayList) {
            if (reservation.getStatus().equals("ENATTENTE"))                 pendingNumber++;
             else                 confirmedNumber++;
                   }
        double[] values = new double[]{pendingNumber, confirmedNumber};
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(128);
        renderer.setChartTitle("Reservation");
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer simpleSeriesRendererSpectacle = renderer.getSeriesRendererAt(0);
        simpleSeriesRendererSpectacle.setGradientEnabled(true);
        simpleSeriesRendererSpectacle.setGradientStart(0, ColorUtil.MAGENTA);
        simpleSeriesRendererSpectacle.setGradientStop(0, ColorUtil.MAGENTA);
        simpleSeriesRendererSpectacle.setHighlighted(true);

        SimpleSeriesRenderer simpleSeriesRendererFilm = renderer.getSeriesRendererAt(1);
        simpleSeriesRendererFilm.setGradientEnabled(true);
        simpleSeriesRendererFilm.setGradientStart(0, ColorUtil.CYAN);
        simpleSeriesRendererFilm.setGradientStop(0, ColorUtil.CYAN);
        simpleSeriesRendererFilm.setHighlighted(true);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart pieChart = new PieChart(buildCategoryDataset("Reservation", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent chartComponent = new ChartComponent(pieChart);
        add(chartComponent);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(32);
        renderer.setLegendTextSize(64);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setAxesColor(ColorUtil.BLACK);

        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Pending", values[0]);
        series.add("Confirmed", values[1]);
        return series;
    }
}