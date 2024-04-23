package model;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoutingService {
	private static RoutingService instance;
	private GraphHopper hopper;

	public static RoutingService getInstance() {
		if(instance == null) {
			instance = new RoutingService();
		}
		return instance;
	}

	public RoutingService() {
		hopper = createGraphHopperInstance("src\\main\\java\\mapdata\\osm_file\\vietnam_latest.osm.pbf");
	}

	private GraphHopper createGraphHopperInstance(String ghLoc) {
		GraphHopper graHopper = new GraphHopper();
		graHopper.setOSMFile(ghLoc);
		// specify where to store graphhopper files
		graHopper.setGraphHopperLocation("src\\main\\java\\mapdata\\target\\routing-graph-cache");

		// see docs/core/profiles.md to learn more about profiles
		graHopper.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));

		// this enables speed mode for the profile we called car
		graHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));

		// now this can take minutes if it imports or a few seconds for loading of course this is dependent on the area you import
		graHopper.importOrLoad();
		return graHopper;
	}

	public ArrayList routing(double fromLat, double fromLon, double toLat, double toLon) {
		ArrayList arrayList = new ArrayList<>();
		GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon).
				setProfile("car").
				setLocale(Locale.US);
		try {
			GHResponse rsp = hopper.route(req);
			ResponsePath path1 = rsp.getBest();
			double distance1 = path1.getDistance();

			// handle errors
			if (rsp.hasErrors())
				return arrayList;

			// use the best path, see the GHResponse class for more possibilities.
			ResponsePath path = rsp.getBest();

			// points, distance in meters and time in millis of the full path
			PointList pointList = path.getPoints();
			double distance = path.getDistance();

			// In ra tên của điểm cuối cùng
//		InstructionList instructionList = path.getInstructions();
//		System.out.println(instructionList.get(instructionList.size() - 2));

			Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
			InstructionList il = path.getInstructions();
			// iterate over all turn instructions
			List<RoutingData> list = new ArrayList<>();
			for (Instruction instruction : il) {
				// System.out.println("distance " + instruction.getDistance() + " for instruction: " + instruction.getTurnDescription(tr));
				list.add(new RoutingData(instruction.getDistance(), instruction.getTurnDescription(tr), instruction.getPoints()));
			}
			arrayList.add(list);
			arrayList.add(distance1 / 1000);
		} catch (Exception e) {
			return arrayList;
		}
		return arrayList;
	}
}
