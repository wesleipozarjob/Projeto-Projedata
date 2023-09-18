package view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;



public class Principal {
    public static void main(String[] args) {
    	//Lista de Funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 1), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente"));
        // 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        System.out.println("Funcionários inseridos:");

        funcionarios.forEach(System.out::println);

        // 3.2 - Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações.
        System.out.println("\nFuncionários após remover João:");
        funcionarios.forEach(System.out::println);

        // 3.4 - Os funcionários receberam 10% de aumento de salário.
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });

        System.out.println("\nFuncionários após aumento de salário:");
        funcionarios.forEach(System.out::println);

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> {
                    int month = funcionario.getDataNascimento().getMonthValue();
                    return month == 10 || month == 12;
                })
                .collect(Collectors.toList());

        System.out.println("\nAniversariantes do mês 10 e 12:");
        aniversariantes.forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade.
        LocalDate today = LocalDate.now();
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparingInt(f -> Period.between(f.getDataNascimento(), today).getYears()))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), today).getYears();
            System.out.println("\nFuncionário mais velho: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade + " anos");
        } else {
            System.out.println("\nNão há funcionários na lista.");
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nFuncionários por ordem alfabética:");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("\nTotal dos salários dos funcionários: R$" + df.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nSalários em relação ao salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal vezesSalarioMinimo = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + df.format(vezesSalarioMinimo) + " salários mínimos");
        });
        
    }
}
