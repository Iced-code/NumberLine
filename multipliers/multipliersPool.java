package multipliers;
import java.util.*;

public class multipliersPool {
    private ArrayList<multiplier> multipliers;
    private int size;

    public multipliersPool(){
        this.multipliers = new ArrayList<multiplier>();
        loadMultiplierPool();
        this.size = multipliers.size();
    }

    private void loadMultiplierPool(){
        multipliers.add(new multiplier_additionPlus());
        multipliers.add(new multiplier_subtractionPlus());
        multipliers.add(new multiplier_multiplicationPlus());
        multipliers.add(new multiplier_divisionPlus());
        multipliers.add(new multiplier_fourAndBelow());
    }

    public multiplier getRandomMultiplier(){
        return multipliers.get((int)(Math.random() * size));
    }
}
