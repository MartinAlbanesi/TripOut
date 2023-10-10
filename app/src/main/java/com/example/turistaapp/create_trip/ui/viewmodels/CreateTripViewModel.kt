package com.example.turistaapp.create_trip.ui.viewmodels

import android.util.Log
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.data.database.entities.CoordinateEntity
import com.example.turistaapp.create_trip.data.database.entities.LocationEntity
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.InsertTripUseCase
import com.example.turistaapp.create_trip.domain.models.TripModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val insertTripUseCase: InsertTripUseCase,
    private val getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase
) : ViewModel() {

    //Viajes que se muestran en la lazy list
    private var _trips = MutableLiveData<List<TripModel>>()
    val trips: LiveData<List<TripModel>> = _trips

    //Nombre del viaje
    private var _name = MutableLiveData("")
    val name: LiveData<String> = _name
    fun onNameChange(name: String) {
        _name.value = name.toString()
    }

    //Lugares del viaje
    private var _origin = MutableLiveData("")
    val origin: LiveData<String> = _origin
    fun onOriginChange(origin: String) {
        _origin.value = origin.toString()
    }

    private var _destination = MutableLiveData("")
    val destination: LiveData<String> = _destination
    fun onDestinationChange(destination: String) {
        _destination.value = destination.toString()
    }


    //Fechas del viaje
    val calendar: Calendar = Calendar.getInstance()

    private var _startDate = MutableLiveData(calendar.timeInMillis)
    val startDate: LiveData<Long> = _startDate
    fun onStartDateChange(startDate: Long) {
        _startDate.value = startDate
    }

    private var _endDate = MutableLiveData(calendar.timeInMillis)
    val endDate: LiveData<Long> = _endDate
    fun onEndDateChange(endDate: Long) {
        _endDate.value = endDate
    }

    private var _showDateRangePickerDialog = MutableLiveData(false)
    val showDateRangePickerDialog: LiveData<Boolean> = _showDateRangePickerDialog
    fun onShowDateRangePickerDialogChange(showDateRangePickerDialog: Boolean) {
        _showDateRangePickerDialog.value = showDateRangePickerDialog
    }

    //Miembros
    private var _members = MutableLiveData(mutableListOf<String>())
    val members: LiveData<MutableList<String>> = _members

    private var _memberName = MutableLiveData("")
    val memberName: LiveData<String> = _memberName

    private var _isMemberDialogOpen = MutableLiveData(false)
    val isMemberDialogOpen: LiveData<Boolean> = _isMemberDialogOpen

    fun onMemberNameChange(memberName: String) {
        _memberName.value = memberName
    }

    fun onMemberDialogOpenChange(isMemberDialogOpen: Boolean) {
        _isMemberDialogOpen.value = isMemberDialogOpen
    }

    fun onAddMember(member: String) {
        _members.value?.add(member)
    }

    fun onRemoveMember(member: String) {
        _members.value?.remove(member)
    }

    fun resetMemberNameValue(){
        _memberName.value = ""
    }

    //Paradas
    private var _stops = MutableLiveData(mutableListOf<String>())
    val stops: LiveData<MutableList<String>> = _stops

    private var _stopName = MutableLiveData("")
    val stopName: LiveData<String> = _stopName

    private var _isStopDialogOpen = MutableLiveData(false)
    val isStopDialogOpen: LiveData<Boolean> = _isStopDialogOpen

    fun onStopNameChange(stopName: String) {
        _stopName.value = stopName
    }

    fun onStopDialogOpenChange(isStopDialogOpen: Boolean) {
        _isStopDialogOpen.value = isStopDialogOpen
    }

    fun onAddStop(stop: String) {
        _stops.value?.add(stop)
    }

    fun onRemoveStop(stop: String) {
        _stops.value?.remove(stop)
    }

    fun resetStopNameValue(){
        _stopName.value = ""
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
    fun onIsExpandedChange(isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    private var _transport = MutableLiveData("")
    val transport: LiveData<String> = _transport
    fun onTransportChange(transport: String) {
        _transport.value = transport
    }

    //Descripción
    private var _description = MutableLiveData("")
    val description: LiveData<String> = _description
    fun onDescriptionChange(description: String) {
        _description.value = description.toString()
    }

    //Crear viaje con los datos ingresados

    fun onCreateTripClick() {
        val trip = TripModel(
            id = 0,
            name = _name.value.toString(),
            origin = LocationEntity(
                0, _origin.value.toString(), "0.0", CoordinateEntity(
                    987654321.2,
                    123456789.1
                ), "imagenURIOrigin", listOf("tipo1, tipo2")
            ),
            destination = LocationEntity(
                1,
                _destination.value.toString(),
                "0.0",
                CoordinateEntity(123456789.1, 987654321.2),
                "imagenURIDestination",
                listOf("tipo1, tipo2")
            ),
            startDate = calendar.timeInMillis.toString(),
            endDate = calendar.timeInMillis.toString(),
            transport = _transport.value.toString(),
            members = _members.value,
            stops = null,
            description = _description.value.toString(),
            author = "author",
            images = null,
            comments = null,
            isFavorite = false,
            isFinished = false
        )

        try{
            viewModelScope.launch(Dispatchers.IO) {
                insertTripUseCase.execute(trip)
            }
        }catch (e: Exception){
            Log.d("CreateTripViewModel", e.message.toString())
            Log.d("CreateTripViewModel", e.stackTraceToString())
        }

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

    /*
    suspend fun getTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            getTripsUseCase.execute().collect {
                _trips = MutableLiveData(it)
            }
        }
    }
    */


}