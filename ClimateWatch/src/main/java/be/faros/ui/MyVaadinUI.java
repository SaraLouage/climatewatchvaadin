package be.faros.ui;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import be.faros.config.ApplicationConfig;
import be.faros.services.ClimateWatchEventService;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

	@Autowired
	ClimateWatchEventService eventService;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout content = new VerticalLayout();
		setContent(content);

		// Calendar  
		InlineDateField calendarMenu = new InlineDateField();
		calendarMenu.setValue(new Date());
		calendarMenu.setImmediate(true);
		calendarMenu.setTimeZone(TimeZone.getTimeZone("GMT 01:00"));
		calendarMenu.setLocale(Locale.ENGLISH);
		calendarMenu.setResolution(Resolution.DAY);

		// DropDown locatie
		NativeSelect locatieMenu = new NativeSelect("Select an option", eventService.findAllLocations());
		locatieMenu.setNullSelectionAllowed(false);
		locatieMenu.setImmediate(true);
		locatieMenu.addValueChangeListener(
				e -> InitializeElements.checkValueChange(locatieMenu.getValue(), calendarMenu.getValue(), eventService));
		calendarMenu.addValueChangeListener(
				e -> InitializeElements.checkValueChange(locatieMenu.getValue(), calendarMenu.getValue(), eventService));

		// adding content
		content.addComponents(locatieMenu, calendarMenu, InitializeElements.chart);
	}




	// configuration classes
	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {
	}

	@Configuration
	@EnableVaadin
	@Import(value = { ApplicationConfig.class })
	public static class MyConfiguration {
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "MyWidgetset")
	public static class Servlet extends SpringVaadinServlet {
	}
}