package calculator;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

 
public class Controller implements EventHandler<ActionEvent>{
   private Command command;

   public Controller(Viewer viewer) {
       command = new Command(viewer);
   }

   public void pickNumber(ActionEvent event){
       String num = ((Button)event.getSource()).getText();
       if (
               num.equals("0") || num.equals("1") || num.equals("2") ||
               num.equals("3") || num.equals("4") || num.equals("5") ||
               num.equals("6") || num.equals("7") || num.equals("8") || num.equals("9")
       ){
           command.number(num);
       }
   }

   public void pickOperation(ActionEvent event){
       String o = ((Button)event.getSource()).getText();
       if (o.equals("+") || o.equals("-") || o.equals("*") || o.equals("/")) {
           command.arithMeticOperations(o);
       }
       else if(o.equals("C")){
           command.clear();
       }
       else if(o.equals("Del")){
           command.delete();
       }
       else if(o.equals(".")){
           command.point();
       }
       else if(o.equals("x^2")){
           command.square();
       }
       else if(o.equals("+/-")){
           command.convertToNegOrPos();
       }
       else if(o.equals("=")){
           command.calculate();
       }
       else if (o.equals("(")){
           command.leftBracket();
       }
       else if (o.equals(")")){
           command.rightBracket();
       }
       else if (o.equals("√")){
           command.sqrt();
       }
       else if (o.equals("%")){
           command.percent();
       }
   }

   @Override
   public void handle(ActionEvent event) {
       pickNumber(event);
       pickOperation(event);
   }
}


