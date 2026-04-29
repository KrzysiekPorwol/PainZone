package porwol.krzysztof.myapplication.NawigacjaMiędzyEkranami

// sealed class = zamknięta klasa bazowa dla tras
sealed class Ekran(val trasa: String) {

    // Główny ekran aplikacji (startowy)
    object Główny_Ekran : Ekran("ekran_główny")

    //Ekrany planów treningowych
    object Plan_A : Ekran("ekran_plan_A")
    object Plan_B : Ekran("ekran_plan_B")
    object Plan_C : Ekran("ekran_plan_C")

    // Ekran do edycji ćwiczeń
    object Edytuj_Ćwiczenia : Ekran("ekran_edytuj_ćwiczenia")

    // Ekran rozpoczęcia treningu — z parametrem planu (A/B/C)
    object Rozpocznij_Trening : Ekran("ekran_rozpocznij_trening/{plan}") {
        fun zTrasa(plan: String) = "ekran_rozpocznij_trening/$plan"
    }
}