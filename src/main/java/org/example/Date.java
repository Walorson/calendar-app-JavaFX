package org.example;

public class Date {
    private int year;
    private Month month;
    private int day;

    /**
     * Długi format daty, np: 12 August 2026
     */
    static final int LONG_FORMAT = 55335;
    /**
     * Krótki format daty, np: 12 Aug 2026
     */
    static final int SHORT_FORMAT = 65535;
    /**
     * Format daty z rzymską liczbą miesiąca, np. 12 VIII 2026
     */
    static final int ROMAN_FORMAT = 75535;

    /**
     * Format daty z arabską liczbą miesiąca, np. 12.08.2026
     */
    static final int NUMBER_FORMAT = 85535;

    /**
     * Tablica dni tygodni
     */
    static public String[] weekdays = {
            "Poniedziałek",
            "Wtorek",
            "Środa",
            "Czwartek",
            "Piątek",
            "Sobota",
            "Niedziela"
    };

    /**
     * Konstruktor przypisujący poprawną wartość dnia, miesiąca, roku
     * @param day dzień - liczba całkowita (1 - 28 / 29 / 30 / 31) poprawna wartość zależna od miesiąca
     * @param month miesiąc - liczba całkowita (1 - 12)
     * @param year rok - liczba całkowita
     * @throws IllegalArgumentException
     */
    public Date(int day, int month, int year) throws IllegalArgumentException {
        this.year = year;

        if(month >= 1 && month <= 12) {
            this.month = Month.months[month - 1];
        }
        else throw new IllegalArgumentException("Invalid month");

        if(day >= 1 && day <= this.month.days) {
            this.day = day;
        }
        else if(day == 29 && month == 2 && year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { //obsługa 29 lutego
            this.day = day;
        }
        else throw new IllegalArgumentException("Invalid day");


    }

    /**
     * Funkcja, która zmienia datę o tydzień do przodu
     */
    public void nextWeek()
    {
        this.day += 7;
        if(this.day > this.month.days) {
            int rest_of_days = this.day - this.month.days;

            this.month.index += 1;

            if(this.month.index > 11) {
                this.year++;
                this.month = Month.months[0];
            }
            else {
                this.month = Month.months[this.month.index];
            }

            this.day = rest_of_days;
        }
    }

    /**
     * Funkcja, która zmienia datę o tydzień do tyłu
     */
    public void previousWeek()
    {
        this.day -= 7;
        if(this.day < 1) {
            this.month.index -= 1;

            if(this.month.index < 0) {
                this.year--;
                this.month = Month.months[11];
            }
            else {
                this.month = Month.months[this.month.index];
            }

            this.day = this.month.days + this.day;
        }
    }

    /**
     * Funkcja pobierająca dzień tygodnia z daty
     * @return nazwa dnia tygodnia
     */
    public String getWeekday()
    {
        int y = year;
        int m = month.index + 1; // 1..12

        if (m < 3) {
            m += 12;
            y--;
        }

        int q = day;
        int K = y % 100;
        int J = y / 100;

        int h = Math.floorMod(
                q + (13 * (m + 1)) / 5 +
                        K + K / 4 +
                        J / 4 +
                        5 * J,
                7
        );

        return this.weekdays[Math.floorMod(h - 2, 7)];
    }

    @Override
    public String toString() {
        return this.day + " " + this.month.name + " " + this.year;
    }

    /**
     * Wyświetla datę w podanym formacie.
     *
     * @param format format wyświetlania daty:
     *               {@link #LONG_FORMAT} - pełny format daty, np. 12 August 2026
     *               {@link #SHORT_FORMAT} - skrócony format daty, np. 12 Aug 2026
     *               {@link #ROMAN_FORMAT} - romański format daty, np. 12 VIII 2026
     *               {@link #NUMBER_FORMAT} - numeryczny numer daty, np. 12.08.2026
     */
    public String getDate(final int format) {
        switch(format)
        {
            case LONG_FORMAT: return this.day + " " + this.month.name + " " + this.year;
            case SHORT_FORMAT: return this.day + " " + this.month.name.substring(0, 3) + " " + this.year;
            case ROMAN_FORMAT: return this.day + " " + this.month.toRoman() + " " + this.year;
            case NUMBER_FORMAT: return this.day + "." + (this.month.index+1) + "." + this.year;
            default: throw new IllegalArgumentException("Zly format daty.");
        }
    }

    /**
     * Sprawdza czy rok jest przestępny
     * @param year Rok do sprawdzenia
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Zamienia datę na liczbę dni
     * @param date Data, która zostanie przekonwertowana na dni
     */
    private static int toDays(Date date) {
        int days = 0;

        for (int y = 1; y < date.year; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }

        for (int m = 0; m < date.month.index; m++) {
            days += Month.months[m].days;

            if (m == 1 && isLeapYear(date.year)) { // luty
                days++;
            }
        }

        days += date.day;

        return days;
    }

    /**
     * Oblicza różnicę w dniach pomiędzy dwiema datami
     * @param date1 pierwsza data
     * @param date2 druga data
     */
    public static int daysBetween(Date date1, Date date2) {
        return Math.abs(toDays(date2) - toDays(date1));
    }
}
