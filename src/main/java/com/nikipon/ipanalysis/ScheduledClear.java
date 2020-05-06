package com.nikipon.ipanalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nikipon.ipanalysis.domain.Client;
import com.nikipon.ipanalysis.domain.ClientRepository;
import com.nikipon.ipanalysis.domain.IpdataRepository;

@Component
public class ScheduledClear {
	@Autowired
	private ClientRepository urepo;
	@Autowired
	private IpdataRepository iprepo;
	
	// Clears the whole database and reinserts default users every hour
	@Scheduled(fixedRate = 3600000)
	public void clearDB() {
		urepo.deleteAll();
		iprepo.deleteAll();
		
		if(urepo.findByUsername("admin") == null) {
			Client admin = new Client("admin", "$2y$12$nKgLT6EzFKKRM2AQ/d1FouZJ469kbSJGWEMSM3Dx8jb53W1fCfPma", "admin@app.com", "ADMIN");
			urepo.save(admin);
		}
		if(urepo.findByUsername("user") == null) {
	Client client = new Client("user", "$2y$12$d.0pg9NArxtZ5yMFAD.R9egDFmlxxdHnkCoW952fDdCISn88O7KlK", "test@email.com", "USER");
	urepo.save(client);
		}
	}
}
