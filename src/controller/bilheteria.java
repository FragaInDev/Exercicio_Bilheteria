package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class bilheteria extends Thread{
	private int pessoa;
	private static int ingressos = 100;
	private boolean liberado;
	private Semaphore semaforo;
	
	public bilheteria (int Pessoa, Semaphore smf) {
		this.pessoa = Pessoa;
		this.semaforo = smf;
	}
	
	@Override
	public void run() {
		Login();
		if (liberado == true) {
			compraIngresso();
			if (liberado == true) {
				verificaIngresso();
			}
		}
	}
	
	private void Login() { //sistema de timeout no login
		int timeLogin = new Random().nextInt(2000) + 50;
		
		try {
			sleep(timeLogin);
			
			if (timeLogin > 1000) {
				System.out.println("Timeout!");
				liberado = false;
			}else {
				System.out.println("o user " +pessoa+ " logou com sucesso! :)");
				liberado = true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void compraIngresso() {
		int timeCompra = new Random().nextInt(3000) + 1000;
		
		try {
			sleep(timeCompra);
			if (timeCompra > 2500) {
				liberado = false;
			}else {
				System.out.println("Confirme a compra user " + pessoa);
				liberado = true;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void verificaIngresso() {
		int cIngresso = new Random().nextInt(4) + 1;
		
		try {
			
			if (ingressos >= 1) {
				ingressos -= cIngresso;
				if (ingressos == 1) {
					ingressos -= 1;
				}
				semaforo.acquire();
				
				System.out.println("o user " + pessoa + " comprou " + cIngresso + " ingressos. Ingressos restantes: " + ingressos);
			}
			else{
				System.err.println("ingressos esgotados ou número de ingressos indisponíveis");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
}