import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;
import java.util.Random;
import java.util.Properties;

/**
 * Created by guo1d on 7/10/16.
 */
public class main {

    public static void main(String[] args){

        Properties props = new Properties();
        try{
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            System.out.println(props.getProperty("source.file.url"));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        final int numThreads = 5;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);

        for (int i=0; i<10; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    try{
                        String threadId = Thread.currentThread().getName();
                        int rn = new Random().nextInt(5) + 1;
                        System.out.println("I am thread " + threadId + " of " + numThreads +"; Task Number = "+rn + " ; Available threads: ");
                        Thread.currentThread().sleep(rn * 1000 );
                    }
                    catch (InterruptedException interruptedException){
                        interruptedException.printStackTrace();
                    }

                }
            });
        }

        exec.shutdown();


    }
}
