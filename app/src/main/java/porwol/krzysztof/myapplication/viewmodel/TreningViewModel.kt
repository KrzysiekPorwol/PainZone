package porwol.krzysztof.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import porwol.krzysztof.myapplication.data.AppDatabase
import porwol.krzysztof.myapplication.data.Cwiczenie
import porwol.krzysztof.myapplication.data.Plan

class TreningViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).ćwiczenieDao()

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
        dao.dodaj(Cwiczenie(nazwa = nazwa, serie = serie, plan = plan))
    }

    fun usun(cwiczenie: Cwiczenie) = viewModelScope.launch {
        dao.usun(cwiczenie)
    }
}