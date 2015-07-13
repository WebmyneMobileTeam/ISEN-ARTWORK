package com.xitij.android.isen_artwork.ui.widget;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
public class SportDialog extends AlertDialog {

    private Builder builder;
    private Context _context;
    private ArrayList selectedItemsArrayList;
    private OnOptionClickListner onOptionClickListner;


    public SportDialog(Context context) {
        super(context);
        this._context = context;
        init();
    }

    public SportDialog(Context context, int theme) {
        super(context, theme);
        this._context = context;
        init();
    }

    public SportDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this._context = context;
        init();
    }

    private void init() {
        builder = new Builder(_context);
        this.selectedItemsArrayList = new ArrayList();
    }

    public void setTitle(String string){
        builder.setTitle(string);
    }

    public void setItems(String[] array,boolean[] selectedArray){

        builder.setMultiChoiceItems(array, selectedArray, new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                if (isChecked) {
                    selectedItemsArrayList.add(which);
                } else if (selectedItemsArrayList.contains(which)) {
                    selectedItemsArrayList.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton("OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onOptionClickListner.onOk(selectedItemsArrayList);
            }
        });

        builder.setNegativeButton("Cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onOptionClickListner.onCancel();
            }
        });
        builder.show();



    }

    public void setOnOptionClickListner(OnOptionClickListner onClick){
        this.onOptionClickListner = onClick;
    }

    public ArrayList getSelectedValuesPositions(){
        return selectedItemsArrayList;
    }

    public interface OnOptionClickListner{
        public void onOk(ArrayList selectedItems);
        public void onCancel();
    }
}