package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.gui.BattleScreen;
import eg.edu.guc.yugioh.gui.MonstersButton;
import eg.edu.guc.yugioh.gui.SpellsButton;

public class handListeners implements ActionListener{

	Board board;
	BattleScreen BS;
	JButton FC;
	JButton SC;
	JButton TC;
	JOptionPane optMPane;
	JOptionPane optSPane;
	Updated updater;
	Player player1;
	Player player2;
	int ok = 1;
	int ok2 = 1;
	int pane = 2;

	ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();

	public handListeners(Board b, BattleScreen bs, Player p1, Player p2){
		player1 = p1;
		player2 = p2;
		board = b;
		BS = bs;	
		updater = new Updated(b, bs, p1, p2, this);
		SC = null;
		TC = null;
	}


	public void actionPerformed(ActionEvent e) {
		try{
			if(board.getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
				battleEvent(e);
				//FC = null;
			}
			actionPanelPerformed(e);
			
			if(!board.getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
				Object [] monstoptions = {"ATTACK", "DEFENSE"};
				Object [] spellsoptions = {"SET", "ACTIVATE"};
				if(player1.equals(board.getActivePlayer())){
					if(FC == null){
						if(BS.getH1().getHand().contains(e.getSource())){
							FC = (JButton) e.getSource();
							int cardIndex = BS.getH1().getHand().indexOf(e.getSource());
							if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof MonsterCard){
								MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(cardIndex);
								pane = JOptionPane.showOptionDialog(null, "Choose monster mode", "Monster Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, monstoptions, monstoptions[0]);
								if(temp.getLevel()<5){
									//								if(pane == 0){
									//									board.getActivePlayer().summonMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)));
									//									FC = null;
									//									sacrifices.clear();
									//									updater.update();
									//								}else{
									//									board.getActivePlayer().setMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)));
//									try{
										if(pane == 0) board.getActivePlayer().summonMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)));
										else if(pane == 1) board.getActivePlayer().setMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)));
//									}catch(Exception er){
//										JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//									}
									FC = null;
									sacrifices.clear();
									updater.update();

								}else{
									Object[] options = { "OK", "CANCEL" };
									ok = JOptionPane.showOptionDialog(null, "Please choose a monster to sacrifice!", "Warning",
											JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
											null, options, options[0]);
									if(ok == 1) FC = null;
								}
							} else if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof SpellCard){
								ok2 = JOptionPane.showOptionDialog(null, "Choose spell mode", "Spell Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, spellsoptions, spellsoptions[0]);
								if(board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)) instanceof SpellCard){
									if(ok2 == 0){
										board.getActivePlayer().setSpell((SpellCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)));
										FC = null;
										updater.update();
									}else{
										if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof MagePower){
											JOptionPane.showOptionDialog(null, "please choose a monster from your field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
										}else if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof ChangeOfHeart){
											JOptionPane.showOptionDialog(null, "please choose a monster from your opponent's field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
										}else{
											board.getActivePlayer().activateSpell((SpellCard) board.getActivePlayer().getField().getHand().get(cardIndex), null);
											updater.update();
											FC = null;
										}

									}
								}
							}
						}
					}else if(SC == null){
						if(board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)) instanceof MonsterCard){

							if(ok == 0){
								if(e.getSource() instanceof MonstersButton){
									SC = (MonstersButton) e.getSource();
									int indSacr1 = BS.getP1().getM().getMonstersButtons().indexOf(e.getSource());
									sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr1));
								}
							}
							MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC));
							if(temp.getLevel()<7){
//								try{
									if(pane == 0) board.getActivePlayer().summonMonster(temp, sacrifices);
									else if(pane == 1) board.getActivePlayer().setMonster(temp, sacrifices);
//								}catch(Exception er){
//									JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//								}
								FC = null;
								SC = null;
								updater.update();
							}
							else{
								Object[] options = { "OK", "CANCEL" };

								ok2 = JOptionPane.showOptionDialog(null, "Please choose another monster to sacrifice!", "Warning",
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
										null, options, options[0]);
								if(ok2 ==  1){
									FC = null;
									SC = null;
								}

							}
						}else{ 
							if(e.getSource() instanceof MonstersButton){
								SC = (MonstersButton) e.getSource();
								if(BS.getP1().getM().getMonstersButtons().contains(SC)){
									int indSacr1 = BS.getP1().getM().getMonstersButtons().indexOf(e.getSource());
									board.getActivePlayer().activateSpell((SpellCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC)), board.getActivePlayer().getField().getMonstersArea().get(indSacr1));
									updater.update();
									FC = null;
									SC = null;
								}
							} else FC = null;
						}
					}else if(TC == null && SC != null){
						MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH1().getHand().indexOf(FC));
						if(ok2 == 0){
							if(e.getSource() instanceof MonstersButton){
								TC = (MonstersButton) e.getSource();
								int indSacr2 = BS.getP1().getM().getMonstersButtons().indexOf(e.getSource());
								sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr2));
//								try{
									if(pane == 0) board.getActivePlayer().summonMonster(temp, sacrifices);
									else if(pane == 1) board.getActivePlayer().setMonster(temp, sacrifices);
//								}catch(Exception er){
//									JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//								}
								FC = null;
								SC = null;
								TC = null;
								sacrifices.clear();
								updater.update();
							} else {
								FC = null;
								SC = null;
							}
						}else{
							FC = null;
							SC = null;
							sacrifices.clear();
						}
					}
				}
				if(player2.equals(board.getActivePlayer())){
					if(FC == null){
						if(BS.getH2().getHand().contains(e.getSource())){
							FC = (JButton) e.getSource();
							int cardIndex = BS.getH2().getHand().indexOf(e.getSource());
							if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof MonsterCard){
								MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(cardIndex);
								pane = JOptionPane.showOptionDialog(null, "Choose monster mode", "Monster Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, monstoptions, monstoptions[0]);
								if(temp.getLevel()<5){
									if(pane == 0){
//										try{
											board.getActivePlayer().summonMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)));
//										}catch(Exception er){
//											JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//										}
										FC = null;
										sacrifices.clear();
										updater.update();
									}else{
//										try{
											board.getActivePlayer().setMonster((MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)));
//										}catch(Exception er){
//											JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//										}
										FC = null;
										sacrifices.clear();
										updater.update();
									}
								}else{
									Object[] options = { "OK", "CANCEL" };
									ok = JOptionPane.showOptionDialog(null, "Please choose a monster to sacrifice!", "Warning",
											JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
											null, options, options[0]);
									if(ok == 1) FC = null;
								}
							} else if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof SpellCard){
								pane = JOptionPane.showOptionDialog(null, "Choose spell mode", "Spell Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, spellsoptions, spellsoptions[0]);
								if(board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)) instanceof SpellCard){
									if(pane == 0){
										board.getActivePlayer().setSpell((SpellCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)));
										FC = null;
										updater.update();
									}		else{
										if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof MagePower){
											JOptionPane.showOptionDialog(null, "please choose a monster from your field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
										}else if(board.getActivePlayer().getField().getHand().get(cardIndex) instanceof ChangeOfHeart){
											JOptionPane.showOptionDialog(null, "please choose a monster from your opponent's field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
										}else {
											board.getActivePlayer().activateSpell((SpellCard) board.getActivePlayer().getField().getHand().get(cardIndex), null);
											updater.update();
											FC = null;
										}

									}
								}
							}
						}
					}else if(SC == null){
						if(board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)) instanceof MonsterCard){

							if(ok == 0){
								if(e.getSource() instanceof MonstersButton){
									SC = (MonstersButton) e.getSource();
									int indSacr1 = BS.getP2().getM().getMonstersButtons().indexOf(e.getSource());
									sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr1));
								} else FC = null;
							}
							MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC));
							if(temp.getLevel()<7){
//								try{
									if(pane == 0) board.getActivePlayer().summonMonster(temp, sacrifices);
									else if(pane == 1) board.getActivePlayer().setMonster(temp, sacrifices);
//								}catch(Exception er){
//									JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//								}
								FC = null;
								SC = null;
								updater.update();
							}
							else{
								Object[] options = { "OK", "CANCEL" };

								ok2 = JOptionPane.showOptionDialog(null, "Please choose another monster to sacrifice!", "Warning",
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
										null, options, options[0]);
								if(ok2 == 1){
									FC = null;
									SC = null;
								}
							}
						}else{ 
							if(e.getSource() instanceof MonstersButton){
								SC = (MonstersButton) e.getSource();
								if(BS.getP2().getM().getMonstersButtons().contains(SC)){
									int indSacr1 = BS.getP2().getM().getMonstersButtons().indexOf(e.getSource());
									board.getActivePlayer().activateSpell((SpellCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC)), board.getActivePlayer().getField().getMonstersArea().get(indSacr1));
									updater.update();
									FC = null;
									SC = null;
								}
							} else {
								FC = null;
								SC = null;										
							}
						}
					}else if(TC == null && SC != null){
						MonsterCard temp = (MonsterCard) board.getActivePlayer().getField().getHand().get(BS.getH2().getHand().indexOf(FC));
						if(ok2 == 0){
							if(e.getSource() instanceof MonstersButton){
								TC = (MonstersButton) e.getSource();
								int indSacr2 = BS.getP2().getM().getMonstersButtons().indexOf(e.getSource());
								sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr2));
						//		try{
									if(pane == 0) board.getActivePlayer().summonMonster(temp, sacrifices);
									else if(pane == 1) board.getActivePlayer().setMonster(temp, sacrifices);
						//		}catch(Exception er){
						//			JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
						//		}
								FC = null;
								SC = null;
								TC = null;
								sacrifices.clear();
								updater.update();
							}	
						}else{
							FC = null;
							SC = null;
							sacrifices.clear();
						}

					}

				}
			}
			e = null;
		}catch(NullPointerException e1){
			JOptionPane.showMessageDialog(new JFrame(), "Please stick to the game rules");
			FC=null;
			SC=null;
			TC=null;
			updater.update();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
			FC = null;
			SC = null;
			TC = null;
			updater.update();
		}
	}
	public void battleEvent(ActionEvent e){
		try{
			Player active;
			Player opp;
			int activeMonst = -1;
			int oppMonst = -1;
			int attackOpt;
			if(player1.equals(board.getActivePlayer())){
				active = player1;
				opp = player2;
			}else{
				active = player2;
				opp = player1;
			}
			if(FC == null){
				if(e.getSource() instanceof MonstersButton){
					FC = (MonstersButton) e.getSource();
					if(player1.equals(active))
						activeMonst = BS.getP1().getM().getMonstersButtons().indexOf(FC);
					else activeMonst = BS.getP2().getM().getMonstersButtons().indexOf(FC);
					if(opp.getField().getMonstersArea().size() == 0){
						active.declareAttack(active.getField().getMonstersArea().get(activeMonst));
						updater.update();
						FC = null;


					}else{
						Object[] options = { "OK", "CANCEL" };
						attackOpt = JOptionPane.showOptionDialog(null, "Please choose a monster to attack!", "Warning",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null, options, options[0]);
						if(attackOpt == 1){ FC = null; e = null;}
					}
				}
			}else if(opp.getField().getMonstersArea().size()>0){
				if(e.getSource() instanceof MonstersButton){
					SC = (MonstersButton) e.getSource();
					if(player1.equals(opp))
						oppMonst = BS.getP1().getM().getMonstersButtons().indexOf(SC);
					else oppMonst = BS.getP2().getM().getMonstersButtons().indexOf(SC);
					if(player1.equals(active))
						activeMonst = BS.getP1().getM().getMonstersButtons().indexOf(FC);
					else activeMonst = BS.getP2().getM().getMonstersButtons().indexOf(FC);
					active.declareAttack(active.getField().getMonstersArea().get(activeMonst), opp.getField().getMonstersArea().get(oppMonst));
					e = null;
					FC = null;
					SC = null;
					updater.update();
				}
			}

			e = null;
		}catch(NullPointerException e1){
			JOptionPane.showMessageDialog(new JFrame(), "Please stick to the game rules");
			FC=null;
			SC=null;
			TC=null;
			updater.update();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
			FC = null;
			SC = null;
			TC = null;
			updater.update();
		}
	}

	public void actionPanelPerformed(ActionEvent e) {
		try{
			Player active;
			Player opp;
			int activeMonst = -1;
			int oppMonst = -1;
			int activeSpell = -1;
			if(player1.equals(board.getActivePlayer())){
				active = player1;
				opp = player2;
			}else{
				active = player2;
				opp = player1;
			}
			if(FC == null){
				if(!active.getField().getPhase().equals(Phase.BATTLE)){
					if(e.getSource() instanceof MonstersButton){
						FC = (MonstersButton) e.getSource();
						if(player1.equals(active))
							activeMonst = BS.getP1().getM().getMonstersButtons().indexOf(FC);
						else 
							activeMonst = BS.getP2().getM().getMonstersButtons().indexOf(FC);
						Object[] options = { "Change Mode", "CANCEL" };
						int ok = JOptionPane.showOptionDialog(null, "Monster Mode!", "Warning",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null, options, options[0]);
						if(ok == 0) {
//							try{
								active.switchMonsterMode(active.getField().getMonstersArea().get(activeMonst));
//							}catch(Exception er){
//								JOptionPane.showMessageDialog(new JFrame(), er.getMessage());
//							}
							FC = null;
							updater.update();
						}
						else FC = null;
					}else if(e.getSource() instanceof SpellsButton){
						FC = (SpellsButton) e.getSource();
						if(player1.equals(active))
							activeSpell = BS.getP1().getS().getSpellsButtons().indexOf(FC);
						else 
							activeSpell = BS.getP2().getS().getSpellsButtons().indexOf(FC);
						if(active.getField().getSpellArea().get(activeSpell) instanceof MagePower){
							JOptionPane.showOptionDialog(null, "please choose a monster from your field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
						}else if(active.getField().getSpellArea().get(activeSpell) instanceof ChangeOfHeart){
							JOptionPane.showOptionDialog(null, "please choose a monster from your opponent's field", "WARINING", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, null, null);
						}else {
							active.getField().activateSetSpell((SpellCard) board.getActivePlayer().getField().getSpellArea().get(activeSpell), null);
							updater.update();
							FC = null;
						}
					}
				}
			}else if(SC == null && FC instanceof SpellsButton){
				if(e.getSource() instanceof MonstersButton){
					SC = (MonstersButton) e.getSource();
					if(active.getField().getSpellArea().get(activeSpell) instanceof MagePower || active.getField().getSpellArea().get(activeSpell) instanceof ChangeOfHeart){
						if(BS.getP1().getM().getMonstersButtons().contains(SC))
							active.getField().activateSetSpell((SpellCard) board.getActivePlayer().getField().getSpellArea().get(activeSpell), player1.getField().getMonstersArea().get(oppMonst));
						if(BS.getP2().getM().getMonstersButtons().contains(SC))
							active.getField().activateSetSpell((SpellCard) board.getActivePlayer().getField().getSpellArea().get(activeSpell), player2.getField().getMonstersArea().get(oppMonst));
					}
				}
			}
		}catch(NullPointerException e1){
			JOptionPane.showMessageDialog(new JFrame(), "Please stick to the game rules");
			FC=null;
			SC=null;
			TC=null;
			updater.update();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
			FC = null;
			SC = null;
			TC = null;
			updater.update();
		}
	}
}
