package porwol.krzysztof.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import porwol.krzysztof.myapplication.data.AppDatabase
import porwol.krzysztof.myapplication.data.Cwiczenie
import porwol.krzysztof.myapplication.data.Plan
import porwol.krzysztof.myapplication.data.Trening

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

    fun dodaj(nazwa: String, serie: Int, plan: Plan) = viewModelScope.launch {
        println("🟢 ViewModel.dodaj: nazwa=$nazwa, serie=$serie, plan=$plan")
        dao.dodaj(Cwiczenie(nazwa = nazwa, serie = serie, plan = plan))
        println("🟢 Dodano do bazy!")
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
}