//with blocking threads
public class Operations {

    static void  transfer (Account a, Account b, int amount) throws InterruptedException {
        if (a.getBalance() - amount >=0) {
        System.out.println("\t" + "перевод денег со счета " + a.getNum() + " с балансом " + a.getBalance() + " на счет " + b.getNum() + " с балансом " + b.getBalance() + " на сумму " + amount + " - работает поток " + Thread.currentThread().getName());
        Thread.sleep(1000);
        Account acc1 = a.getNum() < b.getNum() ? a : b;
        Account acc2 = a.getNum() < b.getNum() ? b : a;
            synchronized (acc1) { //выстраивание последовательности блокировок: в первую очередь всегда будет блокироваться счет с меньшим номером -> устранится вероятность, что параллельная транзакция заблокирует счет с бОльшим номером в обход счета с меньшим номером, что и приводило к dead lock
                synchronized (acc2) {
                    a.withdraw(amount);
                        b.deposit(amount);
                                    }
                                }
        System.out.println("успешная операция! баланс стал: счет " + a.getNum() + " = " + a.getBalance() + "; счет " + b.getNum() + " = " + b.getBalance() + " - работает поток " + Thread.currentThread().getName());
                                          }
        else {
        System.out.println("\t\t"+"на счете " + a.getNum() + " недостаточно денег= " + a.getBalance() + " - работает поток " + Thread.currentThread().getName()); }
             }

public static void main(String[] args) throws InterruptedException {

    Account acc1 = new Account(1000, 1);
    Account acc2 = new Account(1000, 2);

        for (int i = 1; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try{
                        transfer(acc1, acc2, 200);
                       }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                                                    }
                                  }
                                     }).start();}

        new Thread(new Runnable() {
            public void run() {
                try {
                    transfer(acc2, acc1, 200);
                    }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                              }
            }                     ).start();
                                                                     }
                          }

