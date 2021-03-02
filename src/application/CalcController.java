package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CalcController {
	
	//VARIABLES
	double finalGrade;
	double desiredGrade;
	double currentGrade;
	double percentage;
    @FXML
    private TextField desireField;
    @FXML
    private Button calcButton;
    @FXML
    private TextField gradeField;
    @FXML
    private TextField percentageField;
    @FXML
    private TextArea messageField;
    
    //METHODS
    @FXML
    void calculateGrade(ActionEvent event) {
    	
    	//check if all fields are full
    	if(! desireField.getText().isEmpty()) {
    		if(isDouble(desireField.getText())) {
    			desiredGrade = Double.parseDouble(desireField.getText());
    		}
    		else {
    			desiredGrade = -2;
    		}
    	}
    	else {
    		desiredGrade = -1;
    	}
    	if(! gradeField.getText().isEmpty()) {
    		if(isDouble(gradeField.getText())) {
    			currentGrade = Double.parseDouble(gradeField.getText());
    		}
    		else {
    			currentGrade = -2;
    		}
    	}
    	else {
    		currentGrade = -1;
    	}
    	if(! percentageField.getText().isEmpty()) {
    		if(isDouble(percentageField.getText())) {
    			percentage = Double.parseDouble(percentageField.getText());
    		}
    		else {
    			percentage = -2;
    		}
    	}
    	else {
    		percentage =-1;
    	}
    	
    	//Clear the text fields
    	desireField.clear();
    	gradeField.clear();
    	percentageField.clear();
    	
    	//Move decimal point
    	percentage = percentage/100;
    	
    	//Check if they're all full and of type double
    	if((desiredGrade != -1) && (currentGrade != -1) && (percentage != -1) && (desiredGrade != -2) && (currentGrade != -2) && (percentage != -2)) {
    		System.out.println("Calculating grade");
    		//Formula F = (G - (1-w) * c)/w
    		finalGrade = (desiredGrade - ((1 - percentage) * currentGrade))/percentage;
    		System.out.println("Grade Needed: " + finalGrade);
    		messageField.setText("You will need atleast a grade of " + String.format("%.2f", finalGrade) + "% \non your final exam to score a\n" + String.format("%.2f", desiredGrade) + "% overall grade in the course");
    	}
    	//else throw error message
    	else {
    		System.out.println("Error, not all values were of type double");
    		//Empty field error message
    		if((desiredGrade == -1) || (currentGrade == -1) || (percentage == -1)) {
        		Alert mainError = new Alert(AlertType.ERROR);
        		mainError.setTitle("Error Message");
        		mainError.setHeaderText("Not all fields were filled");
        		mainError.setContentText("Please try again...");
        		mainError.showAndWait();
    		}
    		//Not of type double error message
    		else {
        		Alert mainError = new Alert(AlertType.ERROR);
        		mainError.setTitle("Error Message");
        		mainError.setHeaderText("Not all values were of type double");
        		mainError.setContentText("Please try again...");
        		mainError.showAndWait();
    		}
    	}
    }
    
    //Is double method
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
