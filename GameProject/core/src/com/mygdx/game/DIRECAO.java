package com.mygdx.game;

public enum DIRECAO {

	 BAIXO(false), CIMA(false), DIREITA(false), ESQUERDA(false);
	 boolean v;
	 DIRECAO(boolean v) {
		 this.v = v;
		
	}
	 
	 public final boolean getID() {
		 return this.v;
	 }
	 public void set(boolean v) {
		 this.v = v;
	 }
}
