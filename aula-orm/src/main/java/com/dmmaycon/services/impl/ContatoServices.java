package com.dmmaycon.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.dmmaycon.models.Contato;
import com.dmmaycon.services.interfaces.CrudService;
import com.dmmaycon.utils.JpaUtils;

public class ContatoServices implements CrudService<Contato, Integer>{

	public List<Contato> all() {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.createQuery("from Contato", Contato.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Contato byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Contato.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Contato insert(Contato entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager(); // conexão ativa
			em.getTransaction().begin(); // inicia uma transação
			em.persist(entity);
			em.getTransaction().commit(); // escreve a transação grava no banco
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Contato update(Contato entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager(); // conexão ativa
			em.getTransaction().begin(); // inicia uma transação
			em.merge(entity);
			em.getTransaction().commit(); // escreve a transação grava no banco
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void delete(Contato entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager(); // conexão ativa			
			if (entity != null) {
				em.getTransaction().begin(); // inicia uma transação
				Contato c = em.merge(entity);
				em.remove(c);
				em.getTransaction().commit(); // escreve a transação grava no banco
			}			
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}

}
