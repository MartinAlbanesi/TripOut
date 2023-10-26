package com.example.turistaapp.create_trip.ui.viewmodels // ktlint-disable package-name

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import com.example.turistaapp.create_trip.domain.InsertTripUseCase
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val insertTripUseCase: InsertTripUseCase,
    private val getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase,
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
) : ViewModel() {

    // Viajes que se muestran en la lazy list
    private var _trips = MutableLiveData<List<TripModel>>()
    val trips: LiveData<List<TripModel>> = _trips

    // Fechas del viaje
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

    // Miembros
    private var _members = MutableLiveData(mutableListOf<String>())
    val members: LiveData<MutableList<String>> = _members

    private var _memberName = MutableLiveData("")
    val memberName: LiveData<String> = _memberName

    fun onMemberNameChange(memberName: String) {
        _memberName.value = memberName
    }

    fun onAddMember(member: String) {
        val updatedMembers = _members.value?.toMutableList() ?: mutableListOf()
        updatedMembers.add(member)
        _members.value = updatedMembers
        resetMemberNameValue()
    }

    fun onRemoveMember(index: Int) {
        val updatedMembers = _members.value?.toMutableList()
        updatedMembers?.removeAt(index)
        _members.value = updatedMembers
    }

    fun resetMemberNameValue() {
        _memberName.value = ""
    }

    // Paradas
    private var _stops = MutableLiveData(mutableListOf<String>())
    val stops: LiveData<MutableList<String>> = _stops

    private var _stopName = MutableLiveData("")
    val stopName: LiveData<String> = _stopName

    fun onStopNameChange(stopName: String) {
        _stopName.value = stopName
    }

    fun onAddStop(stop: String) {
        val updatedStops = _stops.value?.toMutableList() ?: mutableListOf()
        updatedStops.add(stop)
        _stops.value = updatedStops
        resetStopNameValue()
    }

    fun onRemoveStop(index: Int) {
        val updatedStops = _stops.value?.toMutableList()
        updatedStops?.removeAt(index)
        _stops.value = updatedStops
    }

    fun resetStopNameValue() {
        _stopName.value = ""
    }

    // Transportes
    private var _transports = MutableLiveData(
        listOf(
            "Auto",
            "Moto",
            "Transporte Público",
            "A pie",
            "Bicicleta",
        ),
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

    // Descripción
    private var _description = MutableLiveData("")
    val description: LiveData<String> = _description
    fun onDescriptionChange(description: String) {
        _description.value = description.toString()
    }

    // Focus Requesters
    private var _originFocusRequester = MutableLiveData(FocusRequester())
    val originFocusRequester: LiveData<FocusRequester> = _originFocusRequester

    private var _destinationFocusRequester = MutableLiveData(FocusRequester())
    val destinationFocusRequester: LiveData<FocusRequester> = _destinationFocusRequester

    private var _descriptionFocusRequester = MutableLiveData(FocusRequester())
    val descriptionFocusRequester: LiveData<FocusRequester> = _descriptionFocusRequester

    // Datos de origen para el autocomplete

    private val _originQuery = MutableLiveData("")
    val originQuery: LiveData<String> get() = _originQuery

    private val _originPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val originPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _originPredictions

    fun onOriginAutocompleteQueryValueChange(newQuery: String) {
        _originQuery.value = newQuery
        searchOriginPlaces(newQuery)
    }

    private fun searchOriginPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query)
            _originPredictions.value = newPredictions
        }
    }

    private var _isOriginAutocompleteDropdownVisible = MutableLiveData(false)
    val isOriginAutocompleteDropdownVisible: LiveData<Boolean> =
        _isOriginAutocompleteDropdownVisible

    fun onOriginAutocompleteDropdownVisibilityChange(isVisible: Boolean) {
        _isOriginAutocompleteDropdownVisible.value = isVisible
    }

    fun onOriginAutocompletePredictionSelect(prediction: PlaceAutocompletePredictionModel) {
        _originQuery.value = prediction.description
        _isOriginAutocompleteDropdownVisible.value = false
    }

    fun onClearOriginField() {
        _originQuery.value = ""
        _selectedOriginLocation.value = null
    }

    private val _selectedOriginLocation = MutableLiveData<PlaceAutocompletePredictionModel>(
        PlaceAutocompletePredictionModel(
            placeId = "",
            description = "",
            distanceMeters = null,
            types = emptyList(),
            structured_formatting = null,
        ),
    )
    val selectedOriginLocation: LiveData<PlaceAutocompletePredictionModel> = _selectedOriginLocation

    fun onSelectedOriginLocationChange(selectedLocation: PlaceAutocompletePredictionModel) {
        _selectedOriginLocation.value = selectedLocation
    }

    // Datos de destino para el autocomplete

    private val _destinationQuery = MutableLiveData("")
    val destinationQuery: LiveData<String> get() = _destinationQuery

    private val _destinationPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val destinationPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _destinationPredictions

    fun setDestination(address: String?) {
        _destinationQuery.value = address
        searchDestinationPlaces(address!!)
    }

    fun onDestinationAutocompleteQueryValueChange(newQuery: String) {
        _destinationQuery.value = newQuery
        searchDestinationPlaces(newQuery)
    }

    private fun searchDestinationPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query)
            _destinationPredictions.value = newPredictions
        }
    }

    private var _isDestinationAutocompleteDropdownVisible = MutableLiveData(true)
    val isDestinationAutocompleteDropdownVisible: LiveData<Boolean> =
        _isDestinationAutocompleteDropdownVisible

    fun onDestinationAutocompleteDropdownVisibilityChange(isVisible: Boolean) {
        _isDestinationAutocompleteDropdownVisible.value = isVisible
    }

    fun onDestinationAutocompletePredictionSelect(prediction: PlaceAutocompletePredictionModel) {
        _destinationQuery.value = prediction.description
        _isDestinationAutocompleteDropdownVisible.value = false
    }

    fun onClearDestinationField() {
        _destinationQuery.value = ""
        _selectedDestinationLocation.value = null
    }

    private val _selectedDestinationLocation = MutableLiveData<PlaceAutocompletePredictionModel>(
        PlaceAutocompletePredictionModel(
            placeId = "",
            description = "",
            distanceMeters = null,
            types = emptyList(),
            structured_formatting = null,
        ),
    )
    val selectedDestinationLocation: LiveData<PlaceAutocompletePredictionModel> =
        _selectedDestinationLocation

    fun onSelectedDestinationLocationChange(selectedLocation: PlaceAutocompletePredictionModel) {
        _selectedDestinationLocation.value = selectedLocation
    }

    // Crear viaje con los datos ingresados

    // private val _originLocation = MutableLiveData<LocationModel>()
    // val originLocation: LiveData<LocationModel> get() = _originLocation

    // private val _destinationLocation = MutableLiveData<LocationModel>(null)
    // private val destinationLocation: LiveData<LocationModel> get() = _destinationLocation

    fun onCreateTripClick(name: String): Boolean {
        var isSuccessful = true

        viewModelScope.launch {
            try {
                val origin = getPlaceDetailsUseCase(_selectedOriginLocation.value!!.placeId)
                val destination =
                    getPlaceDetailsUseCase(_selectedDestinationLocation.value!!.placeId)

                /*
                _originLocation.value = origin
                _destinationLocation.value = destination
                 */

                val trip = TripModel(
                    name = name,
                    origin = origin!!,
                    destination = destination!!,
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
                    isFinished = false,
                )
                insertTripUseCase.execute(trip)
                isSuccessful = true
            } catch (e: Exception) {
                isSuccessful = false
            }
        }
        return isSuccessful
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
