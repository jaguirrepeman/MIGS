package graph;

import java.util.Comparator;

public class RouteLabelComparator implements Comparator<RouteLabel> {

	@Override
	public int compare(RouteLabel r1, RouteLabel r2) {
		
		if (r1.getf() < r2.getf())
        {
            return -1;
        }
        if (r1.getf() > r2.getf())
        {
            return 1;
        }
        return 0;
	}

}
