package aula.orientacao.enumarator.modelo;

import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import aula.orientacao.enumarator.persistencia.ClientePersistencia;

public class Aplicacao {

	
	public static void main(String[] args) {
		
		
		ClientePersistencia persistencia = new ClientePersistencia();
		Scanner scanner = new Scanner(System.in);
		
		int op;
		
		do {
			System.out.println("--- BEM VINDO AO BANCO SANTA CRUZ ---");
			System.out.println("-------------------------------------");
			System.out.println("-- O QUE DESEJA FAZER? --");
			System.out.println("-------------------------------------");
			System.out.println("-- 1.  CADASTRAR CLIENTE --");
			System.out.println("-- 2.  LISTAR TODOS OS CLIENTES --");
			System.out.println("-- 3.  LISTAR CLIENTE POR CPF --");
			System.out.println("-- 4.  REMOVER CLIENTE --");
			System.out.println("-- 5.  CRIAR CONTA E ASSOCIAR AO CLIENTE --");
			System.out.println("-- 6.  LISTAR CONTAS DO CLIENTE --");
			System.out.println("-- 7.  REMOVER CONTA DE UM CLIENTE --");
			System.out.println("-- 8.  DEPOSITAR --");
			System.out.println("-- 9.  SACAR --");
			System.out.println("-- 10. TRANSFERIR PARA OUTRA CONTA --");
			System.out.println("-- 11. VER EXTRATO POR MES E ANO --");
			System.out.println("-- 12. CONSULTAR SALDO --");
			System.out.println("-- 13. VER BALANÇO DAS CONTAS --");
			System.out.println("----------------------------------");
			System.out.println("-- PARA SAIR DIGITE 0 --");
			
			op = scanner.nextInt();
			scanner.nextLine();
			
			switch (op) {
			case 1: {
				System.out.println("Digite o nome do cliente");
				String nome = scanner.next();
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				
				Cliente cliente = new Cliente(cpf, nome);
				ArrayList<Cliente> clientes = persistencia.getClientesCadastrados();
				
				for(Cliente cli : clientes) {
					if(cli.cpf == cliente.cpf) {
						System.out.println("Cliente já cadastrado");
						System.out.println("------------------");
						break;
					} else {						
						persistencia.salvarCliente(cliente);
						System.out.println("Cliente cadastrado!");
						System.out.println("------------------");
						break;
					}
				}
				
			} case 2: {
				System.out.println("-- Lista de clientes --");
				System.out.println(persistencia.getClientesCadastrados());
				System.out.println("------------------");
				break;
				
			} case 3: {
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					System.out.println("------------------");
					System.out.println(persistencia.localizarClientePorCPF(cpf));
					System.out.println("------------------");
					break;					
				}
				
			} case 4: {
					System.out.println("Digite o cpf do cliente que deseja remover");
					String cpf = scanner.next();
					
					if(persistencia.localizarClientePorCPF(cpf) == null) {
						System.out.println("Esse cliente não existe!");
						break;
					} else {
						Cliente cli = persistencia.localizarClientePorCPF(cpf);
						persistencia.removerCliente(cli);
						System.out.println("Cliente removido!");
						System.out.println("------------------");
						break;
					}
			} case 5: {
					System.out.println("Digite o numero da conta que deseja criar");
					String numero = scanner.next();
					
					Conta conta = new Conta(numero);
					System.out.println("Conta criada com sucesso!");
					System.out.println("-----------------");
					System.out.println("Digite o CPF do cliente que deseja associar a essa conta");
					String cpf = scanner.next();
					if(persistencia.localizarClientePorCPF(cpf) == null) {
						System.out.println("------------------");
						System.out.println("Nenhum cliente encontrado!");
						System.out.println("------------------");
						break;
					} else {
						Cliente cli = persistencia.localizarClientePorCPF(cpf);
						cli.adicionarConta(conta);
						persistencia.atualizarCliente(cli);
						break;					
					}
			} case 6: {
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					System.out.println(cli.contas);
					break;					
				}
			} case 7: {
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					if(cli.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;
					} else {
						Conta c = cli.localizarContaPorNumero(numero);
						cli.removerConta(c);
						persistencia.atualizarCliente(cli);
						break;
					}
				}
			} case 8: {
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					if(cli.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;
					} else {
						System.out.println("Digite a quantia que deseja depositar");
						float valor = scanner.nextFloat();
						Conta c = cli.localizarContaPorNumero(numero);
						c.depositar(valor);
						persistencia.atualizarCliente(cli);
						break;
					}
				}
			} case 9: {
				System.out.println("Digite o CPF do cliente");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					if(cli.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;
					} else {
						System.out.println("Digite a quantia que deseja sacar");
						float valor = scanner.nextFloat();
						Conta c = cli.localizarContaPorNumero(numero);
						c.sacar(valor);
						persistencia.atualizarCliente(cli);
						break;
					}
				}
			} case 10: {
				System.out.println("Digite o CPF do cliente transferidor");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta origem");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cliOri = persistencia.localizarClientePorCPF(cpf);
					if(cliOri.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;	
					} else {
						System.out.println("Digite o CPF do cliente de destino");
						String cpfDest = scanner.next();
						Conta contOrig = cliOri.localizarContaPorNumero(numero);
						if(persistencia.localizarClientePorCPF(cpfDest) == null) {
							System.out.println("------------------");
							System.out.println("Nenhum cliente encontrado!");
							System.out.println("------------------");
							break;
						} else {
							Cliente cliDest = persistencia.localizarClientePorCPF(cpfDest);
							System.out.println("Digite o numero da conta de destino");
							String numeroDest = scanner.next();
							if(cliDest.localizarContaPorNumero(numeroDest) == null) {
								System.out.println("Conta não existe");
								break;
							} else {
								System.out.println("Digite a quantia que deseja transferir");
								float valor = scanner.nextFloat();
								Conta contDest = cliOri.localizarContaPorNumero(numero);
								contOrig.transferir(valor, contOrig, contDest);
								persistencia.atualizarCliente(cliDest);
								persistencia.atualizarCliente(cliOri);
								break;
							}
						}
					}
				}
			} case 11: {
				System.out.println("Digite o CPF do cliente portador da conta");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta a consultar o extrato");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					if(cli.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;
					} else {
						Conta c = cli.localizarContaPorNumero(numero);
						System.out.println("Digite o ano do extrato que quer ver");
						int ano = scanner.nextInt();

						System.out.println("Digite o numero equivalente ao mes do extrato que quer ver");
						System.out.println(" 1. JANEIRO ");
						System.out.println(" 2. FEVEREIRO ");
						System.out.println(" 3. MARÇO ");
						System.out.println(" 4. ABRIL ");
						System.out.println(" 5. MAIO ");
						System.out.println(" 6. JUNHO ");
						System.out.println(" 7. JULHO ");
						System.out.println(" 8. AGOSTO ");
						System.out.println(" 9. SETEMBRO ");
						System.out.println(" 10. OUTUBRO ");
						System.out.println(" 11. NOVEMBRO ");
						System.out.println(" 12. DEZEMBRO ");
						int mes = scanner.nextInt();
						
						if(mes < 1 || mes > 12) {
							System.out.println("Mes invalido");
							break;
						} else {
							Month mesFormat = Month.of(mes);
							c.extrato(ano, mesFormat);
							break;
						}
					}
				}
			} case 12: {
				System.out.println("Digite o CPF do cliente portador da conta");
				String cpf = scanner.next();
				System.out.println("Digite o numero da conta a consultar o extrato");
				String numero = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					if(cli.localizarContaPorNumero(numero) == null) {
						System.out.println("Conta não existe");
						break;
					} else {
						Conta c = cli.localizarContaPorNumero(numero);
						System.out.println("O saldo é: " + c.saldo);
						break;
					}
				}
			} case 13: {
				System.out.println("Digite o CPF do cliente da conta");
				String cpf = scanner.next();
				if(persistencia.localizarClientePorCPF(cpf) == null) {
					System.out.println("------------------");
					System.out.println("Nenhum cliente encontrado!");
					System.out.println("------------------");
					break;
				} else {
					Cliente cli = persistencia.localizarClientePorCPF(cpf);
					float balance = 0;
					for(Conta conta : cli.contas) {
						balance += conta.saldo;
					}
					
					System.out.println("Seu balanço total é: " + balance);
					break;
				}
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + op);
			}
			
		} while(op != 0);
	}
}
