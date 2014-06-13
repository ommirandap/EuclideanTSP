public class Point{

	public double X, Y;

	public Point(double x, double y){
		this.X = x;
		this.Y = y;
	}

	public double getX(){
		return this.X;
	}

	public double getY(){
		return this.Y;
	}

	public double EuclideanDist(Point p){
		return Math.sqrt((Math.pow(Math.abs(this.X + p.getX())),2) + 
			Math.pow(Math.abs((this.Y + p.getY()),2)));
	}

}