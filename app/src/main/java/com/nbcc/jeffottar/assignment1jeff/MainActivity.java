/**
 * @file
 * @author Jeff Ottar-
 * @version 1.0
 *
 *
 * @section DESCRIPTION
 * <  >
 *
 *
 * @section LICENSE
 *
 *
 * Copyright 2018
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * @section Academic Integrity
 * I certify that this work is solely my own and complies with
 * NBCC Academic Integrity Policy (policy 1111)
 */

package com.nbcc.jeffottar.assignment1jeff;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ShoppingList list = new ShoppingList();
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    /**
     * oncreate method for the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if not null restore the state
        if (savedInstanceState != null) {
            //delete text views
            list=(ShoppingList)savedInstanceState.getSerializable("list");
            LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
            layout.removeAllViews();
            populateLayout();
        }
    }


    /**
     * button click to add item from other activity
     * @param view
     */
    public void AddItem(View view) {
        Intent intent = new Intent(this, ShoppingActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }


    /**
     * on the result add item from intent to the list and populate layout
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
           if (requestCode == TEXT_REQUEST) {
               if (resultCode == RESULT_OK) {
                   String item = data.getStringExtra("item");
                   list.addItem(item);
                   //add item and make text views
                   populateLayout();
               }
           }
       }

    /**
     * populate the linear layout with the textview holding the values
     */
    private void populateLayout() {
        Map<String, Integer> cart =list.getItems();
        String[] keys = cart.keySet().toArray(new String[cart.size()]);
        for(int i =0; i < cart.size(); i++)
        {
            TextView text = new TextView(this);
            text.setText((keys[i]+": " + cart.get(keys[i])));
            text.setPadding(5,5,5,5);
            if(isTablet(this))
            {
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
            }
            else
            {
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            }

            LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
            layout.addView(text);
        }
    }

    /**
     * check f the device is a tablet
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * save the state
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState - Jeff");
        //save map and delete text views if list and map are not null
        if(list!=null) {
            outState.putSerializable("list", list);
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart - Jeff");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause - Jeff");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart - Jeff");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume - Jeff");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop - Jeff");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy - Jeff");
    }

}
