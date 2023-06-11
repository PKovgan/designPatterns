package com.origin;

public class TemplateMethod {
	
	public static void main(String...args) {
		Game game = new Chess();
		game.run();
	}

}

abstract class Game {
	protected int currentPlayer;
	protected final int numberOfPLayer;
	public Game(int numberOfPLayer) {
		this.numberOfPLayer = numberOfPLayer;
	}
	public void run() {
		start();
		while (!haveWinner()) {
			takeTurn();
		}
		System.out.println("Player " + getWinningPlayer() + " wins");
	}
	protected abstract int getWinningPlayer();
	protected abstract void takeTurn();
	protected abstract boolean haveWinner();
	protected abstract void start(); 
	
}

class Chess extends Game {
	
	private int maxTurns = 10;
	private int turn = 1;
	
	public Chess() {
		super(2);
	}

	@Override
	protected int getWinningPlayer() {
		return ++currentPlayer%numberOfPLayer;
	}

	@Override
	protected void takeTurn() {
		System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
		currentPlayer = ++currentPlayer%numberOfPLayer;
	}

	@Override
	protected boolean haveWinner() {
		return turn == maxTurns;
	}

	@Override
	protected void start() {
		System.out.println("Starting a game of chess");
	}
	
}
