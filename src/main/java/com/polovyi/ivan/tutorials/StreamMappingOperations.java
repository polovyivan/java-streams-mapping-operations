package com.polovyi.ivan.tutorials;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamMappingOperations {

    private static final List<Transaction> transactions = List.of(
            new Transaction("1",
                    "customer1",
                    List.of("Laptop", "Headset"),
                    1150.00,
                    LocalDateTime.of(2024, 8, 20, 10, 19)),
            new Transaction("2",
                    "customer2",
                    List.of("Smartphone"),
                    600.99,
                    LocalDateTime.of(2024, 8, 21, 1, 45)),
            new Transaction("3",
                    "customer1",
                    List.of("Monitor", "Keyboard", "Mouse"),
                    380.00,
                    LocalDateTime.of(2024, 8, 22, 3, 30))
    );

    public static void main(String[] args) {
        System.out.println("Map operation");
        Stream<String> exampleStream1 = Stream.of("1", "2", "3", "4", "5");
        Stream<Integer> mappedStream1 = exampleStream1
                //.map(s -> Integer.parseInt(s)) -- lambda version
                .map(Integer::parseInt);
        List<Integer> example1 = mappedStream1.toList();
        // [1, 2, 3, 4, 5]
        System.out.println("example1 = " + example1);

        Set<String> example2 = transactions.stream()
                .map(Transaction::customerId)
                .collect(Collectors.toSet());
        // [customer2, customer1]
        System.out.println("example2 = " + example2);

        System.out.println("Map to double operation");
        double sumExample3 = transactions.stream()
                .mapToDouble(Transaction::totalAmount)
                .sum();
        // 2130.99
        System.out.println("sumExample3 = " + sumExample3);

        System.out.println("Flat Map operations");
        Stream<List<Integer>> example4 = Stream.of(List.of(1, 2), List.of(3, 4));
        Stream<Integer> flattenedExample4 = example4
                .flatMap(List::stream);
        List<Integer> listExample4 = flattenedExample4.toList();
        // [1, 2, 3, 4]
        System.out.println("listExample4 = " + listExample4);

        List<String> example5 = Stream.of(new String[]{"a", "b"},
                        new String[]{"c", "d"})
                .flatMap(Arrays::stream)
                .toList();
        // [a, b, c, d]
        System.out.println("example5 = " + example5);

        List<Integer> example6 = Stream.of(Stream.of(1, 2),
                        Stream.of(3, 4))
                .flatMap(s -> s)
                .toList();
        // [1, 2, 3, 4]
        System.out.println("example6 = " + example6);

        List<String> example7 = transactions.stream()
                .map(Transaction::productIds)
                .flatMap(List::stream)
                .toList();
        // [Laptop, Headset, Smartphone, Monitor, Keyboard, Mouse]
        System.out.println("example7 = " + example7);

        List<String> example8 = Stream.of("a", "b", "c", "d")
                .<String>mapMulti((str, consumer) -> {
                    consumer.accept(str);
                    consumer.accept(str.toUpperCase());
                })
                .toList();
        // [a, A, b, B, c, C, d, D]
        System.out.println("example8 = " + example8);

        IntStream example9 = IntStream.of(1, 2, 3);
        LongStream longStreamExample9 = example9
                .mapToLong(Long::valueOf);
        IntStream example10 = IntStream.of(1, 2, 3);
        DoubleStream doubleStreamExample10 = example10
                .mapToDouble(Double::valueOf);

        LongStream example11 = LongStream.of(1L, 2L, 3L);
        IntStream longStreamExample11 = example11
                .mapToInt(n -> (int) n);
        LongStream example12 = LongStream.of(1L, 2L, 3L);
        DoubleStream doubleStreamExample12 = example12
                .mapToDouble(Double::valueOf);

        DoubleStream example13 = DoubleStream.of(1.1, 2.2, 3.3);
        IntStream longStreamExample13 = example13
                .mapToInt(n -> (int) Math.round(n));
        DoubleStream example14 = DoubleStream.of(1.1, 2.2, 3.3);
        LongStream doubleStreamExample14 = example14
                .mapToLong(Math::round);

        IntStream example15 = IntStream.of(1, 2, 3);
        Stream<Integer> boxedExample15 = example15.boxed();

        LongStream example16 = LongStream.of(1L, 2L, 3L);
        Stream<Long> boxedExample16 = example16.boxed();

        DoubleStream example17 = DoubleStream.of(1.1, 2.2, 3.3);
        Stream<Double> boxedExample17 = example17.boxed();

        IntStream example18 = IntStream.of(1, 2, 3);
        Stream<Integer> integerStreamExample18 = example18
                .mapToObj(Integer::valueOf);
        LongStream example19 = LongStream.of(1L, 2L, 3L);
        Stream<Long> longStreamExample19 = example19
                .mapToObj(Long::valueOf);
        DoubleStream example20 = DoubleStream.of(1.1, 2.2, 3.3);
        Stream<Double> doubleStreamExample20 = example20
                .mapToObj(Double::valueOf);

    }
}
