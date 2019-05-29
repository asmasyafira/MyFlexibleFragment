package com.example.safuan.myflexiblefragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {
    Button btnChoose, btnClose;
    RadioGroup rgOption;
    RadioButton rbDora, rbDoraemon, rbUpinIpin, rbBoboiboy;
    OnOptionDialogListener optionDialogListener;


    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnChoose = view.findViewById(R.id.btn_choose);
        btnClose = view.findViewById(R.id.btn_close);
        rgOption = view.findViewById(R.id.rg_option);
        rbDoraemon = view.findViewById(R.id.rb_doraemon);
        rbDora = view.findViewById(R.id.rb_dora);
        rbBoboiboy = view.findViewById(R.id.rb_boboiboy);
        rbUpinIpin = view.findViewById(R.id.rb_UpinIpin);

        btnChoose.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailCategoryFragment){
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener; //klo g bisa diubah ke dialogfragment
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose: //dibawah ini buat deklarasiin rb sm rg pas diklik
                int chechedRadioButtonId = rgOption.getCheckedRadioButtonId(); //kondisinya ter-klik
                //bikin sebuah kondisi pas rb ter checklist, dia akan ngecek setiap nilai dr yg terpilih
                if (chechedRadioButtonId != -1){
                    String kartun = null; //null ini nilai awal si kartun
                    switch (chechedRadioButtonId){
                        case R.id.rb_doraemon:
                            kartun = rbDoraemon.getText().toString().trim(); //trim buat ngapus enter depan an belakang
                            break;
                        case R.id.rb_dora:
                            kartun = rbDora.getText().toString().trim();
                            break;
                        case R.id.rb_boboiboy:
                            kartun = rbBoboiboy.getText().toString().trim();
                            break;
                        case R.id.rb_UpinIpin:
                            kartun = rbUpinIpin.getText().toString().trim();
                            break;
                    }
                    if (optionDialogListener != null){
                        optionDialogListener.onOptionChoosen(kartun);
                    }
                    getDialog().dismiss();
                }
                break;

        }

    }


    public interface OnOptionDialogListener { //private classnya ganti public interface
         void onOptionChoosen(String text) ;//public samping dh diapus



    }
}
