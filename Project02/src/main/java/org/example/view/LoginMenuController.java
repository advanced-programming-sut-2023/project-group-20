package org.example.view;

import javafx.scene.input.MouseEvent;

public class LoginMenuController {

    public void back(MouseEvent mouseEvent) throws Exception {
        Main main=new Main();
        main.start(Main.getStage());
    }
}
