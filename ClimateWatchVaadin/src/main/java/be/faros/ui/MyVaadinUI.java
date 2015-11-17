package be.faros.ui;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout content = new VerticalLayout();
		setContent(content);

		// DropDown locatie
		NativeSelect locatie = new NativeSelect("Select an option");
		for (int i = 1; i < 7; i++) {
			locatie.addItem("location" + i);
			locatie.setItemCaption(i, "Option " + i);
		}

		locatie.setNullSelectionAllowed(false);
		locatie.setValue("location1");
		locatie.setImmediate(true);

		locatie.addValueChangeListener(e -> Notification.show("Value changed:",
				String.valueOf(e.getProperty().getValue()), Type.TRAY_NOTIFICATION));
		// Calendar
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

}