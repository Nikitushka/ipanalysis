package com.nikipon.ipanalysis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ipdata {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private Long id;

private String ip;
private String location;
private String isp;
private String userAgent;
private int connectedPort;
private String usern;

@OneToOne(mappedBy = "ipdata")
@JsonIgnore
private Client client;

public Ipdata(String ip, String location, String isp, String userAgent, int connectedPort, String usern) {
	this.ip = ip;
	this.location = location;
	this.isp = isp;
	this.userAgent = userAgent;
	this.connectedPort = connectedPort;
	this.usern = usern;
}


public void setUsern(String usern) {
	this.usern = usern;
}


public int getConnectedPort() {
	return connectedPort;
}

public void setConnectedPort(int connectedPort) {
	this.connectedPort = connectedPort;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsern() {
	return usern;
}


public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getIsp() {
	return isp;
}

public void setIsp(String isp) {
	this.isp = isp;
}

public String toString() {
	return "User: " + usern + ", IP: " + ip + ", Location: " + location + ", ISP: " + isp +
			", User-Agent: " + userAgent;
}

}
