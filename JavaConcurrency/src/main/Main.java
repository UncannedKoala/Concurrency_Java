package main;

/**
 * <li>A thread can be created in 2 ways:
 * <ol>
 * <li>Creating {@linkplain Thread} class object and passing a
 * {@linkplain Runnable} object to {@code Thread} constructor. This
 * {@code Runnable} object must have overridden the {@code run()} of
 * {@code Runnable} interface. Then calling {@code thread.start()}
 * <li>Extending a {@code Thread} and overriding the {@code run()} of the
 * {@code Thread} class, which is inherited && overridden by {@code Thread} from
 * {@code Runnable}. Then calling {@code thread.start();}
 * </ol>
 * 
 * <li>The <strong>{@code Thread.setPriority(int)}</strong> will set the static priority of the
 * thread; The
 * <li><strong>{@code Thread.setUncaughtExceptionHandler(UncaughtExceptionHandler)}</strong> can be
 * used to handle any uncaught exceptions in the thread and handle the cleanups
 */
public class Main {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				throw new RuntimeException("Intentionally throwing RuntimeException from created Thread");
			}
		}, "new thread");

		thread1.setPriority(1);
		thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			// SUPPOSED TO BE THREAD CLEAN-UP CODE
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("Uncaught exception in thread " + t.getName() + ", caused by: " + e.getMessage());
			}
		});
		thread1.start();

		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			System.out.println("interruption in thread : " + Thread.currentThread().getName());
		}

		System.out.println(Thread.currentThread().getName());
	}
}
