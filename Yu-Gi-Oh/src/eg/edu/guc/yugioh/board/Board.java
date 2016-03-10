package eg.edu.guc.yugioh.board;

import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;

public class Board {
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	public Board() throws IOException, UnexpectedFormatException {
		winner = null;
		activePlayer = new Player("");
		opponentPlayer = new Player("");
		Card.setBoard(this);
	}

	public void whoStarts(Player p1, Player p2) {
		int x = (int) (Math.random() * 2) + 1;
		if (x == 1) {
			setActivePlayer(p1);
			setOpponentPlayer(p2);
		} else {
			setActivePlayer(p2);
			setOpponentPlayer(p1);
		}
	}

	public void startGame(Player p1, Player p2) {
		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);
		whoStarts(p1, p2);
		activePlayer.getField().addCardToHand();
	}

	public void nextPlayer() {
		if(winner != null) return;
		Player temp = getActivePlayer();
		setActivePlayer(getOpponentPlayer());
		setOpponentPlayer(temp);
		activePlayer.addCardToHand();
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player newActivePlayer) {
		activePlayer = newActivePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player newOpponentPlayer) {
		opponentPlayer = newOpponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player newWinner) {
		winner = newWinner;
	}

}
