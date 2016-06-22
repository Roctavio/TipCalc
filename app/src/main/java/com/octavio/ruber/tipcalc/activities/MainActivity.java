package com.octavio.ruber.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.octavio.ruber.tipcalc.R;
import com.octavio.ruber.tipcalc.TipCalcClass;
import com.octavio.ruber.tipcalc.fragments.TipHistoryListFragment;
import com.octavio.ruber.tipcalc.fragments.TipHistoryListFragmentListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE=1;
    private final static int DEFAULT_TIP_PERCENTAGE=10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment =(TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener= (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
       // Log.e(getLocalClassName(),"click en submit");
        hideKeyboard(); //esconde el teclado cuando el usuario ya no quiere ingresar algo
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){ //si no esta vacio hacemos:
            double total=Double.parseDouble(strInputTotal); //definimos una variabe total a partir del string
            int tipPercentage=getTipPercentage();  //voy a obtener el % de propina con un nuevo metodo getTipPercentage
            double tip=total*(tipPercentage/100d);//vamos a obtener la propina total

            String strTip=String.format(getString(R.string.global_message_tip),tip); //Vamos a colocar un string y darle formato
            //le enviamos el monto de propina
            fragmentListener.action(strTip);
            txtTip.setVisibility(View.VISIBLE); //vamos a hacer este campo visible
            txtTip.setText(strTip); //y le vamos a colocar el valor correspondiente
        }

    }
    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);

    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE); //esto aumenta la proopina en una unidad

    }

    private void handleTipChange(int change) { //en este metodo handleTipChange esta toda la logica de los OnClick de arriba
        int tipPercentage=getTipPercentage(); //trae el % de propina actual
        tipPercentage+=change; //a este % le
       // if (tipPercentage > 0){ //si esta variable es > 0 entonces
            inputPercentage.setText(String.valueOf(tipPercentage)); //lo colocamos en el inputPercentange
            //esto solo funciona si tengo implementado mi getTipPercentage

       // }
    }

    private int getTipPercentage() {
        int tipPercentage =DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage=inputPercentage.getText().toString().trim(); //lo vamos a traer del edittext
        if (!strInputTipPercentage.isEmpty()){ //si no esta vacio =>
            tipPercentage=Integer.parseInt(strInputTipPercentage);
        } else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyboard() {
        InputMethodManager InputManager =(InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            InputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe){
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcClass app = (TipCalcClass)getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW); //ACTION_VIEW me va a permitir pasar a un navegador
        intent.setData(Uri.parse(strUrl)); //android me va a mostrar un navegador con esto
        startActivity(intent);
    }


}
