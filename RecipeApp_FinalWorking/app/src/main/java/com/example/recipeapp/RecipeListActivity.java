package com.example.recipeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        listView = findViewById(R.id.recipeListView);
        loadRecipes();
    }

    private void loadRecipes() {
        RecipeDbHelper dbHelper = new RecipeDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("recipes", null, null, null, null, null, "name ASC");

        ArrayList<String> recipeNames = new ArrayList<>();
        ArrayList<Integer> recipeIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            recipeNames.add(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            recipeIds.add(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int recipeId = recipeIds.get(position);
            Intent intent = new Intent(RecipeListActivity.this, AddRecipeActivity.class);
            intent.putExtra("RECIPE_ID", recipeId);
            startActivity(intent);
        });
    }
}
