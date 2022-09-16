package FileExplorer;

import java.util.ArrayList;
import java.util.Scanner;

public class Directory {
    private String directoryName;
    private String path;
    private Directory parentDirectory;
    private ArrayList<Directory> subDirectory = new ArrayList<>();
    private ArrayList<Files> files = new ArrayList<>();
    private static Directory presentDirectory;
    private static Scanner input = new Scanner(System.in);

    public void initializeDirectory(String directoryName, Directory route){
        route.directoryName = directoryName;
        presentDirectory = route;
        presentDirectory.path = ". / ";
    }
    public void cmdWindow(){
        System.out.println("""
                MD - Make Directory
                CD - Change Directory
                PWD - Current running file/Path
                touch - New File
                list - List of Folders and files
                CD.. - Previous Folder""");
        String cmd = input.next();
        switch (cmd) {
            case "md" -> {
                System.out.println("Enter the file name :");
                makeDirectory(input.next());
            }
            case "cd" -> {
                System.out.println("Enter the file name :");
                changeDirectory(input.next());
            }
            case "pwd" -> PWD();
            case "touch" -> touch();
            case "list" -> list();
            case "cd.." -> previousDirectory();
            default -> {
                System.out.println("Invalid Input");
                System.out.println();
            }
        }
        cmdWindow();
    }
    private void makeDirectory(String directoryName){
        Directory directory1 = new Directory();
        directory1.directoryName = directoryName;
        directory1.parentDirectory = presentDirectory;
        presentDirectory.subDirectory.add(directory1);
        System.out.println(directoryName + " Directory Created\n");
        directory1.path = presentDirectory.path+directoryName + " / ";

    }
    private void changeDirectory(String directoryName){
        boolean folderPresent = false;
        for (Directory subD : presentDirectory.subDirectory){
            if (subD.directoryName.equals(directoryName)){
                presentDirectory = subD;
                folderPresent = true;
                System.out.println("Directory Changed\n");
                break;
            }
        }
        if (!folderPresent) {
            System.out.println("Invalid Action\nFolder not present\n");
        }
    }
    private void PWD() {
        System.out.println("Current directory is " + presentDirectory.directoryName + "\n");
        System.out.println(presentDirectory.path);
    }
    private void touch(){
        Files files1 = new Files();
        System.out.println("Enter the file name :");
        files1.fileName = input.next();
        files1.parentDirectory = presentDirectory;
        files1.path = presentDirectory.path;
        presentDirectory.files.add(files1);
    }
    private void list(){
        ArrayList<String> folderAndFiles = new ArrayList<>();
        for (Directory folder : presentDirectory.subDirectory){
            folderAndFiles.add(folder.directoryName);
        }
        for (Files files : presentDirectory.files){
            folderAndFiles.add(files.fileName);
        }
        System.out.println(folderAndFiles);
    }
    private void previousDirectory(){
        try {
            presentDirectory = presentDirectory.parentDirectory;
            System.out.println("Moved to Previous Directory " + presentDirectory.directoryName + "\n");
        }
        catch (NullPointerException e){
            System.out.println("This is the root Directory");
            cmdWindow();
        }
    }
}
