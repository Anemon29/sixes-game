package pl.edu.agh.sixes.controller.util;

import javafx.scene.image.Image;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.Objects;

public class ImageProvider {

    public Image getCardImage(CardContainer cardContainer){
        String imagePath = cardContainer.getCardImagePath();
        if(Objects.isNull(imagePath)) return null;
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public Image getCardBack(){
        return new Image(getClass().getResourceAsStream("/cards/PNG/purple_back.png"));
    }

}
