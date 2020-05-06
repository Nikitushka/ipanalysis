package com.nikipon.ipanalysis.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IpdataRepository extends CrudRepository<Ipdata, Long> {
	List<Ipdata> findByIp(String ip);
}
