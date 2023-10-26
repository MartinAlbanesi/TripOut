package com.example.turistaapp.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.core.utils.getLocationString
import com.example.turistaapp.create_trip.domain.GetTripsUseCase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.example.turistaapp.home.domain.GetRouteModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getNearbyLocationsUseCase: GetNearbyLocationsUseCase,
    private val getRandomLocationFromDB: GetRandomLocationFromDB,
    private val getGetTripsUseCase: GetTripsUseCase,
    private val getRouteModel: GetRouteModel
) : ViewModel() {

    private val _nearbyLocationsApi = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<LocationModel?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    private val _destinationLocations = MutableStateFlow<Pair<List<LocationModel>, List<LocationModel>>?>(null)
    val destinationLocations = _destinationLocations.asStateFlow()
//    private val _destinationLocations = MutableStateFlow<List<LocationModel>>(emptyList())
//    val destinationLocations = _destinationLocations.asStateFlow()
//

    private val _polyLinesPoints = MutableStateFlow<List<LatLng>>(emptyList())
    val polyLinesPoints = _polyLinesPoints.asStateFlow()

    init {
        getFlowLocationFromDB()
        getRandomLocation()
//        decoPoints("dzjsEfmwdJl@v@_CxCyFxH_GnHwFxHaJnL{GxIqEbGUp@YOcH{D}EkCsGwDuBeAoBy@wL{CoLqCoEgAwC{@cMqEcJgDe^oMsBe@mCe@sGsA_NmCeEu@_Do@gAu@a@i@O_@Gc@?e@Ru@PShAo@f@q@Nk@F{@E{A_AoHaDmRuFaZgDeQiPe|@_BwIwAeHqBuFwHqQoLoXoG{NgQsa@cDsHgDsGiAuBi@_BkDmGwAyCgIoRuIgS_d@ceAiIoR_CmF{@{AoBkC}BiC_FsF}EmH_GqJwAgCIWQiAKW?QAGFSPw@Xy@nCiElKsObHaKbFmHrDmFHMKKqI_HoEmDg@i@E_@B]dBcFv@cETmCD}C]kMHoANWDMC_@Bm@FWX_@j@k@Z_ALoAEw@_@_@]Mc@?m@@wBUsD[yAU}Bq@{@Q_FoBiGsCyDqBmCuAoAy@}X{PoMeJyEcDkBwAuE{D}K_JqIqGuGcFwMaKmGaF{DwCqIeG_G}EyCiC_FkE_CwA{EuD{RkOmImGc@_@MQgBqA{@m@[YY]?ECG[q@Ko@?o@Hq@\\_Af@iA?m@Mu@Yg@m@g@e@Mk@F[`@Md@m@~EyN~S_B`CcBxBi@z@oC|DmD`FiFvH{FjIiRxXuKtOeGzIwKvOqMfRcMpQyGvJoBzCePxUwF~HaFdHwEbH}GjK}ExGoCvCw@n@uA|@aBr@k@Vq@NoB^{AJmGPiBJk@CcCH}M^cCLwB?qCEiA@uFVaHP{EPoKT}Lh@mJTcPZsV^uM\\kj@jAwP\\qAEo@IcB_@oDoAwPkG_N_F}TeIsOyF}B{@qCaAqB{@_CmA{C_B}CoA}Bu@sD_AcI}BoBu@iX{J_P}FwM}Eqo@yUyXcKmG}B{HeCoDmA}BcAyC}A_GuCuCaAsCy@cFuAcOkFgWkJmK}DsMyEoNmFiAm@yA}@iBcBqBqCoAgCgAsCwDgLgD}JeCmH}HwTqCaIGk@yAcEiHqT_CeHgDqJoDsIqBeFk@cBsB}GoAsEeGiRwDwK_ByFm@aCk@}COqA@_BNoAl@eBrAuCdAiBpE}GfGeJ|B}DrAaDlFsNp@aCj@gDVwAr@yCn@gBlCaG~CaHxB}EzEeLxBqEx@oBrS}d@dRob@zJoTdCaFfCoEvGsKnCwEnDuF~BiEl@cAlBcDlBsCvCmDxAsA~DgF`B{BfCuEx@sBdF}L~AaEPOfGoLf@_Af@m@l@Yl@@j@PtB`BhAv@XD^Z~HnHfBtAZJn@Ll@d@x@vALU`@cA`AoCnAiBzAsAtAaCr@iB~@uAn@e@")
    }

    private fun getRandomLocation(){
        viewModelScope.launch(dispatcher) {
            if (getRandomLocationFromDB() != null) {
                setNearbyLocations(getRandomLocationFromDB()!!.lat, getRandomLocationFromDB()!!.lng)
            } else {
                setNearbyLocations(-34.67113975510375, -58.56181551536259)
            }
        }
    }

    private fun getFlowLocationFromDB() {
        viewModelScope.launch(dispatcher) {
            getGetTripsUseCase()
                .map { item -> Pair(item.map { it.destination }, item.map { it.destination }) }
                .collect{
                    _destinationLocations.value = it
            }
        }
    }

    fun setNearbyLocations(lat: Double, lng: Double) {
        viewModelScope.launch(dispatcher) {
            try {
                val nearbyLocations = getNearbyLocationsUseCase(getLocationString(lat, lng))

                if (nearbyLocations.isNullOrEmpty()) {
                    _nearbyLocationsApi.emit(ResponseUiState.Error("No se encontraron lugares cercanos"))
                } else {
                    _nearbyLocationsApi.emit(ResponseUiState.Success(nearbyLocations))
                }
            } catch (e: Exception) {
                _nearbyLocationsApi.emit(ResponseUiState.Error(e.message.toString()))
            }
        }
    }

    fun setNearbyLocationSelect(nearbyLocationName: String) {
        val getNearbyLocation = getNearbyLocationByName(nearbyLocationName)
        _nearbyLocationSelect.value = getNearbyLocation
    }

    private fun getNearbyLocationByName(name: String): LocationModel? {
        val nearbyLocation =
            (nearbyLocations.value as ResponseUiState.Success<List<LocationModel>>).values
        return nearbyLocation.find { it.name == name }
    }

    private fun decoPoints(points: String): List<LatLng>{
        _polyLinesPoints.value = decodePoly(points)
        return decodePoly(points);
    }

    /**
     * Method to decode polyline points
     * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / 1E5,
                lng.toDouble() / 1E5)
            poly.add(p)
        }

        return poly
    }
}
