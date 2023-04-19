package calculator;

public class Command {
   private Viewer viewer;
   private String value = "";

   public Command(Viewer viewer) {
       this.viewer = viewer;
   }

   public void delete(){
       if (value.length() > 0) {
           value = value.substring(0, value.length() - 1);
           viewer.update(value);
       }
   }

   public void clear(){
       value = "";
       viewer.update(value);
   }

   public void number(String n){
       value += n;
       viewer.update(value);
   }

   public void point(){
       value += ".";
       viewer.update(value);
   }

   public void arithMeticOperations(String o){
       value += " " + o + " ";
       viewer.update(value);
   }

   public void square(){
       value += " ^2 ";
       viewer.update(value);
   }

   public void convertToNegOrPos(){
       if (value.length() >= 1) {
           String[] valArray = value.split(" ");
           int lastIndex = valArray.length - 1;
           String n = valArray[lastIndex];
           if (Double.parseDouble(n) > 0) {
               valArray[lastIndex] = "-" + n;
           }
           else if(Double.parseDouble(n) < 0){
               valArray[lastIndex] = valArray[lastIndex].split("-")[1];
           }
           value = String.join(" ", valArray);
           viewer.update(value); 
       }
   }

   public void leftBracket(){
       value += " ( ";
       viewer.update(value);
   }

   public void rightBracket(){
       value += " ) ";
       viewer.update(value);
   }

   public void sqrt(){
       value += " √ ";
       viewer.update(value);
   }

   public void percent(){
       value += " % ";
       viewer.update(value);
   }

   public void calculate() {
       if(value.length() > 1){
           PolishNotation pn = new PolishNotation();
           String result = pn.rpnExpressionToAnswer(value);
           if (result.equals("Infinity")){
               viewer.setResFontSize(20);
               viewer.updateRes("Деление на ноль невозможно");
           }
           else {
               viewer.setResFontSize(40);
               viewer.updateRes(result);
           }
       }
   }
}



