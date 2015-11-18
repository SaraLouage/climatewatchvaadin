package be.faros.ui;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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

import be.faros.services.ClimateWatchEventService;
import config.ApplicationConfig;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	
	@Autowired
	ClimateWatchEventService eventService;
	
//	public MyVaadinUI() {
//		ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
//		WebApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//		
//		eventService = appCtx.getBean("eventService", ClimateWatchEventService.class);
//	}
	
	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
	public static class Servlet extends SpringVaadinServlet {
	} 

	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout content = new VerticalLayout();
		setContent(content);
//		 DropDown locatie
		NativeSelect locatie = new NativeSelect("Select an option");
		System.out.println("-------------------------------------------------------");
		System.out.println(eventService);
//		List<ClimateWatchEvent> locations = eventService.findAll();
		for (int i = 1; i < 7; i++) {
			locatie.addItem("location" + i);
			locatie.setItemCaption(i, "Option " + i);
		}

		locatie.setNullSelectionAllowed(false);
		locatie.setValue("location1");
		locatie.setImmediate(true);

		locatie.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));
//		 Calendar
		InlineDateField calendar = new InlineDateField();
		calendar.setValue(new Date());
		calendar.setImmediate(true);
		calendar.setTimeZone(TimeZone.getTimeZone("GMT 01:00"));
		calendar.setLocale(Locale.ENGLISH);
		calendar.setResolution(Resolution.DAY);

		calendar.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));

		content.addComponents(calendar, locatie);
	}
	
	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {}
	
	@Configuration
	@EnableVaadin
	@Import(value = {ApplicationConfig.class})
	public static class MyConfiguration{
		
	}

}