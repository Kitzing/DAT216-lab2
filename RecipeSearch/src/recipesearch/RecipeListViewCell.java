package recipesearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.lab2.Recipe;

import java.io.IOException;

/**
 * Created by madeleine on 2017-02-01.
 */

public class RecipeListViewCell extends ListCell<Recipe>{


    @FXML
    private Label heading;

    @FXML
    private  Label description;

    @FXML
    private Label time;

    @FXML
    private ImageView recipeImage;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    private RecipeSearchController controller;

    private Recipe recipe1;


    protected RecipeListViewCell(RecipeSearchController controller){
        this.controller = controller;

    }

    @Override
    protected void updateItem(Recipe recipe, boolean empty) {
        super.updateItem(recipe, empty);

        recipe1 = recipe;

        if(empty || recipe == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ResultList.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            String name = recipe.getName().replace('-', ' ');
            heading.setText(name);
            description.setText(recipe.getDescription());
            time.setText(recipe.getTime() + " min");
            recipeImage.setImage(recipe.getFXImage(200, 150));

            setText(null);
            setGraphic(anchorPane);
        }

    }


    @FXML protected void cellClickedActionPerformed(ActionEvent event){
        controller.openRecipe(recipe1);
    }


}
