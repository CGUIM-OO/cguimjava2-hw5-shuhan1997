public class Card {
		private Suit suit; 
		private int rank;
        enum Suit {Club, Diamond, Heart, Spade};
		public Card(int s,int r){
			if (s==1) 
			{
				suit = Suit.Club;
			}
			if (s==2) 
			{
				suit = Suit.Diamond;
			}
			if (s==3) 
			{
				suit = Suit.Heart;
			}
			if (s==4) 
			{
				suit = Suit.Spade;
			}
			
			rank=r;
		}	
		
		public void printCard(){
			
			
			String RankArray[] = {"Ace","2","3","4","5","6","7","8","9","10","J","Q","K"};
			
			System.out.println(suit+","+RankArray[rank-1]);
			
		}
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}

