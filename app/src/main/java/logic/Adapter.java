package logic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.victor.inventario.Objeto_detalle;
import com.victor.inventario.R;

import java.util.ArrayList;
import java.util.List;

import model.Data;
import model.Objeto;

public class Adapter extends RecyclerView.Adapter<Adapter.HolderObjeto> {

    Context context;
    public static int iCont = 0;
    public static List<Objeto> listaObjetos;

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HolderObjeto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tarjeta, parent, false);
        return new HolderObjeto(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderObjeto holder, int position) {

        holder.txtNombreProd.setText(listaObjetos.get(position).getNOMBRE());
        Uri img = Uri.parse(Data.HOST + "/inventario/imagenes/" + listaObjetos.get(position).getID_OBJETO() + ".png");

        Glide.with(context).load(img).placeholder(R.drawable.inventario).into(holder.imgObjeto);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCont = position;
                Intent Objeto_detalle = new Intent(context, Objeto_detalle.class);
                Objeto_detalle.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Objeto_detalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaObjetos.size();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public class HolderObjeto extends RecyclerView.ViewHolder {
        CardView card;
        TextView txtNombreProd;
        ImageView imgObjeto;

        public HolderObjeto(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            txtNombreProd = itemView.findViewById(R.id.txtNombreProd);
            imgObjeto = itemView.findViewById(R.id.imgObjeto);
        }
    }
}
