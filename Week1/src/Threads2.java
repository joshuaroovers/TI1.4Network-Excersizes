import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Threads2 {
    private static Account account = new Account();

//Gegeven is de volgende code om op een bankrekening te werken.
//Pas de volgende code aan met de wait() en notifyAll() methode in de Object klasse

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();

        System.out.println("Thread 1\t\tThread2\t\tBalance");
    }

    public static class DepositTask implements Runnable {
        @Override
        public void run()
        {
            try {
                while(true) {
                    account.deposit((int)(Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable {
        @Override
        public void run()
        {
            while(true) {
                account.withdraw((int)(Math.random() * 10) + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while(balance < amount) {
                    System.out.println("\t\t\tWait for deposit");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\t\tWithdraw " + amount + "\t\t" + getBalance());
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void deposit(int amount)
        {
            lock.lock();
            try {
                balance += amount;
                System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());
                newDeposit.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}