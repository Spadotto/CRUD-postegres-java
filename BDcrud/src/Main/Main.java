package Main;

import java.sql.ResultSet;
import java.util.Scanner;

import BancodeDados.Conexao;

public class Main {

	private static Scanner Teclado = new Scanner(System.in);

	public static void main(String[] args) {
		
		do {
			
			System.out.println("+-----------------------------------------------+\n|                                               |");
			System.out.println("|Digite a opção:                                |\n|                                               |");
			System.out.println("|1. Adicionar aluno;                            |\n|                                               |");
			System.out.println("|2. Buscar aluno;                               |\n|                                               |");
			System.out.println("|3. Remover aluno;                              |\n|                                               |");
			System.out.println("|4. Editar dados de aluno;                      |\n|                                               |");
			System.out.println("|5. Listar todos os alunos;                     |\n|                                               |");
			System.out.println("|0. Sair                                        |\n|                                               |");
			System.out.println("+-----------------------------------------------+\n");
			int opcao = Teclado.nextInt();
			Teclado.nextLine();
			
			if(opcao == 1) {
				Conexao con = new Conexao();
				System.out.println("\nID do novo aluno:\n");
				int id = Teclado.nextInt();
				Teclado.nextLine();
				System.out.println("\nNome do novo aluno:\n");
				String nome = Teclado.nextLine();
				System.out.println("\nNova turma do novo aluno:\n");
				String turma = Teclado.nextLine();
				String sql = "INSERT INTO alunos (id, nome, turma)VALUES ("+id+", '"+nome+"', '"+turma+"')";
				int res = con.executaSQL(sql);
				if(res>0) {
					System.out.println("Cadastro realizado com sucesso!");
				}else {
					System.out.println("Erro ao cadastrar aluno!");
				}
			}
			
			if(opcao == 2) {
				Conexao con = new Conexao();
				System.out.println("\nNome do aluno que deseja buscar:\n");
				String busca = Teclado.nextLine();
				String sql = "Select * from alunos where nome = '"+busca+"'";
				ResultSet rs = con.executaBusca(sql);
				try {
					while(rs.next()) {
						int id = rs.getInt("id");
						String nome = rs.getString("nome");
						String turma = rs.getString("turma");
						System.out.println(id+" - "+nome+" - "+turma);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(opcao == 3) {
				Conexao con = new Conexao();
				System.out.println("\nNome do aluno que deseja deletar:\n");
				String busca = Teclado.nextLine();
				String sql = "DELETE FROM alunos where nome = '"+busca+"'";
				int res = con.executaSQL(sql);
				if(res>0) {
					System.out.println("Aluno exluido com sucesso!");
				}else {
					System.out.println("Erro ao excluir aluno!");
				}
			}
			
			if(opcao == 4) {
				Conexao con = new Conexao();
				System.out.println("\nNome do aluno que deseja alterar:\n");
				String busca = Teclado.nextLine();
				System.out.println("\nNome do aluno:\n");
				String novonome = Teclado.nextLine();
				System.out.println("\nNova turma do aluno:\n");
				String novaturma = Teclado.nextLine();
				String sql = "UPDATE alunos SET nome = '"+novonome+"', turma = '"+novaturma+"' where nome = '"+busca+"'";
				int res = con.executaSQL(sql);
				if(res>0) {
					System.out.println("Aluno alterado com sucesso!");
				}else {
					System.out.println("Erro ao alterar dados de aluno!");
				}
			}
			
			if(opcao == 5) {
				Conexao con = new Conexao();
				String sql = "Select * from alunos order by id";
				ResultSet rs = con.executaBusca(sql);
				try {
					while(rs.next()) {
						int id = rs.getInt("id");
						String nome = rs.getString("nome");
						String turma = rs.getString("turma");
						System.out.println(id+" - "+nome+" - "+turma);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			
			if (opcao == 0) {
				System.out.println("\n\nDeslogando....\n");
				System.exit(0);
			}
			
		}while(true);

	}

}
