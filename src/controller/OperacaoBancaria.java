package controller;

import java.util.concurrent.Semaphore;

public class OperacaoBancaria extends Thread {
	private int idThread;   // tipo de transação
	private int codconta;
	private double saldoinicial;
	private double vlrtransacao;
	Semaphore semaforo;
	Semaphore semaforosal;
	Semaphore semaforodep;
	
	public OperacaoBancaria(int idThread, int codconta,  double saldoinicial, double vlrtransacao, Semaphore semaforo, Semaphore semaforosal, Semaphore semaforodep ) {
		this.idThread = idThread;
		this.codconta = codconta;
		this.saldoinicial = saldoinicial;
		this.vlrtransacao = vlrtransacao;
		this.semaforo = semaforo;
		this.semaforosal = semaforosal;
		this.semaforodep = semaforodep;
	}
	
	@Override
	public void run() {
		if (idThread % 2 == 0) {
			try {
				semaforosal.acquire();
					saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforosal.release();	
			}
		}
		
		if (idThread % 2 != 0) {
			try {
				semaforodep.acquire();
					deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforodep.release();	
			}
		}
	} // fim run
	
	public void saque() {
		saldoinicial = saldoinicial - vlrtransacao;
		System.out.println(+ idThread + " Conta: " + codconta + " op. saque de: - " +vlrtransacao + " saldo: " +saldoinicial);
	} // fim saque
	
	public void deposito() {
		saldoinicial = saldoinicial + vlrtransacao;
		System.out.println(+idThread + " Conta: " + codconta + " op. deposito de: + " +vlrtransacao + " saldo: " +saldoinicial);
	} // fim deposito
	
}  // fim de classe



