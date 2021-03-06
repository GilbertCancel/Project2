

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener
{
  int p1points;
  int p2points;
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Pile stackDeck;

  Set pl1Hand;
  Set pl2Hand;


	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	DefaultListModel p1Hand;
	DefaultListModel p2Hand;

	private void deal(Card [] cards)
	{
		for(int i = 0; i < cards.length; i ++)
			cards[i] = (Card)cardDeck.dealCard();
	}

	public Table()
	{
		super("A Card Game");

		setLayout(new BorderLayout());
		setSize(1200,700);


		cardDeck = new Deck();

		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		cardDeck.shuffle();

    //Stack initialization
		stackDeck = new Pile();
		Card startercard = cardDeck.dealCard();
		stackDeck.addcard(startercard);
    

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));


		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);

		player1 = new JPanel();

		player1.add(top);




		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();




		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);


		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);
		p1Hand = new DefaultListModel();
		for(int i = 0; i < cardsPlayer1.length; i++){
			p1Hand.addElement(cardsPlayer1[i]);
      }
		p1HandPile = new JList(p1Hand);


		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(startercard.getCardImage());
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);
		p2Hand = new DefaultListModel();

		for(int i = 0; i < cardsPlayer2.length; i++){
			p2Hand.addElement(cardsPlayer2[i]);
      }

		p2HandPile = new JList(p2Hand);

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);

	}

	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		if(p1Deck == src|| p2Deck == src){

			Card card = cardDeck.dealCard();

			if (card != null){
				if(src == p1Deck){
          System.out.println("PLAYER 1 ");
          System.out.println("ADDED: ");
          System.out.println(card);
					p1Hand.addElement(card);
          pl1Hand.addCard(card);

          if(p1Hand != null){
          p1points = pl1Hand.evaluateHand();
          }
          if(p1points >= 52){
              End(1);
            }
        }
				else{
          System.out.println("PLAYER 2 ");
          System.out.println("ADDED: ");
          System.out.println(card);
					p2Hand.addElement(card);
          pl2Hand.addCard(card);

          if(pl2Hand != null){
          p2points = pl2Hand.evaluateHand();
          }
            if(p2points >= 52){
              End(2);
            }
        }
			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));

		}
		if(p1Stack == src || p2Stack == src){

			Card card = stackDeck.takelast();
    
			if(card != null){
        stackDeck.pop();
				Card topCard = stackDeck.gettopcard();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				if(p1Stack == src){
          System.out.println("PLAYER 1 ");
          System.out.println("ADDED: ");
          System.out.println(card);
					p1Hand.addElement(card);
          pl1Hand.addCard(card);
          stackDeck.pop();

          if(pl1Hand != null){
          p1points = pl1Hand.evaluateHand();
          }
          if(p1points >= 52){
              End(1);
            }
        }
				else{
          System.out.println("PLAYER 2 ");
          System.out.println("ADDED: ");
          System.out.println(card);
					p2Hand.addElement(card);
          pl2Hand.addCard(card);
          stackDeck.pop();

          if(pl2Hand != null){
          p2points = pl2Hand.evaluateHand();
          }
            if(p2points >= 52){
              End(2);
            }
        }
			}

		}

		if(p1Lay == src){
			Object [] cards = p1HandPile.getSelectedValues();
			if (cards != null){
          System.out.println("PLAYER 2 ");
          System.out.println("DISCARDED: ");
          System.out.println(cards);
        }
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p1Hand.removeElement(card);

          if(p1Hand == null){
            End(1);
          }


				}

    

		}


		if(p2Lay == src){
			Object [] cards = p2HandPile.getSelectedValues();
			if (cards != null){
          System.out.println("PLAYER 2 ");
          System.out.println("DISCARDED: ");
          System.out.println(cards);
        }
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p2Hand.removeElement(card);

          if(p2Hand == null){
            End(2);
          }

				}

		}


		if(p1LayOnStack == src){
      int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
          System.out.println("PLAYER 1 ");
          System.out.println("DISCARDED: ");
          System.out.println(obj);
				{
					p1Hand.removeElement(obj);
					Card card = (Card)obj;
					stackDeck.addcard(card);
					topOfStack.setIcon(card.getCardImage());

				}
			}
      else if(num.length == 0){
        End(1);
      }

		}


		if(p2LayOnStack == src){
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null)
          System.out.println("PLAYER 2 ");
          System.out.println("DISCARDED: ");
          System.out.println(obj);
				{

					p2Hand.removeElement(obj);
					Card card = (Card)obj;
					stackDeck.addcard(card);
					topOfStack.setIcon(card.getCardImage());

          if(p2Hand == null){
            End(2);
          }

				}
			}

		}

	}

	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}


  //Function that is called in the event of a Player reaching the point goal with sets.
  void End(int player){

    if(p1points >= 52){
      System.out.println("Points" + p1points + " to " + p2points);
      System.out.println("Player 1 Wins!");
    }
    else if(p2points >= 52){
      System.out.println("Points" + p1points + " to " + p2points);
      System.out.println("Player 2 Wins!");
    }
    else{
      cardDeck.restoreDeck();

    }

  }

}
class HandPanel extends JPanel
{

	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}
class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}


