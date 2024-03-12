package CALabTests;

import CALab.Cell;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class GridCellTests {
    @Test
    public void testNeighborsCorner() {
        ConcreteGrid grid = new ConcreteGrid();
        assertEquals(20, grid.getDim());

        ConcreteCell temp = (ConcreteCell) grid.getCell(0, 0);
        assertEquals(0, temp.getRow());
        assertEquals(0, temp.getCol());

        Set<Cell> neighbors = temp.getNeighbors();

        // make sure we didn't neighbor ourself
        assertFalse(neighbors.contains(temp));

        //make sure we have all 8 items
        assertEquals(8, neighbors.size());
        assertTrue(neighbors.contains(grid.getCell(0, 19)));  // item on left
        assertTrue(neighbors.contains(grid.getCell(0, 1)));  // item on right
        assertTrue(neighbors.contains(grid.getCell(1, 0)));  // item below
        assertTrue(neighbors.contains(grid.getCell(19, 0)));  // item above
        assertTrue(neighbors.contains(grid.getCell(1, 19)));  // item below left
        assertTrue(neighbors.contains(grid.getCell(1, 1)));  // item below right
        assertTrue(neighbors.contains(grid.getCell(19, 19)));  // item above left
        assertTrue(neighbors.contains(grid.getCell(19, 1)));  // item above right
    }

    @Test
    public void testNeighborsCornerLarger() {
        ConcreteGrid grid = new ConcreteGrid(50);
        assertEquals(50, grid.getDim());

        ConcreteCell temp = (ConcreteCell) grid.getCell(0, 0);
        assertEquals(0, temp.getRow());
        assertEquals(0, temp.getCol());

        Set<Cell> neighbors = temp.getNeighbors();

        // make sure we didn't neighbor ourself
        assertFalse(neighbors.contains(temp));

        //make sure we have all 8 items
        assertEquals(8, neighbors.size());
        assertTrue(neighbors.contains(grid.getCell(0, 49)));  // item on left
        assertTrue(neighbors.contains(grid.getCell(0, 1)));  // item on right
        assertTrue(neighbors.contains(grid.getCell(1, 0)));  // item below
        assertTrue(neighbors.contains(grid.getCell(49, 0)));  // item above
        assertTrue(neighbors.contains(grid.getCell(1, 49)));  // item below left
        assertTrue(neighbors.contains(grid.getCell(1, 1)));  // item below right
        assertTrue(neighbors.contains(grid.getCell(49, 49)));  // item above left
        assertTrue(neighbors.contains(grid.getCell(49, 1)));  // item above right
    }

    @Test
    public void testNeighborsRegular() {
        ConcreteGrid grid = new ConcreteGrid();

        ConcreteCell temp = (ConcreteCell) grid.getCell(5, 5);
        Set<Cell> neighbors = temp.getNeighbors();

        // make sure we didn't neighbor ourself
        assertFalse(neighbors.contains(temp));


        assertEquals(8, neighbors.size());
        assertTrue(neighbors.contains(grid.getCell(5, 4)));  // item on left
        assertTrue(neighbors.contains(grid.getCell(5, 6)));  // item on right
        assertTrue(neighbors.contains(grid.getCell(4, 5)));  // item below
        assertTrue(neighbors.contains(grid.getCell(6, 5)));  // item above
        assertTrue(neighbors.contains(grid.getCell(6, 4)));  // item below left
        assertTrue(neighbors.contains(grid.getCell(6, 6)));  // item below right
        assertTrue(neighbors.contains(grid.getCell(4, 4)));  // item above left
        assertTrue(neighbors.contains(grid.getCell(4, 6)));  // item above right
    }

    @Test
    public void testGetPartnerFull() {
        ConcreteGrid grid = new ConcreteGrid();

        // fill area around 1, 1
        ConcreteCell temp = (ConcreteCell) grid.getCell(0, 0);
        temp.setPartner((ConcreteCell) grid.getCell(0, 1));
        temp = (ConcreteCell) grid.getCell(0, 2);
        temp.setPartner((ConcreteCell) grid.getCell(1, 2));
        temp = (ConcreteCell) grid.getCell(2, 2);
        temp.setPartner((ConcreteCell) grid.getCell(2, 1));
        temp = (ConcreteCell) grid.getCell(1, 0);
        temp.setPartner((ConcreteCell) grid.getCell(2, 0));

        // this should remain null
        temp = (ConcreteCell) grid.getCell(1, 1);
        assertNull(temp.getPartner());
        temp.choosePartner();  // should remain null since no options
        assertNull(temp.getPartner());
    }

    @Test
    public void testGetPartnerOne() {
        ConcreteGrid grid = new ConcreteGrid();

        // fill area around (1, 1), except for (1, 2)
        ConcreteCell temp = (ConcreteCell) grid.getCell(0, 0);
        temp.setPartner((ConcreteCell) grid.getCell(0, 1));
        temp = (ConcreteCell) grid.getCell(0, 2);
        temp.setPartner((ConcreteCell) grid.getCell(0, 3));
        temp = (ConcreteCell) grid.getCell(2, 2);
        temp.setPartner((ConcreteCell) grid.getCell(2, 1));
        temp = (ConcreteCell) grid.getCell(1, 0);
        temp.setPartner((ConcreteCell) grid.getCell(2, 0));

        // this should remain null
        temp = (ConcreteCell) grid.getCell(1, 1);
        assertNull(temp.getPartner());
        assertNull(((ConcreteCell) grid.getCell(1, 2)).getPartner());
        temp.choosePartner();  // should be (1, 2) since there are no other options
        assertEquals(grid.getCell(1, 2), temp.getPartner());
        assertEquals(temp, ((ConcreteCell) grid.getCell(1, 2)).getPartner());

        grid.getCell(1, 2).unpartner();
        assertNull(temp.getPartner());
        assertNull(((ConcreteCell) grid.getCell(1, 2)).getPartner());
    }

    @Test
    public void testRepopulate() {
        ConcreteGrid grid = new ConcreteGrid();

        // fill area around 1, 1
        ConcreteCell temp = (ConcreteCell) grid.getCell(0, 0);
        temp.setPartner((ConcreteCell) grid.getCell(0, 1));
        temp = (ConcreteCell) grid.getCell(0, 2);
        temp.setPartner((ConcreteCell) grid.getCell(1, 2));
        temp = (ConcreteCell) grid.getCell(2, 2);
        temp.setPartner((ConcreteCell) grid.getCell(2, 1));
        temp = (ConcreteCell) grid.getCell(1, 0);
        temp.setPartner((ConcreteCell) grid.getCell(2, 0));

        grid.repopulate(false);
        grid.foreach((row, col) -> assertNull(((ConcreteCell) grid.getCell(row, col)).getPartner()));
    }

    @Test
    public void testUpdateLoop() {
        ConcreteGrid grid = new ConcreteGrid();

        // make sure we're calling update as many times as we say we are
        grid.updateLoop(100);
        grid.foreach((row, col) -> assertEquals(100, ((ConcreteCell) grid.getCell(row, col)).updateTimes));
        grid.foreach((row, col) -> assertEquals(100, ((ConcreteCell) grid.getCell(row, col)).interactTimes));
        // observe should be called 1 more time
        grid.foreach((row, col) -> assertEquals(101, ((ConcreteCell) grid.getCell(row, col)).observeTimes));
        assertEquals(100, grid.getTime());
    }
}
