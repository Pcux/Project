package com.example.belka.progaosnova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RoomActivity extends AppCompatActivity{
    ListView listView;
    String[] name;
    int maxPl = 5;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        listView = (ListView)findViewById(R.id.listView);
        name = new String[5];
        boolean a=createNewPlayer("onsdl");
//        final String[] catNames = new String[] {
//                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
//                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
//                "Китти", "Масяня", "Симба"
//        };
//
//// используем адаптер данных
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, catNames);
//        listView.setAdapter(adapter);
    }

    public boolean createNewPlayer(String namePl) {
        if (count<maxPl) {
            name[count] = namePl;
            count++;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, name);
            listView.setAdapter(adapter);
            return true;
        }
        return false;
    }
}
