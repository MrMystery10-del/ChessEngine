package core;

import persistence.ProfileRepo;
import persistence.repos.FileRepo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Runner {

    public static void main(String[] args) {

        Configuration.startWithNewProfile = true;
        Configuration.userConfigLocation = System.getProperty("user.home") + "/temp/chessEngine";
        //FileRepo repo = FileRepo.getInstance();
        Path location = Path.of(Configuration.userConfigLocation + "/"+ Configuration.profileFileName);


        try {
            System.out.println(Configuration.userConfigLocation);
            Files.createDirectories(Path.of(Configuration.userConfigLocation));

        } catch (FileAlreadyExistsException e) {
            System.out.println("i exists");
        }
        catch (IOException e) {
            System.out.println("to far but catched");
        }


    }



}