package be.faros.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;
import be.faros.services.ClimateWatchEventService;
import ua.net.freecode.chart.AxisSystem;
import ua.net.freecode.chart.AxisSystem.AxisHorizontal;
import ua.net.freecode.chart.AxisSystem.AxisVertical;
import ua.net.freecode.chart.Chart;
import ua.net.freecode.chart.CurvePresentation;
import ua.net.freecode.chart.Marker;

@SpringView(name= StartView.VIEW_NAME)
public class StartView extends VerticalLayout implements View{
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "";
	@Autowired
	ClimateWatchEventService eventService;

		@PostConstruct
		public void init(){

		// Calendar  
		InlineDateField calendarMenu = new InlineDateField();
		calendarMenu.setValue(new Date());
		calendarMenu.setImmediate(true);
		calendarMenu.setTimeZone(TimeZone.getTimeZone("GMT 01:00"));
		calendarMenu.setLocale(Locale.ENGLISH);
		calendarMenu.setResolution(Resolution.DAY);

		// DropDown locatie
		NativeSelect locatieMenu = new NativeSelect("Select an option", 
				eventService.findAllLocations());
		locatieMenu.setNullSelectionAllowed(false);
		locatieMenu.setImmediate(true);
//		chart
		Chart chart = new Chart();
//		listeners
		locatieMenu.addValueChangeListener(
				e -> checkValueChange(locatieMenu.getValue(), calendarMenu.getValue(), eventService, chart));
		calendarMenu.addValueChangeListener(
				e -> checkValueChange(locatieMenu.getValue(), calendarMenu.getValue(), eventService, chart));

		// adding content
		addComponents(locatieMenu, calendarMenu, chart);
	}
	public void checkValueChange(Object locatieMenu, Date calendarMenu,
			ClimateWatchEventService eventService, Chart chart) {
		if (locatieMenu != null) {
			makeChart(eventService.findByDateAndLocation(calendarMenu,
					((Location) locatieMenu).getLocation_id()), chart);

		}
	}

	public void makeChart(List<ClimateWatchEvent> events, Chart chart) {
		chart.addStyleName("UniqueColorsBlueGreenRedScheme");
		chart.setWidth("100%");
		chart.setHeight("400px");
		chart.setMinRightMargin(20);
		chart.setAxisLabelStringHeight(100);
		chart.setGeneralTitle("ClimateWatch");
		chart.setLegendData(new String[] { "temperature" });
		chart.setLegendItemWidth(112);
		AxisSystem axisSystem = chart.createAxisSystem(AxisHorizontal.Bottom, AxisVertical.Left);
		axisSystem.setVerticalAxisMaxValue(100);
		axisSystem.setHorizontalAxisLabelAngle(45);
		axisSystem.setHorizontalAxisTitle("time");
		axisSystem.setVerticalAxisTitle("temperature");
		axisSystem.setCurvePresentation(new CurvePresentation[] {
				new CurvePresentation(new Marker(Marker.MarkerShape.Circle), 0, CurvePresentation.CurveKind.Line) });
		String[] hours = new String[] { "01", "03", "05", "07", "09", "11", "13", "15", "17", "19", "21", "23" };
		axisSystem.setXDiscreteValues(hours);
		double[] array = new double[hours.length];

		for (ClimateWatchEvent ce : events) {
			for (int i = 0; i < hours.length; i++) {
				if (ce.getTime().get(Calendar.HOUR_OF_DAY) == Integer.parseInt(hours[i])) {
					array[i] = (double) ce.getDegrees();
				}
			}
		}
		axisSystem.setYDiscreteValuesForAllSeries(new double[][] { array });
	}
	@Override
	public void enter(ViewChangeEvent event) {
	}
}
