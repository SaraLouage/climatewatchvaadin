//package be.faros.web;
//
//import javax.servlet.Filter;
//
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import be.faros.dao.CreateDAOBeans;
//import be.faros.services.CreateServiceBeans;
//
//
//public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return 
//				new Class<?>[] {
//			CreateServiceBeans.class,
//				CreateDAOBeans.class};
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class<?>[] { CreateControllerBeans.class };
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
//		utf8Filter.setEncoding("UTF-8");
//		return new Filter[] { utf8Filter };
//	}
//}