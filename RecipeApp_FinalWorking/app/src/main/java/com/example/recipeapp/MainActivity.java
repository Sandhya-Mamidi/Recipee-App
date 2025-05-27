package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addRecipeButton = findViewById(R.id.btnAddRecipe);
        Button viewRecipesButton = findViewById(R.id.btnViewRecipes);

        addRecipeButton.setOnClickListener(v -> startActivity(new Intent(this, AddRecipeActivity.class)));
        viewRecipesButton.setOnClickListener(v -> startActivity(new Intent(this, RecipeListActivity.class)));
    }
}
