package br.inatel.t141.dm110.network.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.t141.dm110.network.dao.AddressDAO;
import br.inatel.t141.dm110.network.entities.Address;
import br.inatel.t141.dm110.network.interfaces.NetworkLocal;
import br.inatel.t141.dm110.network.interfaces.NetworkRemote;
import br.inatel.t141.dm110.network.to.AddressTO;
import br.inatel.t141.dm110.network.to.PatchAddressesTO;

@Stateless
@Remote(NetworkRemote.class)
@Local(NetworkLocal.class)
public class NetworkBean implements NetworkRemote, NetworkLocal {

	@EJB
	private NetworkSender networkSender;
	
	@EJB
	private AddressDAO addressDAO;
	
	
	@Override
	public List<AddressTO> listStatusAdress() {
		return addressDAO.listAllAdress().stream().map(a -> new AddressTO(a.getIp(), a.getStatus())).collect(Collectors.toList());
	}
	
	@Override
	public List<AddressTO> listActiveAdress() {
		return addressDAO.listActiveAdress().stream().map(a -> new AddressTO(a.getIp(), a.getStatus())).collect(Collectors.toList());
	}
	
	
	@Override
	public AddressTO getStatusAdress(String ip) {
		AddressTO addressTO = new AddressTO();
		Address address = addressDAO.getAdressByIp(ip);
		if( address != null ){
			addressTO.setIp(address.getIp());
			addressTO.setStatus(address.getStatus());
		}
		return addressTO;
	}
	
	@Override
	public void sendScanPatchAddresses(PatchAddressesTO patchAddressesTO){
		networkSender.sendListIpAddress(patchAddressesTO);
	}
	
}
