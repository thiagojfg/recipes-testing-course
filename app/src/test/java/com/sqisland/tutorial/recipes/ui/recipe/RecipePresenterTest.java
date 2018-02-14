package com.sqisland.tutorial.recipes.ui.recipe;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;
import com.sqisland.tutorial.recipes.data.model.RecipeTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.InputStream;

import static org.junit.Assert.*;

public class RecipePresenterTest {
    private RecipeStore store;
    private Favorites favorites;
    private RecipeContract.View view;
    private RecipePresenter presenter;

    @Before
    public void setUp(){
        store = Mockito.mock(RecipeStore.class);
        favorites = Mockito.mock(Favorites.class);
        view = Mockito.mock(RecipeContract.View.class);
        presenter = new RecipePresenter(store, view, favorites);
    }

    @Test
    public void recipeNotFound(){
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("no_such_recipe");
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }

    @Test(expected = IllegalStateException.class)
    public void toogleWithoutLoad(){
        presenter.toogleFavorite();
    }

    @Test
    public void loadWaterAndFavorite(){

        InputStream stream = RecipePresenterTest.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(stream);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(recipe);
        Mockito.when(favorites.toogle(Mockito.anyString())).thenReturn(true);

        presenter.loadRecipe("water");
        presenter.toogleFavorite();

        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view, Mockito.times(2)).setFavorite(captor.capture());
        assertFalse(captor.getAllValues().get(0));
        assertTrue(captor.getAllValues().get(1));
    }
}