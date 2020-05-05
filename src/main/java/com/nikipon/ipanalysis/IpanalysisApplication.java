package com.nikipon.ipanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nikipon.ipanalysis.domain.ClientRepository;
import com.nikipon.ipanalysis.domain.Client;

@SpringBootApplication
public class IpanalysisApplication {
	private static final Logger log = LoggerFactory.getLogger(IpanalysisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IpanalysisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner book(ClientRepository urepo) {
		return (args) -> {
			log.info("TEST");
//			Client admin = new Client("admin", "$2y$12$nKgLT6EzFKKRM2AQ/d1FouZJ469kbSJGWEMSM3Dx8jb53W1fCfPma", "admin@app.com", "ADMIN");
//			Client client = new Client("user", "$2y$12$d.0pg9NArxtZ5yMFAD.R9egDFmlxxdHnkCoW952fDdCISn88O7KlK", "test@email.com", "USER");
//			urepo.save(client);
//			urepo.save(admin);
		};
}
}