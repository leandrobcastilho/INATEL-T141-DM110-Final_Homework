package br.inatel.t141.dm110.network.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.t141.dm110.network.to.PatchAddressesTO;

@Stateless
public class NetworkSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/t141dM110networkqueue")
	private Queue queue;

	public void sendListIpAddress(PatchAddressesTO patchAddressesTO) {
		
		try (
				Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue);
				) {
			
			ObjectMessage objectMessage = session.createObjectMessage(patchAddressesTO);
			producer.send(objectMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}