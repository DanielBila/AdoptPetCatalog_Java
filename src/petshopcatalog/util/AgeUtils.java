package petshopcatalog.util;

import java.time.Period;

/**
 * Converte a idade digitada no formato "anos.meses" para Period e vice-versa.
 * Exemplos: "0.5" = 5 meses, "1.2" = 1 ano e 2 meses, "3" = 3 anos.
 */
public final class AgeUtils {
    public static final String FORMAT_HINT = "Ex.: 0.5 = 5 meses, 1.2 = 1 ano e 2 meses";

    private AgeUtils() {
    }

    public static Period parse(String text) {
        String value = text == null ? "" : text.trim().replace(',', '.');
        if (!value.matches("\\d{1,3}(\\.\\d{1,2})?")) {
            throw new IllegalArgumentException("Idade inválida. Use anos.meses (" + FORMAT_HINT + ").");
        }

        // O número depois do ponto são meses inteiros, não uma fração de ano
        String[] parts = value.split("\\.");
        int years = Integer.parseInt(parts[0]);
        int months = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        if (months > 11) {
            throw new IllegalArgumentException("Os meses devem estar entre 0 e 11.");
        }
        return Period.of(years, months, 0);
    }

    public static String format(Period age) {
        int years = age.getYears();
        int months = age.getMonths();

        String yearsText = years == 1 ? "1 ano" : years + " anos";
        String monthsText = months == 1 ? "1 mês" : months + " meses";

        if (years == 0) {
            return monthsText;
        }
        if (months == 0) {
            return yearsText;
        }
        return yearsText + " e " + monthsText;
    }
}
