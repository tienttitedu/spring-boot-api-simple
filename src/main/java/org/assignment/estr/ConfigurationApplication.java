package org.assignment.estr;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApplication {
	  @Bean
	  public DozerBeanMapper dozerBean() {
	    List<String> mappingFiles = Arrays.asList(
	      "dozer-bean-mappings.xml"
	    );

	    DozerBeanMapper dozerBean = new DozerBeanMapper();
	    dozerBean.setMappingFiles(mappingFiles);
	    return dozerBean;
	  }
}
