package com.example.belka.progaosnova;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterPlayerNameDialog extends AlertDialog {

    private AppCompatEditText playerName;
    private NameResultListener listener;

    public EnterPlayerNameDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public EnterPlayerNameDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public EnterPlayerNameDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_enter_player_name, null);
        setTitle("Введите имя игрока");
        setView(contentView);
        playerName = contentView.findViewById(R.id.dialog_enter_player_name__text);
        setButton(BUTTON_POSITIVE, "Начать игру", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.deliverPlayerName(playerName.getText().toString());
                }
            }
        });
    }

    public void setNameResultListener(NameResultListener listener) {
        this.listener = listener;
    }

    interface NameResultListener {

        public void deliverPlayerName(String playerName);
    }
}
