package pro.eng.yui.sample.demo;

public class TererunBox {

    public static void main(String[] args) {
        BlockBoard myBoard = new BlockBoard(5, 5);

        myBoard.setBlockState(0, 3, BlockState.filled);
        myBoard.setBlockState(4, 2, BlockState.filled);
        myBoard.setBlockState(2, 0, BlockState.filled);
        myBoard.setBlockState(2, 3, BlockState.filled);
        myBoard.setBlockState(3, 4, BlockState.filled);

        myBoard.printBoard();

        System.out.println(myBoard.accessableFromCorner());

        myBoard.printBoard();

    }

    enum BlockState{
        empty, filled
    }
    enum FindState{
        empty, filled, passed
    }

    static class BlockBoard{
        private final BlockState[][] board;
        private final int height;
        private final int width;

        public BlockBoard(int height, int width){
            this.height = height;
            this.width = width;
            this.board = new BlockState[this.height][this.width];
            initBoard();
        }
        private void initBoard(){
            for(int hIdx = 0; hIdx < height; hIdx++) {
                for (int wIdx = 0; wIdx < width; wIdx++) {
                    board[hIdx][wIdx] = BlockState.empty;
                }
            }
        }

        public void printBoard(){
            for(int hIdx = 0; hIdx < height; hIdx++) {
                for (int wIdx = 0; wIdx < width; wIdx++) {
                    System.out.print((board[hIdx][wIdx] == BlockState.empty? "□":"■"));
                }
                System.out.println();
            }
        }

        public BlockState getBlock(int row, int col){
            return board[row][col];
        }

        public void setBlockState(int row, int col, BlockState newState){
            this.board[row][col] = newState;
        }

        public boolean accessableFromCorner(){
            return findAll(0, 0);
        }

        private boolean findAll(int startLeft, int startTop){

            FindState[][] findBoard = new FindState[width][height];
            for(int hIdx = 0; hIdx < height; hIdx++) {
                for (int wIdx = 0; wIdx < width; wIdx++) {
                    findBoard[hIdx][wIdx] = FindState.empty;
                }
            }

            findNeighbor(findBoard, startLeft, startTop);

            for(int hIdx = 0; hIdx < height; hIdx++) {
                for (int wIdx = 0; wIdx < width; wIdx++) {
                    if(findBoard[hIdx][wIdx] == FindState.empty){
                        return false;
                    }
                }
            }
            return true;
        }

        private void findNeighbor(FindState[][] findBoard, int left, int top){
            if (findBoard[top][left] == FindState.filled || findBoard[top][left] == FindState.passed){
                return;
            }
            if (getBlock(top, left) == BlockState.filled){
                findBoard[top][left] = FindState.filled;
                return;
            }else {
                findBoard[top][left] = FindState.passed;
            }
            printStateBoard(findBoard, left, top);
            System.out.println("----------");
            if(top - 1 >= 0){
                //上検査
                findNeighbor(findBoard, left, (top-1));
            }
            if(left - 1 >= 0 ){
                //左隣検査
                findNeighbor(findBoard, (left-1), top);
            }
            if(top + 1 < height){
                //下検査
                findNeighbor(findBoard, left, (top+1));
            }
            if(left+1 < width){
                //右検査
                findNeighbor(findBoard, (left+1), top);
            }
            printStateBoard(findBoard, left, top);
            System.out.println(">>>>>>>>>>");

        }

        public void printStateBoard(FindState[][] findBoard, int curLeft, int curTop){
            for(int hIdx = 0; hIdx < height; hIdx++) {
                for (int wIdx = 0; wIdx < width; wIdx++) {
                    String block = "□";
                    if(findBoard[hIdx][wIdx] == FindState.filled){
                        block = "■";
                    }
                    if(findBoard[hIdx][wIdx] == FindState.passed){
                        block = "✔";
                    }
                    if(hIdx == curTop && wIdx == curLeft){
                        block = "★";
                    }
                    System.out.print(block);
                }
                System.out.println();
            }
        }

    }
}