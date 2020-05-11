package com.nikipon.ipanalysis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nikipon.ipanalysis.domain.Ipdata;
import com.nikipon.ipanalysis.domain.IpdataRepository;

// tests that all the repository commands work

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IpdataRepositoryTest {

	@Autowired
	private IpdataRepository repo;
	
	// test the creation of test Ip-data
	@Test
	public void createClient() {
		Ipdata ipdata = new Ipdata("127.0.0.1", "Finalnd, Minnesota", "Ramon Films Productions", "Mozilla etc.",
				9001, "TestCase");
		
		repo.save(ipdata);
		
		assertThat(ipdata.getId()).isNotNull();
	}
	
	// test that finding by ip should return the correct data
	@Test
	public void returnClientByUsernameTest() {
		Ipdata ipdata = new Ipdata("127.0.0.1", "Finalnd, Minnesota", "Ramon Films Productions", "Mozilla etc.",
				9001, "TestCase");
		
		assertThat(ipdata.getIp().equals("127.0.0.1"));
	}
	
	// test that deleting ipdata works
	@Test
	public void deleteTest() {
		Ipdata ipdata = new Ipdata("127.0.0.1", "Finalnd, Minnesota", "Ramon Films Productions", "Mozilla etc.",
				9001, "TestCase");
		
		repo.save(ipdata);
		
		Long id = ipdata.getId();
		
		repo.deleteById(id);
		
		Ipdata test = repo.findById(id).get();
		
		assertThat(test).isNull();
	}

}
