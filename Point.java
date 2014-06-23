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

	public double distance(Point p){
		
		double absX = Math.abs(this.X + p.getX());
		double absY = Math.abs(this.Y + p.getY());
		double powX = absX * absX;
		double powY = absY * absY;

		return Math.sqrt(powX + powY);
	}

	public void setX(double x){
		this.X = x;
	}

	public void setY(double y){
		this.Y = y;
	}

	public String toString(){
		return "(" + this.X + "," + this.Y + ")";
	}

}