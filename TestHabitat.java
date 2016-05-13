import java.util.ArrayList;

import org.testng.annotations.*;
import static org.testng.Assert.*;


public class TestHabitat {
  private Zoo zoo;

  private static final String ZOO_NAME = "Zoo";
  private static final int ZOO_AREA = Integer.MAX_VALUE;
  private static final int ZOO_MAX_ANIMALS = Integer.MAX_VALUE;

  @BeforeClass
  private void setUp() {
    zoo = new Zoo(ZOO_NAME, ZOO_AREA, ZOO_MAX_ANIMALS);
  }

  @DataProvider
  private Object[][] domainMatrixSuccess() {
    return new Object[][] {
        {1, 10, 1},
        {10, 4, 10},
        {50, 10, 50},
        {67, 87, 1}
    };
  }

  @DataProvider
  private Object[][] domainMatrixFail() {
    return new Object[][] {
        {20, 3, 7},
        {39, 20, 40}
    };
  }

  @Test(dataProvider = "domainMatrixSuccess")
  public void successTestCase(int maxAnimals, int areaPerAnimal, int numAnimals)
      throws InvalidOperationException {
    final int area = areaPerAnimal * numAnimals;

    ArrayList<Animal> lstAnimals = new ArrayList<Animal>();

    for (int i = 0; i < numAnimals; i++) {
      lstAnimals.add(new Animal("Pássaro", "Ave"));
    }

    Habitat habitat = new Habitat(area, lstAnimals, maxAnimals, zoo);

    assertEquals(habitat.getArea(), area);
    assertEquals(habitat.getAnimals(), lstAnimals);
    assertEquals(habitat.getZoo(), zoo);
  }

  @Test(dataProvider = "domainMatrixFail",
      expectedExceptions = InvalidOperationException.class)
  public void failTestCase(int maxAnimals, int areaPerAnimal, int numAnimals)
      throws InvalidOperationException {
    final int area = areaPerAnimal * numAnimals;

    ArrayList<Animal> lstAnimals = new ArrayList<Animal>();

    for (int i = 0; i < numAnimals; i++) {
      lstAnimals.add(new Animal("Tareco", "Gato"));
    }

    new Habitat(area, lstAnimals, maxAnimals, zoo);
  }
}
