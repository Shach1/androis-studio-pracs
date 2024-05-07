package com.example.pr7;

import android.app.Dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.widget.EditText;


public class MyDialogFragment extends DialogFragment { // CustomDialog

    public interface OnTextEnteredListener {
        void onTextEntered(String text);
    }

    private OnTextEnteredListener listener;

    public void setOnTextEnteredListener(OnTextEnteredListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("CustomDialog");
        builder.setView(R.layout.fragment_my_dialog);
        builder.setPositiveButton("Да", (dialog, which) -> {
            EditText editText = getDialog().findViewById(R.id.editTextToSet);
            String text = editText.getText().toString();
            if (listener != null) {
                listener.onTextEntered(text);
            }
        });
        builder.setNegativeButton("Отмена", null);
        return builder.create();
    }

}

