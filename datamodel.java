import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

import java.util.Random;

public class datamodel {
	
	int xdim;
	int ydim;
	DenseMatrix64F beta;
	double variance;
	double scale; //input scale (does not matter v much.)
	Random r;

	public datamodel(DenseMatrix64F b, double v, double s){
		xdim = b.getNumRows();
		ydim = b.getNumCols();
		beta = b;
		variance = v;
		scale = s;
		r = new Random();
	}
	
	public DenseMatrix64F[] genData(int size){
		DenseMatrix64F[] rtn = new DenseMatrix64F[2];
		
		rtn[0] = new DenseMatrix64F(size, xdim); //x
		rtn[1] = new DenseMatrix64F(size, ydim); //y
		double x;
		DenseMatrix64F curx = new DenseMatrix64F(1,xdim);
		DenseMatrix64F cury = new DenseMatrix64F(ydim,1);
		for (int i=0;i<size;i++){
			for (int j=0; j<xdim-1;j++){
				x=r.nextDouble()*scale+r.nextGaussian()*variance;
				curx.set(0,j,x);
				rtn[0].set(i,j,x);
			}
			curx.set(0,xdim-1,1);
			rtn[0].set(i,xdim-1,1);
			CommonOps.mult(curx,beta,cury);
			for(int k=0;k<ydim;k++){
				rtn[1].set(i,k,cury.get(k,0));
			}
		}
		
		DenseMatrix64F newx = new DenseMatrix64F(size,xdim-1);
		CommonOps.extract(rtn[0],0,size,0,xdim-1,newx,0,0);
		rtn[0] = newx;
		
		return rtn;
	}
	
}
