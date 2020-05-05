package com.nikipon.ipanalysis.domain;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
	Client findByUsername(String username);
	Client findByEmail(String email);
}
