# INATEL-T141-DM110-Final_Homework

-----------------------------------------

# Projeto final da disciplina DM110

## Propósito

Criar uma aplicação capaz de fazer a varredura de uma rede de computadores para identificar os equipamentos que estão ativos, dada uma faixa de IP (endereço de rede/máscara CIDR).

## Interface de serviço

### Comando de início de varredura:

* Tipo da requisição: GET
* URL: `<context-root>/api/poller/start/{IP}/{Mask}`
* Funcionamento:
  * Calcula todos os endereços da rede (desconsiderando o endereço de rede e de broadcast).
  * Cria mensagens JMS sendo que cada mensagem deverá conter uma lista de no máximo 10 endereços IP.
  * Insere mensagens na fila.
  * Deverá existir um MDB consumindo mensagens desta fila e fazendo a verificação do status do equipamento referente ao endereço IP.
  * Para cada endereço verificado, deverá salvar uma linha em uma tabela na base de dados contendo, pelo menos, o endereço IP verificad e o status (Ativo ou Inativo).

### Verificação do status do equipamento

* Tipo de requisição: GET
* URL: `<context-root>/api/poller/status/{IP}`
* Funcionamento: Busca o equipamento na base de dados e retorna o status.

## Exemplo de código para fazer ping

```java
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestRuntime {

	public static void main(String[] args) {
		System.out.println(execPing("192.168.60.91"));
	}

	public static boolean execPing(String address) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -n 1 " + address);
			InputStream is = process.getInputStream();
			InputStream es = process.getErrorStream();
			String input = processStream(is);
			String error = processStream(es);
			int code = process.waitFor();
			if (code != 0) {
				return false;
			}
			if (error.length() > 0) {
				return false;
			}
			if (input.contains("Host de destino inacess")) {
				return false;
			}
			return true;
		} catch (IOException | InterruptedException e) {
			return false;
		}
	}

	public static String processStream(InputStream is) {
		StringBuilder input = new StringBuilder();
		try (Scanner scanner = new Scanner(is)) {
			while (scanner.hasNextLine()) {
				input.append(scanner.nextLine()).append("\n");
			}
		}
		return input.toString();
	}
}
```