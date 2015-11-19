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
	static Chart chart;
	public static void makeChart(InlineDateField calendar, List<ClimateWatchEvent> events){
		chart.addStyleName("UniqueColorsBlueGreenRedScheme");
		chart.setWidth("100%");
		chart.setHeight("400px");
		//20 pixels at the right is necessary because we have long labels at the bottom axis and when
		//there is no legend, they are cut if we do not provide some additional space at the right
		chart.setMinRightMargin(20);
		//our labels at the bottom axis are at the angle of 45 and they need 100-pixel height of the space
		chart.setAxisLabelStringHeight(100);
		chart.setGeneralTitle("ClimateWatch");
		chart.setLegendData(new String[]{"temperature"});
		//The value of legend item width is adjusted according to the longest string in the legend data,
		//which can be chosen experimentally
		chart.setLegendItemWidth(112);
		AxisSystem axisSystem = chart.createAxisSystem(AxisHorizontal.Bottom, AxisVertical.Left);
		//our range is 0..1000 (it is not necessary to specify 0 as minimum since it is the default)
		axisSystem.setVerticalAxisMaxValue(1000);
		//the angle for each label at the bottom axis is 45 degrees
		axisSystem.setHorizontalAxisLabelAngle(45);
		axisSystem.setHorizontalAxisTitle("time");
		axisSystem.setVerticalAxisTitle("temperature");
		axisSystem.setCurvePresentation(new CurvePresentation[]{
				new CurvePresentation(new Marker(Marker.MarkerShape.Circle),0,CurvePresentation.CurveKind.VerticalBar),
//				new CurvePresentation(new Marker(Marker.MarkerShape.Rectangle),0,CurvePresentation.CurveKind.VerticalBar),
//				new CurvePresentation(new Marker(Marker.MarkerShape.Square),2,CurvePresentation.CurveKind.Line),
//				new CurvePresentation(new Marker(Marker.MarkerShape.NoMarker),1,CurvePresentation.CurveKind.Area),
		});
		axisSystem.setXDiscreteValues(new String[]{"January 2012",
				"February 2012","March 2012","April 2012","May 2012","June 2012",
				"July 2012", "August 2012","September 2012","October 2012",
				"November 2012","December 2012"});
		axisSystem.setYDiscreteValuesForAllSeries(new double[][]{
				new double[]{300,400,450,500,657,450,230,100,500,200,300,500},
				new double[]{196,20,212,302,0,12,30,33,64,100,200,212}
		});
		
	}
	private static Table makeTable(InlineDateField calendar, List<ClimateWatchEvent> events) {
		Table table = new Table();

		table.removeAllItems();
		table.addContainerProperty("location", Location.class, null);
		table.addContainerProperty("time", Date.class, null);
		
//		List<ClimateWatchEvent> eventsByDate = eventService.findByDate(calendar.getValue());

		int row = 0;
		for(ClimateWatchEvent ce : events){
			Calendar ceCalendar = Calendar.getInstance();

			ceCalendar.setTime(calendar.getValue());
			//Timestamp vs date probleem oplossen

			if (ce.getTime().getDate()== ceCalendar.getTime().getDate())
		table.addItem(new Object[]{ce.getLocation(),ce.getTime()},row++ );
		
		}
		return table;
	}

}
