package br.inatel.t141.dm110.network.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

import br.inatel.t141.dm110.network.to.PatchAddressesTO;

public class NetworkIpGenerate {

	public static List<PatchAddressesTO> genPatchNetwork(String ip, String cidr) {
		
		List<PatchAddressesTO> patchAddressesTOs = new ArrayList<>();
		if( NetworkIpValidate.isIpv4(ip) && NetworkIpValidate.isCidr(cidr)){
			String[] ips = generateIps(ip, Integer.valueOf(cidr));
			List<String> listIpAddress = new ArrayList<>();
			for (String ipAddress : ips) {
				listIpAddress.add(ipAddress);
				if( listIpAddress.size() == 10){
					patchAddressesTOs.add(new PatchAddressesTO(listIpAddress));
					listIpAddress = new ArrayList<>();
				}
			}
			if(listIpAddress.size() > 0){
				patchAddressesTOs.add(new PatchAddressesTO(listIpAddress));
			}
		}
		return patchAddressesTOs;
	}
	
	public static String[] generateIps(String networkIp, int cidr) {
		String cidrNotation = networkIp+"/"+cidr;
		SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
		return subnetUtils.getInfo().getAllAddresses();
	}
	
}
