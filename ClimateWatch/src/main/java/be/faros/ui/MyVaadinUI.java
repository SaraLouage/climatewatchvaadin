package be.faros.ui;

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

import com.ibm.icu.util.Calendar;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import be.faros.config.ApplicationConfig;
import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;
import be.faros.services.ClimateWatchEventService;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	
	@Autowired
	ClimateWatchEventService eventService;

	
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
		List<Location> events = eventService.findAllLocations();
		NativeSelect locatie = new NativeSelect("Select an option", events);
		locatie.setNullSelectionAllowed(false);
		locatie.setImmediate(true);
		locatie.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));
		
//		InitializeElements.makeChart(eventService.findByDate(new Date()));
		
		//aan de hand van locatie toevoegen
//
		calendar.addValueChangeListener(e -> checkValueChange(locatie, calendar));

		//adding content
		content.addComponents(locatie, calendar, InitializeElements.chart);		
		}
	private void checkValueChange(NativeSelect locatie, InlineDateField calendar){
		if((locatie.getValue()!=null) && (calendar.getValue()!=null)){
			InitializeElements.makeChart(eventService
					.findByDateAndLocation(calendar.getValue(), locatie.getValue().));
		}
	}
	
	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {}
	
	@Configuration
	@EnableVaadin
	@Import(value = {ApplicationConfig.class})
	public static class MyConfiguration{}

}