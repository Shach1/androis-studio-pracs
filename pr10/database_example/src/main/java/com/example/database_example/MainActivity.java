package com.example.database_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.name_input);
        EditText phoneInput = findViewById(R.id.phone_input);
        Button saveButton = findViewById(R.id.save_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button findButton = findViewById(R.id.find_button);
        Button updateButton = findViewById(R.id.update_button);

        RecyclerView contactsList = findViewById(R.id.contacts_list);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        List<Contact> contacts = dbHelper.getAllContacts();
        ContactAdapter adapter = new ContactAdapter(contacts);
        contactsList.setLayoutManager(new LinearLayoutManager(this));
        contactsList.setAdapter(adapter);

        //Прописываем логику для сохранения нового контакта
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String phone = phoneInput.getText().toString();
            if (name.isEmpty() || phone.isEmpty())
            {
                Toast.makeText(this, "Please enter name and phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dbHelper.addContact(new Contact(0, name, phone)))
            {
                contacts.add(new Contact(0, name, phone));
                adapter.notifyItemInserted(contacts.size() - 1);
                Toast.makeText(this, "Contact saved successfully!", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, "Failed to save contact", Toast.LENGTH_SHORT).show();
            }
        });

        //удаляем контакт
        deleteButton.setOnClickListener(v -> {
            String phone = phoneInput.getText().toString();
            if (phone.isEmpty())
            {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dbHelper.deleteContact(phone)) {
                refreshContactsList(dbHelper, contacts, adapter, contactsList);
                Toast.makeText(this, "Contact deleted successfully!", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, "Failed to delete contact", Toast.LENGTH_SHORT).show();
            }
        });

        //ищем контакт по номеру телефона
        findButton.setOnClickListener(v -> {
            String phone = phoneInput.getText().toString();
            if (phone.isEmpty())
            {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            Contact foundContact = dbHelper.findContact(phone);
            if (foundContact != null) {
                nameInput.setText(foundContact.getName());
                phoneInput.setText(foundContact.getPhoneNumber());
                Toast.makeText(this, "Contact found: " + foundContact.getName(), Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show();
            }
        });

        //обновляем данные

        updateButton.setOnClickListener(v -> {
            String phone = phoneInput.getText().toString(); //Считаем что это старый номер для поиска
            String newName = nameInput.getText().toString(); //Новое имя для обновления
            if (phone.isEmpty() || newName.isEmpty())
            {
                Toast.makeText(this, "Please enter phone number and new name", Toast.LENGTH_SHORT).show();
                return;
            }

            Contact foundContact = dbHelper.findContact(phone);
            if (foundContact == null) {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show();
                return;
            }
            contacts.remove(foundContact);

            if (dbHelper.updateContact(phone, newName)) {
                Toast.makeText(this, "Contact updated successfully!", Toast.LENGTH_SHORT).show();

                // Обновляем список и адаптер
                refreshContactsList(dbHelper, contacts, adapter, contactsList);
            } else
            {
                Toast.makeText(this, "Failed to update contact",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Метод для обновления списка контактов после изменения в базе данных
    private void refreshContactsList(DatabaseHelper dbHelper, List<Contact> contacts, ContactAdapter adapter, RecyclerView contactsList)
    {
        contacts = dbHelper.getAllContacts(); // Загружаем обновленный список
        adapter = new ContactAdapter(contacts);
        contactsList.setAdapter(adapter);
    }

}