package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

	public static final int MAX_PASSWORD_VALUE = 9999;

	public static void main(String[] args) {
		Random random = new Random();
		
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD_VALUE));

		List<Thread> threads = new LinkedList<>();
		
		threads.add(new DescHackerThread(vault));
		threads.add(new AscHackerThread(vault));
		threads.add(new PoliceThread());
		
		threads.forEach(t -> t.start());
	}

	/**
	 * Representing a vault Object
	 */
	private static class Vault {
//		password ranges from 0 to 9999
		private final int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			return this.password == guess;
		}
	}

	/**
	 * A simple abstract class representing a Hacker
	 */
	private static abstract class HackerThread extends Thread {

		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName()); // getSimpleName() vs getName()
			this.setPriority(MAX_PRIORITY);
		}

		@Override
		public void start() {
			System.out.println("Starting thread: " + this.getName());
			super.start();
		}
	}

	private static class PoliceThread extends Thread {

		@Override
		public void run() {
			super.run();
			for (int i = 10; i > 0; i--)
				try {
					Thread.sleep(1000);
					System.out.println(i + " second for police to arrive");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println("Game over, police Arrived!!");
			System.exit(0);

		}
	}

	private static class AscHackerThread extends HackerThread {

		public AscHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			super.run();
			for (int guess = 0; guess < MAX_PASSWORD_VALUE; guess++) {
				if (this.vault.isCorrectPassword(guess)) {
					System.out.println("Thread: " + this.getName() + " guessed the correct password to be " + guess);
					System.exit(0); // equal to calling "Runtime.getRuntime().exit(n)"
				}
				;
			}
		}
	}

	private static class DescHackerThread extends HackerThread {

		public DescHackerThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			super.run();
			for (int guess = MAX_PASSWORD_VALUE; guess >= 0; guess--) {
				if (this.vault.isCorrectPassword(guess)) {
					System.out.println("Thread: " + this.getName() + " guessed the correct password to be " + guess);
					System.exit(0); // equal to calling "Runtime.getRuntime().exit(n)"
				}
			}
		}
	}
}
