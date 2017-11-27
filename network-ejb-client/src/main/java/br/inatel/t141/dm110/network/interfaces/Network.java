package br.inatel.t141.dm110.network.interfaces;

import java.util.List;

import br.inatel.t141.dm110.network.to.AddressTO;
import br.inatel.t141.dm110.network.to.PatchAddressesTO;


public interface Network {

	
	public List<AddressTO> listStatusAdress();
	
	public List<AddressTO> listActiveAdress();
	
	public AddressTO getStatusAdress(String ip);
	
	public void sendScanPatchAddresses(PatchAddressesTO patchAddressesTO);

}
