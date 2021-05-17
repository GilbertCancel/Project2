


public class MyStack
{

  //Stack will be implemented through an array.
  Card stack[];
  public int top;
  public int length;

  //Initialize MyStack
  public MyStack(){
    this.stack = new Card[52];
    top = -1;
    length = 0;

  }

  //Push funtion implementation
  public void push(Card ar[],Card val, int pos){

    if(top + 1 ==  52){
      System.out.print("Stack is Full");
    }
    else{
      ar[pos] = val;
    }

  }

  //top implementation
  public Card top(){

    if(top == -1){
      return null;
    }
    else{
      return stack[top];
    }
  }

  //Pop implementation
  public void pop(){

    if(top == -1){
      System.out.println("Stack is empty");
    }
    else{
      top--;
    }

  }

  //Checks if the Stack is empty
  public Card isEmpty(){

    if(top == -1){
    return null;
    }
    else{
      return(stack[top]);
    }

  }


}
