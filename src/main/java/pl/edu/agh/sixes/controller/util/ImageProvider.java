package pl.edu.agh.sixes.controller.util;

import javafx.scene.image.Image;

public class ImageProvider {

    public Image getCardImage(String path){
        return new Image(getClass().getResourceAsStream(path));
    }

}
