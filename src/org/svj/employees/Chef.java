package org.svj.employees;

public interface Chef {

    String favoriteFOod= "Hamburger"; // fields in interface are final by default

    default void cook(String food){
        System.out.println("I'm now cooking "+food);
    }

    default String cleanUp(){
        return "I'm done cleaning up";
    }

    default String getFavoriteFOod(){
        return favoriteFOod;
    }
}
