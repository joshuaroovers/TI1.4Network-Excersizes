public class Threads1 {
//    Schrijf een programma dat 1000 threads start.
//    Iedere thread telt 1 op bij een teller.
//    Deze teller moet je als referentie meegeven,
//    daarom zul je deze in een wrapper-object moeten zetten (zoals Integer).
//    Maak hier twee wrapper-objecten voor,
//    eentje die synchronized is en vergelijk de verschillen

    static int counter = 0;
    public static void main(String[] args) {

//this is horrible it's so incredibly inconsistent. but I suppose that's the point of this demonstration
        boolean doSynchronized = false;
        if(doSynchronized){
            System.out.println("Unsynchronized:");
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                Thread t = new Thread(()->{
                    System.out.println("Thread #"+ finalI +": "+ counter);
                    counter++;
                });
                t.start();
            }
        }else{
            System.out.println("Synchronized:"); //increments in order but still skips over some numbers..
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                Thread t = new Thread(()->{
                    System.out.println("Thread #"+ finalI +": "+ getCounter());
                    incrementCount();
                });
                t.start();
            }
        }

    }
    public static synchronized void incrementCount() {
        counter++;
    }
    public static synchronized int getCounter() {
        return counter;
    }
}