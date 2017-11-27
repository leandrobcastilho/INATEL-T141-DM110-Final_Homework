package br.inatel.t141.dm110.network.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address", allocationSize = 1)
public class Address {

	@Id
	@GeneratedValue(generator = "seq_address", strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String ip;

	private String status;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
