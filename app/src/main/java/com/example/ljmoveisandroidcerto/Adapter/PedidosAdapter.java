package com.example.ljmoveisandroidcerto.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ljmoveisandroidcerto.databinding.ItemListRowPedidosBinding;

import java.util.List;

import modelDominio.Pedido;


public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.MyViewHolder> {
    private List<Pedido> listaPedidos;
    private PedidoOnClickListener pedidoOnClickListener;

    public PedidosAdapter(List<Pedido> listaPedidos, PedidoOnClickListener pedidoOnClickListener) {
        this.listaPedidos = listaPedidos;
        this.pedidoOnClickListener = pedidoOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListRowPedidosBinding itemListRowPedidosBinding = ItemListRowPedidosBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemListRowPedidosBinding);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Pedido pedido = listaPedidos.get(position);
        holder.itemListRowPedidosBinding.tvVisualizarNomePedido.setText(pedido.getNomePedido());
        holder.itemListRowPedidosBinding.tvVisualizarCorPedido.setText(pedido.getCor());
        holder.itemListRowPedidosBinding.tvVisualizarTexturaPedido.setText(pedido.getTextura());
        holder.itemListRowPedidosBinding.tvVisualizarDecricaoPedido.setText(pedido.getDescricao());
        holder.itemListRowPedidosBinding.tvVisualizarValorPedido.setText((int) pedido.getValor());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // tratando o clique no item
        if (pedidoOnClickListener != null) {
            holder.itemListRowPedidosBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PedidoOnClickListener.onClickPedido(holder.itemView, position, pedido);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemListRowPedidosBinding itemListRowPedidosBinding;

        public MyViewHolder(ItemListRowPedidosBinding itemListRowPedidosBinding) {
            super(itemListRowPedidosBinding.getRoot());
            this.itemListRowPedidosBinding = itemListRowPedidosBinding;
        }
    }

    public interface PedidoOnClickListener {
        public static void onClickPedido(View view, int position, Pedido pedido) {

        }


    }
}