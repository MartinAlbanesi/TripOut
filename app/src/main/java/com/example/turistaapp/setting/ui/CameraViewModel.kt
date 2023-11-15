package com.example.turistaapp.setting.ui

//class CameraViewModel : ViewModel() {
//
//    private val _cameraController: MutableStateFlow<LifecycleCameraController?> =
//        MutableStateFlow(null)
//    val cameraController: StateFlow<LifecycleCameraController?> = _cameraController.asStateFlow()
//
//
//    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
//    val bitmap = _bitmaps.asStateFlow()
//
//
//    fun initializeCamera(context: Context) {
//        viewModelScope.launch {
//            viewModelScope.launch {
//                val cameraController = LifecycleCameraController(context)
//                _cameraController.value = cameraController
//            }
//
//            fun takePhoto(bitmap: Bitmap) {
//                _bitmaps.value += bitmap
//            }
//        }
//
//    }
//}