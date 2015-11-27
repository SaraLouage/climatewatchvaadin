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

@SpringView(name = StartView.VIEW_NAME)
public class StartView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "";
	String[] valuesXAxisTime; 
	NativeSelect locationSelection;
	InlineDateField calendar;
	Chart chart;
	AxisSystem axisSystem;

	@Autowired
	ClimateWatchEventService eventService;

	@PostConstruct
	public void init() {

		makeLocationSelection();
		makeCalendar();
		makeChartLayout();

		locationSelection.addValueChangeListener(e -> ifLocationNotNullAddContent());
		calendar.addValueChangeListener(e -> ifLocationNotNullAddContent());

		addComponents(locationSelection, calendar, chart);
	}

	private void makeLocationSelection() {
		locationSelection = new NativeSelect("Select an option", eventService.findAllLocations());
		locationSelection.setNullSelectionAllowed(false);
		locationSelection.setImmediate(true);
	}

	private void makeCalendar() {
		calendar = new InlineDateField();
		calendar.setValue(new Date());
		calendar.setImmediate(true);
		calendar.setTimeZone(TimeZone.getTimeZone("GMT 01:00"));
		calendar.setLocale(Locale.ENGLISH);
		calendar.setResolution(Resolution.DAY);
	}

	private void makeChartLayout() {
		chart = new Chart();
		chart.addStyleName("UniqueColorsBlueGreenRedScheme");
		chart.setWidth("100%");
		chart.setHeight("400px");
		chart.setMinRightMargin(20);
		chart.setAxisLabelStringHeight(100);
		chart.setGeneralTitle("ClimateWatch");
		chart.setLegendData(new String[] { "temperature" });
		chart.setLegendItemWidth(112);
		makeAxisSystem();
		valuesXAxisTime = new String[] { "01", "03", "05", "07", "09", "11", "13", "15", "17", "19", "21", "23" };
		axisSystem.setXDiscreteValues(valuesXAxisTime);
	}

	private void makeAxisSystem() {
		axisSystem = chart.createAxisSystem(AxisHorizontal.Bottom, AxisVertical.Left);
		axisSystem.setVerticalAxisMaxValue(100);
		axisSystem.setHorizontalAxisLabelAngle(45);
		axisSystem.setHorizontalAxisTitle("time");
		axisSystem.setVerticalAxisTitle("temperature");
		axisSystem.setCurvePresentation(new CurvePresentation[] {
				new CurvePresentation(new Marker(Marker.MarkerShape.Circle), 0, CurvePresentation.CurveKind.Line) });
	}

	private void ifLocationNotNullAddContent() {
		if (locationSelection.getValue() != null) {
			addChartContent(eventService.findByDateAndLocation(calendar.getValue(),
					((Location) locationSelection.getValue()).getLocation_id()));
		}
	}

	private void addChartContent(List<ClimateWatchEvent> events) {

		double[] valuesYAxisDegrees = new double[valuesXAxisTime.length];
		for (ClimateWatchEvent ce : events) {
			for (int i = 0; i < valuesXAxisTime.length; i++) {
				if (ce.getTime().get(Calendar.HOUR_OF_DAY) == Integer.parseInt(valuesXAxisTime[i])) {
					valuesYAxisDegrees[i] = (double) ce.getDegrees();
				}
			}
		}
		axisSystem.setYDiscreteValuesForAllSeries(new double[][] { valuesYAxisDegrees });
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
