package com.mygdx.game;

public enum DIRECAO {

	 BAIXO(0), CIMA(1), DIREITA(2), ESQUERDA(3);
	 final int i;
	 DIRECAO(int i) {
		 this.i = i;
		
	}
	 
	 public final int getID() {
		 return this.i;
	 }
}
