package jpc.pd.threadjoin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class App {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		LOGGER.info("\tStarting Main App");
		Integer[] values = new Integer[] { 3, 1, 14, 3, 4, 5, 6, 7, 8, 9, 11, 3, 2, 1 };
		Average avg = new Average(values);
		Median median = new Median(values);
		Thread t1 = new Thread(avg, "t1");
		Thread t2 = new Thread(median, "t2");
		System.out.println("Start the thread t1 to calculate average");
		t1.start();
		System.out.println("Start the thread t2 to calculate median");
		t2.start();
		
		try {
			System.out.println("Join on t1");
			t1.join();
			System.out
					.println("\t> t1 has done with its job of calculating average");
		} catch (InterruptedException e) {
			System.out.println(t1.getName() + " interrupted");
		}
		
		try {
			System.out.println("Join on t2");
			t2.join();
			System.out
					.println("\t> t2 has done with its job of calculating median");
		} catch (InterruptedException e) {
			System.out.println(t2.getName() + " interrupted");
		}
		
		System.out.println("Average: " + avg.getMean() + ", Median: "
				+ median.getMedian());
	}

	/**
	 * Calculate average of numbers. Sum all the int values and divide it by
	 * total count.
	 */
	private static class Average implements Runnable {
		private Integer[] values;
		private int mean;

		Average(Integer[] values) {
			this.values = values;
		}

		@Override
		public void run() {
			mean = 0;
			int n = values.length;
			for (int i : values) {
				mean += i;
			}
			mean /= n;
			LOGGER.info("Thread.sleep for 5000 millis...");
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public int getMean() {
			return mean;
		}
	}

	/**
	 * Sorts the given int list and calculates the median value. If size is
	 * even, the mean of middle and middle-1.
	 *
	 */
	private static class Median implements Runnable {
		private Integer[] values;
		private int median;

		Median(Integer[] values) {
			this.values = values;
		}

		@Override
		public void run() {
			List<Integer> sortedList = Arrays.asList(values);
			Collections.sort(sortedList);
			int n = values.length;
			int middle = n / 2;
			if (n % 2 == 0) {
				median = (((Integer) sortedList.get(middle)) + ((Integer) sortedList.get(middle - 1))) / 2;
			} else {
				median = (Integer) sortedList.get(middle);
			}
		}

		public int getMedian() {
			return median;
		}

	}
}
/*
class Citation extends Thread
{
  private String chaine;
  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  Citation(String chaine)
    {
	  LOGGER.info("\t" + chaine);
      this.chaine=chaine;
    }
 */
  