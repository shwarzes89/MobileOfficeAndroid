package dev.se.mobileoffice.model.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.se.mobileoffice.R;
import dev.se.mobileoffice.model.cell.agent.AirConditioner;
import dev.se.mobileoffice.model.cell.agent.LightController;

/**
 * Created by makyungjae on 2014. 11. 5..
 */
public class CellManager {

    private static final int MAX_CELL_COUNT = 16;
    private List<OfficeCell> cells;
    private static CellManager instance;

    static  {
        instance = new CellManager();
    }


    private CellManager() {
        cells = new ArrayList<OfficeCell>();

        for(int i = 0; i < MAX_CELL_COUNT; ++i) {

            OfficeCell cell =
                    new OfficeCell.OfficeCellBuilder(i)
                            .noise(60 + new Random().nextInt(10))
                            .computingPower(2.4)
                            .humidity(30 + new Random().nextInt(8))
                            .networkBandwidth(1000)
                            .computingPower(2400)
                            .temperature(23 + new Random().nextInt(2)).createOfficeCell();

            cells.add(cell);
        }


        OfficeCell cell = cells.get(3);
        cell.addAgent(new AirConditioner(3, R.drawable.aircon, cell.getEnvironment()));

        OfficeCell cell2 = cells.get(0);
        cell2.addAgent(new LightController(0, R.drawable.bulb, cell2.getEnvironment()));


    }

    public static CellManager getInstance() {
        if(instance != null) {
            return instance;
        } else {
            return new CellManager();
        }
    }

    public int size() {
        return MAX_CELL_COUNT;
    }

    public OfficeCell get(int idx) {

        assert idx > -1 && cells != null;

        return cells.get(idx);
    }

}
