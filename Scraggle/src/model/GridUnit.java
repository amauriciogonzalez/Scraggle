package model;

import dictionary.Alphabet;
import javafx.scene.image.Image;

import java.util.Objects;

/* While GridPoint stores locational data for each tile, GridUnit stores
   general data such as the letter (from Alphabet), the x-y location in the
   grid (from GridPoint), and the image file path location (from String). */
public class GridUnit 
{
  private final Alphabet alphabet;
  private final GridPoint gridPoint;
  private final String imgPath;
  private Image img;

  /* GridUnit stores general data such as the letter (from Alphabet), the
     x-y location in the grid (from GridPoint), and the image file path
     location (from String). */
  public GridUnit(Alphabet alphabet, GridPoint gridPoint) 
  {
        this.alphabet = alphabet;
        this.gridPoint = gridPoint;
        /* We navigate to the "images" package folder by moving one directory
           up through "../" then to images by "images/". We access the letter
           needed by this.alphabet.get() + ".png". */
        this.imgPath = "../images/" + this.alphabet.get() + ".png";
  }

  /* This method retrieves the letter from the current GridUnit and turns
     it uppercase. */
  public String getLetter() 
  {
        return alphabet.get().toUpperCase();
  }

  /* This method retrieves the score from the current GridUnit's letter. */
  public int getScore() 
  {
    return alphabet.getScore();
  }

  /* This method retrieves the x-y coordinates from the current GridUnit. */
  public GridPoint getPoint() 
  {
        return gridPoint;
  }

  /* This method retrieves the image of the current GridUnit's letter
     by using the image's filepath. */
  public Image getImage() 
  {
    if (this.img == null) 
    {
        String path = getClass().getResource(this.imgPath).toExternalForm();
        this.img = new Image(path);
    }

    return this.img;
  }
  
  /* This method is a getter for the attribute imgPath. */
  public String getImgPath()
  {
      return this.imgPath;
  }

    /* If the GridUnit is equal to the object, we return true. */
    @Override
    public boolean equals(Object object) 
    {
        if (object instanceof GridUnit) 
        {
            GridUnit unit = (GridUnit) object;
            return this.alphabet == unit.alphabet && this.gridPoint.equals(unit.gridPoint);
        }

        return false;
    }

    @Override
    public int hashCode() 
    {
          return Objects.hash(alphabet, gridPoint);
    }
}
