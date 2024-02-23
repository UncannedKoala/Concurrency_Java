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
 * <li><strong>Thread.setPriority(int)</strong> will set the static priority of
 * the thread.
 * <li><strong>Thread.setUncaughtExceptionHandler()</strong> can be used to
 * handle any uncaught exceptions in the thread and handle the cleanups.
 * <li><strong>{@linkplain Runnable}</strong> is Functional interface
 * representing an operation that does not return a result, and its functional
 * method is {@code run();}
 * <li><strong>{@linkplain Thread} </strong> is a thread of execution in a
 * program. The Java virtual machine allows an application to have multiple
 * threads of execution running concurrently.<br>
 * 
 * A task that returns a result and may throw an exception. Implementors define
 * a single method with no arguments called {@code run()}.
 * <hr>
 * <p>
 * The {@code Callable} interface is similar to {@link java.lang.Runnable}, in
 * that both are designed for classes whose instances are potentially executed
 * by another thread. A {@code Runnable}, however, does not return a result and
 * cannot throw a checked exception.
 * </p>
 * <hr>
 * <p>
 * <b>STATE OF A THREAD</b>
 * <li>NEW: Thread state for a thread which has not yet started.
 * <li>RUNNABLE: A thread in the runnable state is executing in the Java virtual
 * machine but it may be waiting for other resources from the operating system
 * such as processor.
 * <li>BLOCKED: A thread in the blocked state is waiting for a monitor lock to
 * enter a synchronized block/method or reenter a synchronized block/method
 * after calling {@link Object#wait() Object.wait}.
 * <li>WAITING: A thread in the waiting state is waiting for another thread to
 * perform a particular action.
 * <li>TIMED_WAITING: Thread state for a waiting thread with a specified waiting
 * time.
 * <li>TERMINATED: The thread has completed execution.
 * </p>
 */
public class Main {

	public static void main(String[] args) {
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				throw new RuntimeException("Intentionally throwing RuntimeException from created Thread");
			}
		};

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
