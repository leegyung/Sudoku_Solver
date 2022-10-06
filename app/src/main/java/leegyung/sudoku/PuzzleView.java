package leegyung.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {
    private PuzzlePlay Play;
    private Solver Solver;


    public PuzzleView(Context context) {
        super(context);
        init(null, 0);

    }
    public PuzzleView(Context context, AttributeSet att){
        super(context,att);
        init(null, 0);
    }
    public PuzzleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle){
        Play = new PuzzlePlay();
        Solver = new Solver();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        SharedPreferences pref = MainActivity.context.getSharedPreferences("Sudoku", 0);
        int solve = pref.getInt("Solve", 0);
        if(solve == 100){
            Solver.SetBoard(Play.GetNumbers());
            if(Solver.SolvePuzzle()){

            }
            else{
                //못푸는 퍼즐인거임
            }




        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Solve", 0);
        editor.apply();

        Play.Draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                Play.NumberSelect(event.getX(), event.getY());
                invalidate();
                break;
        }
        return true;
    }



}
