import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QRGenerator extends Application {

	public static void main(String[] args) {
        launch(args);
    }
    
	Group group = new Group();
	Group qrGroup = new Group();
	int max = 6; // rnd max for rect generation
    @Override
    public void start(Stage primaryStage) {        
        group.getChildren().add(qrGroup);
    	newQR();
        Button generateButton = new Button("Generate");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newQR();
            }
        });
        
        // SAVE QR CODE
        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    FileChooser fileChooser = new FileChooser();
                    
                    FileChooser.ExtensionFilter extFilter = 
                            new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                    fileChooser.getExtensionFilters().add(extFilter);
                   
                    File file = fileChooser.showSaveDialog(primaryStage);
                     
                    if(file != null){
                        try {
                            WritableImage writableImage = new WritableImage(512, 512);
                            qrGroup.snapshot(null, writableImage);
                            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                            ImageIO.write(renderedImage, "png", file);
                        } catch (IOException ex) {
                            
                    }
                }
            }
        });
        generateButton.setLayoutX(250);
        generateButton.setLayoutY(540);
        saveButton.setLayoutX(200);
        saveButton.setLayoutY(540);
        group.getChildren().addAll(saveButton, generateButton);

        Scene scene = new Scene(group, 512, 572, Color.WHITE);
        primaryStage.setTitle("QR Code");
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
    
    public void newQR() {
        qrGroup.getChildren().clear();
    	
    	
        // Big three rectangles and inner rectangles
        Rectangle rect1 = new Rectangle(10, 10, 80, 80);
        rect1.setFill(Color.TRANSPARENT);
        rect1.setStroke(Color.BLACK);
        rect1.setStrokeWidth(10);
        Rectangle rect1_inner = new Rectangle(30, 30, 40, 40);
        rect1_inner.setStroke(Color.BLACK);
        rect1_inner.setStrokeWidth(10);
        
        Rectangle rect2 = new Rectangle(420, 10, 80, 80);
        rect2.setFill(Color.TRANSPARENT);
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeWidth(10);
        Rectangle rect2_inner = new Rectangle(440, 30, 40, 40);
        rect2_inner.setStroke(Color.BLACK);
        rect2_inner.setStrokeWidth(10);
        
        Rectangle rect3 = new Rectangle(10, 420, 80, 80);
        rect3.setFill(Color.TRANSPARENT);
        rect3.setStroke(Color.BLACK);
        rect3.setStrokeWidth(10);
        Rectangle rect3_inner = new Rectangle(30, 440, 40, 40);
        rect3_inner.setStroke(Color.BLACK);
        rect3_inner.setStrokeWidth(10);
        qrGroup.getChildren().addAll(rect1, rect1_inner, rect2, rect2_inner, rect3, rect3_inner);
        
        
        // Generate the rest of the QR Code without overlapping the big three
        // From 100 to 480
        for (int i = 100; i<=480; i+=10) {
            for (int j = 100; j<=480; j+=10) {
            	int rnd =new Random().nextInt(max);
            	if(rnd == 0) {
            		Rectangle r = new Rectangle(i,j,20,20);
            		qrGroup.getChildren().add(r);
            	}
            }
        }
        // Upper section
        // X from 100 to 390 | Y from 10 to 90
        for (int i = 100; i<=390; i+=10) {
            for (int j = 10; j<=90; j+=10) {
            	int rnd =new Random().nextInt(max);
            	if(rnd == 0) {
            		Rectangle r = new Rectangle(i,j,20,20);
            		qrGroup.getChildren().add(r);
            	}
            }
        }
        // Left section
        // X from 10 to 90 | Y from 100 to 390
        for (int i = 10; i<=90; i+=10) {
            for (int j = 100; j<=390; j+=10) {
            	int rnd =new Random().nextInt(max);
            	if(rnd == 0) {
            		Rectangle r = new Rectangle(i,j,20,20);
            		qrGroup.getChildren().add(r);
            	}
            }
        }
    }
}
