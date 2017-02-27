import controller.ControlHandler;
import controller.Controller;
import gui.UIMain;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelController;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage s) throws Exception {
		Controller controller = new Controller();
		s.setScene(controller.getViewController().getScene());
		s.setTitle("SLOGO");
		s.show();
	}

}
