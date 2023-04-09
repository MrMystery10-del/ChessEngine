package core;


import persistence.ProfileRepo;
import persistence.repos.FileRepo;

public class Runner {


    public static void main(String[] args) {

        System.out.println("Test class");


        ProfileRepo repo = ProfileRepo.getInstance();
        FileRepo fileRepo = FileRepo.getInstance();
        System.out.println(repo==null);
        System.out.println(fileRepo==null);
        try{

            fileRepo.readFromProfile().get();
        }
        catch (NullPointerException e){
            System.out.println("not yet implemented");
        }

    }
}
