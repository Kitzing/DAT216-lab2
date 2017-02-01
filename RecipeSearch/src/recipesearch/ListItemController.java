package recipesearch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.lab2.*;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by madeleine on 2017-02-01.
 */
public class ListItemController implements Initializable {

    RecipeDatabase db = RecipeDatabase.getSharedInstance();

    @FXML
    private ListView<Recipe> listView;

    private ObservableList<Recipe> recipeObservableList;

    public ListItemController()  {

        recipeObservableList = FXCollections.observableArrayList();

        //add some Students
        recipeObservableList.addAll(
                new Student("John Doe", Student.GENDER.MALE),
                new Student("Jane Doe", Student.GENDER.FEMALE),
                new Student("Donte Dunigan", Student.GENDER.MALE),
                new Student("Gavin Genna", Student.GENDER.MALE),
                new Student("Darin Dear", Student.GENDER.MALE),
                new Student("Pura Petty", Student.GENDER.FEMALE),
                new Student("Herma Hines", Student.GENDER.FEMALE)
        );


    }


    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private FontAwesomeIconView fxIconGender;

    @FXML
    private GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(recipeObservableList);
        listView.setCellFactory(recipeListView -> new recipeListViewCell());
    }


    @Override
    protected void updateItem(Recipe recipe, boolean empty) {
        super.updateItem(recipe, empty);

        if(empty || recipe == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(String.valueOf(recipe.getStudentId()));
            label2.setText(recipe.getName());

            if(student.getGender().equals(Student.GENDER.MALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.MARS);
            } else if(student.getGender().equals(Student.GENDER.FEMALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.VENUS);
            } else {
                fxIconGender.setIcon(FontAwesomeIcon.GENDERLESS);
            }

            setText(null);
            setGraphic(gridPane);
        }

    }
}
