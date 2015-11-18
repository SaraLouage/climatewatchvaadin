package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.faros.dao.ClimateWatchEventDAO;
import be.faros.dao.CreateDAOBeans;
import be.faros.entities.Location;
import be.faros.services.ClimateWatchEventService;
import be.faros.services.CreateServiceBeans;

@Configuration
@Import(value = {CreateDAOBeans.class, CreateServiceBeans.class})
@ComponentScan(basePackageClasses = {Location.class, ClimateWatchEventDAO.class, ClimateWatchEventService.class})
public class ApplicationConfig {

}
