package cc4102.tarea3.algorithm;

import java.util.LinkedList;
import java.util.List;

import cc4102.tarea3.geom.Point;

public class NearestPointAlgorithm implements TSPAlgorithm {

	@Override
	public TSPAlgorithmResults run(Point[] points) {
		
		final Point[] pointList = points.clone();
		int nPoints = pointList.length;
		
		//float [][] distanceMatrix = new float[nPoints][nPoints];
		boolean[] visited = new boolean[nPoints];
		
		/*for(int i = 0; i < nPoints; i++){
			for(int j = 0; j < nPoints; j++){
				if(i == j)
					distanceMatrix[i][j] = Integer.MAX_VALUE;
				else
					distanceMatrix[i][j] = (float)pointList[i].distance(pointList[j]);
			}
		}*/
		
		int startPoint = (int) (Math.random()*nPoints);
		
		List<Point> currentCircuit = new LinkedList<Point>();
		currentCircuit.add(pointList[startPoint]);
		
		// TODO Modificar este valor centinela
		int indexMin = startPoint;
		int currentIndexPoint = startPoint;
		
		while(currentCircuit.size() != nPoints){
			// Ahora este punto es inalcanzable para el resto de puntos
			visited[currentIndexPoint] = true;
			/*for(int i = 0; i < nPoints; i++)
				distanceMatrix[i][currentIndexPoint] = Integer.MAX_VALUE;*/
			double distanceMin = Double.MAX_VALUE;
			for(int iterPoint = 0; iterPoint < nPoints; iterPoint++){
				if(visited[iterPoint]) continue;
				double volatileDistance = pointList[currentIndexPoint].distance2(pointList[iterPoint]);
				//double volatileDistance = distanceMatrix[currentIndexPoint][iterPoint];
				if(volatileDistance < distanceMin){
					indexMin = iterPoint;
					distanceMin = volatileDistance;
					
				}
			}
			currentCircuit.add(pointList[indexMin]);
			currentIndexPoint = indexMin;
			
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
