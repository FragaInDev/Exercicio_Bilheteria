package view;

import java.util.concurrent.Semaphore;

import controller.bilheteria;

public class Main {

	public static void main(String[] args) {
		int pessoa = 300;
		Semaphore semaforo = new Semaphore(1);

		for (int i = 0; i < pessoa; i++) {
			new bilheteria(i, semaforo).start();

		}

	}

}