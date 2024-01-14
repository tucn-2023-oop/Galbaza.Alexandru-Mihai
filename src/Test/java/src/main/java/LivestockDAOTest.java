package src.main.java;

import com.example.farm2.Classes.FarmerLeaderboardEntry;
import com.example.farm2.Classes.Livestock;
import com.example.farm2.DAO.LivestockDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LivestockDAOTest {
    private static LivestockDAO livestockDAO;

    @BeforeAll
    public static void setUp() {
        livestockDAO = new LivestockDAO();
    }

    @Test
    public void testGetLivestockByNameTypeAndFarmerId() {
        Livestock livestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Cow", "Livestock", 1);

        assertNotNull(livestock);
        assertEquals("Cow", livestock.getLivestockName());
        assertEquals("Livestock", livestock.getType());
        assertEquals(10, livestock.getQuantity());
        assertEquals(1, livestock.getFarmerId());
    }

    @Test
    public void testGetAllLivestockByFarmerId() {
        List<Livestock> livestockList = livestockDAO.getAllLivestockByFarmerId(1);

        assertNotNull(livestockList);
        assertFalse(livestockList.isEmpty());
        assertEquals(3, livestockList.size());
    }

    @Test
    public void testAddLivestock() {
        Livestock newLivestock = new Livestock("Pig", "Livestock", 5, 1);
        livestockDAO.addLivestock(newLivestock);

        Livestock retrievedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Pig", "Livestock", 1);

        assertNotNull(retrievedLivestock);
        assertEquals("Pig", retrievedLivestock.getLivestockName());
        assertEquals("Livestock", retrievedLivestock.getType());
        assertEquals(5, retrievedLivestock.getQuantity());
        assertEquals(1, retrievedLivestock.getFarmerId());
    }

    @Test
    public void testDeleteLivestock() {
        Livestock existingLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Chicken", "Poultry", 1);
        assertNotNull(existingLivestock);

        livestockDAO.deleteLivestock(existingLivestock.getLivestockId());

        Livestock deletedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Chicken", "Poultry", 1);
        assertNull(deletedLivestock);
    }

    @Test
    public void testAddOrUpdateLivestock() {
        Livestock existingLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Horse", "Equines", 1);
        assertNull(existingLivestock);

        Livestock newLivestock = new Livestock("Horse", "Equines", 3, 1);
        livestockDAO.addOrUpdateLivestock(newLivestock);

        Livestock updatedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Horse", "Equines", 1);
        assertNotNull(updatedLivestock);
        assertEquals(3, updatedLivestock.getQuantity());

        // Test update existing livestock
        Livestock updatedExistingLivestock = new Livestock("Horse", "Equines", 2, 1);
        livestockDAO.addOrUpdateLivestock(updatedExistingLivestock);

        Livestock reUpdatedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Horse", "Equines", 1);
        assertNotNull(reUpdatedLivestock);
        assertEquals(5, reUpdatedLivestock.getQuantity()); // 3 + 2 = 5
    }

    @Test
    public void testSellLivestock() {
        Livestock existingLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Pig", "Livestock", 1);
        assertNotNull(existingLivestock);

        int soldQuantity = 2;
        livestockDAO.sellLivestock(existingLivestock, soldQuantity);

        Livestock updatedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Pig", "Livestock", 1);
        assertNotNull(updatedLivestock);
        assertEquals(3, updatedLivestock.getQuantity()); // 5 - 2 = 3

        // Attempt to sell more than available
        int invalidSoldQuantity = 5;
        livestockDAO.sellLivestock(existingLivestock, invalidSoldQuantity);

        Livestock unchangedLivestock = livestockDAO.getLivestockByNameTypeAndFarmerId("Pig", "Livestock", 1);
        assertNotNull(unchangedLivestock);
        assertEquals(3, unchangedLivestock.getQuantity()); // Quantity should remain unchanged
    }

    @Test
    public void testGetLeaderboardData() {
        List<FarmerLeaderboardEntry> leaderboardData = livestockDAO.getLeaderboardData();

        assertNotNull(leaderboardData);
        assertFalse(leaderboardData.isEmpty());
        assertEquals(2, leaderboardData.size());
    }
}
