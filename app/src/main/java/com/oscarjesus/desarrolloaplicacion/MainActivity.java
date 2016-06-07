package com.oscarjesus.desarrolloaplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSiguiente;
    private EditText etNombre, etTelefono, etCorreo, etDescripcion;
    private DatePicker etNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNombre = (EditText)findViewById(R.id.etNombre);
        etNacimiento = (DatePicker)findViewById(R.id.etNacimiento);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        etCorreo = (EditText)findViewById(R.id.etCorreo);
        etDescripcion = (EditText)findViewById(R.id.etDescripcion);

        Bundle parametros = getIntent().getExtras();

        if (parametros!=null){

            final String nombre = parametros.getString("Nombre");
            final String year = parametros.getString("Year");
            final String month = parametros.getString("Month");
            final String day = parametros.getString("Day");
            final String telefono = parametros.getString("Telefono");
            final String correo = parametros.getString("Correo");
            final String descripcion = parametros.getString("Descripcion");

            etNombre.setText(nombre);
            etNacimiento.updateDate(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));
            etTelefono.setText(getResources().getString(R.string.confirmar_telefono) + telefono);
            etCorreo.setText(getResources().getString(R.string.confirmar_correo) + correo);
            etDescripcion.setText(getResources().getString(R.string.confirmar_descripcion) + descripcion);
        }


        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String Year = String.valueOf(etNacimiento.getYear());
                String Month = ( (etNacimiento.getMonth()+1) )<10?"0"+String.valueOf(etNacimiento.getMonth()) : String.valueOf(etNacimiento.getMonth());
                String Day = ( (etNacimiento.getDayOfMonth ()+1) )<10?"0"+String.valueOf(etNacimiento.getDayOfMonth ()) : String.valueOf(etNacimiento.getDayOfMonth());


                Intent i = new Intent(MainActivity.this, ConfirmarContacto.class);
                i.putExtra("Nombre",etNombre.getText().toString());
                i.putExtra("Year",Year);
                i.putExtra("Month",Month);
                i.putExtra("Day",Day);
                i.putExtra("Telefono",etTelefono.getText().toString());
                i.putExtra("Correo",etCorreo.getText().toString());
                i.putExtra("Descripcion",etDescripcion.getText().toString());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
