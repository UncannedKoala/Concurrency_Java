package main;

public class SingletonClass implements Cloneable {

	// Double-Check locking Singleton implementation

	/*
	 * private static volatile SingletonClass instance;
	 * 
	 * private SingletonClass() { }
	 * 
	 * @Override protected Object clone() throws CloneNotSupportedException { throw
	 * new CloneNotSupportedException(); }
	 * 
	 * public SingletonClass getInstance() { if (SingletonClass.instance == null) {
	 * synchronized (SingletonClass.class) { if (SingletonClass.instance == null)
	 * SingletonClass.instance = new SingletonClass(); } } return
	 * SingletonClass.instance; }
	 */

	// "Initialization on Demand Holder" pattern.
	private static class InstanceHolder {
		/* inner class holding a constant pointing to singleton instance */
		private static final SingletonClass INSTANCE = new SingletonClass();
	}

	/* constructor */
	private SingletonClass() {
	}

	@Override
	protected final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/* public method to distribute instance */
	public static SingletonClass getInstance() {
		return InstanceHolder.INSTANCE;
	}

}
