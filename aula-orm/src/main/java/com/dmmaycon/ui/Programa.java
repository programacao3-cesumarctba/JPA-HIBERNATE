package com.dmmaycon.ui;

import java.util.List;
import java.util.Scanner;

import com.dmmaycon.models.Contato;
import com.dmmaycon.services.impl.ContatoServices;

public class Programa {

	private static Scanner scan = new Scanner(System.in);
	private static ContatoServices cs = new ContatoServices();
	
	public static void main(String[] args) {
		listaTodos();
		int condition = 0;
		do {
			condition = menu();
			
			switch (condition) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				listaTodos();
				break;
			case 5:
				listaUm();
				break;
			case 0:
				System.out.println("Fechando programa!");
				break;
			default:
				System.out.println("Opção invalida!");
				break;
			}
			
		} while (condition != 0);

	}

	
	private static int menu() {
		System.out.println("--- Escolha a Opção ---");
		System.out.println("1 - Para Inserir Contato");
		System.out.println("2 - Para Alterar Contato");
		System.out.println("3 - Para Excluir Contato");
		System.out.println("4 - Listar todos os Contatos");
		System.out.println("5 - Listar Contato por ID");
		System.out.println("0 - Sair do Programa");
		
		return scan.nextInt();		
	}
	
	private static void insert() {
		scan.nextLine();
		Contato c = new Contato();
		System.out.println("Digite o nome do contato");
		c.setNome(scan.nextLine());
		System.out.println("Digite o telefone do contato");
		c.setTelefone(scan.nextLine());		
		Contato c2 = cs.insert(c);		
		System.out.println("Inseriu o contato id: " + c2.getId());
	}
	
	private static void update() {
		scan.nextLine();
		
		System.out.println("Digite o id do contato");
		int id = scan.nextInt();
		scan.nextLine();		
		Contato c = cs.byId(id);
		
		System.out.println("Nome: " + c.getNome() + " Digite o novo nome:");
		c.setNome(scan.nextLine());
		System.out.println("Telefone: " + c.getTelefone() + " Digite o novo telefone do contato");
		c.setTelefone(scan.nextLine());		
		cs.update(c);		
		System.out.println("Alterou o contato id: " + c.getId());
	}
	
	private static void delete() {
		scan.nextLine();
		
		System.out.println("Digite o id do contato");
		Integer id = scan.nextInt();
		scan.nextLine();		
		Contato c = cs.byId(id);
		cs.delete(c);
		System.out.println("Excluiu o contato id: " + id);
	}
	
	private static void listaUm() {
		scan.nextLine();
		System.out.println("Digite o id do contato");
		int id = scan.nextInt();
		scan.nextLine();		
		Contato c = cs.byId(id);
		
		System.out.println(String.format("Id: %d \nNome: %s \nTelefone: %s", c.getId(), c.getNome(), c.getTelefone()));
	}
	
	private static void listaTodos() {
		List<Contato> lista = cs.all();
		System.out.println("Lista de Contatos");
		lista.forEach(c -> {
			System.out.println("---------");
			System.out.println(String.format("Id: %d \nNome: %s \nTelefone: %s", c.getId(), c.getNome(), c.getTelefone()));
		});
		if (lista.isEmpty()) {
			System.out.println("Não existe contato cadastrado!");
		}
	}

}
