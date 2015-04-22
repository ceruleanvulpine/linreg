import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;


public class linregtest {

	public static void main(String[] args){
		linregtest lr = new linregtest();
	}
	
	public linregtest(){
		DenseMatrix64F b = new DenseMatrix64F(3,1);
		b.set(0,0,5);
		b.set(1,0,0.1);
		b.set(2,0,100);
		
		System.out.println(b);
		
		datamodel d = new datamodel(b,1,10);
		DenseMatrix64F[] test = d.genData(10);

		System.out.println(test[0]);
		System.out.println(test[1]);
	
		System.out.println("betahat: " + fitData(test[0],test[1]));
		
	}
	
	public DenseMatrix64F fitData(DenseMatrix64F x, DenseMatrix64F y){
		
		DenseMatrix64F x2 = new DenseMatrix64F(x.numRows,x.numCols+1);
		CommonOps.insert(x,x2,0,0);
		for(int i=0;i<x.numRows;i++){
			x2.set(i, x.numCols,1);
		}

		DenseMatrix64F betahat = new DenseMatrix64F(x2.numCols,y.numCols);
		DenseMatrix64F xtranspose = new DenseMatrix64F(x2.numCols,x.numRows);
		CommonOps.transpose(x2,xtranspose);
		DenseMatrix64F mult = new DenseMatrix64F(x2.numCols,x2.numCols);
		CommonOps.mult(xtranspose,x2, mult);
		CommonOps.invert(mult);
		DenseMatrix64F mult2 = new DenseMatrix64F(xtranspose.numRows,y.numCols);
		CommonOps.mult(xtranspose, y, mult2);
		CommonOps.mult(mult, mult2, betahat);

		return betahat;
	}
	
}
