package com.nikipon.ipanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nikipon.ipanalysis.domain.ClientRepository;
import com.nikipon.ipanalysis.domain.Client;

@SpringBootApplication
@EnableScheduling
public class IpanalysisApplication {
	private static final Logger log = LoggerFactory.getLogger(IpanalysisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IpanalysisApplication.class, args);
	}
	
	// initialize bean commandlinerunner, if the admin and test user are not in the database
	// then insert their information
	@Bean
	public CommandLineRunner book(ClientRepository urepo) {
		return (args) -> {
			log.info("TEST");
		
			if(urepo.findByUsername("admin") == null) {
				Client admin = new Client("admin", "$2y$12$nKgLT6EzFKKRM2AQ/d1FouZJ469kbSJGWEMSM3Dx8jb53W1fCfPma", "admin@app.com", "ADMIN");
				urepo.save(admin);
			}
			if(urepo.findByUsername("user") == null) {
		Client client = new Client("user", "$2y$12$d.0pg9NArxtZ5yMFAD.R9egDFmlxxdHnkCoW952fDdCISn88O7KlK", "test@email.com", "USER");
		urepo.save(client);
			}
		};
}
}