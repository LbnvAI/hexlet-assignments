package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        var list = new SafetyList();

        var t1 = new Thread(new ListThread(list));
        var t2 = new Thread(new ListThread(list));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(list.getSize());
        // END
    }
}

