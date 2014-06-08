import java.lang.management.ManagementFactory;
import java.util.Arrays;


public class PrimeSieves {
	
	static boolean[] primes  = new boolean[100000001];
	static boolean[] primes2  = new boolean[100000001];
	static boolean[] primes3  = new boolean[100000001];
	static boolean[] primes4  = new boolean[100000001];
	static final int NUM = 100000000;

	private static void SieveOfErathostenes(int num){
		for(int i=0; i < num;i++){
		primes[i] = (!(i == 2 || i%2 == 0 || i == 0 || i == 1));
		}
		for(int i = 3; i <= num; i = i + 2){
			if(primes[i]){
				for(int j= 2*i; j < num; j += i){
					primes[j] = false;
					}
				}
			}
		primes[2] = true;
	}
	
	/* Originally meant for int=3; i <= num; -- i <= num is changed to i <= Math.sqrt(num)+1 */
	private static void SieveOfErathostenesOptimised(int num){
		for(int i=0; i < num;i++){
		primes2[i] = (!(i == 2 || i%2 == 0 || i == 0 || i == 1));
		}
		for(int i = 3; i <= Math.sqrt(num)+1; i = i + 2){
			if(primes2[i]){
				for(int j= 2*i; j < num; j += i){
					primes2[j] = false;
					}
				}
			}
		primes2[2] = true;
	}
	
	private static void SieveOfAtkin(int num){
		for (int x = 1; x <= Math.sqrt(num)+1; x++)
		{
			for (int y = 1; y <= Math.sqrt(num)+1; y++) 
			{
				int n = 4 * x * x + y * y;
				if (n <= num && (n % 12 == 1 || n % 12 == 5)) 
				{
					primes3[n] = !primes3[n];
				}

				n = 3 * x * x + y * y;
				if (n <= num && (n % 12 == 7))
				{
					primes3[n] = !primes3[n];
				}

				n = 3 * x * x - y * y;
				if (x > y && (n <= num) && (n % 12 == 11))
				{
					primes3[n] = !primes3[n];
				}
			}
		}
		for (int n = 5; n <= Math.sqrt(num)+1; n++) 
		{
			if (primes3[n])
			{
				int s = n * n;
				for (int k = s; k <= num; k += s) 
				{
					primes3[k] = false;
				}
			}
		}
		primes3[2] = true;
		primes3[3] = true;
	}
	
	private static void SieveOfSundaram(int num){
		int n = num / 2;
		int n2 = (int) (Math.sqrt(num)+1);
		Arrays.fill(primes4, true);
		for (int i = 1; i <= n2; i++)
		{
			for (int j = i; j <= (n - i) / (2 * i + 1); j++)
			{
				primes4[i + j + 2 * i * j] = false;
			}
		}	
	}
	
	
	public static void main(String[] args) {
		int counter = 0;
		long start, end;
		/* for 1 to 100000000 => 5761455 primes */
		
		start = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		SieveOfErathostenes(NUM);
		end = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		for(int i = 0; i <= NUM; i++){
			if(primes[i])
				counter++;
		}
		
		System.out.println("Sieve of Erathostenes \t\t found " + counter + " primes from 1 to " + NUM + " in \t\t" + ((end-start)/1000000) + " ms");
		start = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		SieveOfErathostenesOptimised(NUM);
		end = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		counter = 0;
		for(int i = 0; i <= NUM; i++){
			if(primes2[i])
				counter++;
		}
		/* for 1 to 100000000 => 5761455 primes */
		System.out.println("Sieve of Erathostenes Optimised \tfound " + counter + " primes from 1 to " + NUM + " in \t" + ((end-start)/1000000) + " ms");
		
		start = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		SieveOfAtkin(NUM);
		end = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		counter = 0;
		for(int i = 0; i <= NUM; i++){
			if(primes3[i])
				counter++;
		}
		/* for 1 to 100000000 => 5761455 primes */
		System.out.println("Sieve of Atkin \t\t found " + counter + " primes from 1 to " + NUM + " in \t\t\t" + ((end-start)/1000000) + " ms");
		
		start = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		SieveOfSundaram(NUM);
		end = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
		counter = 0;
		for(int i = 0; i <= NUM; i++){
			if(primes4[i])
				counter++;
		}
		/* for 1 to 100000000 => 5761455 primes */
		System.out.println("Sieve of Sundaram \t \t found " + counter + " primes from 1 to " + NUM + " in \t\t" + ((end-start)/1000000) + " ms");
	}

}