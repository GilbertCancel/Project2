

public class Pile extends MyStack{
  
  Card pile[];
  public int top;
  public int length;

  public Pile(){
    this.pile = new Card[52];
    top = -1;
    length = 0;

  }

  //In this function length is used to identify the position of the Stack where the card is to be placed.
  public void addcard(Card card){
    super.push(this.pile,card, length);
    top++;
    length++;
  }

  public Card gettopcard(){
    if(top != -1){
    return(pile[top]);
    }
    else{
      System.out.println("Stack is empty");
      return null;
    }
  }

  public void pop(){
    if(top != -1){
    top--;
    length--;
    }
  }

  public Card takelast(){
    if(top != -1){
    return(pile[top]);
    }
    else{
      System.out.println("Stack is empty");
      return null;
    }
  }

}
