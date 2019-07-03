import org.javatuples.Tuple;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;

public class Exercises {

    /**
     * Define two simple functions so that test pass
     */
    @Test
    public void defineSimpleFunctions() throws Exception {
        Function<Integer, Integer> square = null;
        Function<Integer, Boolean> isEven = null;

        assertThat(square.apply(2)).isEqualTo(4);
        assertThat(square.apply(3)).isEqualTo(9);

        assertThat(isEven.apply(1)).isFalse();
        assertThat(isEven.apply(2)).isTrue();

    }

    /**
     * Define two simple functions on tuple so that tests pass
     * tuple._1 - returns first element
     * tuple._2 - returns second element
     */
    @Test
    public void operationsOnTuple() throws Exception {
        Function<Tuple2<String, Integer>, Integer> snd = null;  // return second element
        Function<Tuple2<String, Integer>, Tuple2<Integer, String>> swap = null;  // swap elements


        assertThat(snd.apply(Tuple.of("a", 1))).isEqualTo(1);
        assertThat(snd.apply(Tuple.of("b", 2))).isEqualTo(2);

        assertThat(swap.apply(Tuple.of("a", 1))).isEqualTo(Tuple.of(1, "a"));
        assertThat(swap.apply(Tuple.of("b", 2))).isEqualTo(Tuple.of(2, "b"));
    }

    /**
     * Complete 3 functions so their composition passes tests
     * fst -> return first element of a tuple
     * parse -> parse string to int
     * square -> compute square of input
     */
    @Test
    public void composition() throws Exception {
        Function<Tuple2<String, Integer>, String> fst = null;  //return first element
        Function<String, Integer> parse = null;
        Function<Integer, Integer> square = null;

        Function<Tuple2<String, Integer>, Integer> squareFst = fst.andThen(parse).andThen(square);

        assertThat(squareFst.apply(Tuple.of("2", 1))).isEqualTo(4);
        assertThat(squareFst.apply(Tuple.of("3", 1))).isEqualTo(9);
    }


    /**
     *     Komunikujesz sie z serwerem na ktorym jest kolejka liczaca maksymalnie 5 paylodow. Twoja struktura bazodanowa wyglada tak:
     *     class Script{
     *         Long id;
     *         List<String> payloads;
     *     }
     *     Na wejsciu otrzymujesz liste id skryptow.
     *     Nalezy napisac funkcje ktora zwroci ilosc payloadow do wyslania.
     *             Przyklad:
     *     Pierwszy Skrypt ma jeden payload, drugi skrypt ma 2 payloady to zwracamy 3,
     *     Albo:
     *     Pierwszy ma 4 payloady a drugi ma 15 to zwracamy 5 jako maksymalna ilosc elementow w kolejce.
     */

    public CompletableFuture<Script> fetchScript(String id) {

        switch (new Random().nextInt(4)) {
            case 0: {
                return CompletableFuture.completedFuture(new Script(new ArrayList<>()));
            }
            case 1: {
                ArrayList<String> list = new ArrayList<>();
                list.add("A1");
                return CompletableFuture.completedFuture(new Script(list));
            }
            case 2: {
                ArrayList<String> list = new ArrayList<>();
                list.add("B1");
                list.add("B2");
                return CompletableFuture.completedFuture(new Script(list));
            }
            case 3: {
                ArrayList<String> list = new ArrayList<>();
                list.add("C1");
                list.add("C2");
                list.add("C3");
                return CompletableFuture.completedFuture(new Script(list));
            }
            default: {
                ArrayList<String> list = new ArrayList<>();
                list.add("D1");
                list.add("D2");
                list.add("D3");
                list.add("D4");
                return CompletableFuture.completedFuture(new Script(list));
            }
        }
    }

    class Script {
        Script(List<String> payloads) {
            this.payloads = payloads;
        }

        List<String> payloads;
    }
}
