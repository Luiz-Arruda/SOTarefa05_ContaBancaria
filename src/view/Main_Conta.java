package view;

import java.util.concurrent.Semaphore;

import controller.OperacaoBancaria;

public class Main_Conta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int idTread = 0;
		int idconta = 0;
		double saldoinicial = 0.0;
		double vlrtransacao = 0.0;
		int permissao = 2;
		int permissaosal = 1;
		int permissaodep = 1;
		
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore semaforosal = new Semaphore(permissaosal);
		Semaphore semaforodep = new Semaphore(permissaodep);
		
		for (int idt = 0 ; idt < 20; idt++) {
			idconta = (int) (Math.random() * 10000);
			saldoinicial = (Math.random() * 1000);
			vlrtransacao = (Math.random() * 100);
			Thread IDT = new OperacaoBancaria(idt, idconta, saldoinicial, vlrtransacao, semaforo, semaforosal, semaforodep);
			IDT.start();
		} // final do for 
	} // fim maim
} // fim classe
