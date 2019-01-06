package pl.edu.agh.sixes.controller.util;

import javafx.scene.image.Image;
import java.util.Objects;

public class ImageProvider {

    public Image getCardImage(String path){
        if(Objects.isNull(path)) return null;
        return new Image(getClass().getResourceAsStream(path));
    }

}
