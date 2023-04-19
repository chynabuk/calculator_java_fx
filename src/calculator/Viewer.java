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

   private final String btnBlackWhiteStyle = "-fx-background-color: #000000; -fx-text-fill: #ffffff";
   private final String btnBlackWhiteHoverStyle = "-fx-background-color: #ffffff; -fx-text-fill: #000000";
   private final String btnBlackOrangeStyle = "-fx-background-color: #000000; -fx-text-fill: #f58e11";
   private final String btnBlackOrangeHoverStyle = "-fx-background-color: #f58e11; -fx-text-fill: #000000";

   public void start(Stage stage) {
       Controller controller = new Controller(this);
       Text scenetitle = new Text("Simple Calculator");
       scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 35));
   
       textField = new TextField("");
       textField.setPrefSize(100, 200);
       textField.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
       textField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff");
       textField.setEditable(false);
       textField.setAlignment(Pos.CENTER_RIGHT);
       textField.end();

       resField = new TextField("");
       resField.setPrefSize(100, 100);
       resField.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
       resField.setStyle("-fx-background-color: #000000; -fx-border-color: #f58e11; -fx-text-fill: #ffffff;");
       resField.setEditable(false);
       resField.setAlignment(Pos.CENTER);

       //init buttons
       Button[] buttons = btnInit();

       // setting style for buttons
       settingButtonStyles(buttons, controller);

       // placing buttons, textFields
       GridPane grid = new GridPane();
       grid.setAlignment(Pos.CENTER);
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(5, 10, 5, 5));
       grid.setStyle("-fx-background-color: #000000");

       grid.add(textField, 1, 0, 4, 1);
       grid.add(resField, 1, 1, 4, 1);

       addingButtonsToGrid(buttons, grid);

       Scene scene = new Scene(grid, 540, 850);
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
       textField.end();
   }

   public void updateRes(String res){
       System.out.println("====================");
       System.out.println(res);
       resField.setText("= " + res);
   }

   public void setResFontSize(int size){
       resField.setFont(Font.font("Arial", FontWeight.NORMAL, size));
   }

   private Button[] btnInit(){
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
       Button buttonLog = new Button("log");
       Button buttonSin = new Button("sin");
       Button buttonCos = new Button("cos");
       Button buttonTg = new Button("tg");
       Button buttonCtg = new Button("ctg");

       return new Button[]{
               buttonSin, buttonCos, buttonTg, buttonCtg,
               buttonLeftBracket, buttonRightBracket, buttonSqrt, buttonLog,
               buttonClear, buttonDelete, buttonSquare, buttonDivision,
               buttonSeven, buttonEight, buttonNine, buttonMultiplication,
               buttonFour, buttonFive, buttonSix, buttonMinus,
               buttonOne, buttonTwo, buttonThree, buttonPlus,
               buttonNegAndPos, buttonZero, buttonPoint, buttonCalculate
       };
   }

   private void settingButtonStyles(Button[] buttons, Controller controller){
       for (Button button : buttons) {
           button.setPrefSize(150, 150);
           button.setFont(Font.font("Arial", FontWeight.NORMAL, 27));

           String buttonText = button.getText();
           if (
                   buttonText.equals("9") || buttonText.equals("8") || buttonText.equals("7")
                   || buttonText.equals("6") || buttonText.equals("5") || buttonText.equals("4")
                   || buttonText.equals("3") || buttonText.equals("2") || buttonText.equals("1")
                   || buttonText.equals("0") || buttonText.equals(".")
           ) {
               button.setStyle(btnBlackWhiteStyle);
               button.setOnMouseEntered(e -> button.setStyle(btnBlackWhiteHoverStyle));
               button.setOnMouseExited(e -> button.setStyle(btnBlackWhiteStyle));
           }
           else {
               button.setStyle(btnBlackOrangeStyle);
               button.setOnMouseEntered(e -> button.setStyle(btnBlackOrangeHoverStyle));
               button.setOnMouseExited(e -> button.setStyle(btnBlackOrangeStyle));
           }
           button.setOnAction(controller);
       }
   }

   private void addingButtonsToGrid(Button[] buttons, GridPane grid){
       int row = 2;
       int column = 1;
       for (int i = 0; i < buttons.length; i++){
           grid.add(buttons[i], column, row);
           if (++column == 5){
               row++;
               column = 1;
           }
       }
   }
}

