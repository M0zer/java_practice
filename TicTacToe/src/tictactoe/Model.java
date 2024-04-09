package tictactoe;

import static java.lang.Math.random;
import java.util.Random;


public class Model {

    private int size;

    private static final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    
    private static final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    
    private Player actualPlayer;
    
    private Player winner=Player.NOBODY;

    private Player[][] table;

    public Model(int size) {
        this.size = size;
        actualPlayer = Player.X;

        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }
   public Player[][] getTable(){
       return table;
   }
    
    public Player getWinner(){
        return winner;
    }

    public Player step(int row, int column) {
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }

        table[row][column] = actualPlayer;
        
        findWinner(row,column);
        if(winner==Player.NOBODY){
        hasPenalty(row,column);}
        
       if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }
        return table[row][column];
    }

    public void findWinner(int row,int column) {
       
        for (int dir = 0; dir < 8; dir++) {
             int count = 1;
            for (int i = 1; i < 5; i++) {
                int newRow = row + dx[dir] * i;
                int newCol = column + dy[dir] * i;
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                        table[newRow][newCol] == actualPlayer) {
                    count++;
                } else {
                    break;
                }
            }
            for (int i = 1; i < 5; i++) {
            int newRow = row - dx[dir] * i;
            int newCol = column - dy[dir] * i;
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                    table[newRow][newCol] == actualPlayer) {
                count++;
            } else {
                break;
            }
        }
            if (count >= 5) {
                winner=actualPlayer;
            }
        }
       
    }
    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j] == Player.NOBODY) {
                    return false;
                }
            }
        }
        return true;
    }
     public void hasPenalty(int row,int column) {
        for (int dir = 0; dir < 8; dir=dir+2) {
            int count = 1;
            for (int i = 1; i < 4; i++) {
                int newRow = row + dx[dir] * i;
                int newCol = column + dy[dir] * i;
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                        table[newRow][newCol] == actualPlayer) {
                    count++;
                } else {
                    break;
                }
            }   
            for (int i = 1; i < 4; i++) {
            int newRow = row - dx[dir] * i;
            int newCol = column - dy[dir] * i;
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                    table[newRow][newCol] == actualPlayer) {
                count++;
            } else {
                break;
            }
        }
            if (count >= 4) {
               removeRandomCell();
              removeRandomCell();
            }else{
            count=1;
            for (int i = 1; i < 3; i++) {
                int newRow = row + dx[dir] * i;
                int newCol = column + dy[dir] * i;
                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                        table[newRow][newCol] == actualPlayer) {
                    count++;
                } else {
                    break;
                }
            }   
            for (int i = 1; i < 3; i++) {
            int newRow = row - dx[dir] * i;
            int newCol = column - dy[dir] * i;
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size &&
                    table[newRow][newCol] == actualPlayer) {
                count++;
            } else {
                break;
            }
        }       
            if (count >= 3) {
               removeRandomCell();
            }
            }
            
        } 
    }
     private void removeRandomCell() {
    while (true) {
        Random rand = new Random();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);
        if (table[row][col] == actualPlayer) {
            table[row][col] = Player.NOBODY;
            break;
        }
    }
}
    public Player getActualPlayer() {
        return actualPlayer;
    }

}
