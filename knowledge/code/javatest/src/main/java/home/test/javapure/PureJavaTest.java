package home.test.javapure;

import lombok.val;

import java.io.StringReader;

class PureJavaTest {

    sealed interface A permits B, C {

    }

    record B(int a, String b) implements A {

    }

    record C(int a, String b) implements A {

    }

    public static void main(String[] args) {
        System.out.println("Soooqa, I miss kotlin already!");

        final A a = new B(1, "2");
        //Не компилицца)))00)
//        final var b = if (a instanceof B) {
//            yield (B) a;
//        } else if (a instanceof C) {
//            yield (C) a;
//        }

        if (a instanceof B huiB) {
            val b = switch (huiB.b) {
                case "2" -> "jopa";
                case "3" -> "govno";
                default -> "pizda)))00)";
            };
        }
    }

    private String test(A a) {
        if (a instanceof C) {
            return "hui C";
        } else if (a instanceof B) {
            return "hui B";
        }

        //sealed govno i ne rabotaet blyat)))0))0))0))))))))))
        return "sealed govno";
    }

}

