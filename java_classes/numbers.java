package java_classes;

import org.apache.commons.numbers.primes.Primes;
import org.apache.commons.numbers.angle.Angle;

import java.util.Random;

public class numbers {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt();
        if (randomNumber < 0){randomNumber *= -1;}

        System.out.println("Primes");

        System.out.println("Random Number: "+randomNumber);

        System.out.println("Is it prime?: "+Primes.isPrime(randomNumber));

        System.out.println("Next upcoming prime number:"+Primes.nextPrime(randomNumber));

        //System.out.println(Primes.isPrime());

        System.out.println("Angles");

        Angle.Deg randomDegree = Angle.Deg.of(Math.random()*360);

        System.out.println("Random Degree: " + randomDegree.getAsDouble());

        System.out.println("To Radians: " + randomDegree.toRad().getAsDouble());
    }

}
