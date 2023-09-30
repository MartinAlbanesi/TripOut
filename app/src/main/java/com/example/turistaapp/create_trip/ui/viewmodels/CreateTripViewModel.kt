package com.example.turistaapp.create_trip.ui.viewmodels

import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.GetTripsUseCase
import com.example.turistaapp.create_trip.domain.InsertTripUseCase
import com.example.turistaapp.create_trip.domain.models.TripModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val getTripsUseCase: GetTripsUseCase,
    private val insertTripUseCase: InsertTripUseCase
) : ViewModel() {

    //Viajes que se muestran en la lazy list
    private var _trips = MutableLiveData<List<TripModel>>()
    val trips: LiveData<List<TripModel>> = _trips

    //Nombre del viaje
    private var _name = MutableLiveData("")
    val name: LiveData<String> = _name
    fun onNameChange (name: String) {
        _name.value = name.toString()
    }

    //Lugares del viaje
    private var _origin = MutableLiveData("")
    val origin: LiveData<String> = _origin
    fun onOriginChange (origin: String) {
        _origin.value = origin.toString()
    }

    private var _destination = MutableLiveData("")
    val destination: LiveData<String> = _destination
    fun onDestinationChange (destination: String) {
        _destination.value = destination.toString()
    }


    //Fechas del viaje
    val calendar: Calendar = Calendar.getInstance()

    private var _startDate = MutableLiveData(calendar.timeInMillis)
    val startDate: LiveData<Long> = _startDate
    fun onStartDateChange (startDate: Long) {
        _startDate.value = startDate
    }

    private var _endDate = MutableLiveData(calendar.timeInMillis)
    val endDate: LiveData<Long> = _endDate
    fun onEndDateChange (endDate: Long) {
        _endDate.value = endDate
    }

    private var _showDateRangePickerDialog = MutableLiveData(false)
    val showDateRangePickerDialog: LiveData<Boolean> = _showDateRangePickerDialog
    fun onShowDateRangePickerDialogChange (showDateRangePickerDialog: Boolean) {
        _showDateRangePickerDialog.value = showDateRangePickerDialog
    }

    //Miembros
    private var _members = MutableLiveData(mutableListOf<String>())
    val members: MutableLiveData<MutableList<String>> = _members

    fun onAddMember (member: String) {
        _members.value?.add(member)
    }

    fun onRemoveMember (member: String) {
        _members.value?.remove(member)
    }

    //Paradas
    private var _stops = MutableLiveData(mutableListOf<String>())
    val stops: MutableLiveData<MutableList<String>> = _stops

    fun onAddStop (stop: String) {
        _stops.value?.add(stop)
    }

    fun onRemoveStop (stop: String) {
        _stops.value?.remove(stop)
    }

    //Transportes
    private var _transports = MutableLiveData(
        listOf(
            "Auto",
            "Moto",
            "Transporte Público",
            "A pie",
            "Bicicleta"
        )
    )
    val transports: LiveData<List<String>> = _transports

    private var _isExpanded = MutableLiveData(false)
    val isExpanded: LiveData<Boolean> = _isExpanded
    fun onIsExpandedChange (isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    private var _transport = MutableLiveData("")
    val transport: LiveData<String> = _transport
    fun onTransportChange (transport: String) {
        _transport.value = transport
    }

    //Descripción
    private var _description = MutableLiveData("")
    val description: LiveData<String> = _description
    fun onDescriptionChange (description: String) {
        _description.value = description.toString()
    }

    //Focus Requesters
    private var _originFocusRequester = MutableLiveData(FocusRequester())
    val originFocusRequester: LiveData<FocusRequester> = _originFocusRequester

    private var _destinationFocusRequester = MutableLiveData(FocusRequester())
    val destinationFocusRequester: LiveData<FocusRequester> = _destinationFocusRequester

    private var _membersFocusRequester = MutableLiveData(FocusRequester())
    val membersFocusRequester: LiveData<FocusRequester> = _membersFocusRequester

    private var _descriptionFocusRequester = MutableLiveData(FocusRequester())
    val descriptionFocusRequester: LiveData<FocusRequester> = _descriptionFocusRequester



    suspend fun getTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            getTripsUseCase.execute().collect {
                _trips = MutableLiveData(it)
            }
        }
    }

    suspend fun insertTrip(trip: TripModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertTripUseCase.execute(trip)
        }
    }


}