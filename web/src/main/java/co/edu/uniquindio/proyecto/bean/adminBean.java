package co.edu.uniquindio.proyecto.bean;



import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.servicios.CompraService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class adminBean implements Serializable {

    @Getter @Setter
    public BarChartModel barModel;

    @Getter @Setter
    public  int year;

    @Getter @Setter
    public List<Integer> comprasMes;
    
    @Autowired
    private CompraService compraServicio;

    @PostConstruct
    public void init() {

        year = 2021;
        createBarModel();

    }

    public void createBarModel() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();
        comprasMes = new ArrayList<>();
        llenarReporteCompras();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Tabla de ventas");

        List<Number> values = new ArrayList<>();
        values.add(comprasMes.get(0));
        values.add(comprasMes.get(1));
        values.add(comprasMes.get(2));
        values.add(comprasMes.get(3));
        values.add(comprasMes.get(4));
        values.add(comprasMes.get(5));
        values.add(comprasMes.get(6));
        values.add(comprasMes.get(7));
        values.add(comprasMes.get(8));
        values.add(comprasMes.get(9));
        values.add(comprasMes.get(10));
        values.add(comprasMes.get(11));
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Enero");
        labels.add("Febrero");
        labels.add("Marzo");
        labels.add("Abril");
        labels.add("Mayo");
        labels.add("Junio");
        labels.add("Julio");
        labels.add("Agosto");
        labels.add("Septiembre");
        labels.add("Octubre");
        labels.add("Noviembre");
        labels.add("Diciembre");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Compras en el año");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public void llenarReporteCompras() {
        //Obviamente hay una manera de optimizar esta tarea:
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,01,01, 00, 00, 02),LocalDateTime.of(year,02,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,02,01, 00, 00, 02),LocalDateTime.of(year,03,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,03,01, 00, 00, 02),LocalDateTime.of(year,04,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,04,01, 00, 00, 02),LocalDateTime.of(year,05,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,05,01, 00, 00, 02),LocalDateTime.of(year,06,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,06,01, 00, 00, 02),LocalDateTime.of(year,07,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,07,01, 00, 00, 02),LocalDateTime.of(year,8,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,8,01, 00, 00, 02),LocalDateTime.of(year,9,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,9,01, 00, 00, 02),LocalDateTime.of(year,10,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,10,01, 00, 00, 02),LocalDateTime.of(year,11,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,11,01, 00, 00, 02),LocalDateTime.of(year,12,01, 00, 00, 01)).size());
        comprasMes.add(compraServicio.listarPorMes(LocalDateTime.of(year,12,01, 00, 00, 02),LocalDateTime.of((year+1),01, 01, 00, 01)).size());
        //Obviamente no me se esa manera lol
    }
}
