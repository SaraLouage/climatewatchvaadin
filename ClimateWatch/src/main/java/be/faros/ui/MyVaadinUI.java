package be.faros.ui;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

import be.faros.config.ApplicationConfig;

@Theme("valo")
@SpringUI
public class MyVaadinUI extends UI {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SpringViewProvider viewProvider;
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("ClimateWatch");
		navigator = new Navigator(this,this);
		navigator.addProvider(viewProvider);
		navigator.addView("",  new StartView());
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
		private static final long serialVersionUID = 1L;
	}
}