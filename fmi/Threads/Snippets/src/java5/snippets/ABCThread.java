package java5.snippets;

public class ABCThread extends Thread {
    private final StringBuffer sb;

    public ABCThread(StringBuffer sb) {
        this.sb = sb;
    }

    @Override
    public void run() {
        synchronized (sb) {
            for (int i = 0; i < 100; i++) {
                System.out.print(sb);

            }
            sb.setCharAt(0, (char) (sb.charAt(0) + 1));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("a");
        ABCThread t1 = new ABCThread(sb);
        ABCThread t2 = new ABCThread(sb);
        ABCThread t3 = new ABCThread(sb);

        t1.start();
        t2.start();
        t3.start();
    }
}


