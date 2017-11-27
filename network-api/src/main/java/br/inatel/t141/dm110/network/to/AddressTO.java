package br.inatel.t141.dm110.network.to;

import java.io.Serializable;

public class AddressTO implements Serializable {

	private static final long serialVersionUID = 5130026976280738887L;

	private String ip;

	private String status;
	
	public AddressTO() {
	}

	public AddressTO(String ip, String status) {
		this.ip = ip;
		this.status = status;
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
