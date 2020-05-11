package com.nikipon.ipanalysis;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nikipon.ipanalysis.web.IpAnalysisController;
import com.nikipon.ipanalysis.web.UserController;


// testing that the application creates the controllers
@RunWith(SpringRunner.class)
@SpringBootTest
class IpanalysisApplicationTests {

	@Autowired
	private IpAnalysisController ipcontroller;
	
	@Autowired
	private UserController ucontroller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(ipcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}

}
