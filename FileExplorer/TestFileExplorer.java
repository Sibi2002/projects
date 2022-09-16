package FileExplorer;

public class TestFileExplorer {
    public static void main(String[] args) {
        Directory route = new Directory();
        route.initializeDirectory("routes",route);
        route.cmdWindow();
    }
}
