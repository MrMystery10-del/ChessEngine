package core;

import persistence.repos.FileRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class Runner {

    public static void main(String[] args) throws IOException {

        //Configuration.startWithNewProfile = true;
        //Configuration.userConfigLocation = System.getProperty("user.home") + "/temp/chessEngine";
        //FileRepo repo = FileRepo.getInstance();
        Path location = Path.of(Configuration.userConfigLocation + "/"+ Configuration.profileFileName);

        Properties props = new Properties();
        props.load(Runner.class.getClassLoader().getResourceAsStream("application.properties"));

        props.forEach((a,b)-> System.out.println(a+" + "+ b ));

        FileRepo repo = FileRepo.getInstance();
        System.out.println(repo.readFromProfile().getUserName());


    }



}