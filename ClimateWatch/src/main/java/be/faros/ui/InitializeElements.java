package be.faros.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Table;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;
import ua.net.freecode.chart.AxisSystem;
import ua.net.freecode.chart.Chart;
import ua.net.freecode.chart.CurvePresentation;
import ua.net.freecode.chart.Marker;
import ua.net.freecode.chart.AxisSystem.AxisHorizontal;
import ua.net.freecode.chart.AxisSystem.AxisVertical;

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
		String [] hours = new String[]{"01:00",
			"03:00","05:00","07:00", "09:00","11:00"
			,"13:00","15:00","17:00","19:00","21:00","23:00"
			};
		int i = 0;
		double[] array = new double[hours.length];
		for (ClimateWatchEvent ce: events){
			array[i++]=(double) ce.getDegrees();
		}

		axisSystem.setXDiscreteValues(hours);
		axisSystem.setYDiscreteValuesForAllSeries(new double[][]{array});
		
	}
//	private static Table makeTable(InlineDateField calendar, List<ClimateWatchEvent> events) {
//		Table table = new Table();
//
//		table.removeAllItems();
//		table.addContainerProperty("location", Location.class, null);
//		table.addContainerProperty("time", Date.class, null);
//		
////		List<ClimateWatchEvent> eventsByDate = eventService.findByDate(calendar.getValue());
//
//		int row = 0;
//		for(ClimateWatchEvent ce : events){
//			Calendar ceCalendar = Calendar.getInstance();
//
//			ceCalendar.setTime(calendar.getValue());
//			//Timestamp vs date probleem oplossen
//
//			if (ce.getTime().getDate()== ceCalendar.getTime().getDate())
//		table.addItem(new Object[]{ce.getLocation(),ce.getTime()},row++ );
//		
//		}
//		return table;
//	}

}
