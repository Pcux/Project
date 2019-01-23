package com.example.belka.progaosnova;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.GridLayout;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFieldView extends GridLayout{

    public String TAG = this.getClass().getSimpleName();
    Integer points=0;

    public List<List<GameCellView>> cells= new ArrayList<>();
    private int spaceBetweenCells = 0;

    // обязательно нужно реализовать как минимум 3 конструктора для кастомных вьюх
    public GameFieldView(Context context) {
        super(context);
        ctxt=context;
    }

    public GameFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctxt=context;
    }

    public GameFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctxt=context;
    }
    AnimationSet as1;
    Context ctxt;
    public void SetAnimations(){
        //as1= new AnimationSet(true);
        //as1.setInterpolator(new AccelerateDecelerateInterpolator());
        //AlphaAnimation aa = new AlphaAnimation((float)1.0,(float)0.1);
        //aa.setDuration(1);
        //aa.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        //as1.addAnimation(aa);
    }
    //Socket socket;
    Integer nplayers;
    Integer nplayer;
    Callback callback;
    public void init(final Callback callback, final Integer nplayers, Integer nplayer) {
        setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        this.callback=callback;
        this.nplayers=nplayers;
        this.nplayer=nplayer;
        //this.socket=socket;
        //SetAnimations();


        // добавляем наше ячейки только когда рассчиталась основная разметка, так как до этого
        // момента ширина и высота GridLayout может быть еще неизвестна, а без нее мы не сможем
        // рассчитать размер ячеек
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                List<GameCellView> cellsinit;
                // проверяем, не добавлены ли уже кнопки, если уже добавлены - ничего не делаем
                // проверяем, потому что метод может вызываться несколько раз подряд, и если не
                // проверить - произойдет наложение вьюх
                if (!cells.isEmpty()) return;

                // рассчитываем размер ячеек
                int size = getRowCount();
                int cellSize = getWidth() / size;
                for (int i = 0; i < size; i++){
                    cellsinit = new ArrayList<GameCellView>(5);
                    for (int j = 0; j<size; j++) {

                        final GameCellView cellView = new GameCellView(getContext(),new MyAnimationListener());
                        cellView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                switch(state){
                                    case 0:
                                        if (CheckFishka(view.getId()))
                                            if (p>0) {
                                                if(!ShowOptionsFor(view.getId(), p)){
                                                    return;};
                                                lastid=view.getId();
                                                state++;
                                            }
                                        break;
                                    case 1:
                                        if (CanMove(lastid,view.getId(),p)){
                                            HideOptionsFor(lastid,p);
                                            p=MoveTo(lastid,view.getId(),p);
                                            if (p==0) {
                                                String s = encode();
                                                callback.emits(s);
                                                }
                                            state--;}
                                        else {if (lastid.equals(view.getId())){state=0; HideOptionsFor(lastid,p);}}
                                        break;
                                }
                            }
                        });

                        // устанавливаем параметры разметки ячейки
                        LayoutParams layoutParams = new LayoutParams();
                        layoutParams.height = cellSize - spaceBetweenCells;
                        layoutParams.width = cellSize - spaceBetweenCells;

                        // добавляем оступы
                        int margin = spaceBetweenCells / 2;
                        layoutParams.setMargins(margin, margin, margin, margin);
                        addView(cellView, layoutParams);
                        cellsinit.add(cellView);
                    }
                    cells.add(cellsinit);
                }
                Random random = new Random();
                Integer i=0;
                int x=0;
                int y=0;
                for (i=0;i<size;i++) {
                    for (int j=0;j<size;j++) {
                        cells.get(i).get(j).setId(i*10+j);
                    }
                }
                for(i = 0;i<size;i++){
                    for (int j=0; j<size; j++){
                        MyAnimationListener mal = new MyAnimationListener();
                        //mal.Init(cells.get(i).get(j));
                        cells.get(i).get(j).init(mal);
                    }
                }
                    i=0;
                while (i<nplayers) {
                    x=random.nextInt(size);
                    y=random.nextInt(size);
                    if (cells.get(x).get(y).getText()=="") {
                        cells.get(x).get(y).setText(new Integer(i+1).toString()); i++;}
                }


            }
            });
    }

    /*public void UpdateCell(Integer x, Integer y, String value){
        cells.get(x).get(y).setText(value);
    }*/
    @Override
    public void setRowCount(int rowCount) {
        // переопределяем этот метод, чтобы нельзя было задать кол-во строк
    }
    public String encode() {
        String result="";
        for (int i=0; i<getRowCount();i++)
            for (int j=0; j<getRowCount();j++)
            {
                result=(cells.get(i).get(j).getText()=="")?result+"0 ":result+(cells.get(i).get(j).getText()+" ");
            }
        return result;
    }
    @Override
    public void setColumnCount(int columnCount) {
        // переопределяем этот метод, чтобы нельзя было задать кол-во столбцов
    }

    /**
     * API нашей вьюхи, через которое мы можем, в данном случае, установаить размер сетки и оступы
     **/


    // вводим метод, который позволит создавать только квадратные поля
    public void setSideCount(int sideCount) {
        super.setColumnCount(sideCount);
        super.setRowCount(sideCount);
        cells.clear();
    }

    // устанавливаем расстояние между ячейками
    public void setSpaceBetweenCells(int space) {
        spaceBetweenCells = space;
    }



    Integer state=0;
    Integer p=1;
    public void setstate(int state){
        this.state=state;
    }
    public void setpoints(int points){
        if (state!=1)
        this.p=points;
    }
    public int getstate(){
        return state;
    }
    public int getpoints(){
        return p;
    }
    boolean ShowOptionsFor(Integer id,Integer points){
        if(cells.get(id/10).get(id%10).getText().toString().equals(((Integer)nplayer).toString())){
        for (int i=0;i<getRowCount();i++)
            for (int j=0;j<getRowCount();j++)
            {
                SetAnimations();
                if(Math.abs(id/10-i)+Math.abs(id%10-j)<=points){
                    cells.get(i).get(j).toggle(0);
                }
            };
        return true;
        }
        else {
          //  Toast.makeText(getContext(),((Integer)nplayer).toString()+" "+cells.get(id/10).get(id%10).getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
        }

    }

    void HideOptionsFor(Integer id,Integer points){
        for (int i=0;i<getRowCount();i++)
            for (int j=0;j<getRowCount();j++)
            {
                if(Math.abs(id/10-i)+Math.abs(id%10-j)<=points)
                {
                    SetAnimations();
                    cells.get(i).get(j).toggle(0);
                }
            }
    }
    Random r= new Random();
    void Respawn(Integer id){
        boolean bol=true;
        int x=0;
        int y=0;
        while (bol){
            x=r.nextInt(this.getRowCount());
            y=r.nextInt(this.getRowCount());
            if (cells.get(x).get(y).getText().equals("")){cells.get(x).get(y).setText(cells.get(id/10).get(id%10).getText()); bol=false;}
        }
    }
    boolean CanMove(Integer id1,Integer id2,Integer p){
        return ((Math.abs(id1/10-id2/10)+Math.abs(id1%10-id2%10)<=p)&&(!(id1.equals(id2))));
    }
    Integer MoveTo(Integer id1,Integer id2, Integer p){
        String text=cells.get(id1/10).get(id1%10).getText().toString();
        if (!cells.get(id2/10).get(id2%10).getText().toString().equals("")){
            if(cells.get(id2/10).get(id2%10).getText().toString().equals(""))
            Respawn(id2);
        }
        cells.get(id1/10).get(id1%10).setText("");
        cells.get(id2/10).get(id2%10).setText(text);
        return p-(Math.abs(id1/10-id2/10)+Math.abs(id1%10-id2%10));
    }
    boolean CheckFishka(Integer id1){
        return !cells.get(id1/10).get(id1%10).getText().toString().equals("");
    }
    Integer lastid=0;
    interface Callback{
        public void emits(String gameFieldState );
    }
}
