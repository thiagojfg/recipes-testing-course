package com.sqisland.tutorial.recipes.ui.recipe;

/**
 * Created by desen03 on 14/02/2018.
 */

public interface RecipeContract {

    interface View {
        void showRecipeNotFoundError();
        void setTitle(String title);
        void setDescription(String description);
        void setFavorite(boolean favorite);
    }

    interface Listener {

    }
}
