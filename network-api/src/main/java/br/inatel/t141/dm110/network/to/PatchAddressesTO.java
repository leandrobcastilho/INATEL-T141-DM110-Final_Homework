package br.inatel.t141.dm110.network.to;

import java.io.Serializable;
import java.util.List;

public class PatchAddressesTO implements Serializable {

	private static final long serialVersionUID = 5130026976280738887L;

	private List<String> listIpAddress;
	
	public PatchAddressesTO(List<String> listIpAddress) {
		this.listIpAddress = listIpAddress;
	}
	
	public List<String> getListIpAddress() {
		return listIpAddress;
	}

	public void setListIpAddress(List<String> listIpAddress) {
		this.listIpAddress = listIpAddress;
	}
	
}
