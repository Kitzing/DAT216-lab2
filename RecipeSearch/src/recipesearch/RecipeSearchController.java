
package recipesearch;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.lab2.*;

public class RecipeSearchController implements Initializable {



    /*@FXML private MenuItem sweden;
    @FXML private MenuItem asia;
    @FXML private MenuItem india;
    @FXML private MenuItem greece;
    @FXML private MenuItem africa;
    @FXML private MenuItem france;
    @FXML private MenuItem meat;
    @FXML private MenuItem fish;
    @FXML private MenuItem chicken;
    @FXML private MenuItem vegetables;
    @FXML private MenuItem easy;
    @FXML private MenuItem medium;
    @FXML private MenuItem hard;
    @FXML private Label maxPrice;
    @FXML private MenuItem ten;
    @FXML private MenuItem twenty;
    @FXML private MenuItem thirty;
    @FXML private MenuItem fourty;
    @FXML private MenuItem fifty;
    @FXML private MenuItem sixty;
    @FXML private MenuItem seventy;
    @FXML private MenuItem eighty;
    @FXML private MenuItem ninety;
    @FXML private MenuItem hundred;
    @FXML private MenuItem hundredTen;
    @FXML private MenuItem hundredTwenty;
    @FXML private MenuItem hundredThirty;
    @FXML private MenuItem hundredFourty;
    @FXML private MenuItem hundredFifty;
    */

    @FXML private ListView searchResult;
    @FXML private MenuBar menuBar;
    @FXML private ChoiceBox<String> cusine;
    @FXML private ChoiceBox<String> mainIngredient;
    @FXML private ChoiceBox<String> maxTime;
    @FXML private ChoiceBox<String> difficulty;
    @FXML private TextField maxPrice;
    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    ObservableList<Recipe> items;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cusine.setItems(FXCollections.observableArrayList("Kök","Sverige", "Asiatiskt", "Indiskt", "Grekiskt", "Afrikanskt", "Franskt"));
        mainIngredient.setItems(FXCollections.observableArrayList("Huvudingrediens","Kött", "Fisk", "Kyckling", "Vegetariskt"));
        maxTime.setItems(FXCollections.observableArrayList("Maxtid (min)", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"));
        difficulty.setItems(FXCollections.observableArrayList("Svårighetsgrad", "Lätt", "Mellan", "Svår"));
        maxPrice.setText("0");


        cusine.getSelectionModel().selectFirst();
        mainIngredient.getSelectionModel().selectFirst();
        maxTime.getSelectionModel().selectFirst();
        difficulty.getSelectionModel().selectFirst();

        items = FXCollections.observableArrayList();
        searchResult.setItems(items);

    }

    
    @FXML 
    protected void openAboutActionPerformed(ActionEvent event) throws IOException{
    
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("recipesearch/resources/RecipeSearch");
        Parent root = FXMLLoader.load(getClass().getResource("recipe_search_about.fxml"), bundle);
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(root));
        aboutStage.setTitle(bundle.getString("about.title.text"));
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
    }



    @FXML
    protected void searchActionPreformed(ActionEvent event) throws IOException{
        String cuisineChoice = cusine.getValue();
        String mainIngredientChoice = mainIngredient.getValue();
        String maxTimeChoice = maxTime.getValue();
        String difficultyChoice = difficulty.getValue();
        int maxPriceChoice = Integer.parseInt(maxPrice.getCharacters().toString());
        int maxTimeInt;

        if(cuisineChoice.equals("Kök")){
            cuisineChoice = null;
        }

        if(mainIngredientChoice.equals("Huvudingrediens")){
            mainIngredientChoice = null;
        }

        if(maxTimeChoice.equals("Maxtid (min)")){
            maxTimeInt = 0;
        } else {
            maxTimeInt = Integer.parseInt(maxTimeChoice);
        }

        if(difficultyChoice.equals("Svårighetsgrad")){
            difficultyChoice = null;
        }


        toObservableList(difficultyChoice, maxTimeInt, cuisineChoice, maxPriceChoice, mainIngredientChoice);
    }

    protected void toObservableList(String difficultyChoice, int maxTimeInt, String cuisineChoice, int maxPriceChoice, String mainIngredientChoice){
        List<Recipe> recipes = db.search(new SearchFilter(difficultyChoice, maxTimeInt, cuisineChoice, maxPriceChoice, mainIngredientChoice));

        items.clear();
        for(int i=0; i<7; i++){
            items.add(i, recipes.get(i));
        }
    }
    
    @FXML 
    protected void closeApplicationActionPerformed(ActionEvent event) throws IOException{
        
        Stage addressBookStage = (Stage) menuBar.getScene().getWindow();
        addressBookStage.hide();
    }    
}
