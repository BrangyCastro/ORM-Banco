package com.facci.brangycastro.examen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facci.brangycastro.examen.adapter.Adaptador;
import com.facci.brangycastro.examen.model.Banco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button botonCrear;
    ListView lista;
    Banco banco;
    ArrayList<Banco> bancos;
    Adaptador adaptador;
    List<Banco> listaBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.listViewBanco);
        mostrarListView();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // Accion que nos permite al darle click, que nos aparesca un dialogo y poder
        // ingresa los datos

        botonCrear = (Button)findViewById(R.id.btnCrear);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dlgCrearBanco = new Dialog(MainActivity.this);
                dlgCrearBanco.setContentView(R.layout.dlg_crear);
                Button botonGuardar = (Button)dlgCrearBanco.findViewById(R.id.btnGuardarPersona);
                final EditText cajaCedula = (EditText)dlgCrearBanco.findViewById(R.id.txtCedulaCrear);
                final EditText cajaNombre = (EditText)dlgCrearBanco.findViewById(R.id.txtNombreCrear);
                final EditText cajaSaldo = (EditText)dlgCrearBanco.findViewById(R.id.txtSaldoCrear);
                botonGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Banco banco = new Banco(cajaCedula.getText().toString(),
                                cajaNombre.getText().toString(),
                                cajaSaldo.getText().toString());
                        banco.save();
                        mostrarListView();
                        dlgCrearBanco.hide();

                    }
                });
                dlgCrearBanco.show();

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dlgModificar = new Dialog(MainActivity.this);
                dlgModificar.setContentView(R.layout.dlg_modificar);
                final TextView cedulaView = (TextView) view.findViewById(R.id.lblCedula);
                //Toast.makeText(MainActivity.this,cedulaView.getText().toString(),Toast.LENGTH_SHORT).show();

                final Banco banco = Banco.leerCedula(cedulaView.getText().toString());
                //EditText cajaCedula = (EditText)dlgModificar.findViewById(R.id.txtNombreModificar);
                final EditText cajaNombre = (EditText)dlgModificar.findViewById(R.id.txtNombreModificar);
                final EditText cajaSaldo = (EditText)dlgModificar.findViewById(R.id.txtSaldoModificar);
                final EditText cajaAccion = (EditText) dlgModificar.findViewById(R.id.txtAccion);
                Button retiro = (Button)dlgModificar.findViewById(R.id.btnRetiro);
                Button deposito = (Button)dlgModificar.findViewById(R.id.btnDeposito);

                deposito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Deposito
                        double saldoAnterior = Double.valueOf(cajaSaldo.getText().toString());
                        double saldoRetiro = Double.valueOf(cajaAccion.getText().toString());

                        double saldoActual = saldoAnterior + saldoRetiro;
                        String resultado = String.valueOf(saldoActual);

                        banco.nombre = cajaNombre.getText().toString();
                        banco.saldo = resultado;
                        banco.save();
                        mostrarListView();
                        dlgModificar.hide();
                        Toast.makeText(MainActivity.this, "Retiro Exitosa", Toast.LENGTH_SHORT).show();

                    }

                });

                retiro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //reterio
                        double saldoAnterior = Double.valueOf(cajaSaldo.getText().toString());
                        double saldoRetiro = Double.valueOf(cajaAccion.getText().toString());

                        double saldoActual = saldoAnterior - saldoRetiro;
                        String resultado = String.valueOf(saldoActual);

                        banco.nombre = cajaNombre.getText().toString();
                        banco.saldo = resultado;
                        banco.save();
                        mostrarListView();
                        dlgModificar.hide();
                        Toast.makeText(MainActivity.this, "Deposito Exitosa", Toast.LENGTH_SHORT).show();
                    }

                });

                cajaNombre.setText(banco.nombre);
                cajaSaldo.setText(banco.saldo);
                dlgModificar.show();

            }
        });


    }

    private void mostrarListView(){
        bancos = new ArrayList<Banco>();
        banco = new Banco();

        listaBanco = banco.leerTodos();
        for (int i = 0; i < listaBanco.size(); i ++){
            banco = listaBanco.get(i);
            bancos.add(banco);
        }
        adaptador = new Adaptador(MainActivity.this, bancos);
        lista.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }
}
