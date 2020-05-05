package com.nikipon.ipanalysis.domain;

import org.springframework.data.repository.CrudRepository;

public interface IpdataRepository extends CrudRepository<Ipdata, Long> {
	Ipdata findByUsern(String usern);
}
