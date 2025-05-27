package com.example.recipeapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddRecipeActivity extends AppCompatActivity {
    private EditText nameInput, categoryInput, ingredientsInput, instructionsInput;
    private Button saveButton;
    private int recipeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        nameInput = findViewById(R.id.inputName);
        categoryInput = findViewById(R.id.inputCategory);
        ingredientsInput = findViewById(R.id.inputIngredients);
        instructionsInput = findViewById(R.id.inputInstructions);
        saveButton = findViewById(R.id.btnSave);

        recipeId = getIntent().getIntExtra("RECIPE_ID", -1);

        if (recipeId != -1) {
            saveButton.setText("Update");
            RecipeDbHelper dbHelper = new RecipeDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM recipes WHERE id = ?",
                    new String[]{String.valueOf(recipeId)});
            if (cursor.moveToFirst()) {
                nameInput.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                categoryInput.setText(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                ingredientsInput.setText(cursor.getString(cursor.getColumnIndexOrThrow("ingredients")));
                instructionsInput.setText(cursor.getString(cursor.getColumnIndexOrThrow("instructions")));
            }
            cursor.close();
        }

        saveButton.setOnClickListener(v -> {
            RecipeDbHelper dbHelper = new RecipeDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", nameInput.getText().toString());
            values.put("category", categoryInput.getText().toString());
            values.put("ingredients", ingredientsInput.getText().toString());
            values.put("instructions", instructionsInput.getText().toString());

            if (recipeId == -1) {
                long newRowId = db.insert("recipes", null, values);
                Toast.makeText(this, newRowId != -1 ? "Recipe saved!" : "Error saving recipe.", Toast.LENGTH_SHORT).show();
            } else {
                int rows = db.update("recipes", values, "id = ?", new String[]{String.valueOf(recipeId)});
                Toast.makeText(this, rows > 0 ? "Recipe updated!" : "Error updating recipe.", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
