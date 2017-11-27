package br.inatel.t141.dm110.network.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.inatel.t141.dm110.network.entities.Address;

@Stateless
public class AddressDAO {
	
	@PersistenceContext(unitName = "inventory")
	private EntityManager em;
	
	public List<Address> listAllAdress() {
		return em.createQuery("from Address a", Address.class).getResultList();
	}
	
	public List<Address> listActiveAdress() {
		List<Address> addresses = new ArrayList<>();
		try {
			String status = "Ativo";
			Query query = em.createQuery("SELECT a FROM Address a WHERE a.status = :status", Address.class);
			query.setParameter("status", status);
			
			addresses = (List<Address>) query.getResultList();
		} catch (NoResultException e) {
		}
		
		return addresses;
	}
	
	public Address getAdressByIp(String ip) {
		
		Address address = null;
		try {
			Query query = em.createQuery("SELECT a FROM Address a WHERE a.ip = :ip", Address.class);
			query.setParameter("ip", ip);
			
			address = (Address) query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return address;
	}
	
	public void insert(Address address) {
		em.persist(address);
	}
	
	public void update(Address address) {
		em.merge(address);
	}
	
}
