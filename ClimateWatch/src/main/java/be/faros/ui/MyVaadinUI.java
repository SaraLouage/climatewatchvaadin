package be.faros.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import be.faros.config.ApplicationConfig;
import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;
import be.faros.services.ClimateWatchEventService;
import ua.net.freecode.chart.AxisSystem;
import ua.net.freecode.chart.AxisSystem.AxisHorizontal;
import ua.net.freecode.chart.AxisSystem.AxisVertical;
import ua.net.freecode.chart.Chart;
import ua.net.freecode.chart.CurvePresentation;
import ua.net.freecode.chart.Marker;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	
	@Autowired
	ClimateWatchEventService eventService;
	Table table = new Table();
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "MyWidgetset")
	public static class Servlet extends SpringVaadinServlet {
	} 

	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout content = new VerticalLayout();
		setContent(content);
		
//		 Calendar
		InlineDateField calendar = new InlineDateField();
		calendar.setValue(new Date());	
		calendar.setImmediate(true);
		calendar.setTimeZone(TimeZone.getTimeZone("GMT 01:00"));
		calendar.setLocale(Locale.ENGLISH);
		calendar.setResolution(Resolution.DAY);
		

		
//		DropDown locatie
		List<ClimateWatchEvent> events = eventService.findAll();
		NativeSelect locatie = new NativeSelect("Select an option");
		for(ClimateWatchEvent ce : events){
			locatie.addItem(ce.getLocation());
		}

		locatie.setNullSelectionAllowed(false);
		locatie.setValue(events.get(0).getLocation());
		locatie.setImmediate(true);

		locatie.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));

		//table 
		//aan de hand van locatie toevoegen
		calendar.addValueChangeListener(e -> makeTable(calendar, events));
		Chart chart = new Chart();
		makeChart(chart);
		//adding content
		content.addComponents(locatie, calendar,chart, table);
			
		}
	private void makeChart(Chart chart){
		chart.addStyleName("UniqueColorsBlueGreenRedScheme");
		chart.setWidth("100%");
		chart.setHeight("400px");
		//20 pixels at the right is necessary because we have long labels at the bottom axis and when
		//there is no legend, they are cut if we do not provide some additional space at the right
		chart.setMinRightMargin(20);
		//our labels at the bottom axis are at the angle of 45 and they need 100-pixel height of the space
		chart.setAxisLabelStringHeight(100);
		chart.setGeneralTitle("Sample General Chart Header");
		chart.setLegendData(new String[]{"Freecode","OST Ltd.","IT Ltd.","Dobryvechir"});
		//The value of legend item width is adjusted according to the longest string in the legend data,
		//which can be chosen experimentally
		chart.setLegendItemWidth(112);
		AxisSystem axisSystem = chart.createAxisSystem(AxisHorizontal.Bottom, AxisVertical.Left);
		//our range is 0..1000 (it is not necessary to specify 0 as minimum since it is the default)
		axisSystem.setVerticalAxisMaxValue(1000);
		//the angle for each label at the bottom axis is 45 degrees
		axisSystem.setHorizontalAxisLabelAngle(45);
		axisSystem.setHorizontalAxisTitle("Year 2012");
		axisSystem.setVerticalAxisTitle("Incomes and losses of the company");
		axisSystem.setCurvePresentation(new CurvePresentation[]{
				new CurvePresentation(new Marker(Marker.MarkerShape.Circle),0,CurvePresentation.CurveKind.VerticalBar),
				new CurvePresentation(new Marker(Marker.MarkerShape.Rectangle),0,CurvePresentation.CurveKind.VerticalBar),
				new CurvePresentation(new Marker(Marker.MarkerShape.Square),2,CurvePresentation.CurveKind.Line),
				new CurvePresentation(new Marker(Marker.MarkerShape.NoMarker),1,CurvePresentation.CurveKind.Area),
		});
		axisSystem.setXDiscreteValues(new String[]{"January 2012",
				"February 2012","March 2012","April 2012","May 2012","June 2012",
				"July 2012", "August 2012","September 2012","October 2012",
				"November 2012","December 2012"});
		axisSystem.setYDiscreteValuesForAllSeries(new double[][]{
				new double[]{300,400,450,500,657,450,230,100,500,200,300,500},
				new double[]{196,20,212,302,0,12,30,33,64,100,200,212},
				new double[]{0,100,1500,1750,187,155,-190,1900,199,1200,-5,300,-5,300},
				new double[]{0,72,500,100,20,100,500,88,150,160,10,200}
		});


	}
	private void makeTable(InlineDateField calendar, List<ClimateWatchEvent> events) {
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
	}

	
	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {}
	
	@Configuration
	@EnableVaadin
	@Import(value = {ApplicationConfig.class})
	public static class MyConfiguration{
		
	}

}