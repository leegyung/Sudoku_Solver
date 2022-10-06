package leegyung.sudoku;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    private ArrayList<ArrayList<Integer>> numbers = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> Logs = new ArrayList<>();

    public Solver(){

    }

    public void SetBoard(ArrayList<ArrayList<Integer>> arr){
        numbers = arr;
    }

    private boolean RowCheck(int number, int row){
        for(int i = 0; i < 9; i++){
            if(numbers.get(row).get(i) == number){
                return false;
            }
        }
       return true;
    }

    private boolean ColumnCheck(int number, int column){
        for(int i = 0; i < 9; i++){
            if(numbers.get(i).get(column) == number){
                return false;
            }
        }
        return true;
    }

    private boolean BoxCheck(int number, int col, int raw){
        int row = raw - (raw % 3);
        int column = col - (col % 3);

        for(int i = row; i < row + 3; i++){
            for(int j = column; j < column + 3; j++) {
                if(numbers.get(i).get(j) == number){ return false; }
            }
        }
        return true;
    }

    private boolean AllCheck(int number, int column, int raw){
        return  ColumnCheck(number, column) &&  RowCheck(number, raw) && BoxCheck(number, column, raw);
    }



    public boolean SolvePuzzle(){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(numbers.get(row).get(col) == 0){
                    for(int num = 1; num < 10; num++){
                        if(AllCheck(num, col, row)){
                            numbers.get(row).set(col, num);
                            Logs.add(AddLog(row,col,num));

                            if(SolvePuzzle()) {
                                return true;
                            }
                            else{
                                numbers.get(row).set(col, 0);
                                Logs.add(AddLog(row,col,0));
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private ArrayList<Integer> AddLog(int row, int column, int number){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(row);
        temp.add(column);
        temp.add(number);
        return temp;
    }

    public ArrayList<ArrayList<Integer>> GetLogs(){
        return Logs;
    }


}
