package com.facci.brangycastro.examen.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facci.brangycastro.examen.R;
import com.facci.brangycastro.examen.model.Banco;

import java.util.ArrayList;

/**
 * Created by brangycastro on 24/2/18.
 */

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Banco> listaBanco;

    public Adaptador(Context context, ArrayList<Banco> listaBanco) {
        this.setContext(context);
        this.setListaBanco(listaBanco);
    }

    @Override
    public int getCount() {
        return getListaBanco().size();
    }

    @Override
    public Object getItem(int position) {
        return getListaBanco().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.display_banco, null);
        }
        TextView textoCedula = (TextView)convertView.findViewById(R.id.lblCedula);
        TextView textoNombre = (TextView)convertView.findViewById(R.id.lblNombre);
        TextView textoSaldo = (TextView)convertView.findViewById(R.id.lblSaldo);
        Banco banco = listaBanco.get(position);
        textoCedula.setText(banco.cedula);
        textoNombre.setText(banco.nombre);
        textoSaldo.setText(banco.saldo);
        return convertView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Banco> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(ArrayList<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }
}
