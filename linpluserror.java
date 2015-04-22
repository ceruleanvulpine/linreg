import java.util.Random;


public class linpluserror {
	
	double m;
	double b;
	double variance;
	Random r;

	
	public linpluserror(){
		r = new Random();
		m = 2;
		b = 2;
		variance = 20;
	}
	
	public double[][] genData(int size){
		double[][] rtn = new double[10][2];
		double curx;
		for (int i=0;i<size;i++){
			curx = r.nextDouble()*100;
			rtn[i][0]=curx;
			rtn[i][1]=m*curx+b+variance*r.nextGaussian();
		}
		return rtn;
		
	}
	
}
