package com.example.pr5.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.R;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>
{
    private List<State> states;

    public StateAdapter(List<State> states)
    {
        this.states = states;
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position)
    {
        State state = states.get(position);
        holder.nameView.setText(state.getName());
        holder.capitalView.setText(state.getCapital());
        holder.flagView.setImageResource(state.getFlagResource());
    }

    @Override
    public int getItemCount()
    {
        return states.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameView, capitalView;
        ImageView flagView;
        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.capital);
            flagView = view.findViewById(R.id.flag);
        }
    }

    //вариант адаптера для ListView
/*
    private LayoutInflater inflater;
    private int layout;
    private List<State> states;

    public StateAdapter(Context context, int resource, List<State> states)
    {
        super(context, resource, states);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.states = states;
    }
*/


    // без оптимизации
/*
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = inflater.inflate(layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }
*/

    // с оптимизацией
/*
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        //Проверяем, создавалась ли разметка для этого элемента ранее
        if(convertView == null)
        {
            convertView = inflater.inflate(this.layout, parent, false);
            //создаем объект ViewHolder, который сохраняем в тег в convertView
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            //получаем ViewHolder из тега
            viewHolder = (ViewHolder) convertView.getTag();
        }

        State state = states.get(position);
        //во ViewHolder устанавливаются значения из объекта
        viewHolder.flagView.setImageResource(state.getFlagResource());
        viewHolder.nameView.setText(state.getName());
        viewHolder.capitalView.setText(state.getCapital());

        return convertView;
    }

    private class ViewHolder
    {
        final TextView nameView, capitalView;
        final ImageView flagView;
        ViewHolder(View view)
        {
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.capital);
            flagView = view.findViewById(R.id.flag);
        }
    }
*/

}
