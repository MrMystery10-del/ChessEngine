package gui.constants;

public class MoveCounter
{
   private boolean Turn = true;//false = black, true = white

    public void setTurn(boolean turn) {
        Turn = turn;
    }

    public boolean isTurn() {
        return Turn;
    }
}
