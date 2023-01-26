package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* The class GridPoint represents the x-y location of a GridUnit and
   contains helper methods to check for letter location validity. For
   example, in Boggle, the letters chosen by the user must be one tile
   away. */
public class GridPoint 
{
    public final int x;
    public final int y;

    /* This list, or ordered collection, contains the neighbors of a
       GridPoint (tile). */
    private List<GridPoint> neighbors;

    /* This constructor initializes the x-y coordinates or the row
       column coordinates for the GridPoint. */
    public GridPoint(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    /* This method returns the computed neightbors of the GridPoint. */
    private List<GridPoint> computeNeighbors() 
    {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        List<GridPoint> neighbors = new ArrayList<>(8);
        
        for (int i = 0; i < dx.length; ++i) 
        {
            GridPoint n = new GridPoint(this.x + dx[i], this.y + dy[i]);
            
            if (n.isValid()) 
                neighbors.add(n);
        }

        return neighbors;
    }

    /* If the GridPoint's coordinates are valid (x and y must be between
       0 and 3) then the method returns true. */
    private boolean isValid() 
    {
        return x >= 0 && x <= 3 && y >= 0 && y <= 3;
    }

    /* This method is a getter for the attribute neighbors. If it isn't
       instantiated, then we compute them and return. */
    public List<GridPoint> getNeighbors() 
    {
        if (this.neighbors == null) 
            this.neighbors = this.computeNeighbors();
      
        return this.neighbors;
    }
    
    /* If the parameter point is a neighbor to the GridPoint, we return
       true. */
    public boolean isNeighbor(GridPoint point) 
    {
        return this.getNeighbors().contains(point);
    }

    /* If the object has the same coordinates as GridPoint, we return true. */
    @Override
    public boolean equals(Object object) 
    {
        if (object instanceof GridPoint) 
        {
            GridPoint point = (GridPoint) object;
            return this.x == point.x && this.y == point.y;
        }

        return false;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(x, y);
    }
}
