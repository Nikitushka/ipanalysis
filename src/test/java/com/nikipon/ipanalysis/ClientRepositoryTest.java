package com.nikipon.ipanalysis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nikipon.ipanalysis.domain.Client;
import com.nikipon.ipanalysis.domain.ClientRepository;

// tests that all the repository commands work

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

	@Autowired
	private ClientRepository repo;
	
	// test the creation of user TestCase with password "password"
	@Test
	public void createClient() {
		Client client = new Client("TestCase","$2y$12$kzbOGFp.kW3QgYpFlkvv7.TsEsxylbeK8oretygBSWX3qJU7t9tJa",
				"testuser@testmail.com", "USER");
		repo.save(client);
		
		assertThat(client.getId()).isNotNull();
	}
	
	// test that finding by username should return user
	@Test
	public void returnClientByUsernameTest() {
		Client client = repo.findByUsername("admin");
		
		assertThat(client.getEmail().equals("admin@app.com"));
	}
	
	//test that finding by email should return user
	@Test
	public void returnClientByEmailTest() {
		Client client = repo.findByEmail("admin@app.com");
		
		assertThat(client.getUsername().equals("admin"));
	}
	
	// test that deleting a user works
	@Test
	public void deleteTest() {
		Client client = new Client("TestCase","$2y$12$kzbOGFp.kW3QgYpFlkvv7.TsEsxylbeK8oretygBSWX3qJU7t9tJa",
				"testuser@testmail.com", "USER");
		
		repo.save(client);
		
		Long id = client.getId();
		
		repo.deleteById(id);
		
		Client test = repo.findByUsername("TestCase");
		
		assertThat(test).isNull();
	}

}
