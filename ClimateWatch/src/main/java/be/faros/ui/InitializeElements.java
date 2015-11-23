package be.faros.ui;

import java.util.Formatter;
import java.util.List;

import be.faros.entities.ClimateWatchEvent;
import ua.net.freecode.chart.AxisSystem;
import ua.net.freecode.chart.AxisSystem.AxisHorizontal;
import ua.net.freecode.chart.AxisSystem.AxisVertical;
import ua.net.freecode.chart.Chart;
import ua.net.freecode.chart.CurvePresentation;
import ua.net.freecode.chart.Marker;

public class InitializeElements {
	static Chart chart = new Chart();
	public static void makeChart(List<ClimateWatchEvent> events){

		chart.addStyleName("UniqueColorsBlueGreenRedScheme");
		chart.setWidth("100%");
		chart.setHeight("400px");
		chart.setMinRightMargin(20);
		chart.setAxisLabelStringHeight(100);
		chart.setGeneralTitle("ClimateWatch");
		chart.setLegendData(new String[]{"temperature"});
		chart.setLegendItemWidth(112);
		AxisSystem axisSystem = chart.createAxisSystem(AxisHorizontal.Bottom, AxisVertical.Left);
		axisSystem.setVerticalAxisMaxValue(100);
		axisSystem.setHorizontalAxisLabelAngle(45);
		axisSystem.setHorizontalAxisTitle("time");
		axisSystem.setVerticalAxisTitle("temperature");
		axisSystem.setCurvePresentation(new CurvePresentation[]{
				new CurvePresentation(new Marker(Marker.MarkerShape.Circle),0,CurvePresentation.CurveKind.Line)
		});
		String [] hours = new String[]{"01",
				"03","05","07", "09","11"
				,"13","15","17","19","21","23"
				};
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("%s:00",(Object[])hours);
		axisSystem.setXDiscreteValues(hours);
		double[] array = new double[hours.length];

		for (ClimateWatchEvent ce: events){
			for(int i= 0; i< hours.length; i++){
				if (ce.getTime().getHours()== Integer.parseInt(hours[i])){
					array[i]=(double) ce.getDegrees();
					}
			}	
		}
		axisSystem.setYDiscreteValuesForAllSeries(new double[][]{array});
		
	}
}
