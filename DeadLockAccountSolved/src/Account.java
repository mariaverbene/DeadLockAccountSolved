public class Account {

        volatile int balance;
        private int num;


        public Account (int initialBalance, int num) {
            this.balance = initialBalance;
            this.num = num;
        }

        public void withdraw (int amount) {
            balance -= amount;
        }

        public void deposit (int amount) {
            balance += amount;
        }

        public int getBalance () {
            return balance;
        }

        public int getNum () {
            return num;
        }



}


