package mining;

class Interval {
	private float inf;
	private float sup;
	
	Interval(float inf,float sup) {
		this.inf = inf;
		this.sup = sup;
	}
	
	void setInf(float inf) {
		this.inf = inf;
	}
	
	void setSup(float sup) {
		this.sup = sup;
	}
	
	float getInf() {
		return inf;
	}
	
	float getSup() {
		return sup;
	}
	
	boolean checkValueInclusion(float value) {
		return value >= inf && value < sup;
	}
	
	@Override
	public String toString() {
		return "["+  inf + ";" + sup + "[";
	}
}
