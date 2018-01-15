package clientgit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sascha
 */
public class ClientGit extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/clientgit/Login.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
