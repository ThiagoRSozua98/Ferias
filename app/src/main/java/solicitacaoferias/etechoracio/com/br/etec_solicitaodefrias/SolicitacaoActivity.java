package solicitacaoferias.etechoracio.com.br.etec_solicitaodefrias;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;

import solicitacaoferias.etechoracio.com.br.etec_solicitaodefrias.Utils.DateTimeUtils;

public class SolicitacaoActivity extends AppCompatActivity {

    private Spinner spnQtdeDias;
    private RadioButton rdbSim;
    private RadioButton rdbNao;
    private Button btnData;
    private Button btnRegistrar;
    private TextView viewCalcData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);
        spnQtdeDias = (Spinner) findViewById(R.id.spnQtdeDias);
        rdbSim = findViewById(R.id.rdbSim);
        rdbNao = findViewById(R.id.rdbNao);
        btnData = findViewById(R.id.btnData);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        viewCalcData = findViewById(R.id.viewCalcData);
    }


    private ArrayAdapter getAdapterQtdeDiasSim() {
        return new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, Arrays.asList(10, 15, 20, 30));
    }

    private ArrayAdapter getAdapterQtdeDiasNao() {
        return new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, Arrays.asList(20, 30));
    }

    public void onRadioClick(View view) {
        if (view.getId() == R.id.rdbSim) {
            spnQtdeDias.setAdapter(getAdapterQtdeDiasSim());
        }
        if (view.getId() == R.id.rdbNao) {
            spnQtdeDias.setAdapter(getAdapterQtdeDiasNao());
        }
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker datePicker, int year, int month, int day) {


            btnData.setText(DateTimeUtils.formatDate(day, month, year));
        }
    };


    /* private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String data = String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year);

        }
    }; */

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case R.id.btnData:
                return new DatePickerDialog(this, dateSetListener, year, month, day);
        }
        return null;
    }

    public void onClick(View view) {
        showDialog(view.getId());
    }

    public void onRegistrar(View view) {
        Date data = DateTimeUtils.toDate(btnData.getText().toString());

        if (!DateTimeUtils.isSegundaFeira(data)) {
            String msg = "Selecione uma Segunda-Feira para Inicio das Férias";
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {

            //  if (ID DO RDB)
            int qtde = Integer.parseInt(spnQtdeDias.getSelectedItem().toString());

            Date data_final = DateTimeUtils.addDays(data, qtde);

            viewCalcData.setText(data_final.toString());
        }
    }

}
