package com.example.database_example;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
{
    private final List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount()
    {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView nameTextView;
        private final TextView phoneTextView;
        public ViewHolder(View view)
        {
            super(view);
            nameTextView = view.findViewById(R.id.contact_name);
            phoneTextView = view.findViewById(R.id.contact_phone);
        }
        public void bind(Contact contact)
        {
            nameTextView.setText(contact.getName());
            phoneTextView.setText(contact.getPhoneNumber());
        }
    }


}
