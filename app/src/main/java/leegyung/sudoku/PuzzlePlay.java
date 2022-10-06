package leegyung.sudoku;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzlePlay {
    private float Width = 0;
    private Paint Paint;
    private ArrayList<ArrayList<Integer>> Numbers;


    // 이니셜라이저
    public PuzzlePlay(){
        Numbers = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < 9; i++ ){
            Numbers.add(new ArrayList<Integer>(Collections.nCopies(9,0)));
        }

        Paint = new Paint();
        Paint.setColor(Color.BLACK);


    }

    // 게임 판그리는것
    public void Draw(Canvas canvas){
        if(Width == 0){ Width = (float)canvas.getWidth(); }
        Paint.setColor(Color.BLACK);
        canvas.drawRect(0,0, Width, Width, Paint);
        Paint.setColor(Color.WHITE);
        float temp = Width/9;

        for(int i = 1; i < 9; i++){
            if(i == 3 || i == 6){ Paint.setStrokeWidth(5); }
            else{ Paint.setStrokeWidth(1); }
            canvas.drawLine(0, temp*i, Width, temp*i, Paint);
            canvas.drawLine(temp*i,0,temp*i,Width,Paint);
        }
        DrawNumbers(canvas, temp);
    }

    // 게임판에 숫자 그리는것
    private void DrawNumbers(Canvas canvas, float wid){
        Paint.setTextSize(wid);
        for(int first = 0; first < 9; first++){
            for(int second = 0; second < 9; second++){
                if(Numbers.get(first).get(second) != 0) {
                    canvas.drawText(String.valueOf(Numbers.get(first).get(second)),
                            wid * second + (wid / 4), wid * (first + 1) - (wid / 6), Paint);
                }
            }
        }
    }

    // 터치한위치의 숫자 index 찾고 바꾸기
    public void NumberSelect(float X, float Y){
        int xLaw = 0;
        int yLaw = 0;
        float wid = Width / 9;
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                if(wid * x <= X && X <= wid * (x + 1)){
                    if(wid * y <= Y && Y <= wid * (y + 1)) {
                        xLaw = x;
                        yLaw = y;
                    }
                }
            }
        }

        SharedPreferences pref = MainActivity.context.getSharedPreferences("Sudoku", 0);
        Numbers.get(yLaw).set(xLaw, pref.getInt("Number", 0));
    }

    public ArrayList<ArrayList<Integer>> GetNumbers(){
        return Numbers;
    }



}
