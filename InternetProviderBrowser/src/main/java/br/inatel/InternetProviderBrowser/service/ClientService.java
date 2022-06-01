package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Client;

@Service
@Transactional
public class ClientService implements ServiceModel<Client, Long> {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Client> list() {
		return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
	}

	public List<Client> list(Long id) {
		return em.createQuery("SELECT c FROM Client c WHERE c.id = ?1", Client.class).setParameter(1, id).getResultList();
	}

	@Override
	public Client find(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client insert(Client client) {
		return em.merge(client);
	}

	@Override
	public Client update(Long id, Client client) {
		Client c = em.find(Client.class, id);
		c.setName(client.getName());
		c.setCpf(client.getCpf());
		c.setBirthDate(client.getBirthDate());
		c.setLat(client.getLat());
		c.setLng(client.getLng());
		return em.merge(c);
	}

	@Override
	public void delete(Long id) {
		Client c = em.find(Client.class, id);
		em.remove(c);
	}

}
