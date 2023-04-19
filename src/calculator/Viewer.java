package calculator;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

 
public class Viewer extends Application {
   private TextField textField;
   private TextField resField;

   private final String btnNumbersStyle = "-fx-background-color: #000000; -fx-text-fill: #ffffff";

   public void start(Stage stage) {
       Controller controller = new Controller(this);
       Text scenetitle = new Text("Simple Calculator");
       scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 35));
   
       textField = new TextField("");
       textField.setPrefSize(100, 150);
       textField.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
       textField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff");
       textField.setEditable(false);
       textField.setAlignment(Pos.CENTER_RIGHT);

       resField = new TextField("");
       resField.setPrefSize(100, 100);
       resField.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
       resField.setStyle("-fx-background-color: #000000; -fx-border-color: #f58e11; -fx-text-fill: #ffffff;");
       resField.setEditable(false);
       resField.setAlignment(Pos.CENTER);

       // number buttons
       Button buttonOne = new Button("1");
       Button buttonTwo = new Button("2");
       Button buttonThree = new Button("3");
       Button buttonFour = new Button("4");
       Button buttonFive = new Button("5");
       Button buttonSix = new Button("6");
       Button buttonSeven = new Button("7");
       Button buttonEight = new Button("8");
       Button buttonNine = new Button("9");
       Button buttonZero = new Button("0");
       Button buttonPlus = new Button("+");

       // operation buttons
       Button buttonMinus = new Button("-");
       Button buttonMultiplication = new Button("*");
       Button buttonCalculate = new Button("=");
       Button buttonDivision = new Button("/");
       Button buttonNegAndPos = new Button("+/-");
       Button buttonSquare = new Button("x^2");
       Button buttonPoint = new Button(".");
       Button buttonDelete = new Button("Del");
       Button buttonClear = new Button("C");
       Button buttonLeftBracket = new Button("(");
       Button buttonRightBracket = new Button(")");
       Button buttonSqrt = new Button("√");
       Button buttonPercent = new Button("%");

       Button[] buttons = {
            buttonLeftBracket, buttonRightBracket, buttonSqrt, buttonPercent,
            buttonClear, buttonDelete, buttonSquare, buttonDivision, 
            buttonSeven, buttonEight, buttonNine, buttonMultiplication, 
            buttonFour, buttonFive, buttonSix, buttonMinus, 
            buttonOne, buttonTwo, buttonThree, buttonPlus,
            buttonNegAndPos, buttonZero, buttonPoint, buttonCalculate
       };

       // setting style for buttons
       int row = 2;
       int column = 1;
       for (Button button : buttons) {
           if(column == 5){
               row++;
               column = 1;
           }
           button.setPrefSize(100, 100);
           button.setFont(Font.font("Arial", FontWeight.NORMAL, 27));
           if(row == 2 || row == 3 || column == 4 || (row == 7 && column == 1)){
               button.setStyle("-fx-background-color: #000000; -fx-text-fill: #f58e11");
           }
           else{
               button.setStyle(btnNumbersStyle);
           }
           button.setOnAction(controller);
           column++;
       }

       // placing buttons, textFields
       GridPane grid = new GridPane();
       grid.setAlignment(Pos.CENTER);
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(5, 10, 5, 5));
       grid.setStyle("-fx-background-color: #000000");

       grid.add(textField, 1, 0, 4, 1);
       grid.add(resField, 1, 1, 4, 1);

       row = 2;
       column = 1;
       for (int i = 0; i < buttons.length; i++){
           grid.add(buttons[i], column, row);
           if (++column == 5){
               row++;
               column = 1;
           }
       }

       Scene scene = new Scene(grid, 440, 800);
       stage.setTitle("Simple Calculator");
       stage.setScene(scene);
       stage.show(); 
   }

   public void go(String args[]) {
       launch(args);
   }
 
   public void update(String text) {
       System.out.println(text);
       textField.setText(text);
   }

   public void updateRes(String res){
       System.out.println("====================");
       System.out.println(res);
       resField.setText("= " + res);
   }

   public void setResFontSize(int size){
       resField.setFont(Font.font("Arial", FontWeight.NORMAL, size));
   }
}

