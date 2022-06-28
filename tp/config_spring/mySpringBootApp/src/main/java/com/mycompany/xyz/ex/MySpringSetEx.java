package com.mycompany.xyz.ex;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Basic spring commponent to experiment @DirtiesContext() in test class
@Component
public class MySpringSetEx {
	
	private Set<String> exDataSet = new HashSet<>();
	
	private static Logger logger = LoggerFactory.getLogger(MySpringSetEx.class);
	
	
	public MySpringSetEx() {
		super();
		logger.debug("MySpringSetEx instance="+this.toString());
	}

	public void addDataInExDataSet(String value) {
		exDataSet.add(value);
	}
	
	public Set<String> getExDataSet() {
		return this.exDataSet;
	}

}
