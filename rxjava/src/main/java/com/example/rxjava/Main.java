package com.example.rxjava;

import io.reactivex.rxjava3.core.Flowable;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    void run() {
        hello("Ben", "George");
    }

    void hello(String... args) {
        Flowable.fromArray(args).subscribe(s -> System.out.printf("Hello, %s!\n", s));
    }
}