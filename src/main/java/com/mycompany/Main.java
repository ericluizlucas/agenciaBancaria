package com.mycompany;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public class Utils {
        static NumberFormat formatandoValores = new DecimalFormat("R$ #,##0.00");
        public String doubleToString(Double valor) {
            return formatandoValores.format(valor);
        }
    }
    public static class Cliente {
        private String nome;
        private String sobrenome;
        private String cpf;

        public Cliente(String nome, String sobrenome, String cpf) {
            this.nome = nome;
            this.sobrenome = sobrenome;
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
    }
    
    public static class Conta {
        private static int contadorDeContas = 1;

        private int numeroConta;
        private Cliente cliente;
        private Double saldo = 0.0;

        public Conta(Cliente cliente) {
            this.numeroConta = contadorDeContas;
            this.cliente = cliente;

            contadorDeContas += 1;
        }

        public int getNumeroConta() {
            return numeroConta;
        }

        public void setNumeroConta(int numeroConta) {
            this.numeroConta = numeroConta;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public Double getSaldo() {
            return saldo;
        }

        public void setSaldo(Double saldo) {
            this.saldo = saldo;
        }

        public String toString() {
            return "\nNúmero da conta: " + this.getNumeroConta() +
                   "\nNome: " + this.cliente.getNome() +
                   "\nCPF: " + this.cliente.getCpf() +
                   "\nSaldo: " + Utils.formatandoValores.format(this.getSaldo()) +
                   "\n";
        }

        public void depositar(Double valor) {
            if(valor >0) {
                setSaldo(getSaldo() + valor);
                System.out.println("Seu depósito foi realizado com sucesso!");
            } else {
                System.out.println("Não foi possível realizar o depósito!");
            }
        }

        public void sacar(Double valor) {
            if(valor > 0 && this.getSaldo() >= valor) {
                setSaldo(getSaldo() - valor);
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Não foi possível realizar o saque!");
            }
        }

        public void consultarSaldo() {
            System.out.println("Seu saldo é de " + Utils.formatandoValores.format(this.getSaldo()));
        }
    }

    
    
    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        
        operacoes();
    }

    public static void operacoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("--------------Bem-vindo a nossa agência---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|    Opção 1 - Criar conta       |");
        System.out.println("|    Opção 2 - Depositar         |");
        System.out.println("|    Opção 3 - Sacar             |");
        System.out.println("|    Opção 4 - Consultar saldo   |");
        System.out.println("|    Opção 5 - Listar contas     |");
        System.out.println("|    Opção 6 - Sair              |");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
            case 3:
                sacar();
            case 4:
                consultarSaldo();
            case 5:
                listarContas();
            case 6:
                System.out.println("Agradecemos por utilizar a nossa agência.");
                System.exit(0);
        
            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("\nNome: ");
        String nome = input.next();

        System.out.println("\n Sobrenome: ");
        String sobrenome = input.next();

        System.out.println("\n CPF: ");
        String cpf = input.next();

        Cliente cliente = new Cliente(nome, sobrenome, cpf);
        Conta conta = new Conta(cliente);

        contasBancarias.add(conta);
        System.out.println("Sua conta foi criada com sucesso!");

        operacoes();
    }

    public static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta c: contasBancarias) {
                if(c.getNumeroConta() == numeroConta)
                conta = c;
            }
        }
        return conta;
    }

    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            System.out.println("Qual valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
        } else {
            System.out.println("Conta não encontrada. ");
        }
        operacoes();
    }

    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            System.out.println("Qual valor deseja sacar? ");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
        } else {
            System.out.println("Conta não encontrada. ");
        }
        operacoes();
    }

    public static void consultarSaldo() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            conta.consultarSaldo();
        } else {
            System.out.println("Conta não encontrada. ");
        }
        operacoes();
    }

    public static void listarContas() {
        if(contasBancarias.size() > 0) {
            for(Conta conta : contasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("Não há contas cadastradas! ");
        }
        operacoes();
    }
}