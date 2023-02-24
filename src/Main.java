public class Main {
    public static void main(String[] args) throws InterruptedException {
        Board b = new Board(15);
        b.paintBoard();

        while(true){
            b.moveSnake();
            b.repaint();
            Thread.sleep(150);
        }
    }
}
