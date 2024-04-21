package com.example.mobilesystems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mobilesystems.MainActivity;

public class DialogForSum extends DialogFragment {

    private EditText firstNumberEditText;
    private EditText secondNumberEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_sum_of_numbers_dialog, null);
        builder.setView(view)
                .setTitle("Введите Числа")
                .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String firstNumber = firstNumberEditText.getText().toString();
                        String secondNumber = secondNumberEditText.getText().toString();

                        ((MainActivity) getActivity()).processNumbers(firstNumber, secondNumber);
                    }
                })
                .setNegativeButton("отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

        firstNumberEditText = view.findViewById(R.id.firstNumberEditText);
        secondNumberEditText = view.findViewById(R.id.secondNumberEditText);

        return builder.create();
    }

}
