package org.elkuku.calculadordelivaec2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private EditText edtTotal;
    private TextView txtSinIVA;
    private TextView txtIVA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnClear = (Button) findViewById(R.id.btn_clear);

        edtTotal = (EditText) findViewById(R.id.edt_total);
        txtSinIVA = (TextView) findViewById(R.id.txt_siniva);
        txtIVA = (TextView) findViewById(R.id.txt_iva);

        btnClear.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            edtTotal.setText("");
            txtSinIVA.setText("");
            txtIVA.setText("");
        }});

        TextWatcher textWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){  calculateResult(); }
        };

        edtTotal.addTextChangedListener(textWatcher);

    }

    private void calculateResult() throws NumberFormatException {
        double sinIVA, IVA, total;

        txtSinIVA = (TextView) findViewById(R.id.txt_siniva);
        txtIVA = (TextView) findViewById(R.id.txt_iva);

        Editable edit = edtTotal.getText();

        if (edit.toString().equals(""))
            return;

        try {
            total = Double.parseDouble(edit.toString());
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Numero invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        sinIVA = total / 1.12;
        IVA = total - sinIVA;

        txtSinIVA.setText(new DecimalFormat("#,###,##0.00").format(sinIVA));
        txtIVA.setText(new DecimalFormat("#,###,##0.00").format(IVA));
    }
}
