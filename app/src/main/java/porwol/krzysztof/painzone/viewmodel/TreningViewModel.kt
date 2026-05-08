package porwol.krzysztof.painzone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import porwol.krzysztof.painzone.data.AppDatabase
import porwol.krzysztof.painzone.data.Cwiczenie
import porwol.krzysztof.painzone.data.Plan
import porwol.krzysztof.painzone.data.Trening

class TreningViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).ćwiczenieDao()
    private val treningDao = AppDatabase.get(app).treningDao()

    val planA: StateFlow<List<Cwiczenie>> = obserwuj(Plan.A)
    val planB: StateFlow<List<Cwiczenie>> = obserwuj(Plan.B)
    val planC: StateFlow<List<Cwiczenie>> = obserwuj(Plan.C)

    private fun obserwuj(plan: Plan): StateFlow<List<Cwiczenie>> =
        dao.obserwujPlan(plan).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    suspend fun dodaj(nazwa: String, serie: Int, plan: Plan): Boolean {
        val ilosc = dao.policzWPlanie(plan)
        if (ilosc >= 10) return false
        dao.dodaj(Cwiczenie(nazwa = nazwa, serie = serie, plan = plan))
        return true
    }

    fun usun(cwiczenie: Cwiczenie) = viewModelScope.launch {
        dao.usun(cwiczenie)
    }

    fun zapiszTrening(cwiczenie: Cwiczenie, wyniki: String) = viewModelScope.launch {
        treningDao.dodaj(
            Trening(
                data = System.currentTimeMillis(),
                cwiczenieId = cwiczenie.id,
                cwiczenieNazwa = cwiczenie.nazwa,
                plan = cwiczenie.plan,
                wyniki = wyniki
            )
        )
    }

    fun obserwujOstatniTrening(cwiczenieId: Long): Flow<Trening?> =
        treningDao.obserwujOstatniDlaCwiczenia(cwiczenieId)

    fun obserwujHistoriePlanu(plan: Plan): Flow<List<Trening>> =
        treningDao.obserwujHistoriePlanu(plan)

    fun usunTrening(trening: Trening) = viewModelScope.launch {
        treningDao.usun(trening)
    }
}