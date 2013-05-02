import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class DataManager
{
	private List <Usuario> Usuarios;
	private List <Produto> Produtos;
	private List <Servico> Servicos;
	private Scanner s;
	
	public DataManager(Scanner scanner)
	{
		Usuarios = new ArrayList <Usuario>();
		Produtos = new ArrayList <Produto>();
		Servicos = new ArrayList <Servico>();
		s = scanner;		
	}
		
	public void cadastrarUsuario()
	{
		String name, cpf, email;
		Data today;
		Endereco address;
		
		System.out.print("--CADASTRAR USUARIO--\nEntre com o nome do usuario: ");
		name = s.nextLine();
		
		System.out.print("Entre com o CPF do usuario: ");
		cpf = s.nextLine();
		
		System.out.print("Entre com o email do usuario: ");
		email = s.nextLine();

		System.out.println("Entre com a data de hoje: ");
		{
			System.out.print("Dia: ");
			int day = s.nextInt();
			System.out.print("Mes: ");
			int month = s.nextInt();
			System.out.print("Ano: ");
			int year = s.nextInt();

			today = new Data(day, month, year);
		}

		System.out.println("Entre com o endereco do usuario: ");
		{
			System.out.print("Rua: ");
			String street = s.nextLine();
			System.out.print("Numero: ");
			int number = s.nextInt();
			System.out.print("Complemento: ");
			String apt = s.nextLine();
			System.out.print("Bairro: ");
			String district = s.nextLine();
			System.out.print("Cidade: ");
			String city = s.nextLine();
			System.out.print("Estado: ");
			String state = s.nextLine();
			System.out.print("CEP: ");
			String zip = s.nextLine();

			address = new Endereco(street, number, apt, district, city, state, zip);
		}

		Usuario usr = new Usuario(name, cpf, email, today, address);
		System.out.println("Usuario cadastrado com sucesso:\n" + usr);
		Usuarios.add(usr);
	}
	
	public void cadastrarProduto()
	{
	  System.out.print("--CADASTRAR PRODUTO--\nEntre com o t�tulo do produto: ");
	  String titulo = s.nextLine();
	  
	  System.out.print("Entre com a descri��o do produto: ");
	  String descricao = s.nextLine();
	  
	  System.out.print("Entre com o id do anunciante: ");
	  int user_id = s.nextInt();
	  
	  System.out.print("Entre com valor de venda: ");
	  float valor = s.nextFloat();
	  
	  System.out.print("O produto � novo? s/n ");
	  char op = s.nextLine().charAt(0);
	  boolean novo;
	  if (op == 's' || op == 'S')
	   novo = true;
	  else novo = false;
	  
	  System.out.print("O produto possui nota fiscal? s/n ");
	  op = s.nextLine().charAt(0);
	  boolean nf;
	  if (op == 's' || op == 'S')
	   nf = true;
	  else nf = false;
	  
	  System.out.print("Entre com a quantidade de dias de garantia: ");
	  int garantia = s.nextInt();
	  
	  Produto pr = new Produto(user_id, titulo, descricao, valor, novo, nf, garantia);
	  Produtos.add(pr);
	  System.out.println("Produto cadastrado com sucesso:\n" + pr);
	}
	
	public void cadastrarServico()
	{
		System.out.print("--CADASTRAR SERVI�O--\nEntre com o t�tulo do produto: ");
		String titulo = s.nextLine();
		  
		System.out.print("Entre com a descri��o do servi�o: ");
		String descricao = s.nextLine();
		
		System.out.print("Entre com o id do anunciante: ");
		int user_id = s.nextInt();
		  
		System.out.print("Entre com valor de venda: ");
		float valor = s.nextFloat();
		  
		System.out.print("Entre com a data inicial:\nDia: ");
		int diaI = s.nextInt();
		System.out.print("M�s: ");
		int mesI = s.nextInt();
		System.out.print("Ano: ");
		int anoI = s.nextInt();
		
		System.out.print("Entre com a data final:\nDia: ");
		int diaF = s.nextInt();
		System.out.print("M�s: ");
		int mesF = s.nextInt();
		System.out.print("Ano: ");
		int anoF = s.nextInt();

		Servico serv = new Servico(user_id, titulo, descricao, valor, new Data(diaI, mesI, anoI), new Data(diaF, mesF, anoF));
		Servicos.add(serv);
		System.out.println("Servi�o cadastrado com sucesso:\n" + serv);
	}
	public boolean isUsuariosEmpty(){return Usuarios.isEmpty();}
	public boolean isProdutosEmpty(){return Produtos.isEmpty();}
	public boolean isServicosEmpty(){return Servicos.isEmpty();}
	public int usuariosSize(){return Usuarios.size();}
	public int produtosSize(){return Produtos.size();}
	public int servicosSize(){return Servicos.size();}
	public void exibirUsuarios(){}
	public void exibirProdutos()
	{
		System.out.println("---ANUNCIOS DE PRODUTOS---");
		for(Produto p : Produtos)
		{
			System.out.println(p);
		}
	}
	public void exibirServicos()
	{
		System.out.println("---ANUNCIOS DE SERVICOS---");
		for(Servico s : Servicos)
		{
			System.out.println(s);
		}
	}
	public void comprarAnuncio()
	{
		System.out.print("---COMPRAS DE ANUNCIOS---\n1 - Comprar um produto\n2 - Comprar um servico" 
				+ "\n0 - Voltar.");
		int op = s.nextInt();
		
		if (op == 0)
			return;
		else if (op == 1)
		{
			System.out.print("Entre com a ID do comprador: ");
			int id = s.nextInt();
			
			int count = 0;
			for (Usuario u: Usuarios)
			{
				if (u.getID() == id) count++;
			}
			if(count == 0)
			{
				System.out.print("Usuario inexistente.");
				return;
			}
			else count = 0;
			
			System.out.print("Entre com a ID do produto: ");
			int idP = s.nextInt();
			System.out.print("Pesquisando... ");
			
			for (Produto p : Produtos)
			{
				if (p.getId() == idP)
				{
					count++;
					System.out.print("\nProduto encontrado...");
					if (p.isAvailable())
					{
						System.out.print(" Efetuando compra...");
						p.setComprador(id);
						p.setIndisponivel();
						System.out.print(" Compra realizada com sucesso.\nProduto: " + p
										+ "\nPertence ao usuario de ID " + id + "\n");
					}
					
					else
						System.out.print(" Produto indisponivel.");
				}
			}
			
			if (count == 0)
				System.out.print("Produto nao encontrado.");
		}
		else if (op == 2)
		{
			System.out.print("Entre com a ID do comprador: ");
			int id = s.nextInt();
			
			int count = 0;
			for (Usuario u: Usuarios)
			{
				if (u.getID() == id) count++;
			}
			if(count == 0)
			{
				System.out.print("Usuario inexistente.");
				return;
			}
			else count = 0;
			
			System.out.print("Entre com a ID do servico: ");
			int idS = s.nextInt();
			System.out.print("Pesquisando... ");
			
			for (Servico p : Servicos)
			{
				if (p.getId() == idS)
				{
					count++;
					System.out.print("\nProduto encontrado...");

					System.out.print(" Efetuando compra...");
					p.adicionarComprador(id);
					System.out.print(" Compra realizada com sucesso.\nServico: " + p
										+ "\nContratado ao usuario de ID " + id + "\n");
				}
			}
			
			if (count == 0)
				System.out.print("Servico nao encontrado.");
		}
		
		else System.out.println("Opcao invalida!");
	}
	
}
class Program
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int op = -1;
		DataManager l = new DataManager(s);
		
		while (op!=0)
		{
			//Imprimir menu principal
				System.out.print("----MENU PRINCIPAL----\n"
						+ "\n1 - Cadastrar usu�rios.");
			
				if (!l.isUsuariosEmpty())
				{
					System.out.print("\n2 - Cadastrar an�ncios.");
				
					if (l.usuariosSize() > 1)
						if(l.produtosSize() > 0 ||l.servicosSize() > 0)
							System.out.print("\n3 - Realizar compras.");
					
					System.out.print("\n4 - Exibir Relat�rios");
				}
				
				System.out.print("\n0 - Sair.");
				
				op = s.nextInt();
			
			//Executar de acordo com o input do usu�rio	
				if(op == 1)//Cadastrar Usuario
				{
					l.cadastrarUsuario();
				}

				else if (op == 2)//Cadastrar Anuncio
				{
					if (!l.isUsuariosEmpty())
					{
					//Selecionar o tipo de anuncio
					System.out.print("--CADASTRO DE ANUNCIOS--\n1 - Cadastrar um produto.\n2 - Cadastrar um servi�o."
							+ "\n0 - Voltar.");
					op = s.nextInt();
					
					if (op == 0)
						op = -1;
					
					else if (op == 1)//Cadastrar anuncio de um produto
						l.cadastrarProduto();
					
					else if (op == 2)//Cadastrar anuncio de um servico
						l.cadastrarServico();
					
					else 
						System.out.println("Opcao invalida!");
					
					}
				}
				else if (op == 3) //Realizar Compras
				{ 
					if (l.usuariosSize() > 1)
						if(l.produtosSize() > 0 ||l.servicosSize() > 0)
							l.comprarAnuncio();
				}
				else if (op == 4) //Exibir relatorios
				{
					if (!l.isUsuariosEmpty()) {}
				}
				
				else if (op == 5) //Exibir Anuncios
				{
					System.out.println("---EXIBICAO DE ANUNCIOS---\n1 - Todos.\n2 - Somente produtos.\n3 - Somente servicos."
							+ "\n0 - Voltar.");
					op = s.nextInt();
					
					if (op == 0)
						op = -1;
					
					else if (op == 1)
					{
						l.exibirProdutos();
						l.exibirServicos();
					}
					
					else if (op == 2) { l.exibirProdutos();}
					else if (op == 3) { l.exibirServicos();}
					else System.out.println("Opcao invalida!");
				}
				else if (op == 0) //Sair do programa
				{}
				
				else 
					System.out.println("Opcao invalida!");
			}
		
		s.close();
	}
}



