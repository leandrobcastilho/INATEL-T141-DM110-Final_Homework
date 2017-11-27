package br.inatel.t141.dm110.network.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.t141.dm110.network.api.NetworkService;
import br.inatel.t141.dm110.network.interfaces.NetworkRemote;
import br.inatel.t141.dm110.network.to.AddressTO;
import br.inatel.t141.dm110.network.to.PatchAddressesTO;
import br.inatel.t141.dm110.network.util.NetworkIpGenerate;
import br.inatel.t141.dm110.network.util.NetworkIpValidate;

@RequestScoped
public class NetworkServiceImpl implements NetworkService {

	@EJB(lookup = "java:app/network-ejb-0.1-SNAPSHOT/NetworkBean!br.inatel.t141.dm110.network.interfaces.NetworkRemote")
	private NetworkRemote network;

	@Override
	public List<AddressTO> listStatusAdress() {

		return network.listStatusAdress();
	}
	
	@Override
	public List<AddressTO> listActiveAdress() {

		return network.listActiveAdress();
	}
	
	@Override
	public AddressTO getStatusAdress(String ip) {
		AddressTO addressTO = null;
		if( NetworkIpValidate.isIpv4(ip) ){
			addressTO = network.getStatusAdress(ip);
		}
		return addressTO;
	}
		
	@Override
	public void checkStatusAdressNetwork(String ip, String cidr) {

		List<PatchAddressesTO> patchAddressesTOs = NetworkIpGenerate.genPatchNetwork(ip, cidr);
		for (PatchAddressesTO patchAddressesTO : patchAddressesTOs) {
			network.sendScanPatchAddresses(patchAddressesTO);
		}
	}
	
}
