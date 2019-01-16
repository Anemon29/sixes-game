package pl.edu.agh.sixes.controller.util;

import javafx.scene.image.Image;
import pl.edu.agh.sixes.model.Card;

public class ImageProvider {

    public Image getCardImage(Card card) {
        String imagePath;
        if (card == null) {
            imagePath = "/cards/PNG/empty.png";
        } else {
            imagePath = "/cards/PNG/" + card.toString() + ".png";
        }
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public Image getCardBack() {
        return new Image(getClass().getResourceAsStream("/cards/PNG/purple_back.png"));
    }

    public Image getSuitImage(Card.Suit suit) {
        if(suit == null){
            return null;
        }
        return new Image(getClass().getResourceAsStream("/suits/" + suit + ".png"));
    }
}
