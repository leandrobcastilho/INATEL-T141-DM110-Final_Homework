package br.inatel.t141.dm110.network.mdb;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.t141.dm110.network.businnes.NetworkIpAvaliable;
import br.inatel.t141.dm110.network.dao.AddressDAO;
import br.inatel.t141.dm110.network.entities.Address;
import br.inatel.t141.dm110.network.to.PatchAddressesTO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/t141dM110networkqueue") 
})
public class NetworkMDB implements MessageListener {

	@EJB
	private AddressDAO addressDAO;
	
	@Override
	public void onMessage(Message message) {
		// Message processing...
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				PatchAddressesTO networkTO = (PatchAddressesTO) objectMessage.getObject();
				List<String> listIpAddress = networkTO.getListIpAddress();
				for (String ipAddress : listIpAddress) {
					System.out.println("####  network processing " + ipAddress);
					boolean isAvaliable = NetworkIpAvaliable.execPing(ipAddress);
					Address address = addressDAO.getAdressByIp(ipAddress);
					if( address == null ){
						address = new Address();
						address.setIp(ipAddress);
						address.setStatus(isAvaliable?"Ativo":"Inativo");
						addressDAO.insert(address);
					}else{
						address.setIp(ipAddress);
						address.setStatus(isAvaliable?"Ativo":"Inativo");
						addressDAO.update(address);
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {

		}
	}
	
}
