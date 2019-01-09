package pl.edu.agh.sixes.controller.util;

import javafx.scene.image.Image;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.Objects;

public class ImageProvider {

    public Image getCardImage(CardContainer cardContainer){
        String imagePath;
        if (cardContainer.getContent().isPresent()) {
            imagePath = "/cards/PNG/" + cardContainer.getContent().get().toString() + ".png";
            }
        else {
            imagePath="/cards/PNG/empty.png";
        }
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public Image getCardBack(){
        return new Image(getClass().getResourceAsStream("/cards/PNG/purple_back.png"));
    }
    public Image getForwardButtonImage(){
        return new Image(getClass().getResourceAsStream("/buttons/forward_button.png"));
    }
    public Image getReversButtonImage(){
        return new Image(getClass().getResourceAsStream("/buttons/revers_button.png"));
    }



}
