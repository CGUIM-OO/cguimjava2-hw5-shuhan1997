import java.util.ArrayList;
import java.util.Random;

public class Deck {

		private ArrayList<Card> cards;
		private ArrayList<Card> usedCard;
		private ArrayList<Card> openCard;
		public int nUsed=0;
		public Deck(int nDeck){
			cards=new ArrayList<Card>();
			usedCard=new ArrayList<Card>();
			openCard=new ArrayList<Card>();
			int deck, flower, number;
			for(deck = 0; deck < nDeck; deck++) 
			{
				for(flower = 1; flower < 5; flower++) 
				{
					for(number = 1; number < 14; number++) 
					{
						Card card = new Card(flower, number);
						cards.add(card);
					}
				}
					
			}
			shuffle();			
		}	
		public void shuffle() 
		{
			openCard.clear();
			Random rnd = new Random();
			while (usedCard.size()>0)
			{
				Card backCard;
				backCard = usedCard.get(0);
				usedCard.remove(0);
				cards.add(backCard);
			}
			for(int a = 0; a < cards.size();a++)
			{
				Card cardPlace;
				cardPlace = cards.get(a);
				int j = rnd.nextInt(cards.size());
				cards.set(a, cards.get(j));
				cards.set(j, cardPlace);
			}
			nUsed = 0;
		}
		public Card getOneCard(boolean isOpened)
		{
			if (isOpened == true)
			{
				if (cards.size()>0)
				{
					Card getCard;
					getCard = cards.get(0);
					usedCard.add(getCard);
					cards.remove(0);
					nUsed++;
					return getCard;								
				}
				else
				{
					shuffle();
					getOneCard(true);
					return null;
				}
			}
			else return null;
				
		}
		public void printDeck(){
			int deck;
			for(deck = 0; deck < cards.size(); deck++) 
			{
				Card poker = cards.get(deck);
				poker.printCard();
			}
		}
		public ArrayList<Card> getOpenedCard()
		{
			return openCard;
		}
		public ArrayList<Card> getAllCards(){
			return cards;
		}
	}