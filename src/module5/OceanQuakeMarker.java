package module5;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Jeff Brown
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {

	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	/** Draw the earthquake as a square */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
	    pg.rect(x-radius, y-radius, 2*radius, 2*radius);
	}

	// I do everything my superclass does.  I also make visible line markers that have my location,
    // because they connect to things in my threat circle.
    @Override
    public void showThreatCircleOnly(List<Marker> quakeMarkers, List<Marker> cityMarkers, List<Marker> lineMarkers) {
        super.showThreatCircleOnly(quakeMarkers, cityMarkers, lineMarkers);

        // Where am I?  I will do searching by location.
        Location myLocation = getLocation();

        // loop thru the line markers, looking for either location in the marker matching my location.
        // If found, unhide the marker.  If not found, hide the marker.  (it was already hidden anyway.)
        for (Marker m : lineMarkers) {
            SimpleLinesMarker slm = (SimpleLinesMarker) m;
            //slm.setHidden(! slm.isInsideByLocation(myLocation));  // test doesn't work like I thought it might.
            slm.setHidden(! slm.getLocations().contains(myLocation));   // the (slightly) "harder" way.
        }
    }

}
