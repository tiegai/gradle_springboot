package org.example.baisc;

import org.bson.codecs.ValueCodecProvider;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OpitonalTest {

    public static void main(String[] arg){

        Optional<String> name = Optional.of("Jerry");

        /*Optional<String> someNull = Optional.of(null);*/

        Optional empty = Optional.ofNullable(null);

        System.out.println(name);
        System.out.println(empty);

        if(name.isPresent()){
            System.out.println(name.get());
        }

        name.ifPresent((value)->System.out.println("length of name is : " + value.length()));

        try {
            System.out.println(empty.get());
        } catch (NoSuchElementException e1) {
            System.out.println(e1.getMessage());
        }

        System.out.println(empty.orElse("no value"));
        System.out.println(name.orElse("value"));

        System.out.println(empty.orElseGet(()->"default value"));
        System.out.println(name.orElseGet(()->"default value"));


        try {
            empty.orElseThrow(Exception::new);
        } catch (Throwable e2) {
            System.out.println(e2.getMessage());
        }

        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));


    }

}
