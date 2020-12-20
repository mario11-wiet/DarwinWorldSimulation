package Images;

import Constants.Constants;

import javax.swing.*;

public class ImageFactory {

    public static ImageIcon createImage(Image image) throws IllegalAccessException {
        Constants constants=new Constants();

        ImageIcon imageIcon=null;

        switch (image){
            case GrassPhoto:
                imageIcon=new ImageIcon(constants.GrassPhoto);
                break;
            case AnimalPhoto:
                imageIcon=new ImageIcon(constants.AnimalsPhoto);
                break;
            case JunglePhoto:
                imageIcon=new ImageIcon(constants.JunglePhoto);
                break;
            case MapPhoto:
                imageIcon=new ImageIcon(constants.MapPhoto);
                break;
            case BacKGroundPhoto:
                imageIcon=new ImageIcon(constants.BacKGroundPhoto);
                break;
            default:
                return null;
        }
        return imageIcon;
    }
}
