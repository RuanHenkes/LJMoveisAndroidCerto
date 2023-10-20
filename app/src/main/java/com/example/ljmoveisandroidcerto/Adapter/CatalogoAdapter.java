package com.example.ljmoveisandroidcerto.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ljmoveisandroidcerto.databinding.ItemListRowCatalogoBinding;

import java.util.List;

import modelDominio.Catalogo;


public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.MyViewHolder>{
    private List<Catalogo> listaCatalogos;
    private CatalogoOnClickListener catalogoOnClickListener;

    public CatalogoAdapter(List<Catalogo> listaCatalogos, CatalogoOnClickListener catalogoOnClickListener) {
        this.listaCatalogos = listaCatalogos;
        this.catalogoOnClickListener = catalogoOnClickListener;
    }

    @Override
    public CatalogoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListRowCatalogoBinding itemListRowBinding = ItemListRowCatalogoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemListRowBinding);
    }


    @Override
    public void onBindViewHolder(final CatalogoAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Catalogo catalogo = listaCatalogos.get(position);
//        holder.itemListRowCatalogoBinding.ivImagemCatalogo.setImageURI(catalogo.getImagem());
        holder.itemListRowCatalogoBinding.tvDescricaoCatalogo.setText(catalogo.getDescricao());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // tratando o clique no item
        if (catalogoOnClickListener != null) {
            holder.itemListRowCatalogoBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CatalogoOnClickListener.onClickCatalogo(holder.itemView, position, catalogo);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaCatalogos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemListRowCatalogoBinding itemListRowCatalogoBinding;
        public MyViewHolder(ItemListRowCatalogoBinding itemListRowBinding) {
            super(itemListRowBinding.getRoot());
            this.itemListRowCatalogoBinding = itemListRowBinding;
        }
    }

    public interface CatalogoOnClickListener {
        public static void onClickCatalogo(View view, int position, Catalogo catalogo) {

        }
    }

}

