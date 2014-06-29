package cc4102.tarea3.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cc4102.tarea3.geom.Point;

public class NearestPointAlgorithm implements TSPAlgorithm {

	@Override
	public TSPAlgorithmResults run(Point[] pointList) {
		// TODO Auto-generated method stub
		int nPoints = pointList.length;
		double [][] distanceMatrix = new double[nPoints][nPoints];
		
		for(int i = 0; i < nPoints; i++){
			for(int j = 0; j < nPoints; j++){
				distanceMatrix[i][j] = pointList[i].distance(pointList[j]);
			}
		}
		
		int startPoint = (int) (Math.random()*nPoints);
		
		List<Point> currentCircuit = new LinkedList<Point>();
		currentCircuit.add(pointList[startPoint]);
		
		HashMap<Integer, Point> hashPoint = new HashMap<Integer, Point>();
		for(int i = 0; i < nPoints; i++){
			hashPoint.put(i, pointList[i]);
		}
		
		int indexMin;
		double distanceMin = 100000000;
		for(int i = 0; i < nPoints && i != startPoint; i++){
			double actualDistance = distanceMatrix[startPoint][i];
			if(actualDistance < distanceMin){
				indexMin = i;
				distanceMin = actualDistance;
			}
		}
		
		
		
		TSPAlgorithmResults results = new TSPAlgorithmResults();
		results.circuit = currentCircuit;
		results.length = Point.getCircuitLength(currentCircuit);
		return results;
	}
	
	@Override
	public String getName() {
		return "Nearest";
	}

}
