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
		List<ClimateWatchEvent> events = eventService.findAll();
		NativeSelect locatie = new NativeSelect("Select an option");
		for(ClimateWatchEvent ce : events){
			locatie.addItem(ce.getLocation());
		}

		locatie.setNullSelectionAllowed(false);
		locatie.setImmediate(true);

		locatie.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));
		
		InitializeElements.makeChart(events);
//		Table table = tableMaken(calendar);
		
		//aan de hand van locatie toevoegen

//		calendar.addValueChangeListener(e -> tableMaken(calendar));
		calendar.addValueChangeListener(e -> InitializeElements.makeChart(eventService.findByDate(calendar.getValue())));

		//adding content
		content.addComponents(locatie, calendar, InitializeElements.chart);		
		}

//	private Table tableMaken(InlineDateField calendar) {
//
//		Table table = new Table();
//		table.removeAllItems();
//		table.addContainerProperty("location", Location.class, null);
//		table.addContainerProperty("time", Date.class, null);
//		
//		Date date = calendar.getValue();
//		Calendar calendarVar = Calendar.getInstance();
//		calendarVar.setTime(date);
//		calendarVar.set(Calendar.HOUR_OF_DAY, 0);
//		calendarVar.set(Calendar.MINUTE, 0);
//		calendarVar.set(Calendar.SECOND, 0);
//		calendarVar.set(Calendar.MILLISECOND, 0);   
//		
//		List<ClimateWatchEvent> eventsByDate = eventService.findByDate(calendarVar.getTime());
//System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
//System.out.println("CalenderVar getValue: " + calendarVar.getTime());
//		int row = 0;
//
//		for(ClimateWatchEvent ce : eventsByDate){		
//		table.addItem(new Object[]{ce.getLocation(),ce.getTime()},row++ );			}
//		table.addItem(new Object[]{new Location() ,new Date()},row++ );
//
//		return table;
//	}
	
	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {}
	
	@Configuration
	@EnableVaadin
	@Import(value = {ApplicationConfig.class})
	public static class MyConfiguration{
		
	}

}