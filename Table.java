import java.util.ArrayList;
public class Table {
	public static final int MAXPLAYER = 4;
	private Deck AllCard;
	private Player[] AllPlayer;
	private Dealer dealer;
	private int[] pos_betArray;
	public Table(int nDeck) 
	{
		AllCard = new Deck(nDeck);
		AllPlayer = new Player[MAXPLAYER];
	}
	public void set_player(int pos, Player p) 
	{
		AllPlayer [pos] = p;
	}
	public Player[] get_player() 
	{
		return AllPlayer;
	}
	public void set_dealer(Dealer d) 
	{
		dealer = d;
	}
	public Card get_face_up_card_of_dealer() 
	{
		ArrayList<Card> GetCard = dealer.getOneRoundCard();
		Card x = GetCard.get(1);
		return x;
	}
	private void ask_each_player_about_bets() 
	{
		pos_betArray = new int [MAXPLAYER];
		for(int i = 0; i <MAXPLAYER; i++) 
		{
			Player a = AllPlayer[i];
			a.sayHello();
			int b = a.makeBet();
			pos_betArray[i] = b;
		}
	}
	private void distribute_cards_to_dealer_and_players() 
	{
		for(int j = 1; j <=2; j++) 
		{
			for(int i = 0; i < MAXPLAYER; i++) 
			{
				Player a = AllPlayer[i];
				Card b = AllCard.getOneCard(true);
				ArrayList<Card> GetCard = a.getOneRoundCard();
				GetCard.add(b);
				a.setOneRoundCard(GetCard);
			}
		}
		Card c = AllCard.getOneCard(false);
		Card e = AllCard.getOneCard(true);
		ArrayList<Card> GetCard = dealer.getOneRoundCard();
		GetCard.add(c);
		GetCard.add(e);
		dealer.setOneRoundCard(GetCard);
	}
	private void ask_each_player_about_hits() 
	{
		for(int i = 0; i <MAXPLAYER; i++) 
		{
			Player a = AllPlayer[i];
			boolean hit=false;
			ArrayList<Card> GetCard = a.getOneRoundCard();
			do{
				hit=a.hit_me(this); //this
				if(hit){					
					GetCard.add(AllCard.getOneCard(true));
					a.setOneRoundCard(GetCard);
					System.out.print("Hit! ");
					System.out.println(a.getName()+"'s Cards now:");
					for(Card c : GetCard){
						c.printCard();
					}
				}
				else{
					System.out.println(a.getName()+", Pass hit!");
					System.out.println(a.getName()+", Final Card:");
					for(Card c : GetCard){
						c.printCard();
					}
				}
			}while(hit);
		}
	}
	private void ask_dealer_about_hits() 
	{
		boolean hit=false;
		ArrayList<Card> GetCard = dealer.getOneRoundCard();
		do{
			hit=dealer.hit_me(this); //this
			if(hit){					
				GetCard.add(AllCard.getOneCard(true));
				dealer.setOneRoundCard(GetCard);
				System.out.print("Hit! ");
				System.out.println("dealer's Cards now:");
				for(Card c : GetCard){
					c.printCard();
				}
			}
			else{
				System.out.println("Dealer's hit is over!");								
			}
		}while(hit);
		
	}
	private void calculate_chips() 
	{
		ArrayList<Card> GetCard = dealer.getOneRoundCard();
		System.out.println("Dealer's card value is " + dealer.getTotalValue() + " ,Cards:");
		for(Card c : GetCard){
			c.printCard();
		}
		for(int i = 0; i <MAXPLAYER; i++) 
		{
			Player a = AllPlayer[i];
			if(dealer.getTotalValue()>21 && a.getTotalValue()>21)
			{
				System.out.println("Need another game");
			}
			else if(dealer.getTotalValue()<=21&&a.getTotalValue()>21)
			{
				System.out.println(dealer+" wins the game");
				a.increaseChips(-pos_betArray[i]);
			}
			else if(dealer.getTotalValue()>21&&a.getTotalValue()<=21)
			{
				System.out.println(a.getName()+" wins the game");				
				a.increaseChips(pos_betArray[i]);
			}
			else if(dealer.getTotalValue()>a.getTotalValue()&&dealer.getTotalValue()<=21)
			{
				System.out.println(dealer+" wins the game");
				a.increaseChips(-pos_betArray[i]);
			}
			else if(dealer.getTotalValue()<a.getTotalValue()&&a.getTotalValue()<=21)
			{
				System.out.println(a.getName()+" wins the game");
				a.increaseChips(pos_betArray[i]);
			}
			else
			{
				System.out.println("Need another game");
			}
		}
	}
	public int[] get_players_bet() 
	{
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
