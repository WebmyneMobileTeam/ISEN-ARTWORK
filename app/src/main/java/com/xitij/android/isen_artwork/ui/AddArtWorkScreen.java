package com.xitij.android.isen_artwork.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xitij.android.isen_artwork.R;
import com.xitij.android.isen_artwork.helpers.Functions;
import com.xitij.android.isen_artwork.helpers.PrefUtils;
import com.xitij.android.isen_artwork.model.ArtworkType;
import com.xitij.android.isen_artwork.model.MediumType;
import com.xitij.android.isen_artwork.model.User;
import com.xitij.android.isen_artwork.ui.widget.MaterialSpinner;
import com.xitij.android.isen_artwork.ui.widget.SquareImageView;

import java.util.ArrayList;

public class AddArtWorkScreen extends ActionBarActivity implements View.OnClickListener {

    private LinearLayout linearImages;
    private MaterialSpinner spinnerType;
    private MaterialSpinner spinnerMedium;

    private ArrayList<ArtworkType> art_types;
    private ArrayList<MediumType> art_mediums;
    private ArrayList<MediumType> filtered_mediums;
    private User currentUser;
    private LinearLayout linearSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_art_work_screen);
        linearImages = (LinearLayout) findViewById(R.id.linearImages);
        spinnerMedium = (MaterialSpinner) findViewById(R.id.spinner_medium);
        spinnerType = (MaterialSpinner) findViewById(R.id.spinner_type);
        linearSubmit = (LinearLayout) findViewById(R.id.linearSubmit);
        linearSubmit.setOnClickListener(this);

        currentUser = PrefUtils.getCurrentUser(AddArtWorkScreen.this);
        setupImages();
        setupSpinners();
    }

    private void setupSpinners() {

        art_types = currentUser.artworkTypes;
        String[] types = new String[art_types.size()];
        for (int i = 0; i < art_types.size(); i++) {
            types[i] = art_types.get(i).typeName;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setFloatingLabelText("Type");

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == -1) {

                } else {
                    processSetMedium(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void processSetMedium(int i) {

        int artWorkId = art_types.get(i).artworkTypeID;
        art_mediums = currentUser.mediumTypes;
        filtered_mediums = new ArrayList<>();

        for (MediumType mediumType : art_mediums) {
            if (mediumType.artworkTypeID.equalsIgnoreCase(String.valueOf(artWorkId))) {
                Log.e("Found Medium", mediumType.medium_name);
                filtered_mediums.add(mediumType);
            }
        }
        String[] mediums = new String[filtered_mediums.size()];
        for (int j = 0; j < filtered_mediums.size(); j++) {
            mediums[j] = filtered_mediums.get(j).medium_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mediums);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedium.setAdapter(adapter);
        spinnerMedium.setFloatingLabelText("Medium");

    }

    private void setupImages() {

        for (int i = 0; i < linearImages.getChildCount(); i++) {
            SquareImageView img = (SquareImageView) linearImages.getChildAt(i);
            img.setNoImage();
            img.setOnClickListener(imageClickListner);
            registerForContextMenu(img);
        }

    }

    private View.OnClickListener imageClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SquareImageView img = (SquareImageView) view;
            //Functions.displayMessage(AddArtWorkScreen.this,""+linearImages.indexOfChild(img));
            openContextMenu(img);

        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 1, Menu.NONE, "Take Photo");
        menu.add(0, 2, Menu.NONE, "Pick From Gallery");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.linearSubmit:
                processSubmitArtWork();
                break;
        }

    }

    private void processSubmitArtWork() {

        

    }
}
