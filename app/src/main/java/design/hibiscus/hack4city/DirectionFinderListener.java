package design.hibiscus.hack4city;

/**
 * Created by ASUS-PC on 8.10.2017.
 */

import java.util.List;

/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}