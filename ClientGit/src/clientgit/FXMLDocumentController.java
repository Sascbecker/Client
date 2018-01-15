
package clientgit;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sascha
 */
public class FXMLDocumentController  {
    
    @FXML
    private Label lblstatus;
    @FXML
    private TextField txtpassword;
    @FXML
    private TextField txtusername;
    
       
    public void Login (ActionEvent event)throws Exception{
            if(txtusername.getText().equals("user")&& txtpassword.getText().equals("pass")){
                lblstatus.setText("login Success");
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/clientgit/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
            }else {
                 lblstatus.setText("Login Failed");
            }
    
    
    }
}